package com.example.willpassidomo.androidcomponents;

/**
 * Created by willpassidomo on 6/5/15.
 */
public class FieldValue implements Comparable {
    private String field;
    /// TODO
    // This field should not be string, should be generic....so if it is a number value. it will
    //check it properly...make it so the correct keyboard configuration appers when the field is
    //selected
    private String value;
    private boolean required = false;

    public FieldValue(String field, String value, boolean required) {
        this.field = field;
        this.value = value;
        this.required = required;
    }

    public FieldValue(String field, boolean required) {
        this.field = field;
        this.required = required;
    }

    public FieldValue(String field, String value) {
        this.field = field;
        this.value = value;
    }

    public FieldValue(String field) {
        this.field = field;
        this.value = "";
    }

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

    public boolean getRequired() {
        return this.required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    @Override
    public boolean equals(Object fv) {
        if (fv instanceof FieldValue) {
            FieldValue fvv = (FieldValue)fv;
            return (this.field.equals(fvv.getField()) && value.equals(fvv.getValue()));
        }
        return false;
    }

    @Override
    public int compareTo(Object fv) {
        if (fv instanceof FieldValue) {
            FieldValue fvv = (FieldValue)fv;
            return this.getField().compareTo(fvv.getField());
        } else {
            return 1;}
    }

    @Override
    public int hashCode() {
        return field.hashCode();
    }

    @Override
    public String toString() {
        return field;
    }
}

