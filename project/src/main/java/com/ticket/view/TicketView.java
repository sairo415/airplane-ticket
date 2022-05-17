package com.ticket.view;

import java.util.List;

import com.ticket.model.Ticket;

public class TicketView {

	public void findAll(List<Ticket> tickets) {
		System.out.println("전체 조회 결과");
		System.out.println();
//		System.out.println(tickets);
		for (Ticket ticket : tickets) {
			System.out.println(String.format("티켓 번호 : %d", ticket.getTicketId()));
			System.out.println(String.format("이름 : %s %s", ticket.getLastName(), ticket.getFirstName()));
			System.out.println(String.format("항공사 : %s", ticket.getAirline()));
			System.out.println(String.format("출발지 : %s", ticket.getStartingPoint()));
			System.out.println(String.format("목적지 : %s", ticket.getDestination()));
			System.out.println();
		}
	}

	public void findById(Ticket ticket) {
		System.out.println("하나 조회");
		System.out.println();
		System.out.println(String.format("티켓 번호 : %d", ticket.getTicketId()));
		System.out.println(String.format("이름 : %s %s", ticket.getLastName(), ticket.getFirstName()));
		System.out.println(String.format("항공사 : %s", ticket.getAirline()));
		System.out.println(String.format("출발지 : %s", ticket.getStartingPoint()));
		System.out.println(String.format("목적지 : %s", ticket.getDestination()));
		System.out.println();		
	}

	public void errorPage() {
		System.out.println("문제 발생");
	}

	public void successPage() {
		System.out.println("정상 등록");
	}

	public void delete(int deletedTicket) {
		System.out.println("정상 삭제");
		
	}

	public void match(Ticket newTicket) {
		System.out.println(newTicket);
	}

}
