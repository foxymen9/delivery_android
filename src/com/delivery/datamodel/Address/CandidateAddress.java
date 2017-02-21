package com.delivery.datamodel.Address;

public class CandidateAddress {

	public Integer									candidateAddressId;
	public String									deliveryLine1;
	public String									lastLine;

	public CandidateAddress () {
		
        candidateAddressId = -1;
        deliveryLine1 = null;
        lastLine = null;
	}
}
