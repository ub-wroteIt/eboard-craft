package org.ubwroteit.election.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ubwroteit.election.model.ElectionEntity;
import org.ubwroteit.election.service.ElectionService;

import java.util.UUID;

@RestController
@RequestMapping("election")
@Tag(name = "Election service", description = "the election API to manage elections")
public class ElectionController {

    @Autowired
    ElectionService electionService;

    @PostMapping
    public ElectionEntity saveElection(@RequestBody ElectionEntity electionEntity){
        return electionService.saveElection(electionEntity);
    }

    @GetMapping("{electionId}")
    public ElectionEntity getElection(@PathVariable UUID electionId){
        return electionService.getElection(electionId);
    }

    @DeleteMapping("{electionId}")
    public void deleteElection(@PathVariable UUID electionId){
        electionService.deleteElection(electionId);
    }

}
