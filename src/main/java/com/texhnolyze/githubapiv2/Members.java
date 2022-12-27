package com.texhnolyze.githubapiv2;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Members {

    private String org = "Escihu-Wizards";
    @NotBlank(message = "Debes escribir su correo")
    @Size(min = 1, message = "Debes tu correo completo.")
    @Email(message = "El correo no es correcto")
    private String email;
    private String role = "direct_member";

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
