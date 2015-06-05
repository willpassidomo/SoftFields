package com.example.willpassidomo.androidcomponents;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.Spinner;
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

        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (controlBlock) {
                view = infalInflater.inflate(R.layout.soft_q_control_block, null);
            } else {
                view = infalInflater.inflate(R.layout.soft_q_field_val, null);
            }
        }

        if (controlBlock) {
            return controlBlockView(view);
        } else {
            return listItemView(view);
        }

    }

    private View controlBlockView(View view) {
        final ViewSwitcher vs = view.findViewById(R.layout.soft_q_view_switcher);
        Button addQ = view.findViewById(R.layout.soft_q_add);
        final Spinner newQuestion = view.findViewById(R.layout.soft_q_selector);

        addQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vs.showNext();
            }
        });
        newQuestion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                vs.showNext();
                newFieldValueSelected(newQuestion.getSelectedItemPosition(),(FieldValue) newQuestion.getSelectedItem());
                //TODO
                //refresh/rerender view
            }
        });
        return view;
    }

    private View listItemView(View view) {
        //TODO
    }

    private void newFieldValueSelected(int position, FieldValue newQuestion) {
        FieldValue newQ = out.get(position);
        if (newQ.equals(newQuestion)) {
            out.remove(position);
            in.add(in.size(). newQuestion);
        } else {

        }
    }


    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
