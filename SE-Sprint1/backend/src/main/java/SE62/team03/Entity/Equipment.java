package SE62.team03.Entity;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
@Data
@Entity
@NoArgsConstructor
@Table(name="Equipment")
public class Equipment {
	@Id
	@SequenceGenerator(name="Equipment_seq",sequenceName="Equipment_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Equipment_seq")
	@Column(name="E_id",unique = true, nullable = true)
	private Long E_id;
	private @NotNull String E_name;

	public Long getE_id() {
		return this.E_id;
	}

	public void setE_id(Long E_id) {
		this.E_id = E_id;
	}


	public String getE_name() {
		return this.E_name;
	}

	public void setE_name(String E_name) {
		this.E_name = E_name;
	}

}
