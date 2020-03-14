package SE62.team03.Entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "Budget")
@Getter
@Setter
@NoArgsConstructor
public class Budget {
    @Id
    @SequenceGenerator(name = "budget_seq", sequenceName = "budget_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "budget_seq")
    @Column(name = "budget_Id", unique = true)
    private @NotNull Long budgetId;
    @Min(0) @Max(100000)
    private @NotNull Integer amount;

    public Integer getAmount() {
        return this.amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Long getBudgetId() {
        return this.budgetId;
    }

    public void setBudgetId(Long budgetId) {
        this.budgetId = budgetId;
    }

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Clubs.class)
    private @NotNull Clubs clubs;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = ClubType.class)
    @JoinColumn(name = "ClubType_ID", insertable = true)
    private @NotNull ClubType ClubType;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Clubmember.class)
    @JoinColumn(name = "CLUBMEMBER_ID", insertable = true)
    private @NotNull Clubmember clubmember;

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