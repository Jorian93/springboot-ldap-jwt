/*
 * @Description: 
 * @Author: jorian
 * @Date: 2020-05-28 21:38:55
 */
package com.example.authenticatingldap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class AuthenticatingLdapApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(AuthenticatingLdapApplication.class, args);
    }
}
