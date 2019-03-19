package com.example.focuson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ContentActivity extends AppCompatActivity implements OnDateInfo {
    private View view;

    private TextView dateTextView;
    private DateDialogFragment dateDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        view = findViewById(R.id.date_view_layout);
        dateTextView = findViewById(R.id.date_text_view);
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
        dateTextView.setText(s.toString());
    }
}
