package org.ubwroteit.rating.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "rating")
@SQLDelete(sql = "UPDATE rating SET deleted = true WHERE ratingId=? and citizenId=?")
@Where(clause = "deleted=false")
@IdClass(RatingId.class)
public class RatingEntity {
    @Id
    private UUID ideaId;
    @Id
    private UUID citizenId;
    @Id
    private UUID contenderId;
    private UUID electionId;
    @Min(0)
    @Max(10)
    private float rating;

    @Column(columnDefinition = "boolean default false")
    private boolean deleted= false;
    @CreationTimestamp
    @Column(nullable = false, updatable = false )
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
