package org.ubwroteit.citizen.entity;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="citizen")
@SQLDelete(sql = "UPDATE citizen SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
//For Administrative queries
@FilterDef(name = "deletedCitizenFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedCitizenFilter", condition = "deleted = :isDeleted")
public class Citizen {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name = "uuid", strategy = "uuid4")
    @Setter
    private UUID id;
    private String firstName;
    private String lastName;
    private String emailId;
    private String governmentUniqueId;
    private int age;
    @Column(columnDefinition = "int default 0")
    private int areaId;
    @Column(columnDefinition = "boolean default false")
    private boolean deleted= false;
}
