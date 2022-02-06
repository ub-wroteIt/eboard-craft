package org.ubwroteit.citizen.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ubwroteit.citizen.model.CitizenEntity;
import org.ubwroteit.citizen.service.CitizenService;
import org.ubwroteit.common.exception.ResourceExistException;
import org.ubwroteit.common.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Map;
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
                            schema = @Schema(implementation = CitizenEntity.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Citizen not found",
                    content = @Content)})
    @GetMapping("{citizenId}")
    public CitizenEntity findCitizenById(@Parameter(description = "id of citizen to be searched") @PathVariable UUID citizenId) {
        return citizenService.getCitizenById(citizenId);
    }

    @PostMapping("batch/contacts")
    public Map<UUID, String> getCitizensWithEmail(@RequestBody List<UUID> citizenIds){
        return citizenService.getCitizenEmails(citizenIds);
    }

    @PostMapping()
    public CitizenEntity addCitizen(@RequestBody CitizenEntity citizenEntity) {
        UUID citizenId = citizenEntity.getId();
        if (citizenId != null) {
            boolean citizenExist = citizenService.isCitizenExist(citizenId);
            if (citizenExist) {
                throw new ResourceExistException("Citizen already Exist id=" + citizenId);
            }
        }
        return citizenService.saveCitizen(citizenEntity);
    }

    @PutMapping("{citizenId}")
    public CitizenEntity updateCitizen(@RequestBody CitizenEntity citizenEntity, @PathVariable UUID citizenId) {
        if (!citizenService.isCitizenExist(citizenId)) {
            throw new ResourceNotFoundException("Citizen Id provided for update does not exist");
        }
        citizenEntity.setId(citizenId);
        return citizenService.saveCitizen(citizenEntity);
    }

    @DeleteMapping("{citizenId}")
    public void deleteCitizen(@PathVariable UUID citizenId) {
        citizenService.deleteCitizen(citizenId);
    }

}
