package com.example.administrator.zhuangbishenqi.adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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


/**
 * Created by Administrator on 2016/8/4.
 */
public class DialogueAdapter extends RecyclerView.Adapter <DialogueAdapter.ViewHolder> {
    private ArrayList<Map<String,String>>mDatas;

    private Context mContext;
    static String re1="(smiley_[a-z0-9]{2})";



    //创建新View，被LayoutManager所调用,加载Item布局
    public DialogueAdapter(ArrayList<Map<String,String>>mDatas,Context mContext){
        this.mContext=mContext;
        this.mDatas =mDatas;

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_chat_speak,parent,false);
        ViewHolder vh= new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Name name = new Name();
//        holder.tv_dialogut.setText(mDatas.get(position));
        if(mDatas.get(position).get("type").equals("type1")){
            Logger.i("null"+mDatas.get(position).get("text"));
            holder.tv_dialogut.setText(getExpressionString(mContext, mDatas.get(position).get("text"), 20));
            Logger.i("图片"+mDatas.get(position).get("image"));
            Bitmap mybt = BitmapFactory.decodeFile(mDatas.get(position).get("image"));
            if(mybt==null){
                name.setImag(holder.img_head,"img_10005");
            }else{
                holder.img_head.setImageBitmap(mybt);
            }


        }
          else if(mDatas.get(position).get("type").equals("type2")){
                holder.tv_dialogut.setText(getExpressionString(mContext, mDatas.get(position).get("text"), 20));
            Bitmap yourbt = BitmapFactory.decodeFile(mDatas.get(position).get("image"));
            if(yourbt==null){
                name.setImag(holder.img_head,"img_10025");
            }else {
                holder.img_head.setImageBitmap(yourbt);
            }

        } else if(mDatas.get(position).get("type").equals("type3")){

            Bitmap mybt = BitmapFactory.decodeFile(WxChat.path+"myHead.jpg");
            if(mybt==null){
                name.setImag(holder.img_head,"img_10005");
            }else{
                holder.img_head.setImageBitmap(mybt);
            }
            holder.tv_dialogut.setText("[图片]");

        }else if(mDatas.get(position).get("type").equals("type4")) {
            Bitmap mybt = BitmapFactory.decodeFile(WxChat.path + "yourHead.jpg");
            if(mybt==null){
                name.setImag(holder.img_head,"img_10025");
            }else {
                holder.img_head.setImageBitmap(mybt);
            }

            holder.tv_dialogut.setText("[图片]");
        }else if(mDatas.get(position).get("type").equals("type5")){

            Bitmap mybt = BitmapFactory.decodeFile(WxChat.path + "myHead.jpg");
            if(mybt==null){
                name.setImag(holder.img_head,"img_10005");
            }else{
                holder.img_head.setImageBitmap(mybt);
            }
            holder.tv_dialogut.setText("[发送红吧]");
        }else if(mDatas.get(position).get("type").equals("type6")) {

            Bitmap mybt = BitmapFactory.decodeFile(WxChat.path + "yourHead.jpg");
            if(mybt==null){
                name.setImag(holder.img_head,"img_10025");
            }else {
                holder.img_head.setImageBitmap(mybt);
            }
            holder.tv_dialogut.setText("[发送红吧]");
        }else if(mDatas.get(position).get("type").equals("type7")){

            Bitmap mybt = BitmapFactory.decodeFile(WxChat.path + "myHead.jpg");
            if(mybt==null){
                name.setImag(holder.img_head,"img_10005");
            }else{
                holder.img_head.setImageBitmap(mybt);
            }
            holder.tv_dialogut.setText("[转账]"+ mDatas.get(position).get("redinformation")+"元");
        }else if(mDatas.get(position).get("type").equals("type8")){

            Bitmap mybt = BitmapFactory.decodeFile(WxChat.path + "yourHead.jpg");
            if(mybt==null){
                name.setImag(holder.img_head,"img_10025");
            }else {
                holder.img_head.setImageBitmap(mybt);
            }
            holder.tv_dialogut.setText("[转账]"+ mDatas.get(position).get("redinformation")+"元");
        }else if(mDatas.get(position).get("type").equals("type9")){

            Bitmap mybt = BitmapFactory.decodeFile(WxChat.path + "myHead.jpg");
            if(mybt==null){
                name.setImag(holder.img_head,"img_10005");
            }else{
                holder.img_head.setImageBitmap(mybt);
            }
            holder.tv_dialogut.setText("[语音]"+ mDatas.get(position).get("redinformation")+"\"");

        }else if(mDatas.get(position).get("type").equals("type10")){
            Bitmap mybt = BitmapFactory.decodeFile(WxChat.path + "yourHead.jpg");
            if(mybt==null){
                name.setImag(holder.img_head,"img_10025");
            }else {
                holder.img_head.setImageBitmap(mybt);
            }
            holder.tv_dialogut.setText("[语音]"+ mDatas.get(position).get("redinformation")+"\"");
        }

        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               removeData(position);
                com.orhanobut.logger.Logger.i("移除"+position);
            }
        });


    }



    @Override
    public int getItemCount() {
        try{
           return mDatas.size();
        }catch (Exception e){
            return 0;
        }

    }



    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_dialogut;
        ImageView img_head;
        Button btn_delete;
        public ViewHolder(View view) {
            super(view);
            tv_dialogut = (TextView) view.findViewById(R.id.tv_dialogue);
            img_head= (ImageView) view.findViewById(R.id.img_item_head);
            btn_delete= (Button) view.findViewById(R.id.btn_delete);

        }
    }
    public void removeData(int position ) {
        mDatas.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
        com.orhanobut.logger.Logger.i("移除"+position);
    }

    public static SpannableString getExpressionString(Context context, String str, int textSize) {
        SpannableString spannableString = new SpannableString(str);
        Pattern sinaPatten = Pattern.compile(re1, Pattern.CASE_INSENSITIVE);
        try {
            dealExpression(context, spannableString, textSize, sinaPatten, 0);
        } catch (Exception e) {
                Logger.e("dealExpression"+ e.getMessage());
        }
        return spannableString;
    }

    public static void dealExpression(Context context, SpannableString spannableString, int textSize, Pattern patten, int start) throws Exception {
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
}
