package com.apo.model.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 23/06/2017.
 */
@Document(collection = User.COLLECTION)
public class User implements UserDetails{ //TODO: add new information
    static final String COLLECTION = "users";

    @Id
    private String userID;
    private String username;
    private String email;
    private String password;
    private List<String> roles = new ArrayList<>();
    private boolean expired;
    private boolean locked;
    private boolean credentialsExpired;
    private boolean enabled;

    public User() {
    }

    private User(Builder builder) {
        this.userID = builder.userID;
        this.username = builder.username;
        this.email = builder.email;
        this.password = builder.password;
        this.roles = builder.roles;
        this.expired = builder.expired;
        this.locked = builder.locked;
        this.credentialsExpired = builder.credentialsExpired;
        this.expired = builder.credentialsExpired;
        this.enabled = builder.enabled;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public List<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(String role: roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getUserID() {
        return userID;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public static class Builder {
        private String userID;
        private String username;
        private String email;
        private String password;
        private List<String> roles = new ArrayList<>();
        private boolean expired = false;
        private boolean locked = false;
        private boolean credentialsExpired = false;
        private boolean enabled = true;

        public Builder() {
        }

        public Builder setUserID(String userID) {
            this.userID = userID;
            return this;
        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setRoles(List<String> roles) {
            this.roles = roles;
            return this;
        }

        public Builder setExpired(boolean expired) {
            this.expired = expired;
            return this;
        }

        public Builder setLocked(boolean locked) {
            this.locked = locked;
            return this;
        }

        public Builder setCredentialsExpired(boolean credentialsExpired) {
            this.credentialsExpired = credentialsExpired;
            return this;
        }

        public Builder setEnabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public Builder addRole(String role) {
            this.roles.add(role);
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
