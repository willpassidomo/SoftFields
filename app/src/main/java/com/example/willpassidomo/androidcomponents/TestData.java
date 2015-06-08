package com.example.willpassidomo.androidcomponents;

import java.util.ArrayList;

/**
 * Created by willpassidomo on 6/6/15.
 */
public class TestData {

    public static ArrayList<FieldValue>[] getTestData() {
        ArrayList<FieldValue>[] fv = new ArrayList[2];
        ArrayList<FieldValue> in = new ArrayList<FieldValue>();
        in.add(new FieldValue("First Name"));
        in.add(new FieldValue("Last Name"));
        in.add(new FieldValue("Home Town"));
        in.add(new FieldValue("Favorite Thing"));
        ArrayList<FieldValue> out = new ArrayList<FieldValue>();
        out.add(new FieldValue("College"));
        out.add(new FieldValue("Mothers Maiden name"));
        out.add(new FieldValue("Best Friend"));
        out.add(new FieldValue("Favorite Book"));
        out.add(new FieldValue("Street Name"));
        fv[0]= in;
        fv[1]= out;
        return fv;
    }
}
