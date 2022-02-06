package org.ubwroteit.citizen.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CitizenContactDTO {
    private UUID id;
    private String emailId;
}
