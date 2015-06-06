package com.example.willpassidomo.androidcomponents;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.ArrayList;

/**
 * Created by willpassidomo on 6/5/15.
 */
public class SoftQuestionListAdapter implements ListAdapter {

    private Context context;
    private ArrayList<FieldValue> in;
    private ArrayList<FieldValue> out;

    public SoftQuestionListAdapter (Context context, ArrayList<FieldValue> in, ArrayList<FieldValue> out) {
        this.context = context;
        this.in = in;
        this.out = out;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return in.size() + 1;
    }

    @Override
    public Object getItem(int position) {
        if (position >= in.size()) {
            return new String("last position");
        } else {
            return in.get(position);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        boolean controlBlock = (position >= in.size());

        if (true){///iew == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (controlBlock) {
                view = infalInflater.inflate(R.layout.soft_q_control_block, null);
                return controlBlockView(view);
            } else {
                view = infalInflater.inflate(R.layout.soft_q_field_val, null);
                return listItemView(view, position);
            }
        }
        return view;
    }

    private View controlBlockView(View view) {
        final ViewSwitcher vs = (ViewSwitcher)view.findViewById(R.id.soft_q_view_switcher);
        Button addQ = (Button)view.findViewById(R.id.soft_q_add_q);
        final Spinner newQuestion = (Spinner)view.findViewById(R.id.soft_q_selector);

        addQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vs.showNext();
            }
        });
       //Need to be the string values of the FieldValue
        ArrayAdapter<FieldValue> sa = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, out);
        newQuestion.setAdapter(sa);
        newQuestion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                vs.showNext();
                newFieldValueSelected(newQuestion.getSelectedItemPosition(), (FieldValue) newQuestion.getSelectedItem());
                //TODO
                //refresh/rerender view
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return view;
    }

    private View listItemView(View view, int position) {
        FieldValue fv = in.get(position);

        TextView field = (TextView)view.findViewById(R.id.soft_q_field);
        EditText value = (EditText)view.findViewById(R.id.soft_q_value);

        field.setText(fv.getField());
        value.setText(fv.getValue());

        return view;
    }

    private void newFieldValueSelected(int position, FieldValue newQuestion) {
        FieldValue newQ = out.get(position);
        //a, probably redundent, check to make sure we are removing the correct item
        if (newQ.equals(newQuestion)) {
            out.remove(position);
            in.add(in.size(), newQuestion);
        } else {
            out.remove(newQuestion);
            in.add(in.size(), newQuestion);
            }
    }

    private int getIndex (ArrayList<FieldValue> li, FieldValue v) {
        for (int i = 0; i < li.size(); i++) {
            if (li.get(i).equals(v)) {
                return i;
            }
        }
        return -1;
    }

    public ArrayList<FieldValue>[] getFieldValues() {
        ArrayList<FieldValue>[] li = new ArrayList[2];
        li[0] = in;
        li[1] = out;
        return li;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
