package com.example.focuson;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.focuson.db.Information;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Information> list = new ArrayList<>();
    private InformationAdapter adapter;
    private TextView noFocusTextView;
    private FloatingActionButton addFloatingButton;
    private RecyclerView recyclerView;
    private ImageView backgroundImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_main);
        initComponent();
        clickFab();
        if(list.size() > 0)
        {
            noFocusTextView.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 初始化主界面的控件
     */
    private void initComponent(){
        recyclerView = findViewById(R.id.infor_recycler_view);
        noFocusTextView = findViewById(R.id.no_focus_text_view);
        addFloatingButton = findViewById(R.id.add_fab);
//      backgroundImageView = findViewById(R.id.background_image_view);
        //recyclerView设置
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        Information info = new Information();
        info.setContent("XXX");
        Date date = new Date();
        info.setTime(date);
        list.add(info);
        list.add(info);
        list.add(info);
        adapter = new InformationAdapter(list);
        recyclerView.setAdapter(adapter);
    }

    /**
     * Fab点击事件设置
     */
    private void clickFab(){
        addFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ContentActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right , R.anim.slide_out_left);
            }
        });
    }
}
