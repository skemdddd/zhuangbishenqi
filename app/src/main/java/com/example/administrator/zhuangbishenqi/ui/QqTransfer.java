package com.example.administrator.zhuangbishenqi.ui;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.example.administrator.zhuangbishenqi.R;
import com.example.administrator.zhuangbishenqi.base.BaseActivity;
import com.sevenheaven.segmentcontrol.SegmentControl;

/**
 * Created by Administrator on 2016/9/22.
 */

public class QqTransfer extends BaseActivity implements TextWatcher{
    SegmentControl mSegmentControl;
    EditText edit_transfer_status;
    EditText edit_name;
    Button btn_ture;
    String typee;
    RelativeLayout RL_name;
    ImageButton img_breako;
    @Override
    public void initWidget() {
        setContentView(R.layout.qq_transfer);
        findId();
        typee="1";
        edit_transfer_status.addTextChangedListener(this);
        edit_name.addTextChangedListener(this);

        btn_ture.setOnClickListener(this);
        img_breako.setOnClickListener(this);
        mSegmentControl.setOnSegmentControlClickListener(new SegmentControl.OnSegmentControlClickListener() {
            @Override
            public void onSegmentControlClick(int index) {
                switch (index){
                    case 0:
                        RL_name.setVisibility(View.GONE);
                        typee="1";
                        break;
                    case 1:
                        if(edit_transfer_status.getText().length()==0 ||edit_name.getText().length()==0){
                            btn_ture.setEnabled(false);
                            btn_ture.setTextColor(ContextCompat.getColor(QqTransfer.this,R.color.colorBack_Gray));
                        }
                        RL_name.setVisibility(View.VISIBLE);
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
            case R.id.btn_ture:
                Intent intent = new Intent(this,QqTransferShow.class);
                if(typee.equals("2")){
                    intent.putExtra("name",edit_name.getText().toString());
                }
                intent.putExtra("transfer_status",edit_transfer_status.getText().toString());
                intent.putExtra("typee",typee);
                startActivity(intent);
                break;
            case R.id.img_breako:
                finish();
                break;
            default:

        }

    }

    @Override
    public void findId() {
        mSegmentControl= (SegmentControl) findViewById(R.id.segment_control);
        edit_transfer_status= (EditText) findViewById(R.id.edit_transfer_status);
        edit_name= (EditText) findViewById(R.id.edit_name);
        btn_ture= (Button) findViewById(R.id.btn_ture);
        RL_name= (RelativeLayout) findViewById(R.id.RL_name);
        img_breako= (ImageButton) findViewById(R.id.img_breako);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence s, int i, int i1, int i2) {
        boolean Sign1 = edit_transfer_status.getText().length()>0;
        boolean Sign2 = edit_name.getText().length()>0;
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
