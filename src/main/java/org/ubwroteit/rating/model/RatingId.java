package org.ubwroteit.rating.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
@NoArgsConstructor
public class RatingId implements Serializable {
    private UUID citizenId;
    private UUID ideaId;
    private UUID contenderId;
}
