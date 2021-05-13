package com.mindtree.ticket.tracking.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mindtree.ticket.tracking.model.Tickets;

@Service
public interface TicketTrackingDao {
	
	public Tickets logTicket(Tickets ticket);
	public Tickets closeTicket(int id,Tickets ticket);
	public Optional<Tickets> findTicketById(int id);
	public List<Tickets> findAllTickets();

}
