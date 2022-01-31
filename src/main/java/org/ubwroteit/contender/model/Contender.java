package org.ubwroteit.contender.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.ubwroteit.common.model.BaseEntity;
import org.ubwroteit.common.model.ElectionCategory;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "contender")
@SQLDelete(sql = "UPDATE contender SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Contender extends BaseEntity {
    private UUID citizenId;
    private int areaId;
    private int electionYear; //Assumption is election will happen at most once in year.
    @Enumerated(EnumType.ORDINAL)
    private ElectionCategory category;
}
