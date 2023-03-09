package com.texhnolyze.githubapiv2.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Members {

    private String org = "Escihu-Wizards";
    @NotBlank(message = "Debes escribir su correo")
    @Size(min = 1, message = "Debes tu correo completo.")
    @Email(message = "El correo no es correcto")
    private String email;
    private String role = "direct_member";

}
