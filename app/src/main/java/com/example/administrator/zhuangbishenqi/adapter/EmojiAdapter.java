package com.example.administrator.zhuangbishenqi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.zhuangbishenqi.R;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/6.
 *
 * 表情适配器
 */
public class EmojiAdapter extends RecyclerView.Adapter <EmojiAdapter.ViewHolder> {
    private ArrayList<String> mDatas;
    private Context mContext;



    public EmojiAdapter(Context context){
        this.mContext=context;

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_chat_emoji,parent,false);
//        ViewHolder vh = new ViewHolder(view);
        ViewHolder vh= new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_chat_emoji, parent, false));
        return vh;
    }
    




    @Override
    public void onBindViewHolder(final EmojiAdapter.ViewHolder holder, int position) {


        Class drawable  =  R.drawable.class;
        Field field = null;
        try {
            field = drawable.getField(mDatas.get(position));
            int r_id = field.getInt(field.getName());
            holder.mImageView.setBackgroundResource(r_id);
        } catch (Exception e) {
        }

        if(mOnItemClickLitener !=null){
            holder.mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    ArrayList<String> mDatas=setDatas();
                    mOnItemClickLitener.onItemClick(holder.mImageView, pos,mDatas);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        try{
            return setDatas().size();
        }catch (Exception e){
            return 0;
        }
    }


    public ArrayList<String> setDatas(){
        mDatas =new ArrayList<>();
        for(int i=0;i<100;++i){
            if(i<10){
                mDatas.add("smiley_0"+i);
            }else{
                mDatas.add("smiley_"+i);
            }

        }
        return mDatas;
    }

    


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;

        public ViewHolder(View view) {
            super(view);
            mImageView= (ImageView) view.findViewById(R.id.img_emoji);

        }

 }
    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position , ArrayList<String> mDatas);

    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

}
