package com.example.willpassidomo.androidcomponents;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity implements SoftQuestionListAdapter.DataListener {
//    private ArrayList<FieldValue> fvs;
//    private SoftQuestionListAdapter sqla;
//    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        listView = (ListView)findViewById(R.id.main_activity_list_view);
//        fvs = TestData.getTestData();
//        sqla = new SoftQuestionListAdapter(this,fvs);
//        listView.setAdapter(sqla);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.the_container, SoftQuestionsFragment.newInstance(this, getTestData()));
        ft.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public void onPause() {
//        super.onPause();
//
//        fvs = sqla.getFieldValues();
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        sqla = new SoftQuestionListAdapter(this, fvs);
//        listView.setAdapter(sqla);
//    }


    public static ArrayList<FieldValue> getTestData() {
        ArrayList<FieldValue> fields = new ArrayList<FieldValue>();
        fields.add(new FieldValue("First Name",true,true));
        fields.add(new FieldValue("Last Name",true,true));
        fields.add(new FieldValue("Home Town",true,false));
        fields.add(new FieldValue("Favorite Thing",true,false));
        fields.add(new FieldValue("College",false,false));
        fields.add(new FieldValue("Mothers Maiden name",false,false));
        fields.add(new FieldValue("Best Friend", false, false));
        fields.add(new FieldValue("Favorite Book",false,false));
        fields.add(new FieldValue("Street Name", false, false));
        return fields;
    }

    @Override
    public void setFieldValueData(List<FieldValue> fvs) {
        Log.i("got data", "");
    }
}
}
