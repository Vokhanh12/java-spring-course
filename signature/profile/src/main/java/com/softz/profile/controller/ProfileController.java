package com.softz.profile.controller;

import com.softz.common.dto.ApiResponse;
import com.softz.profile.dto.request.AuthenticationParam;
import com.softz.profile.dto.request.RegistrationParam;
import com.softz.profile.dto.response.ProfileDto;
import com.softz.profile.dto.response.TokenDto;
import com.softz.profile.facade.UserFacade;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.keycloak.Token;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileController {
    private final UserFacade userFacade;

    @GetMapping("/health")
    ApiResponse<String> health() {
        return ApiResponse.<String>builder()
                .result("Profile Service is up and running")
                .build();
    }

    @PostMapping("/registration")
    ApiResponse<ProfileDto> register(
            @RequestBody @Valid RegistrationParam param){
        return ApiResponse.<ProfileDto>builder()
                .result(userFacade.register(param))
                .build();
    }

    @PostMapping("/auth/token")
    ApiResponse<TokenDto> authenticate(
            @RequestBody @Valid AuthenticationParam param){
        return ApiResponse.<TokenDto>builder()
                .result(userFacade.authenticate(param))
                .build();
    }
}
