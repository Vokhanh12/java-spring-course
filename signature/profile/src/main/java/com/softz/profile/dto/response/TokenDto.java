package com.softz.profile.dto.response;

public record TokenDto(
        String accessToken,
        String refreshToken
) { }
