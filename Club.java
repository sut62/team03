package com.cpe.backend.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;





@Data
@Entity
@NoArgsConstructor
@Table(name="Club")
public class Club {

    @Id
    @SequenceGenerator(name="Club_seq",sequenceName="Club_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Club_seq")
    @Column(name = "Club_ID", unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull String ClubName;
    private @NonNull String ClubMember;
    private @NonNull Integer Age;
    private @NonNull String Objective;
    private @NonNull String AdvisorstoClub;
    private @NonNull String ClubType;
    @Column(name="RENT_DATE")
    private @NonNull Date rentDate;
   

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Branch.class)
    @JoinColumn(name = "Branch_ID", insertable = true)
    private Branch Branch;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Year.class)
    @JoinColumn(name = "Year_ID", insertable = true)
    private Year Year;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Officer.class)
    @JoinColumn(name = "Officer_ID", insertable = true)
    private Officer Officer;
  
    
}