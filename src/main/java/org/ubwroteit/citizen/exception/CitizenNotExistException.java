package org.ubwroteit.citizen.exception;

import java.util.UUID;

public class CitizenNotExistException extends RuntimeException {
    public CitizenNotExistException(UUID uuid) {
        super("Citizen Id not exists "+uuid);
    }

    public CitizenNotExistException(String message) {
        super(message);
    }
}
