/*
 * @Description: 
 * @Author: jorian
 * @Date: 2020-06-11 00:04:58
 */
package com.example.authenticatingldap.core;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

import javax.naming.directory.DirContext;

import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapUtils;

public class LDAPUtils {
    // ldap模板

    static LdapTemplate ldapTemplate = new LdapTemplate();

    public static LDAPUser executeLogin(String userDn, String password) {
        DirContext ctx = null;
        LDAPUser ldapUser = null;
        try {
            ctx = ldapTemplate.getContextSource().getContext(userDn, password);
            // 如果验证成功根据sAMAccountName属性查询用户名和用户所属的组
            ldapUser = ldapTemplate.search(query().where("objectclass").is("person").and("sAMAccountName").is(password),
                    new LDAPAttribuitesMapper()).get(0);
        } catch (Exception e) {
            // 登录失败，返回失败HTTP状态码
            e.printStackTrace();
        } finally {
            // 关闭ldap连接
            LdapUtils.closeContext(ctx);
        }
        return ldapUser;
    }

}