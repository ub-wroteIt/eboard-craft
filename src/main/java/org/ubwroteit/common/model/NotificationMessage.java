package org.ubwroteit.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationMessage {
    private IdeaMessage ideaMessage;
    private List<UUID> citizenIds;
}

