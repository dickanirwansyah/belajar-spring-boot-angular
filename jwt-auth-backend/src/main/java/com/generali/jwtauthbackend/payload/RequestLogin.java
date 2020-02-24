package com.generali.jwtauthbackend.payload;

import com.generali.jwtauthbackend.constant.AllMessageConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestLogin implements Serializable {

    @NotBlank(message = AllMessageConstant.UsernameCannotBlank)
    private String username;

    @NotBlank(message = AllMessageConstant.PasswordCannotBlank)
    private String password;
}
