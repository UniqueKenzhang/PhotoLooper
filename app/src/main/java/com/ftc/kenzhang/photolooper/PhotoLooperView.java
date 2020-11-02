package com.ftc.kenzhang.photolooper;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;

import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.customview.widget.ViewDragHelper;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by kenzhang on 2017/1/5.
 */

public class PhotoLooperView extends RecyclerView {


    private ViewDragHelper helper;
    private PhotoItemTouchListener mPhotoItemTouchListener;
    private PhotoLayoutManager mPhotoLayoutManager;
    private float threshold = 0.6f;

    public PhotoLooperView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }


    public PhotoLooperView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PhotoLooperView(Context context) {
        super(context);
        init();
    }

    private void init() {
        mPhotoLayoutManager = new PhotoLayoutManager();
        setLayoutManager(mPhotoLayoutManager);
        mPhotoItemTouchListener = new PhotoItemTouchListener();
        mPhotoItemTouchListener.attachToRecyclerView(this);
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
        return Math.min(mPhotoLayoutManager.MAX_SHOW_COUNT, getAdapter().getItemCount());
    }

    public int getTransYGAP() {
        return mPhotoLayoutManager.TRANS_Y_GAP;
    }

    public float getScaleGap() {
        return mPhotoLayoutManager.SCALE_GAP;
    }

    public void setShowCount(int count) {
        mPhotoLayoutManager.MAX_SHOW_COUNT = count;
    }

    public void setTransYGAP(int gap) {
        mPhotoLayoutManager.TRANS_Y_GAP = gap;
    }

    public void setScaleGap(float scale) {
        mPhotoLayoutManager.SCALE_GAP = scale;

    }

    /**
     * @param threshold 0-1
     */
    public void setThreshold(float threshold) {
        this.threshold = threshold;
    }

    public float getThreshold() {
        return getWidth() * threshold;
    }
}
