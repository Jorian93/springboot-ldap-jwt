/*
 * @Description: 
 * @Author: jorian
 * @Date: 2020-06-11 00:21:51
 */
package com.example.authenticatingldap.core;

import javax.naming.directory.Attributes;

import org.springframework.ldap.NamingException;
import org.springframework.ldap.core.AttributesMapper;

public class LDAPAttribuitesMapper implements AttributesMapper<LDAPUser> {
    public LDAPUser mapFromAttributes(Attributes attrs) throws NamingException, javax.naming.NamingException {
        LDAPUser ldapuser = new LDAPUser();
        ldapuser.setName((String) attrs.get("sAMAccountName").get());
        ldapuser.setDisplayName((String) attrs.get("displayName").get());
        ldapuser.setRole((String) attrs.get("memberOf").toString());
        return ldapuser;
    }
}