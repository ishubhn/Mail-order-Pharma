package com.mailorderpharma.refill.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RefillOrder {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	@JsonFormat(pattern="dd-MM-yyyy hh:mm:ss")
	Date refilledDate;
	private Boolean payStatus;
	private long subId;
	int quantity;
	String memberId;


}
