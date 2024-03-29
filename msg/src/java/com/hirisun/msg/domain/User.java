package com.hirisun.msg.domain;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * User generated by MyEclipse Persistence Tools
 */

public class User  implements java.io.Serializable {


    // Fields    

     private String id;
     private String userid;
     private String password;
     private String name;


    // Constructors

    /** default constructor */
    public User() {
    }

    
    /** full constructor */
    public User(String userid, String password, String name) {
        this.userid = userid;
        this.password = password;
        this.name = name;
    }

   
    // Property accessors

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return this.userid;
    }
    
    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
   
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}