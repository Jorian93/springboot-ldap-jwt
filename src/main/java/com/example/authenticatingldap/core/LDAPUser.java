package com.example.authenticatingldap.core;

import lombok.Data;

@Data
public class LDAPUser {

	String name;
	String displayName;
	String role;

}
