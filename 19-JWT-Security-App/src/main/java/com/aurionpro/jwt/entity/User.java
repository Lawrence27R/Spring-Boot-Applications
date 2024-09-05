package com.aurionpro.jwt.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int userId;
	
	@Column
	private String username;
	
	@Column
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(joinColumns = @JoinColumn(name = "userId"), inverseJoinColumns = @JoinColumn(name = "roleId"))
	private List<Role> role;

}
