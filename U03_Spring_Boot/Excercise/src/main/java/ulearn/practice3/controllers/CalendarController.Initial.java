package ulearn.practice3.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static java.time.temporal.ChronoUnit.*;


@Controller
@ResponseBody
@RequestMapping("/calendar")
public class CalendarController
{
    @PostMapping("/set/{date}")
    public void setDate(@PathVariable("date") String date)
    {

    }

    @GetMapping("/get/{days}")
    public String getDate(@PathVariable("days") int days)
    {
        return "1.1.2000";
    }

    @GetMapping("/get/difference/{date}")
    public String getDifferenceBetweenDates(@PathVariable("date") String date)
    {
        return "0";
    }

    @GetMapping("/reset")
    public void resetModel()
    {

    }

    private class ModelSubstitute
    {

    }
}
