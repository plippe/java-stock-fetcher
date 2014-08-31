package com.secret.model.ranking;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Rank {
    private Date valueDate;
    private Integer friendsRank;
    private Integer dailyRank;
    private Integer weeklyRank;
    private Integer monthlyRank;
    private Integer yearlyRank;
    private Integer globalRank;
}
