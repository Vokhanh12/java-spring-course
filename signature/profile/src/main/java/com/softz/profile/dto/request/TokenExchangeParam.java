package com.softz.profile.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TokenExchangeParam {
    String grant_type;
    String client_id;
    String client_secret;
    String username;
    String password;
}