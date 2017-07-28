package com.ftc.kenzhang.photolooper;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by kenzhang on 2017/1/6.
 */

public class PhotoLayoutManager extends RecyclerView.LayoutManager {

    public int TRANS_Y_GAP = 40;
    public int MAX_SHOW_COUNT = 5;
    public float SCALE_GAP = 0.03f;

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        detachAndScrapAttachedViews(recycler);
        int itemCount = getItemCount() - 1;
        if (itemCount < 0) {
            return;
        }

        for (int position = MAX_SHOW_COUNT; position >= 0; position--) {
            View view = recycler.getViewForPosition(position);
            addView(view);
            measureChildWithMargins(view, 0, 0);
            int widthSpace = getWidth() - getDecoratedMeasuredWidth(view);
            int heightSpace = getHeight() - getDecoratedMeasuredHeight(view);
            //对view进行布局
            layoutDecoratedWithMargins(view, widthSpace / 2, heightSpace / 2,
                    widthSpace / 2 + getDecoratedMeasuredWidth(view),
                    heightSpace / 2 + getDecoratedMeasuredHeight(view));

            int level = position;

            //末尾两项一样
            if (level == MAX_SHOW_COUNT) {
                level = MAX_SHOW_COUNT - 1;
            }

            view.setTranslationY(TRANS_Y_GAP * level);
            view.setScaleX(1 - SCALE_GAP * level);
            view.setScaleY(1 - SCALE_GAP * level);
        }
    }
}
