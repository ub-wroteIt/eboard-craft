package org.ubwroteit.contender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ubwroteit.contender.model.Contender;
import org.ubwroteit.contender.service.ContenderService;

import java.util.UUID;

@RestController
@RequestMapping("contender")
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

}
