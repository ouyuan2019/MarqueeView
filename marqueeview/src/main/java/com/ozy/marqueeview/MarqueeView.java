package com.ozy.marqueeview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ozy on 2017/5/28.
 */

public class MarqueeView extends ViewFlipper {

    private Context mContext;

    private int mInterval;

    private List<?> mNoticeList = new ArrayList<>();

    private int mDuartion;

    public MarqueeView(Context context) {
        this(context, null);
    }

    public MarqueeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;

        TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.MarqueeView);
        mInterval = a.getInteger(R.styleable.MarqueeView_mv_interval, 2000);
        mDuartion = a.getInteger(R.styleable.MarqueeView_mv_duration, 300);
        a.recycle();
        //设置间隔时间
        setFlipInterval(mInterval);
    }


    public void start() {
        if (mNoticeList == null || mNoticeList.size() == 0) return;
        removeAllViews();
        resetAnimation();
        for (int i = 0; i < mNoticeList.size(); i++) {
            if (mViewFactory == null) {
                throw new RuntimeException("Please realize ViewFactory first");
            }
            View view = mViewFactory.onCreateView(mNoticeList.get(i), i);
            addView(view);
        }
        if (mNoticeList.size() > 1) {
            startFlipping();
        } else {
            stopFlipping();
        }
    }


    private void resetAnimation() {
        clearAnimation();
        Animation animIn = AnimationUtils.loadAnimation(mContext, R.anim.anim_marquee_in);
        animIn.setDuration(mDuartion);
        setInAnimation(animIn);

        Animation animOut = AnimationUtils.loadAnimation(mContext, R.anim.anim_marquee_out);
        animOut.setDuration(mDuartion);
        setOutAnimation(animOut);
    }


    public MarqueeView setNoticeList(List<?> noticeList) {
        this.mNoticeList = noticeList;
        return this;

    }

    public interface ViewFactory<T> {
        View onCreateView(T o, int position);
    }

    public ViewFactory mViewFactory;

    public MarqueeView setViewFactory(ViewFactory viewFactory) {
        this.mViewFactory = viewFactory;
        return this;
    }
}
