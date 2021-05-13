package com.mindtree.ticket.tracking.service.impl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.mindtree.ticket.tracking.dao.EmployeeDao;
import com.mindtree.ticket.tracking.dao.TicketTrackingDao;
import com.mindtree.ticket.tracking.exceptions.EmployeeNotFoudException;
import com.mindtree.ticket.tracking.exceptions.InvalidDataException;
import com.mindtree.ticket.tracking.exceptions.InvalidDateTimeException;
import com.mindtree.ticket.tracking.exceptions.InvalidDepartmentException;
import com.mindtree.ticket.tracking.exceptions.TicketNotFoundException;
import com.mindtree.ticket.tracking.model.Employee;
import com.mindtree.ticket.tracking.model.TicketListResponse;
import com.mindtree.ticket.tracking.model.TicketResponse;
import com.mindtree.ticket.tracking.model.TicketWithTurnaroundTimeResponse;
import com.mindtree.ticket.tracking.model.Tickets;
import com.mindtree.ticket.tracking.service.TicketTrackingService;

@Service
public class TicketTrackingServiceImpl implements TicketTrackingService {

	@Autowired
	TicketTrackingDao ticketTrackingDao;

	@Autowired
	EmployeeDao employeeDao;

	private final Logger LOG = Logger.getLogger(TicketTrackingServiceImpl.class);

	@Override
	public TicketResponse logTicket(Tickets ticket) {
		TicketResponse tktResponse = new TicketResponse();
		try {
			LocalDateTime dateTime1 = ticket.getRaised_date();
			LocalDateTime dateTime2 = LocalDateTime.now();
			Duration duration = Duration.between(dateTime1, dateTime2);
			/*
			 * System.out.println("Days difference==========="+duration.toDays());
			 * System.out.println("Hours difference==========="+duration.toHours());
			 * System.out.println("Minutes difference==========="+duration.toMinutes());
			 */
			Optional<Employee> emp = employeeDao.findEmployeeById(ticket.getLogged_by());
			try {
				if (emp.isPresent()) {
					if (emp.get().getDepartment().equals("ISSD")) {
						throw new InvalidDepartmentException(
								"while logging a ticket..The list of employees does not include employees from the ISSD department");
					}
				} else {
					throw new EmployeeNotFoudException(
							"No results found for employee with the given Id " + ticket.getLogged_by());
				}
			} catch (EmployeeNotFoudException e) {
				e.printStackTrace();
				LOG.error(e.getMessage());
				tktResponse.setTicket(null);
				tktResponse.setStatus_code(404);
				tktResponse.setMessage("No results found for employee with the given Id " + ticket.getLogged_by());
			}
			if (duration.toDays() <= 0 | duration.toHours() <= 0 | duration.toMinutes() <= 0) {

				throw new InvalidDateTimeException(
						"While logging a ticket..Date-Time should be earlier than the current date-time");
			} else {
				Tickets tickets = ticketTrackingDao.logTicket(ticket);
				tktResponse.setTicket(tickets);
				tktResponse.setStatus_code(200);
				tktResponse.setMessage("Ticket has been logged Successfully!!");
			}
		} catch (InvalidDateTimeException e) {
			e.printStackTrace();
			LOG.error(e.getMessage());
			tktResponse.setTicket(null);
			tktResponse.setStatus_code(400);
			tktResponse.setMessage("Date-Time should be earlier than the current date-time");
		} catch (InvalidDepartmentException e) {
			e.printStackTrace();
			LOG.error(e.getMessage());
			tktResponse.setTicket(null);
			tktResponse.setStatus_code(400);
			tktResponse.setMessage(
					"while logging a ticket..The list of employees does not include employees from the ISSD department");
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e.getMessage());
			tktResponse.setTicket(null);
			tktResponse.setStatus_code(500);
			tktResponse.setMessage("while logging a ticket..something went wrong please refer logs..");
		}
		return tktResponse;
	}

	@Override
	public TicketResponse closeTicket(int id, Tickets ticket) {
		TicketResponse tktResponse = new TicketResponse();
		try {
			if (id == 0 | (ticket.getResolved_by().isEmpty()) | ticket.getResolved_by() == null
					| ticket.getResolution().isEmpty() | ticket.getResolution() == null | ticket.getStatus().isEmpty()
					| ticket.getStatus() == null) {
				throw new InvalidDataException("Mandatory fields are left empty.");
			} else {
				try {
					Optional<Tickets> tkt = ticketTrackingDao.findTicketById(id);
					if (tkt.isPresent()) {
						Tickets tickets = ticketTrackingDao.closeTicket(id, ticket);
						tktResponse.setTicket(tickets);
						tktResponse.setStatus_code(200);
						tktResponse.setMessage("Ticket No " + id + " is closed");
					} else {
						throw new TicketNotFoundException("No Ticket found for the given ticket id " + id);
					}

				} catch (TicketNotFoundException e) {
					e.printStackTrace();
					LOG.error(e.getMessage());
					tktResponse.setTicket(null);
					tktResponse.setStatus_code(404);
					tktResponse.setMessage("No Ticket found for the given ticket id " + id);
				}

			}

		} catch (InvalidDataException e) {
			e.printStackTrace();
			LOG.error(e.getMessage());
			tktResponse.setTicket(null);
			tktResponse.setStatus_code(400);
			tktResponse.setMessage("Mandatory fields should be filled");
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e.getMessage());
			tktResponse.setTicket(null);
			tktResponse.setStatus_code(500);
			tktResponse.setMessage("while closing ticket, something went wrong...please refer logs");
		}
		return tktResponse;
	}

	@Override
	public TicketListResponse viewTicketsWithTurnaroundTime() {
		TicketListResponse tktListResponse = new TicketListResponse();
		/*
		 * TicketWithTurnaroundTimeResponse tktResponse=new
		 * TicketWithTurnaroundTimeResponse();
		 */
		List<TicketWithTurnaroundTimeResponse> ticketList = new ArrayList<>();
		try {
			List<Tickets> tktList = ticketTrackingDao.findAllTickets();
			if (!CollectionUtils.isEmpty(tktList)) {
				try {
					for (Tickets list : tktList) {
						if (list.getStatus().equalsIgnoreCase("closed")) {
							System.out.println(list.getLogged_by());
							Optional<Employee> employee = employeeDao.findEmployeeById(list.getLogged_by());
							System.out.println(employee.get());
							Optional<Employee> emp = employeeDao.findEmployeeById(list.getResolved_by());
							System.out.println(emp.get());
							if (employee.isPresent() | emp.isPresent()) {
								TicketWithTurnaroundTimeResponse tktResponse = new TicketWithTurnaroundTimeResponse();
								tktResponse.setEmployee_name(employee.get().getEmployee_name());
								tktResponse.setId(list.getTicket_id());
								tktResponse.setSeverity(list.getSeverity());
								tktResponse.setDescription(list.getTicket_desc());
								tktResponse.setResolved_by(emp.get().getEmployee_name());
								LocalDateTime from = list.getRaised_date();
								LocalDateTime to = list.getResolved_date();
								LocalDateTime fromTemp = LocalDateTime.from(from);
								long days = fromTemp.until(to, ChronoUnit.DAYS);
								fromTemp = fromTemp.plusDays(days);

								long hour = fromTemp.until(to, ChronoUnit.HOURS);
								fromTemp = fromTemp.plusHours(hour);

								long minutes = fromTemp.until(to, ChronoUnit.MINUTES);
								fromTemp = fromTemp.plusMinutes(minutes);
                                long hours=24*days+hour;
								String turnaroundTime = hours + "." + minutes;
								tktResponse.setTurnaroundTime(turnaroundTime);

								ticketList.add(tktResponse);
								tktListResponse.setTktList(ticketList);
								tktListResponse.setStatus_code(200);
								tktListResponse.setMessage("Tickets with TurnAroundTime");
							} else {
								throw new EmployeeNotFoudException("No Employee can be found with the given id ");
							}
						}
					}
					/*
					 * tktListResponse.setTktList(ticketList); tktListResponse.setStatus_code(200);
					 * tktListResponse.setMessage("Tickets with TurnAroundTime");
					 */
				} catch (EmployeeNotFoudException e) {
					e.printStackTrace();
					LOG.error(e.getMessage());
					tktListResponse.setTktList(null);
					tktListResponse.setStatus_code(404);
					tktListResponse.setMessage("No Employee can be found with the given id");
				}

			}

			else {
				throw new TicketNotFoundException("No Tickets can be found ");
			}

		} catch (TicketNotFoundException e) {
			e.printStackTrace();
			LOG.error(e.getMessage());
			tktListResponse.setTktList(null);
			tktListResponse.setStatus_code(404);
			tktListResponse.setMessage("No Ticket can be found");
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e.getMessage());
			tktListResponse.setTktList(null);
			tktListResponse.setStatus_code(500);
			tktListResponse.setMessage("Something went wrong..please refer logs");
		}
		return tktListResponse;
	}

}
