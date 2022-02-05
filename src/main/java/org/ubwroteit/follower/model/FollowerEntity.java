package org.ubwroteit.follower.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "follower")
@SQLDelete(sql = "UPDATE follower SET status = DELETED WHERE sourceId=? and destinationId=?")
@Where(clause = " status = 'POSITIVE' OR status = 'NEGATIVE' ")
@IdClass(FollowerId.class)
public class FollowerEntity {
    @Id
    private UUID sourceId;
    @Id
    private UUID destinationId;
    private int position;

    @Enumerated(EnumType.STRING)
    private FollowerStatus status;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
