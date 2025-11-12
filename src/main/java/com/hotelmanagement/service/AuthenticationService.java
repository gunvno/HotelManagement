package com.hotelmanagement.service;

import com.hotelmanagement.dto.request.Authentication.AuthenticationRequest;
import com.hotelmanagement.dto.request.Authentication.IntrospectRequest;
import com.hotelmanagement.dto.request.Authentication.LogoutRequest;
import com.hotelmanagement.dto.request.Authentication.RefreshTokenRequest;
import com.hotelmanagement.dto.response.Authentication.AuthenticationResponse;
import com.hotelmanagement.dto.response.Authentication.IntrospectResponse;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);
    public IntrospectResponse introspect(IntrospectRequest request) throws Exception;
    public void logout(LogoutRequest request) throws ParseException, JOSEException;
    public AuthenticationResponse refreshToken(RefreshTokenRequest request) throws ParseException, JOSEException;
}
