package org.ubwroteit.contender.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.ubwroteit.common.model.ElectionCategory;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "contender")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Contender {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name = "uuid", strategy = "uuid4")
    @Setter
    private UUID contenderId;
    private UUID citizenId;
    private int areaId;
    private int electionYear; //Assumption is election will happen at most once in year.
    @Enumerated(EnumType.ORDINAL)
    private ElectionCategory category;
}
