package edu.qc.seclass.glm;

import java.io.Serializable;

public class ReminderType implements Serializable {

    private String type;

    public ReminderType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals (Object o) {
        return type.equalsIgnoreCase(((ReminderType)o).getType());
    }
}
