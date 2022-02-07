package org.ubwroteit.rating.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.ubwroteit.common.model.ElectionCategory;
import org.ubwroteit.contender.model.Contender;
import org.ubwroteit.rating.service.ResultService;

import java.util.List;
import java.util.UUID;

@RestController
public class ResultController {

    @Autowired
    private ResultService resultService;

    @GetMapping("result/{electionId}")
    public List<UUID> getContenders(@PathVariable UUID electionId, @RequestParam int areaId, @RequestParam ElectionCategory electionCategory){
        return resultService.computeResult(electionId, areaId, electionCategory);
    }
}
