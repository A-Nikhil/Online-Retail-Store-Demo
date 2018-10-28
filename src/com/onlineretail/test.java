package com.onlineretail;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class test {
	public static void main(String []args) {
		LocalDate localDate = LocalDate.now();//For reference
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
		String formattedString = localDate.format(formatter);
		System.out.println(formattedString);
	}
}
