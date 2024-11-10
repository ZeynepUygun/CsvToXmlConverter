package com.magarsus.fileconverter.dto;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class UserDto {
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private Set<String> roles = new LinkedHashSet<>();
    private String key;


    public UserDto(String username, String firstname, String lastname, String email, String key) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.key = key;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void addRole(String role) {
        getRoles().add(role);
    }

    public void addRoles(List<String> role) {
        getRoles().addAll(role);
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
