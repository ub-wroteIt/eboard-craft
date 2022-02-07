package org.ubwroteit.rating.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.ubwroteit.common.model.ElectionCategory;
import org.ubwroteit.contender.model.Contender;
import org.ubwroteit.rating.model.RatingReportSummary;
import org.ubwroteit.rating.repository.RatingRepository;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.summingDouble;
import static java.util.stream.Collectors.summingInt;

@Service
public class ResultServiceImpl implements ResultService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    RatingRepository ratingRepository;

    @Override
    public List<UUID> computeResult(UUID electionId, int areaId, ElectionCategory electionCategory) {
        List<Contender> contenderList = restTemplate.getForObject("", List.class);
        List<RatingReportSummary> groupByReport = ratingRepository.findGroupByReport(contenderList);

        Map<UUID, Double> leaderBoard = groupByReport.stream().collect(Collectors.groupingBy(RatingReportSummary::getContenderId, summingDouble(RatingReportSummary::getAverage)));
        Optional<Map.Entry<UUID, Double>> max = leaderBoard.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue));

        return max.get().getValue();
    }
}
