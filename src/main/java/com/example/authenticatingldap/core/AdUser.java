package com.example.authenticatingldap.core;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Data
public class AdUser extends  User{


	private static final long serialVersionUID = -2840446131502122670L;
		//The field name in LDAP server
		public static final String USERNAME="userPrincipalName";
		public static final String HRCENTRALID="employeeID";
		public static final String LEGACYID="extensionAttribute2";
		public static final String NAME="name";
		public static final String EMAIL="mail";
		public static final String PHONE="telephoneNumber";
		public static final String FUNC="extensionAttribute10";
		public static final String SUBFUNC="extensionAttribute14";
		public static final String TITLE="title";
		public static final String LDAPRESULTS[]={HRCENTRALID,LEGACYID,NAME,EMAIL,PHONE,FUNC,SUBFUNC,TITLE};
		//---------
		public static final String UNDEFINED="undefined";
		
		private String hrCentralId;
		private String legacyId;
		private String name;
		private String email;
		private String phone;
		private String func;
		private String subFunc;
		private String title;
		
		public AdUser(String username, String password, boolean enabled, boolean accountNonExpired,
				boolean credentialsNonExpired, boolean accountNonLocked,
				Collection<? extends GrantedAuthority> authorities) {
			super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
			// TODO Auto-generated constructor stub
		}

		public AdUser(String username, String password, ArrayList<GrantedAuthority> arrayList) {
			// TODO Auto-generated constructor stub
			super(username,password,arrayList);
		}			
		
	}
