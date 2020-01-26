package SE62.team03.Entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Entity
@NoArgsConstructor
@Table(name="ROOM")
public class Room {
    @Id
    @SequenceGenerator(name="ROOM_SEQ",sequenceName="ROOM_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ROOM_SEQ")
    @Column(name="ROOM_ID",unique = true)
    private @NotNull Long id;
    @Pattern(regexp = "^[A-Za-z0-9]+$")
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