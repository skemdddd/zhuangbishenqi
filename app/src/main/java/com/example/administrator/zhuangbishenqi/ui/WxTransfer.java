package com.example.administrator.zhuangbishenqi.ui;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.zhuangbishenqi.R;
import com.example.administrator.zhuangbishenqi.base.BaseActivity;
import com.example.administrator.zhuangbishenqi.entity.Name;
import com.example.administrator.zhuangbishenqi.widget.AccointsTimePivkerDialog;
import com.sevenheaven.segmentcontrol.SegmentControl;

import java.text.SimpleDateFormat;


/**
 * Created by Administrator on 2016/9/13.
 */

public class WxTransfer extends BaseActivity implements View.OnTouchListener,TextWatcher {
    ImageButton img_break;
    RelativeLayout RL_transfer_status;
    TextView tv_moneyTime;
    RelativeLayout RL_money_time;
    RelativeLayout RL_transfer_time;
    ImageButton imgbtn_randomName;
    SegmentControl mSegmentControl;
    RelativeLayout RL_name;
    EditText editText_otherName;
    Button btn_ture;
    EditText edi_transfer;
    TextView tv_money_time;
    TextView tv_transferTime;
    String type ;
    String typee ;
    @Override
    public void initWidget() {
        setContentView(R.layout.wx_transfer);
        findId();
        SimpleDateFormat sDateFormat =  new  SimpleDateFormat("yyyy-MM-dd hh:mm");
        String  date  =  sDateFormat.format(new java.util.Date());
        type="0";
        typee="1";
        tv_money_time.setText(date);
        tv_transferTime.setText(date);

        RL_transfer_status.setOnTouchListener(this);
        RL_transfer_status.setOnClickListener(this);

        RL_money_time.setOnTouchListener(this);
        RL_money_time.setOnClickListener(this);

        RL_transfer_time.setOnTouchListener(this);
        RL_transfer_time.setOnClickListener(this);

        img_break.setOnClickListener(this);
        imgbtn_randomName.setOnClickListener(this);
        btn_ture.setOnClickListener(this);
        editText_otherName.addTextChangedListener(this);
        edi_transfer.addTextChangedListener(this);


        mSegmentControl.setOnSegmentControlClickListener(new SegmentControl.OnSegmentControlClickListener() {
            @Override
            public void onSegmentControlClick(int index) {
                switch (index){
                    case 0:
                        RL_name.setVisibility(View.GONE);
                        typee="1";
                        break;
                    case 1:
                        RL_name.setVisibility(View.VISIBLE);
                        if(edi_transfer.getText().length()==0 || editText_otherName.getText().length()==0){
                            btn_ture.setEnabled(false);
                            btn_ture.setTextColor(ContextCompat.getColor(WxTransfer.this,R.color.colorBack_Gray));
                        }

                        typee="2";
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.img_break:
                finish();
                break;
            case R.id.RL_transfer_status:
                if(tv_moneyTime.getText().toString().equals("已收钱")){
                    tv_moneyTime.setText("未收钱");
                    type="0";
                }else{
                    tv_moneyTime.setText("已收钱");
                    type="1";

                }

                break;
            case R.id.RL_money_time:
                new AccointsTimePivkerDialog(this,tv_money_time).show();
                break;
            case R.id.RL_transfer_time:
                new AccointsTimePivkerDialog(this,tv_transferTime).show();
                break;
            case R.id.imgbtn_randomName:
                Name name = new Name();
                name.changeName(editText_otherName);
                break;
            case R.id.btn_ture:
                Intent intent = new Intent(this,WxTransferAccointsShow.class);
//                转账金额
                intent.putExtra("transfer",edi_transfer.getText().toString());
                intent.putExtra("tv_money_time",tv_money_time.getText().toString());
                intent.putExtra("tv_transferTime",tv_transferTime.getText().toString());
                intent.putExtra("name",editText_otherName.getText().toString());
                intent.putExtra("type",type);
                intent.putExtra("typee",typee);

//                收钱事件
                startActivity(intent);
                break;
            default:
                break;

        }
    }


    @Override
    public void findId() {
        img_break= (ImageButton) findViewById(R.id.img_break);
        RL_transfer_status= (RelativeLayout) findViewById(R.id.RL_transfer_status);
        tv_moneyTime= (TextView) findViewById(R.id.tv_moneyTime);
        RL_money_time= (RelativeLayout) findViewById(R.id.RL_money_time);
        RL_transfer_time= (RelativeLayout) findViewById(R.id.RL_transfer_time);
        imgbtn_randomName= (ImageButton) findViewById(R.id.imgbtn_randomName);
        mSegmentControl= (SegmentControl) findViewById(R.id.segment_control);
        RL_name= (RelativeLayout) findViewById(R.id.RL_name);
        editText_otherName= (EditText) findViewById(R.id.editText_otherName);
        btn_ture= (Button) findViewById(R.id.btn_ture);
        edi_transfer= (EditText) findViewById(R.id.edi_transfer);
        tv_money_time= (TextView) findViewById(R.id.tv_money_time);
        tv_transferTime= (TextView) findViewById(R.id.tv_transferTime);

    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction()==event.ACTION_DOWN)
        {
            v.setBackgroundColor(getResources().getColor(R.color.colorGray));
        }
        else if(event.getAction()==event.ACTION_UP)
        {
            v.setBackgroundColor(getResources().getColor(R.color.colorWhiter));
        }
        return false;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence s, int i, int i1, int i2) {
        boolean Sign1 = edi_transfer.getText().length()>0;
        boolean Sign2 = editText_otherName.getText().length()>0;
        if(typee.equals("2")){

        if (Sign1 & Sign2) {
            btn_ture.setEnabled(true);
            btn_ture.setTextColor(ContextCompat.getColor(this,R.color.colorWhiter));
        }else{
            btn_ture.setEnabled(false);


        }

        }else{
            if(s.length()>0){
                btn_ture.setEnabled(true);
                btn_ture.setTextColor(ContextCompat.getColor(this,R.color.colorWhiter));
            }else{
                btn_ture.setEnabled(false);

            }
        }
    }


    @Override
    public void afterTextChanged(Editable editable) {

    }
}
