package org.lididimi.jsonparsing._02CarDealer.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DiscountType {
    ZERO_PERCENT(0),
    FIVE_PERCENT(0.05),
    TEN_PERCENT(0.10),
    FIFTEEN_PERCENT(0.15),
    TWENTY_PERCENT(0.20),
    THIRTY_PERCENT(0.30),
    FORTY_PERCENT(0.40),
    FIFTY_PERCENT(0.50);

    private final double percentage;
}

