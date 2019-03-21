package com.example.focuson;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.focuson.db.Information;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Information> list = new ArrayList<>();
    private InformationAdapter adapter;
    private View noFocusTextView;
    private FloatingActionButton addFloatingButton;
    private RecyclerView recyclerView;
    private Toolbar titleToolBar;
    private Date dateTime = new Date();
    private Information backInformation = new Information();

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
        setInforList();
       adapter.notifyDataSetChanged();
    }

    /**
     * 初始化主界面的控件
     */
    private void initComponent(){
        recyclerView = findViewById(R.id.infor_recycler_view);
        noFocusTextView = findViewById(R.id.no_focus_text_view);
        addFloatingButton = findViewById(R.id.add_fab);
        titleToolBar = findViewById(R.id.title_tool_bar);
        setSupportActionBar(titleToolBar);

//      backgroundImageView = findViewById(R.id.background_image_view);
        //recyclerView设置
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
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
                LitePal.getDatabase();
                startActivityForResult(intent, 0);
                overridePendingTransition(R.anim.slide_in_right , R.anim.slide_out_left);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(resultCode){
            case RESULT_OK:
                Bundle bundle = data.getBundleExtra("Information");

                backInformation.setContent(bundle.getString("Content"));
                SimpleDateFormat date = new SimpleDateFormat("yyyy.MM.dd");
                String s = bundle.getString("Time");
                System.out.println(s);
                System.out.println(bundle.getString("Content"));
                try {
                    dateTime.setTime(date.parse(s).getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                backInformation.setTime(dateTime);
                list.add(backInformation);
                adapter = new InformationAdapter(list);
                recyclerView.setAdapter(adapter);
                if(list.size() > 0)
                {
                    noFocusTextView.setVisibility(View.INVISIBLE);
                }else{
                    noFocusTextView.setVisibility(View.VISIBLE);
                }
        }
    }

    private void setInforList() {
        List<Information> information = LitePal.findAll(Information.class);
        for(int i = 0; i < information.size(); i++){
            list.add(information.get(i));
        }
        if(list.size() > 0)
        {
            noFocusTextView.setVisibility(View.INVISIBLE);
        }else{
            noFocusTextView.setVisibility(View.VISIBLE);
        }
    }

}
