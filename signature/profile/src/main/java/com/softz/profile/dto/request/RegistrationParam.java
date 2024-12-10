package com.softz.profile.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegistrationParam {
    @NotBlank(message = "")
    String username;

    @NotBlank(message = "")
    String password;

    String firstName;
    String lastName;


}
