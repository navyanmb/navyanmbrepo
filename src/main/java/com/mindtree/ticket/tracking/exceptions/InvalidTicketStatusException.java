package com.mindtree.ticket.tracking.exceptions;

@SuppressWarnings("serial")
public class InvalidTicketStatusException extends Exception{
	
	public InvalidTicketStatusException(String msg)
	{
		super(msg);
	}


}
