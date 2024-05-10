package com.fernandez.statsmatch.model.dto;

import lombok.Data;

@Data
public class StatsMatchBean {

    private String matchId;
    private String categoryName;
    private String homeValue;
    private String awayValue;
    private String cuarto;

    public StatsMatchBean() {
    }

    public StatsMatchBean(String matchId, String categoryName, String homeValue, String awayValue,String cuarto) {
        this.matchId = matchId;
        this.categoryName = categoryName;
        this.homeValue = homeValue;
        this.awayValue = awayValue;
        this.cuarto = cuarto;
    }
}
