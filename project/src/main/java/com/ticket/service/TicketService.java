package com.ticket.service;

import java.util.List;

import com.ticket.dao.TicketDAO;
import com.ticket.model.Ticket;

public class TicketService {

	private final TicketDAO ticketDAO;
	
	public TicketService() {
		ticketDAO = new TicketDAO();
	}

	public List<Ticket> findAll() {
		return ticketDAO.findAll();
	}

	public Ticket findById(long ticketNumber) {
		return ticketDAO.findById(ticketNumber);
	}

	public int save(Ticket newTicket) {
		return ticketDAO.save(newTicket);
	}

	public int deleteById(long ticketNumber) {
		return ticketDAO.deleteById(ticketNumber);
	}

//	public Ticket match(Ticket ticket) {
//		return ticketDAO.match(ticket);
//	}

}
