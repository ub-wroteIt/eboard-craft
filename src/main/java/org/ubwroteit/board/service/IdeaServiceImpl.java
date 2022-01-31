package org.ubwroteit.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.ubwroteit.board.model.IdeaEntity;
import org.ubwroteit.board.repository.IdeaRepository;

import java.util.UUID;

public class IdeaServiceImpl implements IdeaService{

    @Autowired
    private IdeaRepository ideaRepository;

    @Value("${idea.cap:3}")
    private int noOfIdeasAllowed;

    @Override
    public IdeaEntity saveIdea(IdeaEntity ideaEntity) {
         if (!isContenderHasMaximumIdeas(ideaEntity.getContenderId())){
             return ideaRepository.save(ideaEntity);
         }
        return null;
    }

    public boolean isContenderHasMaximumIdeas(UUID contenderId){
       return ideaRepository.countByContenderId(contenderId) == noOfIdeasAllowed;
    }
}
