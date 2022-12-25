package com.texhnolyze.githubapiv2;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class Members {

    private String org = "Escihu-Wizards";
    @NotNull(message = "Debe escribir su correo")
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
