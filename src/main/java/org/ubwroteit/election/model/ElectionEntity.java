package org.ubwroteit.election.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;
import org.ubwroteit.common.model.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="election")
@SQLDelete(sql = "UPDATE election SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
//For Administrative queries
@FilterDef(name = "deletedElectionFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedElectionFilter", condition = "deleted = :isDeleted")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ElectionEntity extends BaseEntity {
    private LocalDateTime votingStartTime;
    private LocalDateTime votingEndTime;
    private LocalDateTime resultDeclarationAfter;
    private String electionTitle;
    private String electionDescription;
}
