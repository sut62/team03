package com.okta.springbootvue.Entity;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import java.util.Collection;

@Entity
@Data
@Table(name="Budget")
@Getter 
@Setter
@NoArgsConstructor
public class Budget {
    @Id
    @SequenceGenerator(name="budget_seq",sequenceName="budget_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="budget_seq")
    @Column(name="budget_Id",unique = true, nullable = false)
    private Long budgetId;


    @NotNull 
    private int amount;

    public Long getBudgetId() {
        return budgetId;
    }

    public void setBudgetI(Long budgetId) {
        this.budgetId = budgetId;
    }

 
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    @OneToOne(fetch = FetchType.EAGER, targetEntity = Club.class)
    private @NotNull Club club;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = ClubType.class)
    private @NotNull ClubType clubtype;

    @OneToMany(fetch = FetchType.EAGER, targetEntity = Member.class)
    private @NotNull Member member;

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public ClubType getClubType() {
        return clubtype;
    }

    public void setClubType(ClubType clubtype) {
        this.clubtype = clubtype;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

   
    

    
    

   


}