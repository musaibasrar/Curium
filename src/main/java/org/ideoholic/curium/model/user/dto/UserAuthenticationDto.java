package org.ideoholic.curium.model.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthenticationDto {
    private String userName;
    private String password;
    private String currentPassword;
    private String newPassword;
    private String ConfirmNewPassword;
}
