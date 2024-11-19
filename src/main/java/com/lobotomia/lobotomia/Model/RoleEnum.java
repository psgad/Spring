package com.lobotomia.lobotomia.Model;


import org.springframework.security.core.GrantedAuthority;

public enum RoleEnum implements GrantedAuthority {
    USER, ADMIN, SYSADMIN, MANAGERROLES;

    @Override
    public String getAuthority() {
        return name();
    }
}
