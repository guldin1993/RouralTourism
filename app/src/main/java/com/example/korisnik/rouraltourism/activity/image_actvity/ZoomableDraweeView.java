package com.example.korisnik.rouraltourism.activity.image_actvity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

import com.facebook.drawee.view.SimpleDraweeView;

/**
 * A SimpleDraweeView that supports Pinch to zoom.
 */
public class ZoomableDraweeView extends SimpleDraweeView {
    private final ScaleGestureDetector mScaleDetector;
    private final ScaleGestureDetector.OnScaleGestureListener mScaleListener;

    private float mCurrentScale;
    private final Matrix mCurrentMatrix;
    private float mMidX;
    private float mMidY;
    @Nullable private OnZoomChangeListener mListener;

    public ZoomableDraweeView(Context context) {
        this(context, null);
    }

    public ZoomableDraweeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ZoomableDraweeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mScaleListener = new ScaleGestureDetector.SimpleOnScaleGestureListener() {
            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                float scaleFactor = detector.getScaleFactor();
                float newScale = mCurrentScale * scaleFactor;
                if (newScale > 1.0f) {
                    if (mMidX == 0.0f) {
                        mMidX = getWidth() / 2.0f;
                    }
                    if (mMidY == 0.0f) {
                        mMidY = getHeight() / 2.0f;
                    }
                    mCurrentScale = newScale;
                    mCurrentMatrix.postScale(scaleFactor, scaleFactor, mMidX, mMidY);
                    invalidate();
                } else {
                    scaleFactor = 1.0f / mCurrentScale;
                    reset();
                }

                if (mListener != null && scaleFactor != 1.0f) {
                    mListener.onZoomChange(mCurrentScale);
                }

                return true;
            }
        };
        mScaleDetector = new ScaleGestureDetector(getContext(), mScaleListener);
        mCurrentMatrix = new Matrix();
    }

    public void setOnZoomChangeListener(OnZoomChangeListener listener) {
        mListener = listener;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setOnZoomChangeListener(null);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        int saveCount = canvas.save();
        canvas.concat(mCurrentMatrix);
        super.onDraw(canvas);
        canvas.restoreToCount(saveCount);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mScaleDetector.onTouchEvent(event) || super.onTouchEvent(event);
    }

    public void resetZoom() {
        reset();
    }

    public void reset() {
        mCurrentMatrix.reset();
        mCurrentScale = 1.0f;
        invalidate();
    }

    public interface OnZoomChangeListener {

        public void onZoomChange(float scale);
    }
}