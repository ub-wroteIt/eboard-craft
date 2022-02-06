package org.ubwroteit.citizen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ubwroteit.citizen.model.CitizenEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface CitizenRepository extends JpaRepository<CitizenEntity, UUID> {

    <T> List<T> findAllCitizenContactDTOByIdIn(List<UUID> citizenIds, Class<T> type);
}
