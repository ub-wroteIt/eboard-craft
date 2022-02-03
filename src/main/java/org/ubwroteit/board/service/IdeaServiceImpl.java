package org.ubwroteit.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.ubwroteit.board.model.IdeaEntity;
import org.ubwroteit.board.repository.IdeaRepository;

import java.util.List;
import java.util.UUID;

@Service
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

    @Override
    public List<IdeaEntity> getAllIdeas(UUID contenderId) {
        return ideaRepository.findAllByContenderId(contenderId);
    }

    @Override
    public void deleteIdea(UUID ideaId){
        ideaRepository.deleteById(ideaId);
    }

    @Override
    public IdeaEntity updateIdea(IdeaEntity ideaEntity) {
        return ideaRepository.save(ideaEntity);
    }

    private boolean isContenderHasMaximumIdeas(UUID contenderId){
       return ideaRepository.countByContenderId(contenderId) == noOfIdeasAllowed;
    }
}
