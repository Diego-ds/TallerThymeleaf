package com.example.tallerdiegogarcia.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import com.example.tallerdiegogarcia.validate.UserValidation;

import lombok.Data;

@Data
@Entity
public class UserWeb {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer id;
	
	@NotBlank(groups = UserValidation.class)
	@Size(min=2, message = "2 character minimum", groups = UserValidation.class)
	private String username;
	
	@NotBlank(groups = UserValidation.class)
	@Size(min=4, message = "4 character minimum", groups = UserValidation.class)
	private String password;
	
	@NotNull(groups = UserValidation.class)
	private UserType type;
	
}
