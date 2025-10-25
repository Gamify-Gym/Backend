package org.gamify.gym.app.config;

import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class RsaKeyConfiguration {

    @Value("${jwt.public.key:}")
    private String publicKeyValue;

    @Value("${jwt.private.key:}")
    private String privateKeyValue;

    @Value("${local.public.key:}")
    private Resource publicKeyResource;

    @Value("${local.private.key:}")
    private Resource privateKeyResource;

    @Bean
    public RSAPublicKey rsaPublicKey() throws Exception {
        if (publicKeyValue != null && !publicKeyValue.isEmpty() && !publicKeyValue.startsWith("classpath:")) {
            return parsePublicKey(publicKeyValue);
        }

        if (publicKeyResource != null && publicKeyResource.exists()) {
            String keyContent = new String(publicKeyResource.getInputStream().readAllBytes());
            return parsePublicKey(keyContent);
        }

        throw new IllegalStateException("No RSA public key configuration found. " +
                "Either provide jwt.public.key or local.public.key");
    }

    @Bean
    public RSAPrivateKey rsaPrivateKey() throws Exception {
        if (privateKeyValue != null && !privateKeyValue.isEmpty() && !privateKeyValue.startsWith("classpath:")) {
            return parsePrivateKey(privateKeyValue);
        }

        if (privateKeyResource != null && privateKeyResource.exists()) {
            String keyContent = new String(privateKeyResource.getInputStream().readAllBytes());
            return parsePrivateKey(keyContent);
        }

        throw new IllegalStateException("No RSA private key configuration found. " +
                "Either provide jwt.private.key or local.private.key");
    }

    private RSAPublicKey parsePublicKey(String key) throws Exception {
        String normalizedKey = key.replace("\\n", "\n");

        String publicKeyPEM = normalizedKey
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s", "");

        byte[] encoded = Base64.getDecoder().decode(publicKeyPEM);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(encoded);
        return (RSAPublicKey) keyFactory.generatePublic(keySpec);
    }

    private RSAPrivateKey parsePrivateKey(String key) throws Exception {
        String normalizedKey = key.replace("\\n", "\n");

        String privateKeyPEM = normalizedKey
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s", "");

        byte[] encoded = Base64.getDecoder().decode(privateKeyPEM);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
        return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
    }
}
