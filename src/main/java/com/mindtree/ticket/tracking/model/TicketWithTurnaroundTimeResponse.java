package com.mindtree.ticket.tracking.model;

public class TicketWithTurnaroundTimeResponse {
	
	private String employee_name;
	private int id;
	private String severity;
	private String TurnaroundTime;
	private String description;
	private String resolved_by;
	
	public TicketWithTurnaroundTimeResponse()
	{
		
	}

	public TicketWithTurnaroundTimeResponse(String employee_name, int id, String severity, String turnaroundTime,
			String description, String resolved_by) {
		this.employee_name = employee_name;
		this.id = id;
		this.severity = severity;
		this.TurnaroundTime = turnaroundTime;
		this.description = description;
		this.resolved_by = resolved_by;
	}

	public String getEmployee_name() {
		return employee_name;
	}

	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getTurnaroundTime() {
		return TurnaroundTime;
	}

	public void setTurnaroundTime(String turnaroundTime) {
		TurnaroundTime = turnaroundTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getResolved_by() {
		return resolved_by;
	}

	public void setResolved_by(String resolved_by) {
		this.resolved_by = resolved_by;
	}
}
