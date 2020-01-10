package SE62.team03.Entity;
import lombok.*;
import javax.persistence.*;
import java.util.*;

@Data
@Entity
@NoArgsConstructor
@Table(name="Branch")
public class Branch {
	@Id
	@SequenceGenerator(name="Branch_seq",sequenceName="Branch_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Branch_seq")
	@Column(name="Branch_ID",unique = true, nullable = true)
	private @NonNull Long id;
	private @NonNull String name;

   @OneToMany(fetch = FetchType.EAGER)
	private Collection<Clubs> Clubs;
}
