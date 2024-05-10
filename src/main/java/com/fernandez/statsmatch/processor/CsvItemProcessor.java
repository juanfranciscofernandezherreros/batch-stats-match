package com.fernandez.statsmatch.processor;

import com.fernandez.statsmatch.model.dto.StatsMatchBean;
import com.fernandez.statsmatch.model.entity.StatsMatch;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class CsvItemProcessor implements ItemProcessor<StatsMatchBean, StatsMatch> {

    @Override
    public StatsMatch process(StatsMatchBean item) throws Exception {
        StatsMatch statsMatch = new StatsMatch();
        statsMatch.setMatchId(item.getMatchId());
        statsMatch.setCategoryName(item.getCategoryName());
        statsMatch.setAwayValue(item.getAwayValue());
        statsMatch.setHomeValue(item.getHomeValue());
        statsMatch.setCuarto(item.getCuarto());
        return statsMatch;
    }
}
