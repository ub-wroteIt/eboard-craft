package org.ubwroteit.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ubwroteit.board.model.RatingEntity;
import org.ubwroteit.board.model.RatingId;

import java.util.List;
import java.util.UUID;

@Repository
public interface RatingRepository extends JpaRepository<RatingEntity, RatingId> {
    List<RatingEntity> findByContenderId(UUID contenderId);
    List<RatingEntity> findByCitizenId(UUID contenderId);
    List<RatingEntity> findByIdeaId(UUID contenderId);

}
