package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class nutrientActivity extends AppCompatActivity {
    ExpandableListAdapter expandableAdapter;
    ExpandableListView expandList;
    List<String> expandTitle;
    HashMap<String ,List<String>> expandDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrient);
        expandList=(ExpandableListView) findViewById(R.id.expandable);
        expandDetail=ExpandList.getData();
        expandTitle =new ArrayList<String>(expandDetail.keySet());
        expandableAdapter=new ExpandableAdapter(getApplicationContext(),expandTitle,expandDetail);
        expandList.setAdapter(expandableAdapter);
        expandList.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int i) {

            }
        });
        expandList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                Toast.makeText(nutrientActivity.this,"Clicked",Toast.LENGTH_SHORT).show();

                return false;
            }
        });

    }
    }
