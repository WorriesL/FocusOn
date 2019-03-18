package com.example.foucson;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.foucson.db.Information;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Information> list = new ArrayList<>();
    private InformationAdapter adapter;
    private TextView noFocusTextView;
    private FloatingActionButton addFloatingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.infor_recycler_view);
        noFocusTextView = findViewById(R.id.no_focus_text_view);
        addFloatingButton = findViewById(R.id.add_fab);
        addFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ContentActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right , R.anim.slide_out_left);
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new InformationAdapter(list);
        recyclerView.setAdapter(adapter);
        if(list.size() > 0)
        {
            noFocusTextView.setVisibility(View.INVISIBLE);
        }
    }
}
