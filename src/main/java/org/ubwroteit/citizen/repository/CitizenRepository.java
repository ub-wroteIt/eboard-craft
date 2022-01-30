package org.ubwroteit.citizen.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.ubwroteit.citizen.entity.Citizen;

import java.util.UUID;

@Repository
public interface CitizenRepository extends CrudRepository<Citizen, UUID> {
}
