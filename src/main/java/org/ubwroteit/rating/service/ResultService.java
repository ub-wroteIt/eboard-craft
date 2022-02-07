package org.ubwroteit.rating.service;

import org.ubwroteit.common.model.ElectionCategory;

import java.util.List;
import java.util.UUID;

public interface ResultService {
    List<UUID> computeResult(UUID electionId, int areaId, ElectionCategory electionCategory);
}
