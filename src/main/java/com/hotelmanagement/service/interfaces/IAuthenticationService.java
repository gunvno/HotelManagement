package com.hotelmanagement.service.interfaces;

import com.hotelmanagement.dto.request.Authentication.*;
import com.hotelmanagement.dto.response.Authentication.AuthenticationResponse;
import com.hotelmanagement.dto.response.Authentication.IntrospectResponse;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;

public interface IAuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);
    public IntrospectResponse introspect(IntrospectRequest request) throws Exception;
    public void logout(LogoutRequest request) throws ParseException, JOSEException;
    public AuthenticationResponse refreshToken(RefreshTokenRequest request) throws ParseException, JOSEException;
    public String register(RegisterRequest request);
}
