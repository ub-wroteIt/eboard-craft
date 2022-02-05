package org.ubwroteit.board.service;

import org.ubwroteit.board.model.IdeaEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IdeaService {

     Optional<IdeaEntity> saveIdea(IdeaEntity ideaEntity);

     List<IdeaEntity> getAllIdeas(UUID contenderId);

     void deleteIdea(UUID ideaId);

     IdeaEntity getIdea(UUID ideaId);

     Optional<IdeaEntity> updateIdea(IdeaEntity ideaEntity);
}
