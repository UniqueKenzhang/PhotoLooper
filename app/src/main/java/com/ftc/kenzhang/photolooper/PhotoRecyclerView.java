package com.ftc.kenzhang.photolooper;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.TypedValue;

/**
 * Created by kenzhang on 2017/1/5.
 */

public class PhotoRecyclerView extends RecyclerView {


    private ViewDragHelper helper;
    private PhotoItemTouchListener mPhotoItemTouchListener;
    private PhotoLayoutManager mPhotoLayoutManager;
    private float threshold = 0.6f;

    public PhotoRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }


    public PhotoRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PhotoRecyclerView(Context context) {
        super(context);
        init();
    }

    private void init() {
        mPhotoLayoutManager = new PhotoLayoutManager();
        setLayoutManager(mPhotoLayoutManager);
        mPhotoItemTouchListener = new PhotoItemTouchListener();
        mPhotoItemTouchListener.attachToRecyclerView(this);
    }

    public void initConfig(Context context) {
        mPhotoLayoutManager.MAX_SHOW_COUNT = 4;
        mPhotoLayoutManager.SCALE_GAP = 0.02f;
        mPhotoLayoutManager.TRANS_Y_GAP = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6.5f, context.getResources().getDisplayMetrics());
    }


    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        mPhotoItemTouchListener.setAdapter(adapter);
    }

    public void setOnSwipListener(PhotoItemTouchListener.OnSwipListener l) {
        mPhotoItemTouchListener.setOnSwipListener(l);
    }


    public void setDragHelper(ViewDragHelper helper) {
        this.helper = helper;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (helper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public int getCurrentPosition() {
        return mPhotoItemTouchListener.getCurrentPosition();
    }

    public int getShowCount() {
        return mPhotoLayoutManager.MAX_SHOW_COUNT;
    }

    public int getTransYGAP() {
        return mPhotoLayoutManager.TRANS_Y_GAP;
    }

    public float getScaleGap() {
        return mPhotoLayoutManager.SCALE_GAP;
    }

    /**
     * Threshold for recycle ;
     *
     * @param threshold 0-1
     */
    public void setThreshold(float threshold) {
        this.threshold = threshold;
    }

    public float getThreshold() {
        return getWidth() * threshold;
    }
}
