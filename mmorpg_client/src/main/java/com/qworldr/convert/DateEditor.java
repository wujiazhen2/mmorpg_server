package com.qworldr.convert;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateEditor  extends PropertyEditorSupport {

    public String  getFormat(){
        return "yyyy-MM-dd";
    }
    public DateFormat getDateFormat(){
        return new SimpleDateFormat(getFormat());
    }
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        DateFormat dateFormat = new SimpleDateFormat(getFormat());
        try {
            setValue(dateFormat.parse(text));
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public String getAsText() {
        Date value = (Date) getValue();
        return (value != null ? getDateFormat().format(value) : "");
    }

}
