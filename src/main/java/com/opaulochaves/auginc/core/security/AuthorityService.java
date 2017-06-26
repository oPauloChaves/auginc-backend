package com.opaulochaves.auginc.core.security;

import java.util.Collection;

/**
 * Access GrantedAuthorities for Users and emails by GrantedAuthorities
 */
public interface AuthorityService {

    Collection<String> getUserEmailsByAuthority(String authority);
}
