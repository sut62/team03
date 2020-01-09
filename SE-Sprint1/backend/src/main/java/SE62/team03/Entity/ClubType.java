package SE62.team03.Entity;
import lombok.*;
import javax.persistence.*;
import java.util.*;

@Data
@Entity
@NoArgsConstructor
@Table(name="ClubType")
public class ClubType {
	@Id
	@SequenceGenerator(name="ClubType_seq",sequenceName="ClubType_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ClubType_seq")
	@Column(name="ClubType_ID",unique = true, nullable = true)
	private @NonNull Long id;
	private @NonNull String name;

   @OneToMany(fetch = FetchType.EAGER)
	private Collection<Clubs> Clubs;

	public String getClubTypeName() {
        return name;
    }

    public void setClubTypeName(String name) {
        this.name = name;
    }
}