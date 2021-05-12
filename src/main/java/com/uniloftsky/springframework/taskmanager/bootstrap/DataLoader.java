package com.uniloftsky.springframework.taskmanager.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.YearMonth;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Component
public class DataLoader implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.YEAR, 2020);
        calendar.set(Calendar.MONTH, Calendar.MAY);
        System.out.println(calendar);
        YearMonth yearMonth = YearMonth.of(2020, 6);
        System.out.println(yearMonth.lengthOfMonth());
        System.out.println(yearMonth.atDay(1));
        System.out.println(yearMonth.atDay(30));

    }
}
