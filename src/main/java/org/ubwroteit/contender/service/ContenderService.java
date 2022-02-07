package org.ubwroteit.contender.service;


import org.ubwroteit.common.model.ElectionCategory;
import org.ubwroteit.contender.model.Contender;

import java.util.List;
import java.util.UUID;

public interface ContenderService {

    Contender saveContender(Contender contender);

    Contender getContender(UUID contenderId);

    void deleteContender(UUID contenderId);

    List<Contender> findAllContenders(UUID electionId, int areaId, ElectionCategory electionCategory);

    Boolean isContenderIdExist(UUID contenderId);
}
