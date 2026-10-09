package com.kubsei.essential.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Shared by every kubsei service: kubsei-users signs with it, the rest only verify.
 *
 * @param secret HS256 key, at least 32 bytes
 * @param issuer expected "iss" claim
 */
@ConfigurationProperties(prefix = "kubsei.security.jwt")
public record JwtProperties(String secret, String issuer) {

    public JwtProperties {
        if (secret == null || secret.getBytes(java.nio.charset.StandardCharsets.UTF_8).length < 32) {
            throw new IllegalStateException("kubsei.security.jwt.secret must be set and at least 32 bytes long");
        }
        issuer = issuer == null ? "kubsei" : issuer;
    }
}
