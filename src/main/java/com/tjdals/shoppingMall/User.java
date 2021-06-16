package com.tjdals.shoppingMall;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="user")
@NoArgsConstructor
@Data
public class User {

	@Column(name="user_name")
	@Id
	private String userName;  // email 
	
	@Column(name="password")
	private String password;
	
	@Column(name="address")
	private String address;
	
	@Column(name="phone_number")
	private String phone_number;
		
}
