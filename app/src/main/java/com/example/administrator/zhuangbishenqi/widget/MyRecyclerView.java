package com.example.administrator.zhuangbishenqi.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2016/8/6.
 */
public class MyRecyclerView extends RecyclerView {
    private int spanRow = 3; // 行数
    private int spanColumn = 2; // 每页列数
    private Context mContext = null;
    public MyRecyclerView(Context context) {
        super(context);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);



//        defaultInit(context);
    }
//    初始化
//    private void defaultInit(Context context) {
//        this.mContext = context;
//        setLayoutManager(new GridLayoutManager(
//                mContext, spanRow, GridLayoutManager.VERTICAL, false));
//        setOverScrollMode(OVER_SCROLL_NEVER);
//    }

    public void setPageSize(int spanRow, int spanColumn) {
        this.spanRow = spanRow <= 0 ? this.spanRow : spanRow;
        this.spanColumn = spanColumn <= 0 ? this.spanColumn : spanColumn;

        setLayoutManager(new AutoGridLayoutManager(
                mContext, this.spanRow, AutoGridLayoutManager.VERTICAL, false));
    }


}
