package org.ubwroteit.idea.service;

import org.ubwroteit.idea.model.IdeaEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IdeaService {

     Optional<IdeaEntity> saveIdea(IdeaEntity ideaEntity);

     List<IdeaEntity> getAllIdeas(UUID contenderId);

     void deleteIdea(UUID ideaId);

     IdeaEntity getIdea(UUID ideaId);

     Optional<IdeaEntity> updateIdea(IdeaEntity ideaEntity);

    List<IdeaEntity> findAllIdeas(UUID electionId);

     Boolean isIdeaIdExist(UUID ideaId);
}
