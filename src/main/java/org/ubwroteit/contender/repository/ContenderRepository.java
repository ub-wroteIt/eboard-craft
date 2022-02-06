package org.ubwroteit.contender.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.ubwroteit.common.model.ElectionCategory;
import org.ubwroteit.contender.model.Contender;

import java.util.List;
import java.util.UUID;

@Repository
public interface ContenderRepository extends CrudRepository<Contender, UUID> {

    List<Contender> findAllByElectionIdAndAreaIdAndCategory(UUID electionId, int areaId, ElectionCategory electionCategory);
}
