package com.delivery.datamodel.Office;

public class CompanyDetail {

	public Integer							count;
	public Integer							officeId;
	public String							officeName;
	public Integer							validatedAddressId;
	public Integer							deliveryLocationId;
	public String							deliveryLine1;
	public String							lastLine;
	public String							longitude;
	public String							latitude;

	public CompanyDetail () {
		
        count = -1;
        officeId = -1;
        officeName = null;
        validatedAddressId = -1;
        deliveryLocationId = -1;
        deliveryLine1 = null;
        lastLine = null;
        longitude = null;
        latitude = null;
	}
}
