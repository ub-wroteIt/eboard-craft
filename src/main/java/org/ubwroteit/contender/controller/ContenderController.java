package org.ubwroteit.contender.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ubwroteit.common.model.ElectionCategory;
import org.ubwroteit.contender.model.Contender;
import org.ubwroteit.contender.service.ContenderService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("contender")
@Tag(name = "Contender service", description = "the contender API to manage contenders")
public class ContenderController {

    @Autowired
    private ContenderService contenderService;

    @GetMapping("/{contenderId}")
    public Contender getContender(@PathVariable UUID contenderId){
        return contenderService.getContender(contenderId);
    }

    @PostMapping
    public Contender saveContender(@RequestBody Contender contender){
        return contenderService.saveContender(contender);
    }

    @DeleteMapping("{contenderId}")
    public void deleteContender(@PathVariable UUID contenderId) {
        contenderService.deleteContender(contenderId);
    }

    @GetMapping("/search/{electionId}")
    public List<Contender> getContenders(@PathVariable UUID electionId, @RequestParam int areaId, @RequestParam ElectionCategory electionCategory){
        return contenderService.findAllContenders(electionId, areaId, electionCategory);
    }
}
