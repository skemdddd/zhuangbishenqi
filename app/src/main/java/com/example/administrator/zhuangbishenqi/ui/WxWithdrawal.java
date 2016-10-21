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
import com.example.administrator.zhuangbishenqi.widget.BanckDialog;
import com.example.administrator.zhuangbishenqi.widget.SwitchView;
import com.mediav.ads.sdk.adcore.Mvad;
import com.orhanobut.logger.Logger;

/**
 *
 * Created by Administrator on 2016/9/9.
 *
 */


public class WxWithdrawal extends BaseActivity implements View.OnTouchListener,SwitchView.OnStateChangedListener,TextWatcher{
    ImageButton img_break;
    EditText edt_money;
    SwitchView viewSwitch;
    Button btn_make_preview;
    String type;
    TextView tv_title;
    TextView banck;
    EditText cadId;
    RelativeLayout RL_changebank;
    TextView change_the_amount;


    @Override
    public void initWidget() {
        setContentView(R.layout.wx_withdrawal);
        findId();
        type="2";
        Intent intent =getIntent();
        tv_title.setText(intent.getStringExtra("title"));

        btn_make_preview.setOnClickListener(this);
        img_break.setOnClickListener(this);
        viewSwitch.setOnStateChangedListener(this);
        RL_changebank.setOnTouchListener(this);
        RL_changebank.setOnClickListener(this);

        cadId.addTextChangedListener(this);
        edt_money.addTextChangedListener(this);
        final String adSpaceid = "aFub09x2i4";
        Mvad.showFloatbannerAd(this,adSpaceid,false,Mvad.FLOAT_BANNER_SIZE.SIZE_DEFAULT,Mvad.FLOAT_LOCATION.BOTTOM);

    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.img_break:
                finish();
                break;
            case R.id.make_preview:
                Intent intent = new Intent(this,WxWithdrawalShow.class);
                intent.putExtra("mony",change_the_amount.getText().toString());
                intent.putExtra("banck",banck.getText().toString());
                intent.putExtra("cadid",cadId.getText().toString());
                intent.putExtra("type",type);
                startActivity(intent);
                break;
            case R.id.RL_changebank:
                new BanckDialog(this,banck).show();
                break;
        }

    }





    @Override
    public void findId() {
        img_break= (ImageButton) findViewById(R.id.img_break);
        edt_money= (EditText) findViewById(R.id.change_the_amount);
        btn_make_preview= (Button) findViewById(R.id.make_preview);
        viewSwitch= (SwitchView) findViewById(R.id.view_switch);
        tv_title= (TextView) findViewById(R.id.tv_title);
        RL_changebank= (RelativeLayout) findViewById(R.id.RL_changebank);
        banck= (TextView) findViewById(R.id.banck);
        cadId= (EditText) findViewById(R.id.cadId);
        change_the_amount= (TextView) findViewById(R.id.change_the_amount);

    }

    @Override
    public void toggleToOn(View view) {
        viewSwitch.toggleSwitch(true);
        type="1";
        Logger.i("1"+type);
    }

    @Override
    public void toggleToOff(View view) {
        viewSwitch.toggleSwitch(false);
        type="2";
        Logger.i("1"+type);
    }
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
        if (s.length()>0) {
                    btn_make_preview.setEnabled(true);
                    btn_make_preview.setTextColor(ContextCompat.getColor(WxWithdrawal.this,R.color.colorWhiter));
                }else{
                    btn_make_preview.setEnabled(false);
                    btn_make_preview.setTextColor(ContextCompat.getColor(WxWithdrawal.this,R.color.colorBack_Gray));

                }
    }

    @Override
    public void afterTextChanged(Editable s) {
        String temp = s.toString();
                int posDot = temp.indexOf(".");
                if (posDot <= 0) return;
                if (temp.length() - posDot - 1 > 2)
                {
                    s.delete(posDot + 3, posDot + 4);
                }
    }
}
