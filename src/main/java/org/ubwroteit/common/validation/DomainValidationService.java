package org.ubwroteit.common.validation;

public interface DomainValidationService<T> {
    void validate(T t);
}
