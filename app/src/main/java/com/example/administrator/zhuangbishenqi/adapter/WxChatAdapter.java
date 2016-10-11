package com.example.administrator.zhuangbishenqi.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.NinePatch;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.zhuangbishenqi.R;
import com.example.administrator.zhuangbishenqi.entity.Name;
import com.example.administrator.zhuangbishenqi.ui.WxChat;
import com.orhanobut.logger.Logger;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.view.View.VISIBLE;

/**
 * Created by Administrator on 2016/8/17.
 */
public class WxChatAdapter extends RecyclerView.Adapter <WxChatAdapter.ViewHolder> {
    private ArrayList<Map<String,String>>mDatas;
    private View view;
    private Context mContext;
    private static String re1="(smiley_[a-z0-9]{2})";
    public enum type{
        type1,
        type2,
        type3,
        type4,
        type5,
        type6,
        type7,
        type8,
        type9,
        type10
    }



    //创建新View，被LayoutManager所调用,加载Item布局
    public WxChatAdapter(ArrayList<Map<String,String>>mDatas,Context mContext){
        this.mContext=mContext;
        this.mDatas =mDatas;

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case 1:
                view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_chat_mydialogue,parent,false);
                break;
            case 2:
                view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_chat_yourdialogue,parent,false);
                break;
            case 3:
                view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_mypicture,parent,false);
                break;
            case 4:
                view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_yourpicture,parent,false);
                break;
            case 5:
                view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_chat_myenvelope,parent,false);
                break;
            case 6:
                view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_chat_yourenvelope,parent,false);
                break;
            case 7:
                view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_chat_myenvelope,parent,false);
                break;
            case 8:
                view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_chat_yourenvelope,parent,false);
                break;
            case 9:
                view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_chat_mydialogue,parent,false);
                break;
            case 10:
                view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_chat_yourdialogue,parent,false);
                break;


        }

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Name name = new Name();
//        holder.tv_dialogut.setText(mDatas.get(position));
        switch (getItemViewType(position)){
            case 1:
                if(mDatas.get(position).get("time").equals("") ){
                    if( !mDatas.get(position).get("text").equals("")) {
                        holder.tv_myspeak.setText(getExpressionString(mContext, mDatas.get(position).get("text"), 18));
                    }
                Bitmap mybt = BitmapFactory.decodeFile(mDatas.get(position).get("image"));
                    if(mybt==null){
                        name.setImag(holder.img_myheard,"img_10005");
                    }else{
                        holder.img_myheard.setImageBitmap(mybt);
                    }

                }else {
                    if( !mDatas.get(position).get("text").equals("")) {
                        holder.tv_myspeak.setText(getExpressionString(mContext, mDatas.get(position).get("text"), 18));
                    }
                    Bitmap mybt = BitmapFactory.decodeFile(mDatas.get(position).get("image"));
                    if(mybt==null){
                        name.setImag(holder.img_myheard,"img_10005");
                    }else{
                        holder.img_myheard.setImageBitmap(mybt);
                    }
                    holder.tv_time.setVisibility(VISIBLE);
                    holder.tv_time.setText(mDatas.get(position).get("time"));
                }
                break;
            case 2:
                if(mDatas.get(position).get("time").equals("")){
                    if( !mDatas.get(position).get("text").equals("")) {
                        holder.tv_myspeak.setText(getExpressionString(mContext, mDatas.get(position).get("text"), 18));
                    }
                Bitmap yourbt = BitmapFactory.decodeFile(mDatas.get(position).get("image"));
                    if(yourbt==null){
                        name.setImag(holder.img_myheard,"img_10025");
                    }else{
                        holder.img_myheard.setImageBitmap(yourbt);
                    }

                }else{
                    if( !mDatas.get(position).get("text").equals("")) {
                        holder.tv_myspeak.setText(getExpressionString(mContext, mDatas.get(position).get("text"), 18));
                    }
                    Bitmap yourbt = BitmapFactory.decodeFile(mDatas.get(position).get("image"));
                    if(yourbt==null){
                        name.setImag(holder.img_myheard,"img_10025");
                    }else{
                        holder.img_myheard.setImageBitmap(yourbt);
                    }
                    holder.tv_time.setVisibility(VISIBLE);
                    holder.tv_time.setText(mDatas.get(position).get("time"));
                }
                break;
            case 3:
                if(!mDatas.get(position).get("time").equals("")) {
                    Bitmap bitmap_in = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.agq);
                    Bitmap mypbit = BitmapFactory.decodeFile(mDatas.get(position).get("pictires"));
                    holder.image_mypicture.setImageBitmap(getRoundCornerImage(bitmap_in, mypbit));
                    Bitmap mybit = BitmapFactory.decodeFile(WxChat.path + "myHead.jpg");
                    if(mybit==null){
                        name.setImag(holder.img_myheard,"img_10005");
                    }else{
                        holder.img_myheard.setImageBitmap(mybit);
                    }
                    holder.tv_time.setVisibility(VISIBLE);
                    holder.tv_time.setText(mDatas.get(position).get("time"));

                }else{
                    Bitmap bitmap_in = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.agq);
                    Bitmap mypbit = BitmapFactory.decodeFile(mDatas.get(position).get("pictires"));
                    holder.image_mypicture.setImageBitmap(getRoundCornerImage(bitmap_in, mypbit));
                    Bitmap mybit = BitmapFactory.decodeFile(WxChat.path + "myHead.jpg");
                    if(mybit==null){
                        name.setImag(holder.img_myheard,"img_10005");
                    }else{
                        holder.img_myheard.setImageBitmap(mybit);
                    }
                }
                break;
            case 4:
                if(!mDatas.get(position).get("time").equals("")) {
                    Bitmap bitmap_in = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.acp1);
                    Bitmap mypbit = BitmapFactory.decodeFile(mDatas.get(position).get("pictires"));
                    holder.image_mypicture.setImageBitmap(getRoundCornerImage(bitmap_in, mypbit));
                    Bitmap mybit = BitmapFactory.decodeFile(WxChat.path + "yourHead.jpg");
                    if(mybit==null){
                        name.setImag(holder.img_myheard,"img_10025");
                    }else{
                        holder.image_myphead.setImageBitmap(mybit);
                    }

                    holder.tv_time.setVisibility(VISIBLE);
                    holder.tv_time.setText(mDatas.get(position).get("time"));

                }else{
                    Bitmap bitmap_in = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.acp1);
                    Bitmap mypbit = BitmapFactory.decodeFile(mDatas.get(position).get("pictires"));
                    holder.image_mypicture.setImageBitmap(getRoundCornerImage(bitmap_in, mypbit));
                    Bitmap mybit = BitmapFactory.decodeFile(WxChat.path + "yourHead.jpg");
                    if(mybit==null){
                        name.setImag(holder.img_myheard,"img_10025");
                    }else{
                        holder.image_myphead.setImageBitmap(mybit);
                    }
                }
                break;
            case 5:
                if(!mDatas.get(position).get("time").equals("")){
                    holder.tv_redinformation.setText(mDatas.get(position).get("redinformation"));
                    Bitmap mybit = BitmapFactory.decodeFile(WxChat.path + "myHead.jpg");
                    if(mybit==null){
                        name.setImag(holder.img_myheard,"img_10005");
                    }else{
                        holder.img_myheard.setImageBitmap(mybit);
                    }
                    holder.tv_time.setVisibility(VISIBLE);
                    holder.tv_time.setText(mDatas.get(position).get("time"));
                    if(mDatas.get(position).get("typered").equals("1")){
                        holder.LL_nameredbag.setVisibility(VISIBLE);
                        holder.tv_mynameredbag.setText(mDatas.get(position).get("yourname")+"领取了你的");
                    }

                }else{
                    holder.tv_redinformation.setText(mDatas.get(position).get("redinformation"));
                    Bitmap mybit = BitmapFactory.decodeFile(WxChat.path + "myHead.jpg");
                    if(mybit==null){
                        name.setImag(holder.image_myphead,"img_10005");
                    }else{
                        holder.image_myphead.setImageBitmap(mybit);
                    }
                    if(mDatas.get(position).get("typered").equals("1")){
                        holder.LL_nameredbag.setVisibility(VISIBLE);
                        holder.tv_mynameredbag.setText(mDatas.get(position).get("yourname")+"领取了你的");
                    }

                }
                break;
            case 6:
                if(!mDatas.get(position).get("time").equals("")){
                    holder.tv_redinformation.setText(mDatas.get(position).get("redinformation"));
                    Bitmap mybit = BitmapFactory.decodeFile(WxChat.path + "yourHead.jpg");
                    if(mybit==null){
                        name.setImag(holder.image_myphead,"img_10025");
                    }else{
                        holder.image_myphead.setImageBitmap(mybit);
                    }
                    holder.tv_time.setVisibility(VISIBLE);
                    holder.tv_time.setText(mDatas.get(position).get("time"));


                }else{
                    holder.tv_redinformation.setText(mDatas.get(position).get("redinformation"));
                    Bitmap mybit = BitmapFactory.decodeFile(WxChat.path + "yourHead.jpg");
                    if(mybit==null){
                        name.setImag(holder.image_myphead,"img_10025");
                    }else{
                        holder.image_myphead.setImageBitmap(mybit);
                    }
                    if(mDatas.get(position).get("typered").equals("1")){
                        holder.LL_nameredbag.setVisibility(VISIBLE);
                        holder.tv_mynameredbag.setText(mDatas.get(position).get("yourname")+"领取了你的");
                    }
                }
                break;
            case 7:
                if(!mDatas.get(position).get("time").equals("") && !mDatas.get(position).get("typered").equals("1")){
                    holder.tv_redinformation.setText("转账给"+mDatas.get(position).get("yourname"));
                    Bitmap mybit = BitmapFactory.decodeFile(WxChat.path + "myHead.jpg");
                    name.setImag(holder.icon,"ic_zhuanzhang");
                    holder.tv_money.setText("￥"+mDatas.get(position).get("redinformation"));
                    holder.tv_wechatpay.setText("微信支付");
                    if(mybit==null){
                        name.setImag(holder.image_myphead,"img_10005");
                    }else{
                        holder.image_myphead.setImageBitmap(mybit);
                    }
                    holder.tv_time.setVisibility(VISIBLE);
                    holder.tv_time.setText(mDatas.get(position).get("time"));
                    if(mDatas.get(position).get("typered").equals("1")){
                        holder.tv_redinformation.setText("已领取");
                        name.setImag(holder.icon,"ic_shouqian");
                    }


                }else{
                    holder.tv_redinformation.setText("转账给"+mDatas.get(position).get("yourname"));
                    Bitmap mybit = BitmapFactory.decodeFile(WxChat.path + "myHead.jpg");
                    name.setImag(holder.icon,"ic_zhuanzhang");
                    holder.tv_money.setText("￥"+mDatas.get(position).get("redinformation")+"元");
                    holder.tv_wechatpay.setText("微信支付");
                    if(mybit==null){
                        name.setImag(holder.image_myphead,"img_10005");
                    }else{
                        holder.image_myphead.setImageBitmap(mybit);
                    }
                    if(mDatas.get(position).get("typered").equals("1")){
                        Logger.i("类型"
                                +mDatas.get(position).get("typered"));
                        holder.tv_redinformation.setText("已领取");
                        name.setImag(holder.icon,"ic_shouqian");
                    }
                }
                break;
            case 8:
                if(mDatas.get(position).get("time").equals("") && !mDatas.get(position).get("typered").equals("1")){
                    holder.tv_redinformation.setText("转账给你");
                    Bitmap mybit = BitmapFactory.decodeFile(WxChat.path + "yourHead.jpg");
                    name.setImag(holder.icon,"ic_zhuanzhang");
                    holder.tv_money.setText("￥"+mDatas.get(position).get("redinformation"));
                    holder.tv_wechatpay.setText("微信支付");
                    if(mybit==null){
                        name.setImag(holder.image_myphead,"img_10025");
                    }else{
                        holder.image_myphead.setImageBitmap(mybit);
                    }
                    holder.tv_time.setVisibility(VISIBLE);
                    holder.tv_time.setText(mDatas.get(position).get("time"));
                    if(!mDatas.get(position).get("typered").equals("1")){
                        holder.tv_redinformation.setText("已领取");
                        name.setImag(holder.icon,"ic_shouqian");
                    }

                }else{
                    holder.tv_redinformation.setText("转账给"+mDatas.get(position).get("yourname"));
                    Bitmap mybit = BitmapFactory.decodeFile(WxChat.path + "myHead.jpg");
                    name.setImag(holder.icon,"ic_zhuanzhang");
                    holder.tv_money.setText("￥"+mDatas.get(position).get("redinformation")+"元");
                    holder.tv_wechatpay.setText("微信支付");
                    if(mybit==null){
                        name.setImag(holder.image_myphead,"img_10025");
                    }else{
                        holder.image_myphead.setImageBitmap(mybit);
                    }
                    if(mDatas.get(position).get("typered").equals("1")){
                        holder.tv_redinformation.setText("已领取");
                        name.setImag(holder.icon,"ic_shouqian");
                    }
                }
                break;
            case 9:
                if(!mDatas.get(position).get("time").equals("") && !mDatas.get(position).get("typered").equals("1")){
                    holder.tv_timewvoice.setVisibility(VISIBLE);
                    holder.tv_myspeak.setWidth(Integer.parseInt(mDatas.get(position).get("redinformation")));
                    holder.tv_timewvoice.setText(mDatas.get(position).get("redinformation")+"\"");
                    holder.tv_time.setText(mDatas.get(position).get("time"));
                    Bitmap mybit = BitmapFactory.decodeFile(WxChat.path + "myHead.jpg");
                    if(mybit==null){
                        name.setImag(holder.image_myphead,"img_10005");
                    }else{
                        holder.image_myphead.setImageBitmap(mybit);
                    }
                    Drawable tv_map=mContext.getResources().getDrawable(R.drawable.chatto_voice_playing);
                    tv_map.setBounds(0, 0, tv_map.getMinimumWidth(), tv_map.getMinimumHeight());
                    holder.tv_myspeak.setCompoundDrawables(null, null, tv_map, null);

                    if(mDatas.get(position).get("typered").equals("1")){
                      holder.imag_red.setVisibility(VISIBLE);
                    }

                }else{
                    Drawable tv_map=mContext.getResources().getDrawable(R.drawable.chatto_voice_playing);
                    tv_map.setBounds(0, 0, tv_map.getMinimumWidth(), tv_map.getMinimumHeight());
                    holder.tv_myspeak.setCompoundDrawables(null, null, tv_map, null);
                    holder.tv_timewvoice.setVisibility(VISIBLE);
                    holder.tv_myspeak.setWidth(Integer.parseInt(mDatas.get(position).get("redinformation"))*5);
                    holder.tv_timewvoice.setText(mDatas.get(position).get("redinformation")+"\"");
                    Bitmap mybit = BitmapFactory.decodeFile(WxChat.path + "myHead.jpg");
                    if(mybit==null){
                        name.setImag(holder.image_myphead,"img_10005");
                    }else{
                        holder.image_myphead.setImageBitmap(mybit);
                    }
                    if(mDatas.get(position).get("typered").equals("1")){
                        holder.imag_red.setVisibility(VISIBLE);
                    }

                }
                break;
            case 10:
                if(!mDatas.get(position).get("time").equals("") && !mDatas.get(position).get("typered").equals("1")){
                    holder.tv_timewvoice.setVisibility(VISIBLE);
                    holder.tv_myspeak.setWidth(Integer.parseInt(mDatas.get(position).get("redinformation")));
                    holder.tv_timewvoice.setText(mDatas.get(position).get("redinformation")+"\"");
                    holder.tv_time.setText(mDatas.get(position).get("time"));
                    Bitmap mybit = BitmapFactory.decodeFile(WxChat.path + "yourHead.jpg");
                    if(mybit==null){
                        name.setImag(holder.image_myphead,"img_10025");
                    }else{
                        holder.image_myphead.setImageBitmap(mybit);
                    }
                    Drawable tv_map=mContext.getResources().getDrawable(R.drawable.chatfrom_voice_playing_f3);
                    tv_map.setBounds(0, 0, tv_map.getMinimumWidth(), tv_map.getMinimumHeight());
                    holder.tv_myspeak.setCompoundDrawables(tv_map, null, null, null);

                    if(mDatas.get(position).get("typered").equals("1")){
                        holder.imag_red.setVisibility(VISIBLE);
                    }

                }else{
                    Drawable tv_map=mContext.getResources().getDrawable(R.drawable.chatfrom_voice_playing_f3);
                    tv_map.setBounds(0, 0, tv_map.getMinimumWidth(), tv_map.getMinimumHeight());
                    holder.tv_myspeak.setCompoundDrawables(tv_map, null, null, null);
                    holder.tv_timewvoice.setVisibility(VISIBLE);
                    holder.tv_myspeak.setWidth(Integer.parseInt(mDatas.get(position).get("redinformation"))*5);
                    holder.tv_timewvoice.setText(mDatas.get(position).get("redinformation")+"\"");
                    Bitmap mybit = BitmapFactory.decodeFile(WxChat.path + "yourHead.jpg");
                    if(mybit==null){
                        name.setImag(holder.image_myphead,"img_10025");
                    }else{
                        holder.image_myphead.setImageBitmap(mybit);
                    }
                    if(mDatas.get(position).get("typered").equals("1")){
                        holder.imag_red.setVisibility(VISIBLE);
                    }

                }
                break;

        }

    }

    @Override
    public int getItemCount() {
        try{
            return mDatas.size();
        }catch (Exception e){
            return 0;
        }

    }

    @Override
    public int getItemViewType(int position) {
      if(type.type1.name().equals( mDatas.get(position).get("type"))){
              return 1;

          }else if(type.type2.name().equals( mDatas.get(position).get("type"))){
              return 2;
          }else if(type.type3.name().equals( mDatas.get(position).get("type"))){
                return 3;
          }else if(type.type4.name().equals( mDatas.get(position).get("type"))){
              return 4;
          }else if(type.type5.name().equals( mDatas.get(position).get("type"))){
              return 5;
          }
          else if(type.type6.name().equals( mDatas.get(position).get("type"))){
              return 6;
          }
          else if(type.type7.name().equals( mDatas.get(position).get("type"))){
              return 7;
          } else if(type.type8.name().equals( mDatas.get(position).get("type"))){
              return 8;
          } else if(type.type9.name().equals( mDatas.get(position).get("type"))){
          return 9;
      } else if(type.type10.name().equals( mDatas.get(position).get("type"))){
          return 10;
      }

        return 0;
      }



    //自定义的ViewHolder，持有每个Item的的所有界面元素
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_myspeak;
        ImageView img_myheard;
        TextView tv_time;
        ImageView image_myphead;
        ImageView image_mypicture;
        TextView  tv_redinformation;
        TextView tv_mynameredbag;
        LinearLayout LL_nameredbag;
        ImageView icon;
        TextView tv_money;
        TextView tv_wechatpay;
        TextView tv_timewvoice;
        ImageView imag_red;

        ViewHolder(View view) {
            super(view);
            image_myphead= (ImageView) view.findViewById(R.id.img_mypheard);
            tv_myspeak = (TextView) view.findViewById(R.id.tv_myspeak);
            img_myheard= (ImageView) view.findViewById(R.id.img_myheard);
            tv_time= (TextView) view.findViewById(R.id.tv_time);
            image_mypicture= (ImageView) view.findViewById(R.id.image_mypicture);
            tv_redinformation= (TextView) view.findViewById(R.id.tv_redinformation);
            tv_mynameredbag= (TextView) view.findViewById(R.id.tv_nameredbag);
            LL_nameredbag= (LinearLayout) view.findViewById(R.id.LL_nameredbag);
            icon= (ImageView) view.findViewById(R.id.icon);
            tv_money= (TextView) view.findViewById(R.id.tv_money);
            tv_wechatpay= (TextView) view.findViewById(R.id.tv_wechatpay);
            tv_timewvoice= (TextView) view.findViewById(R.id.tv_timewvoice);
            imag_red= (ImageView) view.findViewById(R.id.imag_red);
        }
    }

    private static SpannableString getExpressionString(Context context, String str, int textSize) {
        SpannableString spannableString = new SpannableString(str);
        Pattern sinaPatten = Pattern.compile(re1, Pattern.CASE_INSENSITIVE);
        try {
            dealExpression(context, spannableString, textSize, sinaPatten, 0);
        } catch (Exception e) {
            Logger.e("dealExpression"+ e.getMessage());
        }
        return spannableString;
    }

    private static void dealExpression(Context context, SpannableString spannableString, int textSize, Pattern patten, int start) throws Exception {
        Matcher matcher = patten.matcher(spannableString);
        while (matcher.find()) {
            String key = matcher.group();
            if (matcher.start() < start) {
                continue;
            }
            Field field = R.drawable.class.getDeclaredField(key);
            int resId = field.getInt(R.drawable.class);
            if (resId != 0) {
                Drawable d = context.getResources().getDrawable(resId);
                d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
                ImageSpan imageSpan = new ImageSpan(d);
                int end = matcher.start() + key.length();
                spannableString.setSpan(imageSpan, matcher.start(), end,
                        Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                if (end < spannableString.length()) {
                    dealExpression(context, spannableString, textSize, patten, end);
                }
                break;
            }
        }
    }
    private static Bitmap getRoundCornerImage(Bitmap bitmap_bg, Bitmap bitmap_in)
    {
        Bitmap roundConcerImage = Bitmap.createBitmap(bitmap_in.getWidth()/4,bitmap_in.getHeight()/4, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(roundConcerImage);
        Paint paint = new Paint();
        Rect rectF = new Rect(0, 0, bitmap_in.getWidth(), bitmap_in.getHeight());
        Rect rect = new Rect(0,0,bitmap_in.getWidth()/4,bitmap_in.getHeight()/4);
        paint.setAntiAlias(true);
        NinePatch patch = new NinePatch(bitmap_bg, bitmap_bg.getNinePatchChunk(), null);
        patch.draw(canvas, rect);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap_in, rectF, rect, paint);
        return roundConcerImage;
    }
}
