package com.example.willpassidomo.androidcomponents;

import android.content.Context;
import android.database.DataSetObserver;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
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

    private boolean changed = false;
    private FieldValue fvLead = new FieldValue("select field..");
    private Context context;
    private ArrayList<FieldValue> in;
    private ArrayList<FieldValue> out;
    private TextView sample;

    public SoftQuestionListAdapter (Context context, ArrayList<FieldValue> in, ArrayList<FieldValue> out) {
        this.context = context;
        this.in = in;
        // out.add(0,fvLead);
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
        return true;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        boolean controlBlock = (position >= in.size());
        boolean matches = true;

        if (view != null && !controlBlock) {
        /*gets the "field" value of the view passed in */
            sample = (TextView) view.findViewById(R.id.soft_q_field);
        /*is the corresponding field's view from the "in" array equal
         * to the field value of the view */
            matches = in.get(position).getField().equals(sample.getText());
        } else {
            matches = true;
        }
        /*resets view if the ^ doesnt match */
        if (view == null || (changed && position == in.size() - 1) || !matches) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (controlBlock) {
                view = infalInflater.inflate(R.layout.soft_q_control_block, null);
            } else {
                view = infalInflater.inflate(R.layout.soft_q_field_val, null);
            }
            if (controlBlock) {
                return controlBlockView(view);
            } else {
                return listItemView(view, position);
            }
        }
        return view;
    }

    private View controlBlockView(final View view) {
        final ViewSwitcher vs = (ViewSwitcher)view.findViewById(R.id.soft_q_view_switcher);
        Button addQ = (Button)view.findViewById(R.id.soft_q_add_q);
        final Spinner newQuestion = (Spinner)view.findViewById(R.id.soft_q_selector);

        addQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vs.showNext();
            }
        });
        ArrayAdapter<FieldValue> sa = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, out);
        if(!sa.getItem(0).equals(fvLead)) {
            sa.insert(fvLead, 0);
        }
        newQuestion.setAdapter(sa);
        newQuestion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            boolean click = false;

            @Override
            public void onItemSelected(AdapterView<?> parent, View viewI, int position, long id) {
                if (click) {
                    newFieldValueSelected(newQuestion.getSelectedItemPosition());

                    ///TODO
                    //this makes the view update in the emulator
//                    vs.removeView(vs.getNextView());
//                    vs.addView(getView(in.size() - 1,null,null),0);
//                    vs.showNext();
                    //TODO
                    //this does not work in the emulator, but works on a physical device
                    view.invalidate();
                    vs.showNext();
                    changed = true;
                } else {
                    click = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return view;
    }

    private View listItemView(View view, int position) {
        final FieldValue fv = in.get(position);

        TextView field = (TextView)view.findViewById(R.id.soft_q_field);
        EditText value = (EditText)view.findViewById(R.id.soft_q_value);

        field.setText(fv.getField());
        value.setText(fv.getValue());

        value.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i("onTextChanged", s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i("afterTextChanged", "s- " + s.toString());
                fv.setValue(s.toString());
            }
        });

        return view;
    }

    private void newFieldValueSelected(int position) {
        in.add(out.remove(position));
    }

    public ArrayList<FieldValue>[] getFieldValues() {
        //out.remove(0);90-
        ArrayList<FieldValue>[] li = new ArrayList[2];
        li[0] = in;
        li[1] = out;
        return li;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if(position >= in.size()) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
