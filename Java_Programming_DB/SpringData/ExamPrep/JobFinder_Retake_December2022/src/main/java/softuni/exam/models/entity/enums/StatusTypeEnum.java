package softuni.exam.models.entity.enums;

import lombok.Getter;

@Getter
public enum StatusTypeEnum {
    UNEMPLOYED(0, "unemployed"),
    EMPLOYED(1, "employed"),
    FREELANCER(2, "freelancer");

    private final int dbValue;
    private final String jsonValue;

    StatusTypeEnum(int dbValue, String jsonValue) {
        this.dbValue = dbValue;
        this.jsonValue = jsonValue;
    }

    // Convert from JSON string to Enum
    public static StatusTypeEnum fromJsonValue(String jsonValue) {
        for (StatusTypeEnum status : values()) {
            if (status.jsonValue.equalsIgnoreCase(jsonValue)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown JSON value: " + jsonValue);
    }

    // Convert from DB integer to Enum
    public static StatusTypeEnum fromDbValue(int dbValue) {
        for (StatusTypeEnum status : values()) {
            if (status.dbValue == dbValue) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown DB value: " + dbValue);
    }

    // Convert Enum to DB integer
    public int toDbValue() {
        return dbValue;
    }

    // Convert Enum to JSON string
    public String toJsonValue() {
        return jsonValue;
    }
}
