package com.example.willpassidomo.androidcomponents;

import java.util.ArrayList;

/**
 * Created by willpassidomo on 6/6/15.
 */
public class TestData {

    public static ArrayList<FieldValue> getTestData() {
        ArrayList<FieldValue> fields = new ArrayList<FieldValue>();
        fields.add(new FieldValue("First Name",true,false));
        fields.add(new FieldValue("Last Name",true,false));
        fields.add(new FieldValue("Home Town",true,false));
        fields.add(new FieldValue("Favorite Thing",true,false));
        fields.add(new FieldValue("College",false,false));
        fields.add(new FieldValue("Mothers Maiden name",false,false));
        fields.add(new FieldValue("Best Friend",false,false));
        fields.add(new FieldValue("Favorite Book",false,false));
        fields.add(new FieldValue("Street Name",false,false));
        return fields;
    }
}
