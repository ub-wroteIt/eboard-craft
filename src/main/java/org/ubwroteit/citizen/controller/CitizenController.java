package org.ubwroteit.citizen.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ubwroteit.citizen.entity.Citizen;
import org.ubwroteit.citizen.service.CitizenService;
import org.ubwroteit.common.exception.ResourceExistException;
import org.ubwroteit.common.exception.ResourceNotFoundException;

import java.util.UUID;

@RestController
@RequestMapping("citizen")
public class CitizenController {

    @Autowired
    private CitizenService citizenService;

    @Operation(summary = "Get a Citizen by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Citizen found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Citizen.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Citizen not found",
                    content = @Content)})
    @GetMapping("{citizenId}")
    public Citizen findCitizenById(@Parameter(description = "id of citizen to be searched") @PathVariable UUID citizenId) {
        return citizenService.getCitizenById(citizenId);
    }

    @PostMapping()
    public Citizen addCitizen(@RequestBody Citizen citizen) {
        UUID citizenId = citizen.getId();
        if (citizenId != null) {
            boolean citizenExist = citizenService.isCitizenExist(citizenId);
            if (citizenExist) {
                throw new ResourceExistException("Citizen already Exist id=" + citizenId);
            }
        }
        return citizenService.saveCitizen(citizen);
    }

    @DeleteMapping("{citizenId}")
    public void deleteCitizen(@PathVariable UUID citizenId) {
        citizenService.deleteCitizen(citizenId);
    }

    @PutMapping("{citizenId}")
    public Citizen updateCitizen(@RequestBody Citizen citizen, @PathVariable UUID citizenId) {
        if (!citizenService.isCitizenExist(citizenId)) {
            throw new ResourceNotFoundException("Citizen Id provided for update does not exist");
        }
        citizen.setId(citizenId);
        return citizenService.saveCitizen(citizen);
    }
}
