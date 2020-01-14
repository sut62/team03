package SE62.team03.Entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Clubs.class)
    private @NotNull Clubs clubs;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = ClubType.class)
    @JoinColumn(name = "ClubType_ID", insertable = true)
    private ClubType ClubType;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Clubmember.class)
    @JoinColumn(name = "CLUBMEMBER_ID", insertable = true)
    private Clubmember clubmember;


    public void setClubs(Clubs clubs) {
        this.clubs = clubs;
    }
    public Clubs getClubs() {
        return clubs;
    }

    

    public ClubType getClubType() {
        return ClubType;
    }

    public void setClubType(ClubType clubtype) {
        this.ClubType = clubtype;
    }

    public Clubmember getClubmember() {
        return clubmember;
    }

    public void setClubmember(Clubmember clubmember) {
        this.clubmember = clubmember;
    }

   
    

    
    

   


}