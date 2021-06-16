package com.tjdals.shoppingMall;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserForm {

	@NotBlank(message="필수 입력입니다")
	private String userName;  // email 
	
	@NotBlank(message="필수 입력입니다")
	private String password;
	
	private String address;

	private String phone_number;
}
