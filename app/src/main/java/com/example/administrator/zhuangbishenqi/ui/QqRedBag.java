package com.example.administrator.zhuangbishenqi.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.zhuangbishenqi.R;
import com.example.administrator.zhuangbishenqi.base.BaseActivity;
import com.example.administrator.zhuangbishenqi.entity.MyBitmapStore;
import com.example.administrator.zhuangbishenqi.entity.Name;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2016/8/3.
 */
public class QqRedBag  extends BaseActivity {
    TextView tv_back;
    TextView tv_num;
    TextView tv_aname;
    TextView tv_gxfc;
    TextView tv_num1;
    TextView tv_name1;
    TextView tv_time;
    CircleImageView mCircleImageView;
    CircleImageView mCircleImageView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initWidget() {
        setContentView(R.layout.activity_qq_redbag);
        findId();
        tv_back.setOnClickListener(this);
        Intent intent = getIntent();
        String num = getResources().getString(R.string.qq_commonabc);
        String qq_info = getResources().getString(R.string.qq_info);
        String cim1 = getResources().getString(R.string.qq_common1);
        tv_num.setText( String.format(num, Name.getStringNum(intent.getStringExtra("qqnum"))));
        tv_num1.setText( String.format(cim1, Name.getStringNum(intent.getStringExtra("qqnum"))));
        tv_aname.setText(String.format(qq_info,intent.getStringExtra("Aname")));
        if(intent.getStringExtra("gxfc").length()!=0){
            tv_gxfc.setText(intent.getStringExtra("gxfc"));
        }else {
            tv_gxfc.setText("恭喜发财");
        }

        tv_name1.setText(intent.getStringExtra("Bname"));
        tv_time.setText(intent.getStringExtra("time"));
        mCircleImageView.setImageBitmap(MyBitmapStore.getBmp());
        mCircleImageView1.setImageBitmap(MyBitmapStore.getBmp1());

    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.tv_back:
                finish();
                break;
        }

    }

    @Override
    public void findId() {
        tv_back= (TextView) findViewById(R.id.tv_back);
        tv_num= (TextView) findViewById(R.id.tv_num);
        tv_aname= (TextView) findViewById(R.id.tv_aname);
        tv_gxfc= (TextView) findViewById(R.id.qq_gxfc);
        tv_num1= (TextView) findViewById(R.id.tv_num1);
        tv_name1= (TextView) findViewById(R.id.tv_name1);
        tv_time = (TextView) findViewById(R.id.tv_time);
        mCircleImageView= (CircleImageView) findViewById(R.id.cview_big);
        mCircleImageView1= (CircleImageView) findViewById(R.id.cview_sm);

    }


}
