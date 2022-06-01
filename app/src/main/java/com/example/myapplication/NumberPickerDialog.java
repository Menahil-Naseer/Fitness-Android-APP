package com.example.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class NumberPickerDialog extends AppCompatDialogFragment implements NumberPicker.OnValueChangeListener {

    private DialogListener listener;
    public NumberPicker numberPicker;
    public int minValue = 250;
    public int maxValue = 1000;
    public int step = 250;

    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_numberpicker, null);

        numberPicker = view.findViewById(R.id.valuePicker);

        final String[] valueSet = valueSet();
        numberPicker.setDisplayedValues(valueSet);
        numberPicker.setMaxValue(valueSet.length - 1);
        numberPicker.setMinValue(0);

        builder
                .setView(view).setTitle("")
                .setNegativeButton("close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).setPositiveButton("enter", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.applySumButton(Integer.parseInt(valueSet[numberPicker.getValue()]));
            }
        });
        numberPicker.setOnValueChangedListener(this);
        return builder.create();
    }

    public String[] valueSet() {
        final String[] valueSet = new String[maxValue / minValue];

        for (int i = minValue; i <= maxValue; i += step) {
            valueSet[(i / step) - 1] = String.valueOf(i);
        }
        return valueSet;
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (DialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement DialogListener");
        }
    }

    public interface DialogListener {
        void applySumButton(int pick);
    }
}

