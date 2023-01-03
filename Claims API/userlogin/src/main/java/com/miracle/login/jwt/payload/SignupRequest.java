package com.miracle.login.jwt.payload;

import java.util.Set;
import javax.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor 
public class SignupRequest {
    @NotBlank
    @Size(min = 5, max = 20)
    private String username;
 
    @NotBlank
    @Email
    private String email;
    
    private String name;   
    private String phone;
    
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
    
    private String wmsAccountNumber;
    private Set<String> roles;

    
}

