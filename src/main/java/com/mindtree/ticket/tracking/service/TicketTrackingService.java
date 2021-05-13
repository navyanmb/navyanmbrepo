package com.mindtree.ticket.tracking.service;

import org.springframework.stereotype.Service;

import com.mindtree.ticket.tracking.model.TicketListResponse;
import com.mindtree.ticket.tracking.model.TicketResponse;
import com.mindtree.ticket.tracking.model.Tickets;

@Service
public interface TicketTrackingService {

	public TicketResponse logTicket(Tickets ticket);
	public TicketResponse closeTicket(int id,Tickets ticket);
	public TicketListResponse viewTicketsWithTurnaroundTime();

}
