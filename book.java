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
@Table(name="BOOK")
public class Book {

    @Id
    @SequenceGenerator(name="Book_seq",sequenceName="Book_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Book_seq")
    @Column(name = "BOOK_ID", unique = true, nullable = true)
    private @NonNull Long id;
  
  
    
}