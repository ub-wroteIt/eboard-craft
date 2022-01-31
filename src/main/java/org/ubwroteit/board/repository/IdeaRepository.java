package org.ubwroteit.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ubwroteit.board.model.IdeaEntity;

import java.util.UUID;

public interface IdeaRepository extends JpaRepository<IdeaEntity, UUID> {

    int countByContenderId(UUID contenderId);
}
