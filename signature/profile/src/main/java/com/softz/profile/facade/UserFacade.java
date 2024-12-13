package com.softz.profile.facade;

import com.softz.profile.dto.request.AuthenticationParam;
import com.softz.profile.dto.request.RegistrationParam;
import com.softz.profile.dto.response.ProfileDto;
import com.softz.profile.dto.response.TokenDto;
import com.softz.profile.entity.Profile;
import com.softz.profile.service.IdentityService;
import com.softz.profile.service.ProfileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserFacade {
    ProfileService profileService;
    IdentityService identityService;


    public ProfileDto register(RegistrationParam param){
        // Create account in KeyCloak

        // Get accountId form KeyCloak

        String userId = identityService.create(param);

        // Build profile object

        Profile profile = Profile.builder()
                .id(userId)
                .firstName(param.getFirstName())
                .username(param.getUsername())
                .lastName(param.getLastName())
                .build();

        // invoke profileService to create profile

        profile = profileService.create(profile);

        return new ProfileDto(
                profile.getId(),
                profile.getUsername(),
                profile.getFirstName(),
                profile.getLastName()
        );
    }

    public TokenDto authenticate(AuthenticationParam param) {
        // Authenticate user in KeyCloak
        // Get token from KeyCloak
        return identityService.authenticate(param);
    }

}
