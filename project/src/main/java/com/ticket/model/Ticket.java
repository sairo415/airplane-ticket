package com.ticket.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class Ticket {

	private Long ticketId;
	private String lastName;
	private String firstName;
	private String airline;
	private String destination;
	private String startingPoint;
	
	// 생성자
	public Ticket(Long ticketId, String lastName, String firstName, String airline, String destination,
			String startingPoint) {
		this.ticketId = ticketId;
		this.lastName = lastName;
		this.firstName = firstName;
		this.airline = airline;
		this.destination = destination;
		this.startingPoint = startingPoint;
	}
	
	
	

	@Override
	public String toString() {
		return "Ticket [ticketId=" + ticketId + ", lastName=" + lastName + ", firstName=" + firstName + ", airline="
				+ airline + ", destination=" + destination + ", startingPoint=" + startingPoint + "]";
	}




	public Ticket(String lastName, String firstName, String airline, String destination, String startingPoint) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.airline = airline;
		this.destination = destination;
		this.startingPoint = startingPoint;
	}
	
	
	
}
