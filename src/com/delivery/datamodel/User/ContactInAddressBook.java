package com.delivery.datamodel.User;

public class ContactInAddressBook {

	public String							firstName;
	public String							lastName;
	public String							companyName;
	public String							email;
	public boolean							selected;
	
	public ContactInAddressBook () {
		
        firstName = null;
        lastName = null;
        companyName = null;
        email = null;
        selected = false;
	}
}
