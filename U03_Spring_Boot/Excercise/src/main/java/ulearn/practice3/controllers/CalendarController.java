package ulearn.practice3.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.time.temporal.ChronoUnit.*;


@Controller
@ResponseBody
@RequestMapping("/calendar")
public class CalendarController
{
    @PostMapping("/set/{date}")
    public void setDate(@PathVariable("date") String date)
    {
        try
        {
            model.setDate(date);
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                              "Date Invalid. It should be in format dd.mm.yyyy");
        }
    }

    @GetMapping("/get/{days}")
    public String getDate(@PathVariable("days") int days)
    {
        var date = model.getDate(days);
        return String.format("%s.%s.%s",
                             date.getDayOfMonth(),
                             date.getMonth().getValue(),
                             date.getYear());
    }

    @GetMapping("/get/difference/{date}")
    public String getDifferenceBetweenDates(@PathVariable("date") String date)
    {
        try
        {
            return String.valueOf(model.getDifferenceBetweenDates(date));
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                              "Date Invalid. It should be in format dd.mm.yyyy");
        }
    }

    @GetMapping("/reset")
    public void resetModel()
    {
        model = new ModelSubstitute();
    }

    private ModelSubstitute model = new ModelSubstitute();

    private static class ModelSubstitute
    {
        private LocalDate storedDate = LocalDate.of(2000, 1, 1);

        private void setDate(String date) throws Exception
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");
            storedDate = LocalDate.parse(date, formatter);
        }

        public LocalDate getDate(int days)
        {
            return storedDate.plusDays(days);
        }

        public long getDifferenceBetweenDates(String date) throws Exception
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");
            var localDate = LocalDate.parse(date, formatter);
            return DAYS.between(storedDate, localDate);
        }
    }
}
