package com.mindtree.ticket.tracking.exceptions;

@SuppressWarnings("serial")
public class TicketNotFoundException extends Exception{
	
	public TicketNotFoundException(String msg)
	{
		super(msg);
	}

}
