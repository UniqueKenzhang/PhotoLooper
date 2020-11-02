package com.ftc.kenzhang.photolooper;

import android.view.MotionEvent;
import android.view.View;

import androidx.core.view.ViewCompat;
import androidx.customview.widget.ViewDragHelper;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by kenzhang on 2017/1/5.
 */

public class PhotoItemTouchListener implements RecyclerView.OnItemTouchListener {


    private ViewDragHelper mDragHelper;
    private PhotoLooperView mRecyclerView;
    private int initCenterViewX;
    private int initCenterViewY;
    private OnSwipListener onSwipListener;
    private boolean mOverThres;
    private View cache;
    private int curp;


    public void attachToRecyclerView(PhotoLooperView recyclerView) {
        this.mRecyclerView = recyclerView;
        mDragHelper = ViewDragHelper.create(recyclerView, 10f, new DragHelperCallback());
        initCenterViewX = recyclerView.getLeft();
        initCenterViewY = recyclerView.getTop();
        recyclerView.addOnItemTouchListener(this);
        recyclerView.setDragHelper(mDragHelper);
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent event) {
        return mDragHelper.shouldInterceptTouchEvent(event);
    }

    @Override
    public void onTouchEvent(RecyclerView view, MotionEvent event) {
        mDragHelper.processTouchEvent(event);
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                curp = 0;
            }
        });

    }

    private class DragHelperCallback extends ViewDragHelper.Callback {

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            processLinkageView(initCenterViewX - left, initCenterViewY - top);
        }

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            int childAdapterPosition = mRecyclerView.getChildAdapterPosition(child);
            return childAdapterPosition != RecyclerView.NO_POSITION;
        }

        @Override
        public int getViewHorizontalDragRange(View child) {
            return 256;
        }

        @Override
        public void onViewDragStateChanged(int state) {
            super.onViewDragStateChanged(state);
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            int childCount = mRecyclerView.getChildCount();
            if (mOverThres && childCount > 1) {
                mOverThres = false;
                RecyclerView.Adapter adapter = mRecyclerView.getAdapter();
                int itemCount = adapter.getItemCount();

                if (cache != null) {
                    int position = mRecyclerView.getShowCount() + curp;
                    if (position >= itemCount) {
                        position -= itemCount;
                    }

                    adapter.onBindViewHolder(mRecyclerView.getChildViewHolder(cache), position);

                    mRecyclerView.removeCallbacks(showBottomTask);
                    mRecyclerView.postDelayed(showBottomTask, 500);
                }
                cache = releasedChild;

                if (onSwipListener != null) {
                    onSwipListener.onSwip(releasedChild);
                }
                int min = Math.min(mRecyclerView.getShowCount(), adapter.getItemCount() - 1);


                adapter.notifyItemMoved(0, min);


                curp++;
                if (curp == itemCount) {
                    curp = 0;
                }
            } else {
                mDragHelper.smoothSlideViewTo(releasedChild, initCenterViewX, initCenterViewY);
                ViewCompat.postInvalidateOnAnimation(mRecyclerView);
            }
        }


        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            return left;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return top;
        }
    }

    private void processLinkageView(int dX, int dY) {
        double swipValue = Math.sqrt(dX * dX + dY * dY);
        double fraction = swipValue / getThreshold();
        //边界修正 最大为1
        if (fraction > 1) {
            fraction = 1;
            mOverThres = true;
        } else {
            mOverThres = false;
        }

        int childCount = mRecyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = mRecyclerView.getChildAt(i);
            //第几层,举例子，count =7， 最后一个TopView（6）是第0层，
            int level = childCount - i - 1;
            if (level > 0 && level < mRecyclerView.getShowCount()) {
                child.setScaleX((float) (1 - mRecyclerView.getScaleGap() * level + fraction * mRecyclerView.getScaleGap()));
                child.setScaleY((float) (1 - mRecyclerView.getScaleGap() * level + fraction * mRecyclerView.getScaleGap()));
                child.setTranslationY((float) (mRecyclerView.getTransYGAP() * level - fraction * mRecyclerView.getTransYGAP()));
            } else if (level == mRecyclerView.getShowCount()) {
                child.setScaleX(1 - mRecyclerView.getScaleGap() * (mRecyclerView.getShowCount() - 1));
                child.setScaleY(1 - mRecyclerView.getScaleGap() * (mRecyclerView.getShowCount() - 1));
                child.setTranslationY(mRecyclerView.getTransYGAP() * (mRecyclerView.getShowCount() - 1));
            }
        }
    }

    public void setOnSwipListener(OnSwipListener l) {
        this.onSwipListener = l;
    }

    public interface OnSwipListener {
        void onSwip(View itemView);
    }


    //是否可以被回收掉的阈值
    public float getThreshold() {
        return mRecyclerView.getThreshold();
    }

    public int getCurrentPosition() {
        return curp;
    }

    Runnable showBottomTask = new Runnable() {
        @Override
        public void run() {
            processLinkageView(initCenterViewX, initCenterViewY);
        }
    };

}
