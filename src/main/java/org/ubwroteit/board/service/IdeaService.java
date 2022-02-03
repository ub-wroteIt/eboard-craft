package org.ubwroteit.board.service;

import org.ubwroteit.board.model.IdeaEntity;

import java.util.List;
import java.util.UUID;

public interface IdeaService {

     IdeaEntity saveIdea(IdeaEntity ideaEntity);

     List<IdeaEntity> getAllIdeas(UUID contenderId);

     void deleteIdea(UUID ideaId);

     IdeaEntity updateIdea(IdeaEntity ideaEntity);
}
