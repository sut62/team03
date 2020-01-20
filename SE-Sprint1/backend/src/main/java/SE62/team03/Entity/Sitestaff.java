package SE62.team03.Entity;
import lombok.*;
import javax.persistence.*;

import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
@Table(name="SITESTAFF")
public class Sitestaff {
	@Id
	@SequenceGenerator(name="SITESTAFF_SEQ",sequenceName="SITESTAFF_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SITESTAFF_SEQ")
	@Column(name="SITESTAFF_ID",unique = true, nullable = true)
	private @NotNull Long id;
	private @NotNull String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
