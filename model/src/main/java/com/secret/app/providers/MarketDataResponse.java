package com.secret.app.providers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MarketDataResponse {
    private String id; //Symbol	Symbol	s0
    private Date valueDate; //Ou utiliser joda time (1.8)
    private Double last; //LastTradePriceOnly	Last Trade Price Only	l1
    private Double change; //ChangeRealtime	Change (Realtime)	c6
    private Double changePercent; //ChangeInPercentRealtime	Change In Percent (Realtime)	k2 => la seconde partie de la string de retour
}
