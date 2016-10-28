package com.example.administrator.zhuangbishenqi.ui;



import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
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
import android.widget.Toast;
import com.example.administrator.zhuangbishenqi.R;
import com.example.administrator.zhuangbishenqi.base.BaseActivity;
import com.example.administrator.zhuangbishenqi.entity.MyBitmapStore;
import com.example.administrator.zhuangbishenqi.entity.Name;
import com.example.administrator.zhuangbishenqi.widget.DateTimePickerDialog;
import com.mediav.ads.sdk.adcore.Mvad;
import com.mediav.ads.sdk.interfaces.IMvBannerAd;

import java.text.SimpleDateFormat;


/**
 * Created by Administrator on 2016/7/29.
 */
public class QqRedInformation  extends BaseActivity implements View.OnTouchListener{
    ImageButton ibtn_break;
    LinearLayout LL_information;
    LinearLayout LL_information1;
    EditText edt_money;
    Button btn_make_preview;
    ImageView img_head1;
    TextView tv_name1;
    ImageView img_head;
    TextView tv_name;
    EditText edt_wx_information;
    Name name;
    TextView tv_title;//标题名字
    public  static TextView tv_time;//时间
    RelativeLayout RL_changeTime;
    private RelativeLayout adContainer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initEditText();
        name =new Name();
        name.changeImgName(tv_name,img_head);
        name.changeImgName(tv_name1,img_head1);
//        显示系统时间。
        SimpleDateFormat sDateFormat = new SimpleDateFormat("HH:mm:ss");
        String date = sDateFormat.format(new java.util.Date());
        tv_time.setText(date);
        final String adSpaceid = "aFub09x2i4";
        IMvBannerAd bannerad = Mvad.showBanner(adContainer, this, adSpaceid, false);
        bannerad.showAds(this);
    }

    @Override
    public void initWidget() {
        setContentView(R.layout.activity_qq_nameheard);
        findId();
        Intent intent = getIntent();
        tv_title.setText(intent.getStringExtra("title"));
        ibtn_break.setOnClickListener(this);
        LL_information.setOnTouchListener(this);
        LL_information1.setOnClickListener(this);
        LL_information.setOnClickListener(this);
        LL_information1.setOnTouchListener(this);
        btn_make_preview.setOnClickListener(this);
        RL_changeTime.setOnTouchListener(this);
        RL_changeTime.setOnClickListener(this);

    }

    @Override
    public void widgetClick(View v) {
        Intent intent;
        Bitmap image;
        switch (v.getId()){
            case R.id.ibtn_break:
                finish();
                break;
            case R.id.LL_information:
                intent = new Intent(this,WxZfbChangeNameHeard.class);
                intent.putExtra("name",tv_name.getText());
                image = ((BitmapDrawable)img_head.getDrawable()).getBitmap();
                MyBitmapStore.setBmp(image);
                startActivityForResult(intent,1);
                break;
            case R.id.LL_information1:
                intent = new Intent(this,WxZfbChangeNameHeard.class);
                intent.putExtra("name",tv_name1.getText());
                image = ((BitmapDrawable)img_head1.getDrawable()).getBitmap();
                MyBitmapStore.setBmp(image);
                startActivityForResult(intent,5);
                break;
            case R.id.RL_changeTime:
                showDialog();
                        break;
            case R.id.make_preview:
                Intent intent1 = new Intent(this,QqRedBag.class);
                intent1.putExtra("qqnum",edt_money.getText().toString());
                intent1.putExtra("Aname",tv_name.getText().toString());
                intent1.putExtra("Bname",tv_name1.getText().toString());
                intent1.putExtra("gxfc",edt_wx_information.getText().toString());
                intent1.putExtra("time",tv_time.getText().toString());
                image = ((BitmapDrawable)img_head.getDrawable()).getBitmap();
                MyBitmapStore.setBmp(image);
                image = ((BitmapDrawable)img_head1.getDrawable()).getBitmap();
                MyBitmapStore.setBmp1(image);
                startActivity(intent1);
                break;


        }

    }

    @Override
    public void findId() {
        ibtn_break= (ImageButton) findViewById(R.id.ibtn_break);
        LL_information= (LinearLayout) findViewById(R.id.LL_information);
        edt_money= (EditText) findViewById(R.id.money);
        btn_make_preview= (Button) findViewById(R.id.make_preview);
        tv_name= (TextView) findViewById(R.id.tv_name);
        img_head= (ImageView) findViewById(R.id.img_head);
        edt_wx_information= (EditText) findViewById(R.id.wx_information);
        tv_title= (TextView) findViewById(R.id.titlename);
        LL_information1= (LinearLayout) findViewById(R.id.LL_information1);
        tv_name1= (TextView) findViewById(R.id.tv_name1);
        img_head1= (ImageView) findViewById(R.id.img_head1);
        tv_time= (TextView) findViewById(R.id.tv_time);
        RL_changeTime= (RelativeLayout) findViewById(R.id.RL_changeTime);
        adContainer= (RelativeLayout) findViewById(R.id.adContainer);

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


    //    EditText 状态捕捉
    public void initEditText() {
        edt_money.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()>0) {
                    btn_make_preview.setEnabled(true);
                    btn_make_preview.setTextColor(ContextCompat.getColor(QqRedInformation.this,R.color.colorWhiter));
                }else{
                    btn_make_preview.setEnabled(false);
                    btn_make_preview.setTextColor(ContextCompat.getColor(QqRedInformation.this,R.color.colorBack_Gray));

                }

            }

            @Override
            public void afterTextChanged(Editable s) {
//                保留小数点两位。
                String temp = s.toString();
                int posDot = temp.indexOf(".");
                if (posDot <= 0) return;
                if (temp.length() - posDot - 1 > 2)
                {
                    s.delete(posDot + 3, posDot + 4);
                }

            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1 && data!=null && resultCode ==2)
        {
            tv_name.setText(data.getStringExtra("finishtext"));
            img_head.setImageBitmap(MyBitmapStore.getBmp());

        }
        if(requestCode == 5 && data!=null && resultCode ==2)
        {
            tv_name1.setText(data.getStringExtra("finishtext"));
            img_head1.setImageBitmap(MyBitmapStore.getBmp());

        }


        super.onActivityResult(requestCode, resultCode, data);

    }
    public void getRedView(Class lasss){
        if(Double.parseDouble((edt_money.getText().toString())) > 200)
        {
            Toast.makeText(QqRedInformation.this,"红包金额不能超过200元",Toast.LENGTH_SHORT).show();
        }else {
            Intent intent1 = new Intent(this, lasss);
            Bitmap image1 = ((BitmapDrawable) img_head.getDrawable()).getBitmap();
            MyBitmapStore.setBmp(image1);
            intent1.putExtra("name", tv_name.getText());
            intent1.putExtra("money", edt_money.getText().toString());
            intent1.putExtra("information", edt_wx_information.getText().toString());
            startActivity(intent1);
        }
    }
    public void showDialog() {
        DateTimePickerDialog dialog = new DateTimePickerDialog(this,
                System.currentTimeMillis(),1,tv_time
        );
        dialog.show();

    }


}
