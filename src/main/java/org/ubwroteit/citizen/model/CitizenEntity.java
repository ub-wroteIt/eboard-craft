package org.ubwroteit.citizen.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;
import org.ubwroteit.common.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
public class CitizenEntity extends BaseEntity {
    private String firstName;
    private String lastName;
    private String emailId;
    private String governmentUniqueId;
    private int age;
    @Column(columnDefinition = "int default 0")
    private int areaId;
}
