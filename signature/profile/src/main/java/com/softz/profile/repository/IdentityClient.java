package com.softz.profile.repository;

import com.softz.profile.dto.request.TokenExchangeParam;
import com.softz.profile.dto.response.TokenExchangeResponse;
import feign.QueryMap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "identity-service", url = "${idp.url}")
public interface IdentityClient {
    @PostMapping(
            value = "/realms/${idp.realm}/protocol/openid-connect/token",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    TokenExchangeResponse exchangeToken(@QueryMap TokenExchangeParam param);
}
