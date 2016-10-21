package com.example.administrator.zhuangbishenqi.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.zhuangbishenqi.R;
import com.example.administrator.zhuangbishenqi.base.BaseActivity;
import com.example.administrator.zhuangbishenqi.entity.MyBitmapStore;
import com.example.administrator.zhuangbishenqi.entity.Name;
import com.example.administrator.zhuangbishenqi.widget.AccointsTimePivkerDialog;
import com.sevenheaven.segmentcontrol.SegmentControl;

import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2016/9/20.
 */

public class ZfbTransfer extends BaseActivity implements  View.OnTouchListener,TextWatcher {
    SegmentControl mSegmentControl;
    ImageButton img_break;
    LinearLayout LL_transaction_object;
    TextView tv_name;
    ImageView img_head;
    ImageButton imgbtn_randomPhone;
    EditText editText_QQ;
    EditText edt_transfer_amount;
    Button btn_ture;
    EditText edit_reason;
    RelativeLayout RL_detail;
    TextView tv_detailmony;
    RelativeLayout RL_transfer_time;
    TextView tv_transferTime;
    RelativeLayout RL_state;
    TextView tv_state;
    String typee;
    @Override
    public void initWidget() {
        setContentView(R.layout.zfb_transfer);
        findId();
        SimpleDateFormat sDateFormat =  new  SimpleDateFormat("yyyy-MM-dd hh:mm");
        String  date  =  sDateFormat.format(new java.util.Date());
        Name name =new Name();
        name.changeImgName(tv_name,img_head);
        Bitmap image = ((BitmapDrawable)img_head.getDrawable()).getBitmap();
        MyBitmapStore.setBmp(image);
        typee="1";
        tv_transferTime.setText(date);
        img_break.setOnClickListener(this);

        btn_ture.setOnClickListener(this);

        LL_transaction_object.setOnTouchListener(this);
        LL_transaction_object.setOnClickListener(this);

        imgbtn_randomPhone.setOnClickListener(this);

        RL_detail.setOnClickListener(this);
        RL_detail.setOnTouchListener(this);

        RL_transfer_time.setOnTouchListener(this);
        RL_transfer_time.setOnClickListener(this);

        RL_state.setOnClickListener(this);
        RL_state.setOnTouchListener(this);

        editText_QQ.addTextChangedListener(this);
        edt_transfer_amount.addTextChangedListener(this);
        mSegmentControl.setOnSegmentControlClickListener(new SegmentControl.OnSegmentControlClickListener() {
            @Override
            public void onSegmentControlClick(int index) {
                switch (index){
                    case 0:
                        typee="1";
                        break;
                    case 1:
                        if(editText_QQ.getText().length()==0 ||edt_transfer_amount.getText().length()==0){
                            btn_ture.setEnabled(false);
                            btn_ture.setTextColor(ContextCompat.getColor(ZfbTransfer.this,R.color.colorBack_Gray));
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
        Bitmap image;
        switch (v.getId()){
            case R.id.img_break:
                finish();
                break;
//           头像名字
            case R.id.LL_transaction_object:
                Intent intent = new Intent(this,WxZfbChangeNameHeard.class);
                intent.putExtra("name",tv_name.getText());
                image = ((BitmapDrawable)img_head.getDrawable()).getBitmap();
                MyBitmapStore.setBmp(image);
                startActivityForResult(intent,1);
                break;
//            随机账号
            case R.id.imgbtn_randomPhone:
                Name name= new Name();
                name.changeqq(editText_QQ);
              break;
            case R.id.btn_ture:
                Intent intent1 = new Intent(this,ZfbTransferShow.class);
                intent1.putExtra("qq",editText_QQ.getText().toString());
                intent1.putExtra("amount",edt_transfer_amount.getText().toString());
//                理由
                if(edit_reason.getText().toString().length()>0){
                    intent1.putExtra("reason",edit_reason.getText().toString());
                }else{
                    intent1.putExtra("reason","转账");
                }

                intent1.putExtra("transferTime",tv_transferTime.getText().toString());
                intent1.putExtra("detailmony",tv_detailmony.getText().toString());
                intent1.putExtra("state",tv_state.getText().toString());
                intent1.putExtra("typee",typee);
                intent1.putExtra("name",tv_name.getText().toString());
                startActivity(intent1);
                break;
            case R.id.RL_detail:
                if(tv_detailmony.getText().toString().equals("余额宝")){
                    tv_detailmony.setText("余额");

                }else{
                    tv_detailmony.setText("余额宝");
                }
                break;
            case R.id.RL_transfer_time:
                new AccointsTimePivkerDialog(this,tv_transferTime).show();
                break;
            case R.id.RL_state:
                if(tv_state.getText().toString().equals("交易成功")){
                    tv_state.setText("处理中");

                }else{
                    tv_state.setText("交易成功");
                }
                break;

        }
    }

    @Override
    public void findId() {
        mSegmentControl= (SegmentControl) findViewById(R.id.segment_control);
        img_break= (ImageButton) findViewById(R.id.img_break);
        LL_transaction_object= (LinearLayout) findViewById(R.id.LL_transaction_object);
        tv_name= (TextView) findViewById(R.id.tv_name);
        img_head= (ImageView) findViewById(R.id.img_head);
        imgbtn_randomPhone= (ImageButton) findViewById(R.id.imgbtn_randomPhone);
        editText_QQ= (EditText) findViewById(R.id.editText_QQ);
        edt_transfer_amount= (EditText) findViewById(R.id.edt_transfer_amount);
        btn_ture= (Button) findViewById(R.id.btn_ture);
        edit_reason= (EditText) findViewById(R.id.edit_reason);

        RL_detail= (RelativeLayout) findViewById(R.id.RL_detail);
        tv_detailmony= (TextView) findViewById(R.id.tv_detailmony);
        RL_transfer_time= (RelativeLayout) findViewById(R.id.RL_transfer_time);
        tv_transferTime= (TextView) findViewById(R.id.tv_transferTime);
        RL_state= (RelativeLayout) findViewById(R.id.RL_state);
        tv_state= (TextView) findViewById(R.id.tv_state);
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence s, int i, int i1, int i2) {
        boolean Sign1 = editText_QQ.getText().length()>0;
        boolean Sign2 = edt_transfer_amount.getText().length()>0;
            if (Sign1 & Sign2) {
                btn_ture.setEnabled(true);
                btn_ture.setTextColor(ContextCompat.getColor(this,R.color.colorWhiter));
            }else{
                btn_ture.setEnabled(false);
            }


    }

    @Override
    public void afterTextChanged(Editable editable) {

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1 && data!=null && resultCode ==2)
        {
            tv_name.setText(data.getStringExtra("finishtext"));
            img_head.setImageBitmap(MyBitmapStore.getBmp());

        }

        super.onActivityResult(requestCode, resultCode, data);

    }
}
