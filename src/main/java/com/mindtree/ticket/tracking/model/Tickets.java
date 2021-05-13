package com.mindtree.ticket.tracking.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Tickets {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ticket_id;
	private String logged_by;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime raised_date;
	private String severity;
	private String ticket_desc;
	private String resolved_by;
	private String resolution;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime resolved_date;
	private String status;
	
	public Tickets()
	{
		
	}

	public int getTicket_id() {
		return ticket_id;
	}

	public void setTicket_id(int ticket_id) {
		this.ticket_id = ticket_id;
	}

	public String getLogged_by() {
		return logged_by;
	}

	public void setLogged_by(String logged_by) {
		this.logged_by = logged_by;
	}

	public LocalDateTime getRaised_date() {
		return raised_date;
	}

	public void setRaised_date(LocalDateTime raised_date) {
		this.raised_date = raised_date;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getTicket_desc() {
		return ticket_desc;
	}

	public void setTicket_desc(String ticket_desc) {
		this.ticket_desc = ticket_desc;
	}

	public String getResolved_by() {
		return resolved_by;
	}

	public void setResolved_by(String resolved_by) {
		this.resolved_by = resolved_by;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public LocalDateTime getResolved_date() {
		return resolved_date;
	}

	public void setResolved_date(LocalDateTime resolved_date) {
		this.resolved_date = resolved_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Tickets [ticket_id=" + ticket_id + ", logged_by=" + logged_by + ", raised_date=" + raised_date
				+ ", severity=" + severity + ", ticket_desc=" + ticket_desc + ", resolved_by=" + resolved_by
				+ ", resolution=" + resolution + ", resolved_date=" + resolved_date + ", status=" + status + "]";
	}
    
}
