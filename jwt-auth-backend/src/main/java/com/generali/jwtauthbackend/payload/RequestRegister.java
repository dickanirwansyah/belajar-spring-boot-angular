package com.generali.jwtauthbackend.payload;

import com.generali.jwtauthbackend.constant.AllMessageConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestRegister implements Serializable {

    @NotBlank(message = AllMessageConstant.NameCannotBlank)
    private String name;

    @NotBlank(message = AllMessageConstant.UsernameCannotBlank)
    private String username;

    @Email(message = AllMessageConstant.EmailCannotValid)
    @NotBlank(message = AllMessageConstant.EmailCannotBlank)
    private String email;

    @NotBlank(message = AllMessageConstant.PasswordCannotBlank)
    private String password;

    private Set<String> roles;

}
