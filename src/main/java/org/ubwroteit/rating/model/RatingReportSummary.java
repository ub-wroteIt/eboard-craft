package org.ubwroteit.rating.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RatingReportSummary {
    private double average;
    private UUID ideaId;
    private UUID contenderId;
}
