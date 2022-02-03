package org.ubwroteit.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ubwroteit.board.model.IdeaEntity;
import org.ubwroteit.board.service.IdeaService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("idea")
public class IdeaController {

    @Autowired
    IdeaService ideaService;

    @GetMapping("{contenderId}")
    public List<IdeaEntity> getAllIdeas(@PathVariable UUID contenderId){
        return ideaService.getAllIdeas(contenderId);
    }

    @PutMapping
    public IdeaEntity updateIdea(@RequestBody IdeaEntity ideaEntity){
        return ideaService.updateIdea(ideaEntity);
    }

    @PostMapping
    public IdeaEntity saveIdea(@RequestBody IdeaEntity ideaEntity){
        return ideaService.saveIdea(ideaEntity);
    }

    @DeleteMapping
    public void deleteIdea(@PathVariable UUID ideaId){
        ideaService.deleteIdea(ideaId);
    }
}
