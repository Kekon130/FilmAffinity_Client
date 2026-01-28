package es.uah.filmAffinityClient.service.datetime;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class DateTimeFormater implements IDateTimeFormater {
    @Override
    public String formatLocalDate(LocalDate date, String format) {
        String formatedDate = "";
        if (date != null) {
            DateTimeFormatter dateTimeFormater = DateTimeFormatter.ofPattern(format);
            formatedDate = date.format(dateTimeFormater);
        }
        return formatedDate;
    }
}
