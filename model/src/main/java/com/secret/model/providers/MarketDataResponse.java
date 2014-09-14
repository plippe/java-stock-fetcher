package com.secret.model.providers;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Date;

@Getter
@AllArgsConstructor
public class MarketDataResponse implements Serializable {
    private final String id; //Symbol	Symbol	s0
    private final Date valueDate; //Ou utiliser joda time (1.8)
    private final Double last; //LastTradePriceOnly	Last Trade Price Only	l1
    private final Double change; //ChangeRealtime	Change (Realtime)	c6
    private final Double changePercent; //ChangeInPercentRealtime	Change In Percent (Realtime)	k2 => la seconde partie de la string de retour
}
