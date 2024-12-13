package com.softz.profile.service;

import com.softz.common.exception.AppException;
import com.softz.common.exception.ErrorCode;
import com.softz.profile.dto.request.AuthenticationParam;
import com.softz.profile.dto.request.RegistrationParam;
import com.softz.profile.dto.request.TokenExchangeParam;
import com.softz.profile.dto.response.TokenDto;
import com.softz.profile.repository.IdentityClient;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class IdentityService {
    @Autowired
    IdentityClient identityClient;

    public TokenDto authenticate(AuthenticationParam param) {

        try {
            var tokenResponse = identityClient.exchangeToken(
                    TokenExchangeParam.builder()
                            .grant_type("password")
                            .client_id("signature_system")
                            .client_secret("uU6LB8AKvkiXMZ1yqFPLUK6KVR8VSszD")
                            .username(param.getUsername())
                            .password(param.getPassword())
                            .build());
            log.info("Token response {}", tokenResponse);

            return new TokenDto(tokenResponse.getAccessToken(),
                    tokenResponse.getRefreshToken());
        } catch (FeignException ex) {
            log.info("Error orrcured while authenticating user", ex);
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }


    }

    public String create(RegistrationParam param) {
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl("http://localhost:8080")
                .realm("softz")
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .clientId("signature_system")
                .clientSecret("uU6LB8AKvkiXMZ1yqFPLUK6KVR8VSszD")
                .build();

        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setValue(param.getPassword());
        credentialRepresentation.setTemporary(false);
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);

        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(param.getUsername());
        userRepresentation.setEnabled(true);
        userRepresentation.setCredentials(List.of(credentialRepresentation));

        var response = keycloak.realm("softz").users().create(userRepresentation);


        return CreatedResponseUtil.getCreatedId(response);
    }

}
