package org.ubwroteit.common.exception;

import java.util.UUID;

public class ResourceExistException extends RuntimeException {

    public ResourceExistException(String message) {
        super(message);
    }
}
