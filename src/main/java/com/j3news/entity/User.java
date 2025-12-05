package com.j3news.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
	int id;
	String email;
	String password;
	String fullName;
	Date birthDay;
	boolean gender;
	String phone;
	boolean role;
}
