package com.mindtree.ticket.tracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.ticket.tracking.model.Tickets;

@Repository
public interface TicketTrackingRepository extends JpaRepository<Tickets, Integer>{

}
