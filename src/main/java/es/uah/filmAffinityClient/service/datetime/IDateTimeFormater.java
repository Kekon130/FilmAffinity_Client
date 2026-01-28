package es.uah.filmAffinityClient.service.datetime;

import java.time.LocalDate;

public interface IDateTimeFormater {
    String formatLocalDate(LocalDate date, String format);
}
