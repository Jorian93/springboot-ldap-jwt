/*
 * @Description: 
 * @Author: jorian
 * @Date: 2020-06-10 23:27:32
 */
package com.example.authenticatingldap.ldapTest;

import javax.naming.Name;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Name> {

}