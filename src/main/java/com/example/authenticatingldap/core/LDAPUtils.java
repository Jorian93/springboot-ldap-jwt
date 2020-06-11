/*
 * @Description: 
 * @Author: jorian
 * @Date: 2020-06-11 00:04:58
 */
package com.example.authenticatingldap.core;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.security.core.GrantedAuthority;

public class LDAPUtils {
	/**
	 * 
	 */
	private static final String LDAP_URL = "ldap://10.176.56.9:389";
	/**
	 * 
	 */
	private static final String SEARCH_BASE = "OU=ChengDu,OU=China,DC=ncs,DC=corp,DC=int-ads";
	/**
	 * AD User
	 * format: domain\User   or  User@domain.com
	 */
	public static String AD_USERNAME;// = "zeqixu@ncsi.com.cn";
	
	/**
	 * AD Password
	 */
	public static String AD_PASSWORD;// = "X4350198xzq12$";
	
	/**
	 * LDAP工厂类
	 */
	public static final String LDAP_CTX_FACTORY = "com.sun.jndi.ldap.LdapCtxFactory";
	
	/**
	 * 安全认证级别
	 */
	public static final String SECURITY_LEVEL = "simple";
	/**
	 * 
	 */
	public static final String REFERRAL = "follow";
	
	public static final String START = "(&(";
	public static final String END = "))";
	public static final String AND = ")(";
	public static final String EUQAS = "=";

    static LdapTemplate ldapTemplate = new LdapTemplate();

    public static AdUser executeLogin(String username, String password) throws NamingException {
    	
    	if(username == null) {
    		System.out.print("are you kind me? no username ? ");
    	}
    	LdapContext ctx=null;
    	LDAPUtils.AD_USERNAME = username;
		LDAPUtils.AD_PASSWORD = password;			
		Hashtable<String, String> HashEnv = new Hashtable<String, String>();
		HashEnv.put(Context.SECURITY_AUTHENTICATION, SECURITY_LEVEL);
		HashEnv.put(Context.SECURITY_PRINCIPAL, AD_USERNAME); 
		HashEnv.put(Context.SECURITY_CREDENTIALS,  AD_PASSWORD); 
		HashEnv.put(Context.INITIAL_CONTEXT_FACTORY, LDAP_CTX_FACTORY);  
		HashEnv.put(Context.PROVIDER_URL, LDAP_URL);
		HashEnv.put(Context.REFERRAL, REFERRAL);
		ctx = new InitialLdapContext(HashEnv, null);
		System.out.println("登陆成功");
		
		
		AdUser aduser = new AdUser(username,password,new ArrayList<GrantedAuthority>());
		NamingEnumeration<?> rs = LDAPUtils.getADInfo(ctx,"user", AdUser.USERNAME, username,AdUser.LDAPRESULTS);
		fillLdapUser(aduser,rs);
			
		return aduser;
    	
   	 	
    }
    
    
	/**
	 * @param ctx
	 * @param type   organizationalUnit:组织架构 ,   group：用户组    ,user|person：用户
	 * @param filter AD服务器中的某个属性
	 * @param name   对应AD服务器中的某个属性的值
	 * @param returnedAtts  返回属性集
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static NamingEnumeration<SearchResult> getADInfo(LdapContext ctx,String type, String adPoperties, String value, String[] returnedAtts)
			throws NamingException {

		NamingEnumeration<SearchResult> answer = null;
		String searchFilter=null;
		if(value==null||value.equals(""))
			return null;
		else			
		searchFilter = START 
						+ "objectClass" + EUQAS + type +
						AND 
						+ adPoperties + EUQAS + value+
						END;
		
		SearchControls searchCtls = new SearchControls();
		searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		//searchCtls.setReturningAttributes(returnedAtts); // 设置返回属性集, 不设置则返回所有属性	
		// 根据设置的域节点、过滤器类和搜索控制器搜索LDAP得到结果
		answer = ctx.search(SEARCH_BASE, searchFilter, searchCtls);
		return answer;
	}
	
	protected static boolean fillLdapUser(AdUser aduser,NamingEnumeration<?> rs) throws NamingException
	{
		if(rs.hasMoreElements())
		{
			SearchResult sr=(SearchResult)rs.next();
			Attributes attrs=sr.getAttributes();
			NamingEnumeration<? extends Attribute> e=attrs.getAll();
			for(;e.hasMore();)
			{
				Attribute attr=e.next();
				String field=attr.get(0).toString();
				switch(attr.getID()){
					case AdUser.EMAIL:
						aduser.setEmail(field);
						break;
					case AdUser.FUNC:
						aduser.setFunc(field);
						break;
					case AdUser.HRCENTRALID:
						aduser.setHrCentralId(field);
						break;
					case AdUser.LEGACYID:
						aduser.setLegacyId(field);
						break;
					case AdUser.NAME:
						aduser.setName(field);
						break;
					case AdUser.PHONE:
						aduser.setPhone(field);
						break;
					case AdUser.SUBFUNC:
						aduser.setSubFunc(field);
						break;
					case AdUser.TITLE:
						aduser.setTitle(field);
						break;
				}
			}

			return true;
		}
		return false;
	}
    

}