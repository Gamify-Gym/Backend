package org.gamify.gym.app.user.service;

import java.time.Instant;

import org.gamify.gym.app.user.model.UserAuthenticated;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    private final JwtEncoder encoder;

    public JwtService(JwtEncoder encoder) {
        this.encoder = encoder;
    }

    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();
        long expiry = 604800L;

        String userType = ((UserAuthenticated) authentication.getPrincipal()).getUserType();
        String email = ((UserAuthenticated) authentication.getPrincipal()).getEmail();

        var claims = JwtClaimsSet.builder()
                .issuer("Gamify Gym")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(email)
                .claim("userType", userType)
                .build();
        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
