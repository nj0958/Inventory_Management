package com.tjdals.shoppingMall;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="role")
@NoArgsConstructor
@Data
public class Role {
	@Column(name="user_name")
	@Id
	private String userName;  // email
	
	@Column(name="role_name")
	private String roleName;  // admin, user
}
