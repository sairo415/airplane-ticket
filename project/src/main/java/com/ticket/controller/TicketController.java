package com.ticket.controller;

import java.util.List;

import com.ticket.model.Ticket;
import com.ticket.service.TicketService;
import com.ticket.view.TicketView;

public class TicketController {

	private List<Ticket> tickets;
	private final TicketView ticketView;
	private final TicketService ticketService;
	private Exception errorObject;
	
	// 기본 생성자
	public TicketController() {
		this.ticketView = new TicketView();
		this.ticketService = new TicketService();
	}

	public void findAll() {
		tickets = ticketService.findAll();
		
		ticketView.findAll(tickets);
		
	}

	public void findById(long ticketNumber) {
		Ticket ticket = ticketService.findById(ticketNumber);
		if(ticket != null) {
			ticketView.findById(ticket);
		} else {
			ticketView.errorPage();
		}
	}
	
//	public void match(Ticket ticket) {
//		Ticket newTicket = ticketService.match(ticket);
//		if (ticket != null) {
//			ticketView.match(newTicket);
//		} else {
//			ticketView.errorPage();
//		}
//	}

	public void save(Ticket newTicket) {
		int result = ticketService.save(newTicket);
		System.out.println(result);
		
		if (result > 0) {
			ticketView.successPage();
		} else {
			ticketView.errorPage();
		}
	}

	public void deleteById(long ticketNumber) {
		int deletedTicket = ticketService.deleteById(ticketNumber);
		
		if(deletedTicket > 0) {
			ticketView.delete(deletedTicket);
		} else {
			errorObject = new Exception("삭제 실패");
		}
	}

	
	
	
}
