package SE62.team03.Entity;
import lombok.*;
import javax.persistence.*;
import java.util.*;

@Data
@Entity
@NoArgsConstructor
@Table(name="Year")
public class Year {
	@Id
	@SequenceGenerator(name="Year_seq",sequenceName="Year_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Year_seq")
	@Column(name="Year_ID",unique = true, nullable = true)
	private @NonNull Long id;
	private @NonNull String name;

   @OneToMany(fetch = FetchType.EAGER)
	private Collection<Clubs> Clubs;
}