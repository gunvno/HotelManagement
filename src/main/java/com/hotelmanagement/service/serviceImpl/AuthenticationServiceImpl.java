package com.hotelmanagement.service.serviceImpl;

import com.hotelmanagement.dto.request.Authentication.*;
import com.hotelmanagement.entity.Accounts;
import com.hotelmanagement.entity.Roles;
import com.hotelmanagement.entity.User;
import com.hotelmanagement.enums.Role;
import com.hotelmanagement.repository.AccountRepository;
import com.hotelmanagement.repository.RoleRepository;
import com.hotelmanagement.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.hotelmanagement.dto.response.Authentication.AuthenticationResponse;
import com.hotelmanagement.dto.response.Authentication.IntrospectResponse;
import com.hotelmanagement.entity.InvalidatedToken;
import com.hotelmanagement.exception.AppException;
import com.hotelmanagement.exception.ErrorCode;
import com.hotelmanagement.repository.InvalidatedTokenRepository;
import com.hotelmanagement.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationServiceImpl implements AuthenticationService {
    InvalidatedTokenRepository invalidatedTokenRepository;
    AccountRepository accountRepository;
    @NonFinal
    @Value("${signerKey}")
    protected String SIGNER_KEY;
    @NonFinal
    @Value("${valid-duration}")
    protected long VALID_DURATION;
    @NonFinal
    @Value("${refreshable-duration}")
    protected long REFRESHABLE_DURATION;


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public String register(RegisterRequest request){
        if(accountRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_IS_EXISTED);

        User user = User.builder()
                .userType("CUSTOMER")
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .status(true)
                .createdTime(LocalDateTime.now())
                .createdBy(null)
                .modifiedTime(null)
                .modifiedBy(null)
                .deleted(false)
                .deletedTime(null)
                .deletedBy(null)
                .build();
        userRepository.save(user);
        Roles customerRole = roleRepository.findByName(Role.CUSTOMER);
        Set<Roles> roles = new HashSet<>();
        roles.add(customerRole);
        Accounts accounts = Accounts.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .status(true)
                .userId(user.getId())
                .roles(roles)
                .createdTime(LocalDateTime.now())
                .createdBy(null)
                .modifiedTime(null)
                .modifiedBy(null)
                .deleted(false)
                .deletedTime(null)
                .deletedBy(null)
                .build();

        accountRepository.save(accounts);
        return "Dang ki thanh cong";
    }

    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        var token = request.getToken();
        boolean isValid = true;
        try {
            verifyToken(token,false);
        } catch (AppException e){
            isValid = false;
        }
        return IntrospectResponse.builder()
                .valid(isValid)
                .build();
    }
    @Override
    @Transactional(readOnly = true)
    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        var accounts = accountRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        // SỬA Ở ĐÂY
        boolean authenticated = passwordEncoder.matches(request.getPassword(), accounts.getPassword());

        if (!authenticated) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        var token = generateToken(accounts);

        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }
    public void logout(LogoutRequest request) throws ParseException, JOSEException {
        try {
            var signToken = verifyToken(request.getToken(), true);
            String jit = signToken.getJWTClaimsSet().getJWTID();
            Date expiryTime = signToken.getJWTClaimsSet().getExpirationTime();

            InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                    .id(jit)
                    .expiryTime(expiryTime)
                    .build();
            invalidatedTokenRepository.save(invalidatedToken);
        }catch (AppException e){
            log.info("Token already expired");
        }

    }
    public AuthenticationResponse refreshToken(RefreshTokenRequest request) throws ParseException, JOSEException {
        var signedJWT = verifyToken(request.getToken(), true);
        var jit = signedJWT.getJWTClaimsSet().getJWTID();
        var expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                .id(jit)
                .expiryTime(expiryTime)
                .build();
        invalidatedTokenRepository.save(invalidatedToken);

        var username = signedJWT.getJWTClaimsSet().getSubject();
        var accounts = accountRepository.findByUsername(username).orElseThrow(()-> new AppException(ErrorCode.USER_NOT_FOUND));

        var token = generateToken(accounts);

        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();


    }
    private SignedJWT verifyToken(String token, boolean isRefresh) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiryTime = (isRefresh) ? new Date(signedJWT.getJWTClaimsSet().getIssueTime().toInstant().plus(REFRESHABLE_DURATION, ChronoUnit.SECONDS).toEpochMilli())
                :signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(verifier);
        if(!(verified && expiryTime.after(new Date())))
            throw new AppException(ErrorCode.UNAUTHENTICATED);

        if(invalidatedTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID()))
            throw new AppException(ErrorCode.UNAUTHENTICATED);

        return signedJWT;
    }
    private String generateToken(Accounts accounts){
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(accounts.getUsername())
                .issuer("devteria.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(VALID_DURATION, ChronoUnit.SECONDS).toEpochMilli()
                ))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope",buildScope(accounts))
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);
        try{
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        }  catch (KeyLengthException e) {
            throw new RuntimeException(e);
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }
    @Transactional
    private String buildScope(Accounts accounts){
        StringJoiner stringJoiner = new StringJoiner(" ");
        Set<Roles> role = accounts.getRoles();
        if(!CollectionUtils.isEmpty(accounts.getRoles()))

            accounts.getRoles().forEach(roles -> {
                stringJoiner.add("ROLE_" + roles.getName());
                if(!CollectionUtils.isEmpty(roles.getPermissions()))
                    roles.getPermissions().forEach(permission -> {
                        stringJoiner.add(permission.getModule());
                    });
            });


        return stringJoiner.toString();
    }
}
