package com.test.measurelistviewofsliding.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.OverScroller;

/**
 * Created by 张忆 on 2016/9/30.
 */

public class MyListView extends ListView implements AbsListView.OnScrollListener {
    private GestureDetector mGestureDetector;

    public MyListView(Context context) {
        this(context,null);
    }

    public MyListView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnScrollListener(this);
        mGestureDetector = new GestureDetector(context,new MyOnGestureListener());
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState){
            case OnScrollListener.SCROLL_STATE_IDLE:
//                Log.d("MyListView", "运行距离：" + Math.abs(mStartScroll - getScrollY1()));
//                Log.d("MyListView", "运行时间：" + (System.currentTimeMillis() - mStartTime));
                break;
            case OnScrollListener.SCROLL_STATE_FLING:
                break;
        }
    }

    public int getScrollY1(){
        View v = getChildAt(0);
        if (v==null){
            return 0;
        }
        int firstVisivlePosition = getFirstVisiblePosition();
        int top = v.getTop();
        return -top+firstVisivlePosition*v.getHeight();
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        mGestureDetector.onTouchEvent(ev);
        return super.onTouchEvent(ev);
    }

    private long mStartTime;
    private int mStartScroll;
    private FlingScroller mScroller = new FlingScroller();
    class MyOnGestureListener extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            mScroller.withFling((int)velocityY);
            mScroller.start();
            return false;
        }
    }

    private class FlingScroller implements Runnable{

        OverScroller mFlingScroller;
        boolean isRunning;
        public FlingScroller(){
            mFlingScroller = new OverScroller(getContext());
        }

        @Override
        public void run() {
            boolean endAnima = true;
            if (mFlingScroller.computeScrollOffset()){
                final int startY = mFlingScroller.getStartY();
                int currY = startY==Integer.MAX_VALUE ?Integer.MAX_VALUE-mFlingScroller.getCurrY():mFlingScroller.getCurrY();
                Log.d("FlingScroller", "currY:" + currY);
                endAnima = false;
            }

            if (!endAnima){
                postExecute();
            }else {
                isRunning = false;
            }
        }

        private void postExecute() {
            if (isRunning)
                post(this);
        }

        private void start(){
            isRunning = true;
            postExecute();
        }

        void withFling( int velocityY) {
            int initialY = velocityY < 0 ? Integer.MAX_VALUE : 0;
            mFlingScroller.fling(0,initialY,0,velocityY,0, Integer.MAX_VALUE,0,Integer.MAX_VALUE);
        }
    }

}
