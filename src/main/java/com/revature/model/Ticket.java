package com.revature.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

public class Ticket {
	private int ticketId;
	private BigDecimal reqAmount;
	private LocalDateTime submitDate;
	private LocalDateTime resolutionDate;
	private String elaboration;
	private byte[] image;
	private String ticketOwner;
	private String ticketEvaluator;
	private String ticketStatus;
	private String ticketType;
	
	public Ticket() {
		// No-args
	}
	
	public BigDecimal getReqAmount() {
		return reqAmount;
	}

	public void setReqAmount(BigDecimal reqAmount) {
		this.reqAmount = reqAmount;
	}
	
	public String getTicketType() {
		return ticketType;
	}

	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}

	public LocalDateTime getResolutionDate() {
		return resolutionDate;
	}

	public Ticket(int ticketId, BigDecimal reqAmount, LocalDateTime submitDate, LocalDateTime resolutionDate,
			String elaboration, byte[] image, String ticketOwner, String ticketEvaluator, String ticketStatus,
			String ticketType) {
		super();
		this.ticketId = ticketId;
		this.reqAmount = reqAmount;
		this.submitDate = submitDate;
		this.resolutionDate = resolutionDate;
		this.elaboration = elaboration;
		this.image = image;
		this.ticketOwner = ticketOwner;
		this.ticketEvaluator = ticketEvaluator;
		this.ticketStatus = ticketStatus;
		this.ticketType = ticketType;
	}



	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public String getElaboration() {
		return elaboration;
	}

	public void setElaboration(String elaboration) {
		this.elaboration = elaboration;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getTicketOwner() {
		return ticketOwner;
	}

	public void setTicketOwner(String ticketOwner) {
		this.ticketOwner = ticketOwner;
	}

	public String getTicketEvaluator() {
		return ticketEvaluator;
	}

	public void setTicketEvaluator(String ticketEvaluator) {
		this.ticketEvaluator = ticketEvaluator;
	}

	public String getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
	}
	
	public LocalDateTime getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(LocalDateTime submitDate) {
		this.submitDate = submitDate;
	}

	public void setResolutionDate(LocalDateTime resolutionDate) {
		this.resolutionDate = resolutionDate;
	}

	@Override
	public String toString() {
		return "Ticket [ticketId=" + ticketId + ", reqAmount=" + reqAmount + ", submitDate=" + submitDate
				+ ", resolutionDate=" + resolutionDate + ", elaboration=" + elaboration + ", image="
				+ Arrays.toString(image) + ", ticketOwner=" + ticketOwner + ", ticketEvaluator=" + ticketEvaluator
				+ ", ticketStatus=" + ticketStatus + ", ticketType=" + ticketType + "]";
	}
	
	
}
