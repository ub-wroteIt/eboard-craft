package org.ubwroteit.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.ubwroteit.board.model.IdeaEntity;
import org.ubwroteit.board.repository.IdeaRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class IdeaServiceImpl implements IdeaService{

    @Autowired
    private IdeaRepository ideaRepository;

    @Value("${idea.cap:3}")
    private int noOfIdeasAllowed;

    @Override
    public Optional<IdeaEntity> saveIdea(IdeaEntity ideaEntity) {
         if (!isContenderHasMaximumIdeas(ideaEntity.getContenderId())){
             IdeaEntity savedIdeaEntity = ideaRepository.save(ideaEntity);
             return Optional.of(savedIdeaEntity);
         }
        return Optional.empty();
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
    public IdeaEntity getIdea(UUID ideaId) {
        return ideaRepository.getById(ideaId);
    }

    @Override
    public Optional<IdeaEntity> updateIdea(IdeaEntity ideaEntity) {
        if(!Objects.isNull(getIdea(ideaEntity.getId()))){
            return Optional.of(ideaRepository.save(ideaEntity));
        }
        return saveIdea(ideaEntity);
    }

    private boolean isContenderHasMaximumIdeas(UUID contenderId){
       return ideaRepository.countByContenderId(contenderId) == noOfIdeasAllowed;
    }
}
