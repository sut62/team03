package SE62.team03.Entity;
import lombok.*;
import javax.persistence.*;
import java.util.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
@Data
@Entity
@NoArgsConstructor
@Table(name="ClubType")
public class ClubType {
	@Id
	@SequenceGenerator(name="ClubType_seq",sequenceName="ClubType_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ClubType_seq")
	@Column(name="ClubType_ID",unique = true, nullable = true)
	private @NotNull Long id;
	private @NotNull String name;
    @Size(max = 25)

   @OneToMany(fetch = FetchType.EAGER)
	private Collection<Clubs> Clubs;

	public String getClubTypeName() {
        return name;
    }

    public void setClubTypeName(String name) {
        this.name = name;
    }
}