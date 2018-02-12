package com.ef.View.Model;

import java.util.Date;

import com.ef.Util.Duration;

public class ArgumentsVM {
	
	private final Date startDate;

    private final Duration duration;

    private final Integer threshold;

    private final Date endDate;

    public ArgumentsVM(
            Date startDate, Duration duration, Integer threshold, Date endDate
    ) {
        this.startDate = startDate;
        this.duration = duration;
        this.threshold = threshold;
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Duration getDuration() {
        return duration;
    }

    public Integer getThreshold() {
        return threshold;
    }

    public Date getEndDate(){

        return endDate;
    }

}
