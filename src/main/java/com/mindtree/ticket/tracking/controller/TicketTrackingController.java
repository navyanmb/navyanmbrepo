package com.mindtree.ticket.tracking.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.ticket.tracking.model.Response;
import com.mindtree.ticket.tracking.model.TicketListResponse;
import com.mindtree.ticket.tracking.model.TicketResponse;
import com.mindtree.ticket.tracking.model.Tickets;
import com.mindtree.ticket.tracking.service.TicketTrackingService;


@RestController
@RequestMapping(value="tickets")
public class TicketTrackingController {
	
	private static final Logger logger = LoggerFactory.getLogger(TicketTrackingController.class);
	
	@Autowired
	TicketTrackingService ticketTrackingService;
	
	@RequestMapping(value = "/logTicket", method = RequestMethod.POST)
	public TicketResponse logTicket(@RequestBody Tickets ticket) {
		logger.info("Logging ticket..");
		return ticketTrackingService.logTicket(ticket);
	}
	
	@RequestMapping(value = "/closeTicket/{id}", method = RequestMethod.PUT)
	public Response closeTicket(@PathVariable int id,@RequestBody Tickets ticket) {
		logger.info("Closing ticket..");
		return ticketTrackingService.closeTicket(id,ticket);
	}
	
	@RequestMapping(value = "/viewTicketWithTAT", method = RequestMethod.GET)
	public TicketListResponse viewTicketsWithTurnaroundTime() {
		logger.info("viewing TicketsWithTurnaroundTime..");
		return ticketTrackingService.viewTicketsWithTurnaroundTime();
	}
	
}
