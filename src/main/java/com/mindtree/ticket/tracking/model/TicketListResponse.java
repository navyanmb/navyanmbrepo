package com.mindtree.ticket.tracking.model;

import java.util.List;

public class TicketListResponse extends Response{
	
	private List<TicketWithTurnaroundTimeResponse> ticketList;
	
	public TicketListResponse() {
	}
	
	public TicketListResponse(int status_code, String message, List<TicketWithTurnaroundTimeResponse> ticketList) {
		super(status_code, message);
		this.ticketList = ticketList;
	}

	public List<TicketWithTurnaroundTimeResponse> getTktList() {
		return ticketList;
	}

	public void setTktList(List<TicketWithTurnaroundTimeResponse> tktList) {
		this.ticketList = tktList;
	}
	
	

}
