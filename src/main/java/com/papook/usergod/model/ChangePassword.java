package com.papook.usergod.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ChangePassword model class to store the old and new password. Used to change
 * the password of the user.
 * 
 * 
 * @author papook
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePassword {
    @NotNull
    private String oldPassword;
    @NotNull
    private String newPassword;
}
