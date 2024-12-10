package com.softz.profile.service;

import com.softz.profile.dto.request.RegistrationParam;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdentityService {
    public String create(RegistrationParam param) {
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl("http://localhost:8080")
                .realm("softz")
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .clientId("signature_system")
                .clientSecret("QhmZCozCnSFC6xOP1SUVBMObf0nVPyEr")
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
