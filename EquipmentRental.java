package com.cpe.backend.entity;

import lombok.*;

import javax.persistence.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;

import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@Table(name="PATIENT")
public class Patient {
	@Id
	@SequenceGenerator(name="Patient_seq",sequenceName="Patient_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Patient_seq")
	@Column(name="PATIENT_ID",unique = true, nullable = true)
	private @NonNull Long id;
	private @NonNull String name;

   @OneToMany(fetch = FetchType.EAGER)
	private Collection<RightAssingment> rent;
}
