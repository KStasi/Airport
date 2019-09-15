package dao;

import java.sql.Date;
import java.time.LocalDate;

public class RandomDates {
    public static int createRandomIntBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

    public static Date createRandomDate(int startYear, int endYear) {
        int day = createRandomIntBetween(1, 28);
        int month = createRandomIntBetween(1, 12);
        int year = createRandomIntBetween(startYear, endYear);
        return Date.valueOf(LocalDate.of(year, month, day));
    }
}
