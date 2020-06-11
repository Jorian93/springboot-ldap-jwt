/*
 * @Description: 
 * @Author: jorian
 * @Date: 2020-06-10 22:51:00
 */
package com.example.authenticatingldap.core;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.naming.NamingException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/auth")
public class AuthController {

    // jwt加密密匙
    @Value("${jwt.key}")
    private String jwtKey;

    // token过期时间 4小时
    private Date tokenExpired = new Date(new Date().getTime() + 60 * 60 * 4 * 1000);

    // 域名后缀
    @Value("${ldap.domainName}")
    private String ldapDomainName = "ncsi.com.cn";

    /**
     * @param username 用户提交的名称
     * @param password 用户提交的密码
     * @return 成功返回加密后的token信息，失败返回错误HTTP状态码
     * @throws NamingException 
     */
    @CrossOrigin // 因为需要跨域访问，所以要加这个注解
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> authByAd(@RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password) throws NamingException {
        // 这里注意用户名加域名后缀 userDn格式：anwx@minibox.com
        String userDn = username +"@"+ ldapDomainName;

        AdUser ldapuser = LDAPUtils.executeLogin(userDn, password);
            
        // 使用Jwt加密用户名和用户所属组信息
        String compactJws = Jwts.builder().setSubject(ldapuser.getName()).setAudience(ldapuser.getFunc())
                .setExpiration(tokenExpired).signWith(SignatureAlgorithm.HS512, jwtKey).compact();
        // 登录成功，返回客户端token信息。这里只加密了用户名和用户角色，而displayName和tokenExpired没有加密
        Map<String, Object> userInfo = new HashMap<String, Object>();
        userInfo.put("token", compactJws);
        userInfo.put("displayName", ldapuser.getName());
        userInfo.put("tokenExpired", tokenExpired.getTime());
        return new ResponseEntity<String>(JSON.toJSONString(userInfo, SerializerFeature.DisableCircularReferenceDetect),
                HttpStatus.OK);
    }

}
