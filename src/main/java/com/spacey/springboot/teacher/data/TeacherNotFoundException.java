package com.spacey.springboot.teacher.data;

public class TeacherNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TeacherNotFoundException(String message) {
		super(message);
	}
}
