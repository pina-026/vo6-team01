package com.example.api.security;

import com.example.api.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.List;

public class WinterLoginUser extends org.springframework.security.core.userdetails.User {

    // Userエンティティ
    private com.example.api.User user;

    public User getUser() {
        return user;
    }

    public WinterLoginUser(User user) {
        super(user.getUsername(), user.getPassword(), determineRoles(user.getAdmin()));
        this.user = user;
    }

    private static final List<GrantedAuthority> USER_ROLES = AuthorityUtils.createAuthorityList("ROLE_USER");
    private static final List<GrantedAuthority> ADMIN_ROLES = AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER");

    private static List<GrantedAuthority> determineRoles(boolean isAdmin) {
        return isAdmin ? ADMIN_ROLES : USER_ROLES;
    }
}