package com.example.willpassidomo.androidcomponents;

/**
 * Created by willpassidomo on 6/5/15.
 */
public class FieldValue {
    private String field;
    /// TODO
    // This field should not be string, should be generic....so if it is a number value. it will
    //check it properly...make it so the correct keyboard configuration appers when the field is
    //selected
    private String value;

    public void setField(String field) {
        this.field = field;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getField() {
        return this.field;
    }

    public String getValue() {
        return this.value;
    }
}
