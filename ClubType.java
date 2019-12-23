package com.okta.springbootvue.Entity;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import java.util.Collection;

@Entity
@Data
@Table(name="ClubType")
@Getter 
@Setter

@NoArgsConstructor
public class ClubType {
    @Id
    @SequenceGenerator(name="clubtype_seq",sequenceName="clubtype_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="clubtype_seq")
    @Column(name="clubtypeId",unique = true, nullable = false)
    private Long clubtypeId;
    @NotNull 
    private String clubtypeName;

    public Long getClubTypeId() {
        return clubtypeId;
    }

    public void setClubTypeId(Long clubtypeId) {
        this.clubtypeId = clubtypeId;
    }

    public String getClubTypeName() {
        return clubtypeName;
    }

    public void setClubTypeName(String clubtypeName) {
        this.clubtypeName = clubtypeName;
    }
 
    

}