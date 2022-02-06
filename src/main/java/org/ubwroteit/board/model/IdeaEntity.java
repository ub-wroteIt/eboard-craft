package org.ubwroteit.board.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.ubwroteit.common.model.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "idea")
@SQLDelete(sql = "UPDATE idea SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class IdeaEntity extends BaseEntity {
    private UUID contenderId;
    private String title;
    private String description;
    private UUID electionId;
}
