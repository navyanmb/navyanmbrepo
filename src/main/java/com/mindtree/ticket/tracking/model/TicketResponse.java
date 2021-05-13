package com.mindtree.ticket.tracking.model;


public class TicketResponse extends Response{
	
	private Tickets ticket;
	
	public TicketResponse()
	{
		
	}
	
	public TicketResponse(int status_code, String message,Tickets ticket) {
		super(status_code, message);
		this.ticket=ticket;
	}

	public Tickets getTicket() {
		return ticket;
	}

	public void setTicket(Tickets ticket) {
		this.ticket = ticket;
	}
	

}
