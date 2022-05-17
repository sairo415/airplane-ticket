package com.ticket;

import com.ticket.controller.TicketController;
import com.ticket.model.Ticket;

public class App {

	public static void main(String[] args) throws Exception {

		TicketController ticketController = new TicketController();
		
		// 전체 티켓 조회
		ticketController.findAll();
		
		// 하나의 티켓 조회
//		long ticketNumber = 3L;
//		ticketController.findById(ticketNumber);
		
		// insert
		Ticket ticket = new Ticket(null, "이", "광수", "대한항공", "런던", "취리히");
		ticketController.save(ticket);
//		ticketController.findAll();
//		ticketController.match(ticket);
		
		// update
//		long passengerId = 1L;
//		Ticket updateTodo = new Ticket("이", "병헌");
//			
//		
//		ticketController.updateById(passengerId, updateTodo);

		// delete
//		long ticketNumber = 1L;
//		ticketController.deleteById(ticketNumber);
//		ticketController.findAll();
		
	}
}