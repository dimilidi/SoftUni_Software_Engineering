package com.example.football.models.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public enum PositionTypeEnum {
    ATT(0), MID(1), DEF(2);

    private final int value;
}
