package com.saviorru.comsserver.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateDispather {

    private LocalDateTime startDate;
    private Integer allowedHourStart;
    private Integer allowedHourEnd;
    private Integer dateHourOffset;

    public DateDispather(LocalDateTime startDate, Integer allowedHourStart, Integer allowedHourEnd, Integer dateHourOffset) throws Exception
    {
        if ((startDate == null) || (allowedHourStart == null) || (allowedHourEnd == null) || (dateHourOffset == null))
            throw new NullPointerException();
        if (dateHourOffset < 0) throw new Exception("Time offset cannot be below zero");
        if (allowedHourEnd <= allowedHourStart) throw new Exception("End of allowed time cannot be lower or equal to start time");
        if ((allowedHourEnd > 23) || (allowedHourEnd < 0) || (allowedHourStart > 23) || (allowedHourStart < 0))
            throw new Exception("Bad hour values");
        this.startDate = startDate;
        this.allowedHourStart = allowedHourStart;
        this.allowedHourEnd = allowedHourEnd;
        this.dateHourOffset = dateHourOffset;
    }

    public LocalDateTime getNextDate()
    {
        LocalDateTime currentDate = LocalDateTime.now();
        if (currentDate.isBefore(this.startDate))
        {
            currentDate = this.startDate;
        }
        currentDate = currentDate.plusHours(this.dateHourOffset);
        if (currentDate.getHour() < allowedHourStart)
        {
            currentDate = currentDate.withHour(allowedHourStart);
            return currentDate;
        }
        if (currentDate.getHour() > allowedHourStart)
        {
            currentDate = currentDate.plusDays(1);
            currentDate = currentDate.withHour(allowedHourStart);
            return currentDate;
        }
        return currentDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public Integer getAllowedHourStart() {
        return allowedHourStart;
    }

    public Integer getAllowedHourEnd() {
        return allowedHourEnd;
    }
}
