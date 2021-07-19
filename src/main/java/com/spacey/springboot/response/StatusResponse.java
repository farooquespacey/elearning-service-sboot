package com.spacey.springboot.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;

@Builder
@JsonInclude(Include.NON_NULL)
public class StatusResponse {
	String status;
	String reason;

	// for error response
	public StatusResponse(String status, String reason) {
		this.status = status;
		this.reason = reason;
	}

	// for valid response
	public StatusResponse(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}