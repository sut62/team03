package SE62.team03.Entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.util.*;
@Data
@Entity
@NoArgsConstructor
@Table(name="EquipmentRental")
public class EquipmentRental {

    @Id
    @SequenceGenerator(name="EquipmentRental_seq",sequenceName="EquipmentRental_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="EquipmentRental_seq")
    @Column(name = "EquipmentRental_id", unique = true)
    private @NotNull Long EquipmentRental_id;
    private @NotNull Date EquipmentRental_date;
    @Size(min = 5, max = 30)
    private @NotNull String note;
    @Pattern(regexp = "^[A-Za-zก-์\\s]+$")
    private @NotNull String renter_name;

    public String getRenter_name() {
        return this.renter_name;
    }

    public void setRenter_name(String renter_name) {
        this.renter_name = renter_name;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getEquipmentRental_id() {
        return this.EquipmentRental_id;
    }

    public void setEquipmentRental_id(Long id) {
        this.EquipmentRental_id = id;
    }


    public Date getEquipmentRental_date() {
        return this.EquipmentRental_date;
    }

    public void setEquipmentRental_date(Date EquipmentRental_date) {
        this.EquipmentRental_date = EquipmentRental_date;
    }

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Officer.class)
    @JoinColumn(name = "Officer_ID", insertable = true)
    private Officer officer;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Clubs.class)
    @JoinColumn(name = "Clubs_ID", insertable = true)
    private Clubs clubs;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Equipment.class)
    @JoinColumn(name = "E_id", insertable = true)
    private Equipment equipment;

    public Officer getOfficer() {
        return this.officer;
    }

    public void setOfficer(Officer officer) {
        this.officer = officer;
    }

    public void setClubs(Clubs clubs) {
        this.clubs = clubs;
    }
    public Clubs getClubs() {
        return clubs;
    }


    public Equipment getEquipment() {
        return this.equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }
  
    
}