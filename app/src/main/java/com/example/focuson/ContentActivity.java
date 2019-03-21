package com.example.focuson;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.focuson.db.Information;

import org.litepal.LitePal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ContentActivity extends AppCompatActivity implements OnDateInfo {
    private View view;

    private Date dateTime = new Date();
    private String focusContent;

    private TextView dateTextView;
    private DateDialogFragment dateDialog;
    private EditText contentEditText;
    private Information focusInfor;
    private Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        view = findViewById(R.id.date_view_layout);
        dateTextView = findViewById(R.id.date_text_view);
        contentEditText = findViewById(R.id.focus_content_edit_text);
        toolBar = findViewById(R.id.title_tool_bar_content_actitvity);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        dateDialog = new DateDialogFragment();
        dateDialog.setDateInfo(this);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateDialog.show(getSupportFragmentManager(), DateDialogFragment.class.getSimpleName());
            }
        });
    }

    @Override
    public void inputDateInfo(int year, int month, int day) {
        StringBuffer s = new StringBuffer();
        s.append(year).append(".")
                .append(month).append(".")
                .append(day);
        SimpleDateFormat date = new SimpleDateFormat("yyyy.MM.dd");
        try {
            dateTime.setTime(date.parse(s.toString()).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dateTextView.setText(s.toString());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case android.R.id.home: {
                finish();
                return true;
            }

            case R.id.confirm:{
                focusContent = contentEditText.getText().toString().trim();
                focusInfor = new Information();
                focusInfor.setContent(focusContent);
                focusInfor.setTime(dateTime);
                focusInfor.save();
                Intent intent = new Intent(ContentActivity.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("Content", focusContent);
                SimpleDateFormat date = new SimpleDateFormat("yyyy.MM.dd");
                String s = date.format(dateTime);
                bundle.putString("Time", s);
                intent.putExtra("Information", bundle);
                this.setResult(RESULT_OK, intent);
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }
}
