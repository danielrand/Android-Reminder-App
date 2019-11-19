package edu.qc.seclass.glm;

import java.io.Serializable;
import java.util.Date;

public class Alert implements Serializable {

    private Date alertTime;
    private String message;
    private Repeat repeat;

    public Alert(Date alertTime, String message, Repeat repeat) {
        this.alertTime = alertTime;
        this.message = message;
        this.repeat = repeat;
    }

    public Alert(Date alertTime, String message) {
        this.alertTime = alertTime;
        this.message = message;
        this.repeat = Repeat.NEVER;
    }

    public Date getAlertTime() {
        return alertTime;
    }

    public void setAlertTime(Date alertTime) {
        this.alertTime = alertTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Repeat getRepeat() {
        return repeat;
    }

    public void setRepeat(Repeat repeat) {
        this.repeat = repeat;
    }
}

enum Repeat {
    NEVER, DAILY, WEEKLY, MONTHLY, ANNUALLY
}