package com.delivery.datamodel.User;

public class User {

	public Integer							userId;
	public String							inviteCode;
	public String							userName;
	public String							email;
	public String							firstName;
	public String							lastName;
	public String							phone;
	public Integer							officeId;
	public Integer							deliveryLocationId;
	public String							created;
	public String							birthday;
	public Integer							status;
	public String							token;
	public String							tokenExpire;
	public boolean							smsNotify;
	
	public User () {
		
        userId = -1;
        inviteCode = null;
        userName = null;
        email = null;
        firstName = null;
        lastName = null;
        phone = null;
        officeId = -1;
        deliveryLocationId = -1;
        created = null;
        birthday = null;
        status = -1;
        token = null;
        tokenExpire = null;
        smsNotify = false;
	}
}
