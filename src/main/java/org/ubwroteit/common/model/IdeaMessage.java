package org.ubwroteit.common.model;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class IdeaMessage implements Serializable {
    private UUID ideaId;
    private UUID contenderId;
    private String title;
    private String description;
    private long timestampInMilliseconds;
}
