package SE62.team03.Entity;
import lombok.*;
import javax.persistence.*;



@Data
@Entity
@NoArgsConstructor
@Table(name="CLUBMEMBER")
public class Clubmember {

    @Id
    @SequenceGenerator(name="CLUBMEMBER_SEQ",sequenceName="CLUBMEMBER_SEQ")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="CLUBMEMBER_SEQ")  
    @Column(name = "CLUBMEMBER_ID", unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull String name;


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