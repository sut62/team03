package SE62.team03.Entity;
import lombok.*;
import javax.persistence.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Entity
@NoArgsConstructor
@Table(name="Clubmember")
public class Clubmember {

    @Id
    @SequenceGenerator(name="Clubmember_SEQ",sequenceName="Clubmember_SEQ")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Clubmember_SEQ")  
    @Column(name = "Clubmember_ID", unique = true, nullable = true)
    private @NotNull Long id;
    @Pattern(regexp = "^[0-9A-Za-zก-์\\s]+$")
    @Size(min = 3, max = 30)
    private @NotNull String ClubmemberName;

    public String getClubmemberName() {
        return this.ClubmemberName;
    }

    public void setClubmemberName(String ClubmemberName) {
        this.ClubmemberName = ClubmemberName;
    }

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Branch.class)
    @JoinColumn(name = "Branch_ID", insertable = true)
    private @NotNull Branch Branch;

    public Branch getBranch() {
        return this.Branch;
    }

    public void setBranch(Branch Branch) {
        this.Branch = Branch;
    }

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Year.class)
    @JoinColumn(name = "Year_ID", insertable = true)
    private @NotNull Year Year;

    public Year getYear() {
        return this.Year;
    }

    public void setYear(Year Year) {
        this.Year = Year;
    }

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Clubs.class)
    @JoinColumn(name = "Club_ID", insertable = true)
    private @NotNull Clubs Clubs;

    public Clubs getClubs() {
        return this.Clubs;
    }

    public void setClubs(Clubs Clubs) {
        this.Clubs = Clubs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return ClubmemberName;
    }

    public void setName(String name) {
        this.ClubmemberName = name;
    }

}
