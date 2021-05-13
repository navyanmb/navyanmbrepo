package com.mindtree.ticket.tracking.dao.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.ticket.tracking.dao.TicketTrackingDao;
import com.mindtree.ticket.tracking.model.Tickets;
import com.mindtree.ticket.tracking.repository.TicketTrackingRepository;

@Service
public class TicketTrackingDaoImpl implements TicketTrackingDao {

	@Autowired
	TicketTrackingRepository ticketTrackingRepository;
	
	@Override
	public Tickets logTicket(Tickets ticket) {
		return ticketTrackingRepository.save(ticket);
	}

	@Override
	public Tickets closeTicket(int id, Tickets ticket) {
		 LocalDateTime currentDateTime = LocalDateTime.now();  
		 Tickets resultTicket=ticketTrackingRepository.findById(id).get();
		 resultTicket.setResolution(ticket.getResolution());
		 resultTicket.setResolved_by(ticket.getResolved_by());
		 resultTicket.setStatus(ticket.getStatus());
		 resultTicket.setResolved_date(currentDateTime);
         return ticketTrackingRepository.save(resultTicket);
	}

	@Override
	public Optional<Tickets> findTicketById(int id) {
		
		return ticketTrackingRepository.findById(id);
	}

	@Override
	public List<Tickets> findAllTickets() {
		return ticketTrackingRepository.findAll();
	}

}
