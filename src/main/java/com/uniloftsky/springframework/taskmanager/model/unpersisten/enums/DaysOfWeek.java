package com.uniloftsky.springframework.taskmanager.model.unpersisten.enums;

public enum DaysOfWeek {

    MONDAY("Понедельник"),
    TUESDAY("Вторник"),
    WEDNESDAY("Среда"),
    THURSDAY("Четверг"),
    FRIDAY("Пятница"),
    SATURDAY("Суббота"),
    SUNDAY("Воскресенье");

    private final String label;

    DaysOfWeek(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
