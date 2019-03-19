package com.example.focuson;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;

public class DateDialogFragment extends DialogFragment {
    private DatePicker datePicker;

    private Calendar calendar;
    private TextView confirmTextView;
    private OnDateInfo dateInfo;
    private TextView backTextView;
    private View view;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if(inflater == null)
        {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        view = inflater.inflate(R.layout.fragment_datepicker, container, false);
        initView();
        //back关闭datePicker
        backTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        //confirm将datePicker的数据传入接口
        confirmTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int y = datePicker.getYear();
                int m = datePicker.getMonth()+1;
                int d = datePicker.getDayOfMonth();
                dateInfo.inputDateInfo(y,m,d);
                dismiss();
            }
        });


        return view;
    }

    private void initView(){
        datePicker = view.findViewById(R.id.content_date_picker);
        confirmTextView = view.findViewById(R.id.confirm_text_view);
        backTextView = view.findViewById(R.id.back_text_view_dialog);
    }

    /**
     * 回调接口，传递datePicker数据
     * @param dateInfo
     */
    public void setDateInfo(OnDateInfo dateInfo) {
        this.dateInfo = dateInfo;
    }
}
