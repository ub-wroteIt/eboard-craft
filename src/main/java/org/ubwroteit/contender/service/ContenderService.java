package org.ubwroteit.contender.service;


import org.ubwroteit.contender.model.Contender;

import java.util.UUID;

public interface ContenderService {

    Contender saveContender(Contender contender);

    Contender getContender(UUID contenderId);

    void deleteContender(UUID contenderId);
}
