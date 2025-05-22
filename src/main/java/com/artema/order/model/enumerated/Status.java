package com.artema.order.model.enumerated;

import lombok.Getter;
import com.fasterxml.jackson.annotation.JsonValue;

@Getter
public enum Status {
    NEW("Новый"),
    FOR_REVIEW("На рассмотрении"),
    IN_PROGRESS("В работе"),
    REJECTED("Отклонен"),
    SENT_REQUESTED("Отправлен заказчику"),
    SUSPENDED("Приостановлен"),
    COMPLETED("Выполнен"),
    RESUMED("Возобновлен"),
    NONE("NONE");

    @JsonValue
    private final String value;

    Status(String value) {
        this.value = value;
    }

    public static Status getType(String value) {
        if (value == null) return NONE;
        for (Status c : values()) {
            if (c.value.equalsIgnoreCase(value))
                return c;
        }
        return NONE;
    }
}
