package com.delivery.datamodel.Address;

import java.util.ArrayList;

public class ValidatedAddress {

	public Integer									validatedAddressId;
	public ArrayList<CandidateAddress>				candidates;
	public boolean									success;
	
	public ValidatedAddress () {
		
        validatedAddressId = -1;
        candidates = new ArrayList<CandidateAddress> ();
        success = false;
	}
}
