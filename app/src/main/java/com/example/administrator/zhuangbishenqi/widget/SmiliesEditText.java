package com.example.administrator.zhuangbishenqi.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.widget.EditText;

import com.example.administrator.zhuangbishenqi.R;
import com.orhanobut.logger.Logger;

import java.lang.reflect.Field;

public class SmiliesEditText extends EditText {

    public SmiliesEditText(Context context) {
        super(context);
    }

    public SmiliesEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }
    public void insertIcon(int id) {
        Drawable drawable = getResources().getDrawable(id);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        //需要处理的文本，[smile]是需要被替代的文本
        SpannableString spannable = new SpannableString(getText().toString()+"[smile]");
        //要让图片替代指定的文字就要用ImageSpan
        ImageSpan span = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
        //开始替换，注意第2和第3个参数表示从哪里开始替换到哪里替换结束（start和end）
//最后一个参数类似数学中的集合,[5,12)表示从5到12，包括5但不包括12
        spannable.setSpan(span, getText().length(),getText().length()+"[smile]".length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        Logger.i("显示"+getText().length()+"rr"+getText().length()+"[smile]".length());
        setText(spannable);
        drawable.invalidateSelf();

    }
    public void insertIcon2 (int id ,EditText text){
        try
        {
            SpannableString spannableString;
            Field field;
            if(id<10){
               field = R.drawable.class.getDeclaredField("smiley_0" +id);
            }else {
                field = R.drawable.class.getDeclaredField("smiley_" +id);
            }

            field.setAccessible(true);
            //  获得资源ID的值，也就是静态变量的值
            int resourceId = Integer.parseInt(field.get(null).toString());
            //  根据资源ID获得资源图像的Bitmap对象
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resourceId);
            //  根据Bitmap对象创建ImageSpan对象
            ImageSpan imageSpan = new ImageSpan(getContext(), bitmap);
            //  创建一个SpannableString对象，以便插入用ImageSpan对象封装的图像
            if(id<10){
              spannableString = new SpannableString("smiley_0"+id);
            }else{
                spannableString = new SpannableString("smiley_"+id);
            }

            Logger.i("smiley_"+id);
            //  用ImageSpan对象替换face
            spannableString.setSpan(imageSpan, 0,9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            //  将随机获得的图像追加到EditText控件的最后
            text.append(spannableString);
        }
        catch (Exception e)
        {
        }

    }

}