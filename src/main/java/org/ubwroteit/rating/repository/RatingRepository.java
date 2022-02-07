package org.ubwroteit.rating.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.ubwroteit.contender.model.Contender;
import org.ubwroteit.rating.model.RatingEntity;
import org.ubwroteit.rating.model.RatingId;
import org.ubwroteit.rating.model.RatingReportSummary;

import java.util.List;
import java.util.UUID;

@Repository
public interface RatingRepository extends JpaRepository<RatingEntity, RatingId> {
    List<RatingEntity> findByContenderId(UUID contenderId);
    List<RatingEntity> findByCitizenId(UUID contenderId);
    List<RatingEntity> findByIdeaId(UUID contenderId);

    @Query(value = "SELECT AVG(rating), contender_Id, Idea_Id from rating group by contender_Id, Idea_Id")
    List<RatingReportSummary> findGroupByReport(List<Contender> contenderList);
}
