package org.ubwroteit.rating.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ubwroteit.rating.model.RatingEntity;
import org.ubwroteit.rating.model.RatingId;

import java.util.List;
import java.util.UUID;

@Repository
public interface RatingRepository extends JpaRepository<RatingEntity, RatingId> {
    List<RatingEntity> findByContenderId(UUID contenderId);
    List<RatingEntity> findByCitizenId(UUID contenderId);
    List<RatingEntity> findByIdeaId(UUID contenderId);

}
