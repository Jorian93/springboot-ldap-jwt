/*
 * @Description: 
 * @Author: jorian
 * @Date: 2020-06-10 23:44:04
 */ 
package com.example.authenticatingldap.ldapTest;

import org.springframework.beans.factory.annotation.Autowired;

public class TestMain {
    @Autowired
    static PersonRepository personRepository;

    public static void main(String []args){
        
        personRepository.findAll().forEach(item->{
            System.out.println(item);
        });
    }
}