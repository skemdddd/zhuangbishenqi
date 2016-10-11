package com.example.administrator.zhuangbishenqi.entity;

import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.zhuangbishenqi.R;
import com.orhanobut.logger.Logger;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.Random;

/**
 * Created by Administrator on 2016/7/20.
 */
public class Name {


    int res_ID;
    public int getRes_ID() {
        return res_ID;
    }

    public void setRes_ID(int res_ID) {
        this.res_ID = res_ID;
    }

    public static String arr[]={"今天","昨天","周日","周六","周五","周四","周三","周二","周一"};
    public static String arrd[]={"中国工商银行"," 招商银行"," 中国农业银行"," 中国建设银行 ",
            "中国银行"," 中国民生银行"," 中国光大银行"," 中信银行",
            "交通银行"," 兴业银行",
            "华夏银行"," 深圳发展银行"," 广东发展银行"," 国家开发银行",
            "中国邮政储蓄银行"
    };
    String name[] ={"孤岚" , "笑霜," ,"形象导航",
                    "海云","凝天","沛珊","寒云","冰旋","宛儿",
            "绿真","盼儿","晓霜","碧凡","夏菡","曼香","若烟","半梦","雅绿","冰蓝","灵槐","平安"
            ,"书翠","翠风","香巧","代云","梦曼","幼翠","友巧","听寒","梦柏","醉易","访旋","亦玉",
            "冉黧绕","苏夜歌","紫陌寒","白无鸢","慕染汐","柒墨姬","沈卿黛","舒棠音","苏墨染","叶皖卿","夜冷月","妆未然",
            "南春","芷蕊","凡蕾","凡柔","安蕾","天荷","含玉","书兰","雅琴","书瑶","春雁","从安","夏槐","念芹","怀萍","代曼","幻珊","谷丝","秋翠","白晴",
            "Shelly","Mary","Dolly","Nancy","Jane","Barbara","Johnson","Bruce","Robert","Peter","Bill","Joseph","John","hirley","Emily","Sophia" ,
            "Vivian","Lillian","Joy","Burt","Charlie","Elliot","George","Johnson","Ross","Julie","Gloria","Carol","Richard","James","Charles",
            "David","Taylor","Wendy"};
    String QQ[]={
        "119023118@qq.com ","292010344@qq.com"," 516086546@qq.com"," 11302299@qq.com ","695297016@qq.com ","616103992@qq.com ","523512190@qq.com ","307323273@qq.com",
                " 474945849@qq.com ","1013934207@qq.com"," 847449402@qq.com ","119024552@qq.com ","1016946307@qq.com"," 851733175@qq.com ","627254325@qq.com"," 454809244@qq.com ",
                "927330592@qq.com ","729263464@qq.com ","812193944@qq.com ","303462539@qq.com ","350305755@qq.com ","18674833603"," 18578637671"," 18612703473",
            " 13120879487"," 13120879487", "15050293794", "18796804334 ","18776045813", "15286038304"," 13726426758"," 13049805963",
            " 15526223546 ","13168038263"," 15871418495"," 13666630399 ","18758886891 ","13160845343"};


    public void changeName(TextView textView) {

        Random mrandom = new Random();
        int randomNumber = mrandom.nextInt(name.length - 1);
        textView.setText(name[randomNumber]);

    }

    public void changeName( EditText editText) {

        Random mrandom = new Random();
        int randomNumber = mrandom.nextInt(name.length - 1);
        editText.setText(name[randomNumber]);

    }
    public void changeqq( EditText editText) {

        Random mrandom = new Random();
        int randomNumber = mrandom.nextInt(QQ.length - 1);
        editText.setText(QQ[randomNumber]);

    }

    public void changeImgName(TextView textView, ImageView imageView)
    {
        changeImg(imageView);
        changeName(textView);

    }
    public void changeImg(ImageView imageView){
        Random mrandom= new Random();
        int  randomNumber= mrandom.nextInt(50-1)+1;
        Logger.i("随机数"+randomNumber);

        if(randomNumber < 10)
        {

            String mimg="img_1000"+randomNumber;
            Logger.i("图片名字"+mimg);
            Class drawable = R.drawable.class;
            Field field = null;
            try {
                field = drawable.getField(mimg);
                res_ID = field.getInt(field.getName());
                imageView.setImageResource(res_ID);
            } catch (Exception e) {}


        }else{

            String mimg="img_100"+randomNumber;
            Logger.i("图片名字"+mimg);
            Class drawable = R.drawable.class;
            Field field = null;
            try {
                field = drawable.getField(mimg);
                res_ID= field.getInt(field.getName());
                imageView.setImageResource(res_ID);

            } catch (Exception e) {}

        }

    }
    public void setImag(ImageView imageView,String ImagName){
        Class drawable = R.drawable.class;
        Field field = null;
        try {
            field = drawable.getField(ImagName);
            res_ID= field.getInt(field.getName());
            imageView.setImageResource(res_ID);

        } catch (Exception e) {}
    }
   public void setImagButton(ImageButton buttton, String ImagName){
        Class drawable = R.drawable.class;
        Field field = null;
        try {
            field = drawable.getField(ImagName);
            res_ID= field.getInt(field.getName());
            buttton.setImageResource(res_ID);

        } catch (Exception e) {}
    }
    public static String getStringNum(String string) {
        DecimalFormat df=new DecimalFormat(".00");
        double a =Double.parseDouble(string);
        if(a<1){
            return string+"0";
        }else{
            try {
                df.format(a);
            }catch (Exception e){
                return string+".00";
            }
            return (String) df.format(a);
        }

//        return (String) df.format(a);
    }



}


