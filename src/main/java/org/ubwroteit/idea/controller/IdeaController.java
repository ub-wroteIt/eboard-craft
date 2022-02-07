package org.ubwroteit.idea.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ubwroteit.idea.model.IdeaEntity;
import org.ubwroteit.idea.service.IdeaService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("idea")
@Tag(name = "Idea service", description = "the idea API to manage contender ideas")
public class IdeaController {

    @Autowired
    IdeaService ideaService;

    @GetMapping("{contenderId}")
    public List<IdeaEntity> getAllIdeas(@PathVariable UUID contenderId){
        return ideaService.getAllIdeas(contenderId);
    }

    @GetMapping("/search/{electionId}")
    public List<IdeaEntity> findAllIdeas(@PathVariable UUID electionId){
        return ideaService.findAllIdeas(electionId);
    }

    @GetMapping("exist/{ideaId}")
    public Boolean isIdeaIdExist(@PathVariable UUID ideaId){
        return ideaService.isIdeaIdExist(ideaId);
    }


    @PutMapping
    public ResponseEntity<?> updateIdea(@RequestBody IdeaEntity ideaEntity){
        Optional<IdeaEntity> upsertedIdeaEntity = ideaService.updateIdea(ideaEntity);
        return getResponseEntity(upsertedIdeaEntity);
    }

    @PostMapping
    public ResponseEntity<?> saveIdea(@RequestBody IdeaEntity ideaEntity){
        Optional<IdeaEntity> savedIdeaEntity = ideaService.saveIdea(ideaEntity);
        return getResponseEntity(savedIdeaEntity);
    }

    @DeleteMapping
    public void deleteIdea(@PathVariable UUID ideaId){
        ideaService.deleteIdea(ideaId);
    }


    private ResponseEntity<?> getResponseEntity(Optional<?> savedIdeaEntity) {
        if(savedIdeaEntity.isPresent()){
            return new ResponseEntity<>(savedIdeaEntity.get(), HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("Idea Max Limit Reached", HttpStatus.BAD_REQUEST);
        }
    }
}
