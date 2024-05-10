package com.fernandez.statsmatch.writer;

import com.fernandez.statsmatch.model.entity.StatsMatch;
import com.fernandez.statsmatch.repository.StatsMatchRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsoleItemWriter implements ItemWriter<StatsMatch> {

    private final StatsMatchRepository statsMatchRepository;

    @Autowired
    public ConsoleItemWriter(StatsMatchRepository statsMatchRepository) {
        this.statsMatchRepository = statsMatchRepository;
    }

    @Override
    public void write(List<? extends StatsMatch> items) throws Exception {
        statsMatchRepository.saveAll(items);
    }
}