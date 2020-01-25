package SE62.team03.Entity;
import lombok.*;
import javax.persistence.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
    private @NotNull String ClubmemberName;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Branch.class)
    @JoinColumn(name = "Branch_ID", insertable = true)
    private Branch Branch;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Year.class)
    @JoinColumn(name = "Year_ID", insertable = true)
    private Year Year;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Clubs.class)
    @JoinColumn(name = "Club_ID", insertable = true)
    private Clubs Clubs;

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
