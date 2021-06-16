package com.tjdals.shoppingMall;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@NoArgsConstructor
@Data
public class Product_list {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="ItemNumber")
	private String ItemNumber;
	
	
	private String productname;
	
	
	private String productprice;
	
	
	private String producPicture;
	
	

}
