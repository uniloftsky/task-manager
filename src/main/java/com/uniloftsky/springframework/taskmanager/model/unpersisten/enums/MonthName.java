package com.uniloftsky.springframework.taskmanager.model.unpersisten.enums;

public enum MonthName {

    JANUARY("Январь"),
    FEBRUARY("Февраль"),
    MARCH("Март"),
    APRIL("Апрель"),
    MAY("Май"),
    JUNE("Июнь"),
    JULY("Июль"),
    AUGUST("Август"),
    SEPTEMBER("Сентябрь"),
    OCTOBER("Октябрь"),
    NOVEMBER("Ноябрь"),
    DECEMBER("Декабрь");

    private final String label;

    MonthName(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
