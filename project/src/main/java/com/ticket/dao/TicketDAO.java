package com.ticket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ticket.model.Ticket;
import com.ticket.utils.DBUtils;

public class TicketDAO {

	private Ticket ticket;
	PreparedStatement pstmt;

	public List<Ticket> findAll() {

		final String selectQuery = "SELECT ticket_id, last_name, first_name, airline, destination, starting_point \r\n"
				+ "FROM ticket AS t \r\n" + "JOIN airline AS a ON t.airline_id = a.airline_id \r\n"
				+ "JOIN destination AS d ON t.destination_id = d.destination_id \r\n"
				+ "JOIN passenger AS p ON t.passenger_id = p.passenger_id \r\n"
				+ "JOIN starting_point AS sp ON t.starting_point_id = sp.starting_point_id  \r\n"
				+ "GROUP BY ticket_id ORDER BY 1;\r\n" + "";
		List<Ticket> tickets = new ArrayList<>();

		try (Connection conn = DBUtils.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(selectQuery);) {

			while (rs.next()) {
				Long ticketId = rs.getLong("ticket_id");
				String lastName = rs.getString("last_name");
				String firstName = rs.getString("first_name");
				String airline = rs.getString("airline");
				String destination = rs.getString("destination");
				String startingPoint = rs.getString("starting_point");

				ticket = new Ticket(ticketId, lastName, firstName, airline, destination, startingPoint);
				tickets.add(ticket);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tickets;
	}

	public Ticket findById(long ticketNumber) {
		final String selectQuery = "SELECT ticket_id, last_name, first_name, airline, destination, starting_point \r\n"
				+ "FROM ticket AS t \r\n" + "JOIN airline AS a ON t.airline_id = a.airline_id \r\n"
				+ "JOIN destination AS d ON t.destination_id = d.destination_id \r\n"
				+ "JOIN passenger AS p ON t.passenger_id = p.passenger_id \r\n"
				+ "JOIN starting_point AS sp ON t.starting_point_id = sp.starting_point_id  \r\n"
				+ "WHERE ticket_id = ? \r\n";
		try (Connection conn = DBUtils.getConnection();
				PreparedStatement pstmt = createPreparedStatement(conn, selectQuery, ticketNumber);
				ResultSet rs = pstmt.executeQuery();) {

			if (rs.next()) {
				Long ticketId = rs.getLong("ticket_id");
				String lastName = rs.getString("last_name");
				String firstName = rs.getString("first_name");
				String airline = rs.getString("airline");
				String destination = rs.getString("destination");
				String startingPoint = rs.getString("starting_point");

				ticket = new Ticket(ticketId, lastName, firstName, airline, destination, startingPoint);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ticket;
	}

	private PreparedStatement createPreparedStatement(Connection conn, String selectQuery, long ticketNumber)
			throws SQLException {
		pstmt = conn.prepareStatement(selectQuery);
		pstmt.setLong(1, ticketNumber);
		return pstmt;
	}

//	public Ticket match(Ticket newTicket) {
//		final String selectQuery = "SELECT passenger_id FROM passenger WHERE last_name = ? AND first_name = ?;\r\n" + 
////				"SELECT airline_id FROM airline WHERE airline = ?;\r\n" + 
////				"SELECT starting_point_id FROM starting_point WHERE starting_point = ?;\r\n" + 
////				"SELECT destination_id FROM destination WHERE destination = ?;\r\n" + 
//				"";
//		try (Connection conn = DBUtils.getConnection();
//				PreparedStatement pstmt = createPreparedStatement(conn, selectQuery, newTicket);
//				ResultSet rs = pstmt.executeQuery();) {
//				
//				if (rs.next()) {
////					Long ticketId = rs.getLong("ticket_id");
//					String lastName = rs.getString("last_name");
//					String firstName = rs.getString("first_name");
//					String airline = rs.getString("airline");
//					String destination = rs.getString("destination");
//					String startingPoint = rs.getString("starting_point");
//				
//					ticket = new Ticket(lastName, firstName, airline, destination, startingPoint);
//				}
//				
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			
//			return ticket;
//	}

//	private Long passengerId(Ticket newTicket) {
//		String passengerQuery = "SELECT passenger_id FROM passenger WHERE last_name = ? AND first_name = ?";
//		try (Connection conn = DBUtils.getConnection();
//				Statement stmt = conn.createStatement();
//				ResultSet passengerRs = stmt.executeQuery(passengerQuery);) {
//			
//			if(passengerRs.next()) {
//				Long passenger = passengerRs.getLong("passenger_id");
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return passenger;
//	}
//
//	String airlineQuery = "SELECT airline_id FROM airline WHERE airline = ?";
//	String startingPointQuery = "SELECT starting_point_id FROM starting_point WHERE starting_point = ?";
//	String destinationQuery = "SELECT destination_id FROM destination WHERE destination = ?";

	public int save(Ticket newTicket) {

		String insertQuery = "INSERT INTO ticket (ticket_id, passenger_id, destination_id, starting_point_id, airline_id) \r\n" + 
				"SELECT last_name, first_name, airline, destination, starting_point \r\n" + 
				"FROM ticket \r\n" + 
				"JOIN passenger ON last_name = ? AND first_name = ? \r\n" + 
				"JOIN airline ON airline = ?\r\n" + 
				"JOIN starting_point ON starting_point = ?\r\n" + 
				"JOIN destination ON destination = ?\r\n" + 
				"";

//		String insertQuery = "INSERT INTO todo (passenger_id, destination_id, starting_point_id, airline_id) VALUES (?, ?, ?, ?)";
		int affectedRows = 0;

		try (Connection conn = DBUtils.getConnection();
				Statement stmt = conn.createStatement();
//				ResultSet airlineRs = stmt.executeQuery(airlineQuery);
//				ResultSet startingPointRs = stmt.executeQuery(startingPointQuery);
//				ResultSet destinationRs = stmt.executeQuery(destinationQuery);
				PreparedStatement pstmt = createPreparedStatement(conn, insertQuery, newTicket);) {
//			passengerRs.getLong("passenger_id");
//			airlineRs.getLong("airline_id");
//			startingPointRs.getLong("starting_point_id");
//			destinationRs.getLong("destination_id");

			affectedRows = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return affectedRows;
	}

	private PreparedStatement createPreparedStatement(Connection conn, String sql, Ticket newTicket)
			throws SQLException {
		pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, newTicket.getLastName());
		pstmt.setString(2, newTicket.getFirstName());
		pstmt.setString(3, newTicket.getAirline());
		pstmt.setString(4, newTicket.getStartingPoint());
		pstmt.setString(5, newTicket.getDestination());

		return pstmt;
	}

	public int deleteById(long ticketNumber) {
		String deleteQuery = "DELETE FROM ticket WHERE ticket_id = ?";
		int affectedRows = 0;

		try (Connection conn = DBUtils.getConnection();
				PreparedStatement pstmt = createPreparedStatement(conn, ticketNumber, deleteQuery);) {
			affectedRows = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return affectedRows;
	}

	private PreparedStatement createPreparedStatement(Connection conn, long ticketNumber, String deleteQuery)
			throws SQLException {
		pstmt = conn.prepareStatement(deleteQuery);
		pstmt.setLong(1, ticketNumber);

		return pstmt;
	}
}
