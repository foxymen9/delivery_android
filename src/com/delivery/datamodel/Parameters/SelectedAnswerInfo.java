package com.delivery.datamodel.Parameters;

public class SelectedAnswerInfo {
	
	public Integer				answerId;
	public boolean             	selected;
	public Integer				depth;
	
	public SelectedAnswerInfo () {
		
        answerId = -1;
        selected = false;
        depth = -1;
	}
}
