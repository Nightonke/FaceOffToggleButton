package com.nightonke.faceofftogglebutton;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.CompoundButton;

/**
 * Created by Weiping on 2016/5/21.
 */

public class FaceOffToggleButton extends CompoundButton {

    private static final int DEFAULT_BACKGROUND_COLOR = Color.parseColor("#FFFFFF");
    private static final int DEFAULT_LEFT_BACKGROUND_COLOR = Color.parseColor("#D3CFCF");
    private static final int DEFAULT_RIGHT_BACKGROUND_COLOR = Color.parseColor("#49B6EB");
    private static final int DEFAULT_LEFT_FACE_COLOR = Color.parseColor("#FFFFFF");
    private static final int DEFAULT_RIGHT_FACE_COLOR = Color.parseColor("#FFFFFF");
    private static final int DEFAULT_LEFT_EYE_COLOR = Color.parseColor("#D3CFCF");
    private static final int DEFAULT_RIGHT_EYE_COLOR = Color.parseColor("#49B6EB");
    private static final int DEFAULT_LEFT_MOUTH_COLOR = Color.parseColor("#D3CFCF");
    private static final int DEFAULT_RIGHT_MOUTH_COLOR = Color.parseColor("#49B6EB");

    private static final int DEFAULT_FACE_RADIUS_DP = 15;
    private static final float DEFAULT_WIDTH_RADIUS_RATIO = 2f;
    private static final int DEFAULT_FACE_MARGIN_DP = 2;

    private static final int DEFAULT_DURATION = 500;

    private static final boolean DEFAULT_MOVE_TO_SAME_STATE_CALL_LISTENER = false;
    private static final boolean DEFAULT_DRAGGABLE = true;

    private static final ColorChangeType DEFAULT_COLOR_CHANGE_TYPE = ColorChangeType.RGB;

    private static final float DEFAULT_TOUCH_MOVE_RATIO_VALUE = 3.0f;
    private static final float DEFAULT_BEZIER_CONTROL_VALUE = 0.551915024494f;

    private int mBackgroundColor = DEFAULT_BACKGROUND_COLOR;
    private int mLeftBackgroundColor = DEFAULT_LEFT_BACKGROUND_COLOR;
    private int mRightBackgroundColor = DEFAULT_RIGHT_BACKGROUND_COLOR;
    private int mLeftFaceColor = DEFAULT_LEFT_FACE_COLOR;
    private int mRightFaceColor = DEFAULT_RIGHT_FACE_COLOR;
    private int mLeftEyeColor = DEFAULT_LEFT_EYE_COLOR;
    private int mRightEyeColor = DEFAULT_RIGHT_EYE_COLOR;
    private int mLeftMouthColor = DEFAULT_LEFT_MOUTH_COLOR;
    private int mRightMouthColor = DEFAULT_RIGHT_MOUTH_COLOR;

    private float mFaceRadius = -1;
    private float mWidthRadiusRatio = -1;
    private float mFaceMargin = -1;

    private int mDuration = DEFAULT_DURATION;

    private boolean mMoveToSameStateCallListener = DEFAULT_MOVE_TO_SAME_STATE_CALL_LISTENER;
    private boolean mDraggable = DEFAULT_DRAGGABLE;

    private ColorChangeType mColorChangeType = DEFAULT_COLOR_CHANGE_TYPE;

    private float mTouchMoveRatioValue = DEFAULT_TOUCH_MOVE_RATIO_VALUE;
    private float mBezierControlValue = DEFAULT_BEZIER_CONTROL_VALUE;

    private ValueAnimator mProcessAnimator;

    private float mProcess;

    private static final float eyeCenterMarginRatio = 0.2f;
    private static final float eyeWidthRatio = 0.12f;
    private static final float eyeHeightRatio = 0.2f;
    private static final float eyeTopRatio = 0.3f;

    private static final float leftMouthTopRatio = 0.65f;
    private static final float leftMouthWidthRatio = 0.44f;
    private static final float leftMouthHeightRatio = 0.07f;

    private static final float rightMouthTopRatio = 0.60f;
    private static final float rightMouthWidthRatio = 0.48f;
    private static final float rightMouthHeightRatio = 0.3f;

    private static final float DS_Ratio = 1f;

    private Paint mPaint;

    private Path mBorderPath;
    private Path mBackgroundPath;
    private RectF mHoleRectF;

    private RectF mBackgroundRectF;
    private RectF mLeftFaceRectF;
    private RectF mRightFaceRectF;

    private RectF mLeftFaceLeftEyeRectF;
    private RectF mLeftFaceRightEyeRectF;
    private RectF mLeftFaceMouthRectF;

    private RectF mRightFaceLeftEyeRectF;
    private RectF mRightFaceRightEyeRectF;
    private RectF mRightFaceMouthRectF;
    private Path mRightMouthPath;

    private State lastState = null;
    private State mState;

    private float mStartX, mStartY, mLastX;
    private int mTouchSlop;
    private int mClickTimeout;

    private OnStateChangeListener mOnStateChangeListener;

    private boolean mStopRestoreChecked = false;

    private float S;
    private float R2;
    private float S1;
    private float S2;
    private float D;

    private float backgroundRadius;
    private float bezierControlWidth1;
    private float bezierControlHeight1;
    private float bezierControlWidth2;
    private float bezierControlHeight2;

    public FaceOffToggleButton(Context context) {
        super(context);
        init(null);
    }

    public FaceOffToggleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public FaceOffToggleButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        mClickTimeout = ViewConfiguration.getPressedStateDuration() +
                ViewConfiguration.getTapTimeout();

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBackgroundRectF = new RectF();
        mHoleRectF = new RectF();
        mBorderPath = new Path();
        mBorderPath.setFillType(Path.FillType.EVEN_ODD);
        mBackgroundPath = new Path();
        mBackgroundPath.setFillType(Path.FillType.EVEN_ODD);
        mRightMouthPath = new Path();

        mLeftFaceRectF = new RectF();
        mLeftFaceLeftEyeRectF = new RectF();
        mLeftFaceRightEyeRectF = new RectF();
        mLeftFaceMouthRectF = new RectF();

        mRightFaceRectF = new RectF();
        mRightFaceLeftEyeRectF = new RectF();
        mRightFaceRightEyeRectF = new RectF();
        mRightFaceMouthRectF = new RectF();

        setAnimator(0, true);

        Resources res = getResources();
        float density = res.getDisplayMetrics().density;

        TypedArray ta = attrs == null ?
                null : getContext().obtainStyledAttributes(attrs, R.styleable.FaceOffToggleButton);
        if (ta != null) {
            
            mBackgroundColor = ta.getColor(R.styleable.FaceOffToggleButton_foBackgroundColor, DEFAULT_BACKGROUND_COLOR);
            mLeftBackgroundColor = ta.getColor(R.styleable.FaceOffToggleButton_foLeftBackgroundColor, DEFAULT_LEFT_BACKGROUND_COLOR);
            mRightBackgroundColor = ta.getColor(R.styleable.FaceOffToggleButton_foRightBackgroundColor, DEFAULT_RIGHT_BACKGROUND_COLOR);
            mLeftFaceColor = ta.getColor(R.styleable.FaceOffToggleButton_foLeftFaceColor, DEFAULT_LEFT_FACE_COLOR);
            mRightFaceColor = ta.getColor(R.styleable.FaceOffToggleButton_foRightFaceColor, DEFAULT_RIGHT_FACE_COLOR);
            mLeftEyeColor = ta.getColor(R.styleable.FaceOffToggleButton_foLeftEyeColor, DEFAULT_LEFT_EYE_COLOR);
            mRightEyeColor = ta.getColor(R.styleable.FaceOffToggleButton_foRightEyeColor, DEFAULT_RIGHT_EYE_COLOR);
            mLeftMouthColor = ta.getColor(R.styleable.FaceOffToggleButton_foLeftMouthColor, DEFAULT_LEFT_MOUTH_COLOR);
            mRightMouthColor = ta.getColor(R.styleable.FaceOffToggleButton_foRightMouthColor, DEFAULT_RIGHT_MOUTH_COLOR);

            mFaceRadius = ta.getDimensionPixelSize(R.styleable.FaceOffToggleButton_foFaceRadius, -1);
            mWidthRadiusRatio = ta.getFloat(R.styleable.FaceOffToggleButton_foWidthRadiusRatio, -1);
            mFaceMargin = ta.getDimensionPixelSize(R.styleable.FaceOffToggleButton_foFaceMargin, -1);

            mDuration = ta.getInteger(R.styleable.FaceOffToggleButton_foDuration, DEFAULT_DURATION);

            mMoveToSameStateCallListener = ta.getBoolean(R.styleable.FaceOffToggleButton_foMoveToSameStateCallListener, DEFAULT_MOVE_TO_SAME_STATE_CALL_LISTENER);
            mDraggable = ta.getBoolean(R.styleable.FaceOffToggleButton_foDraggable, DEFAULT_DRAGGABLE);

            int colorChangeTypeInteger = ta.getInteger(R.styleable.FaceOffToggleButton_foColorChangeType, -1);
            if (colorChangeTypeInteger != -1) mColorChangeType = ColorChangeType.values()[colorChangeTypeInteger];
            else mColorChangeType = DEFAULT_COLOR_CHANGE_TYPE;

            mTouchMoveRatioValue = ta.getFloat(R.styleable.FaceOffToggleButton_foTouchMoveRatioValue, DEFAULT_TOUCH_MOVE_RATIO_VALUE);

            ta.recycle();
        }

        if (mFaceRadius == -1) mFaceRadius = DEFAULT_FACE_RADIUS_DP * density;
        if (mWidthRadiusRatio == -1) mWidthRadiusRatio = DEFAULT_WIDTH_RADIUS_RATIO;
        if (mFaceMargin == -1) mFaceMargin = DEFAULT_FACE_MARGIN_DP * density;

        setFocusable(true);
        setClickable(true);

        if (isChecked()) {
            setProcess(1, false);
            mState = State.RIGHT;
        } else {
            setProcess(0, false);
            mState = State.LEFT;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
        setup();
    }

    private int measureWidth(int widthMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int measuredWidth;

        int minWidth = (int) (2 * mFaceRadius * mWidthRadiusRatio);
        minWidth = (int) Math.max(minWidth, minWidth + 2 * mFaceMargin);
        minWidth = Math.max(minWidth, minWidth + getPaddingLeft() + getPaddingRight());
        minWidth = Math.max(minWidth, getSuggestedMinimumWidth());

        if (widthMode == MeasureSpec.EXACTLY) {
            measuredWidth = Math.max(minWidth, widthSize);
        } else {
            measuredWidth = minWidth;
            if (widthMode == MeasureSpec.AT_MOST) {
                measuredWidth = Math.min(measuredWidth, widthSize);
            }
        }

        return measuredWidth;
    }

    private int measureHeight(int heightMeasureSpec) {
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int measuredHeight;

        int minHeight = (int) mFaceRadius * 2;
        minHeight = (int) Math.max(minHeight, minHeight + 2 * mFaceMargin);
        minHeight = Math.max(minHeight, minHeight + getPaddingTop() + getPaddingBottom());

        if (heightMode == MeasureSpec.EXACTLY) {
            measuredHeight = Math.max(minHeight, heightSize);
            mFaceRadius = (heightSize - mFaceMargin * 2) / 2;
        } else {
            measuredHeight = minHeight;
            if (heightMode == MeasureSpec.AT_MOST) {
                measuredHeight = Math.min(measuredHeight, heightSize);
            }
        }

        return measuredHeight;
    }

    private void setup() {

        float backgroundLeft = getPaddingLeft();
        float backgroundRight = getMeasuredWidth() - getPaddingRight();
        float backgroundTop = getPaddingTop();
        float backgroundBottom = getMeasuredHeight() - getPaddingBottom();
        mBackgroundRectF.set(backgroundLeft, backgroundTop, backgroundRight, backgroundBottom);

        float faceLeft = getPaddingLeft() + mFaceMargin;
        float faceRight = faceLeft + mFaceRadius * 2;
        float faceTop = getPaddingTop() + mFaceMargin;
        float faceBottom = faceTop + mFaceRadius * 2;
        mHoleRectF.set(faceLeft, faceTop, faceRight, faceBottom);

        R2 = mFaceRadius * 2;
        S = getMeasuredWidth() - 2 * mFaceMargin - R2;
        D = S * DS_Ratio;
        S1 = S2 = (S + R2) / 2 + D / 2;

        float leftFaceLeft = getPaddingLeft() + mFaceMargin;
        float leftFaceRight = leftFaceLeft + mFaceRadius * 2;
        float leftFaceTop = getPaddingTop() + mFaceMargin;
        float leftFaceBottom = leftFaceTop + mFaceRadius * 2;
        mLeftFaceRectF.set(leftFaceLeft, leftFaceTop, leftFaceRight, leftFaceBottom);

        float leftFaceLeftEyeLeft = mLeftFaceRectF.centerX() - eyeCenterMarginRatio * R2 / 2 - eyeWidthRatio * R2;
        float leftFaceLeftEyeTop = mLeftFaceRectF.top + eyeTopRatio * R2;
        mLeftFaceLeftEyeRectF.set(leftFaceLeftEyeLeft, leftFaceLeftEyeTop, leftFaceLeftEyeLeft + eyeWidthRatio * R2, leftFaceLeftEyeTop + eyeHeightRatio * R2);

        float leftFaceRightEyeLeft = mLeftFaceRectF.centerX() + eyeCenterMarginRatio * R2 / 2;
        float leftFaceRightEyeTop = mLeftFaceRectF.top + eyeTopRatio * R2;
        mLeftFaceRightEyeRectF.set(leftFaceRightEyeLeft, leftFaceRightEyeTop, leftFaceRightEyeLeft + eyeWidthRatio * R2, leftFaceRightEyeTop + eyeHeightRatio * R2);

        float leftFaceMouthLeft = mLeftFaceRectF.centerX() - leftMouthWidthRatio * R2 / 2;
        float leftFaceMouthTop = mLeftFaceRectF.top + leftMouthTopRatio * R2;
        mLeftFaceMouthRectF.set(leftFaceMouthLeft, leftFaceMouthTop, leftFaceMouthLeft + leftMouthWidthRatio * R2, leftFaceMouthTop + leftMouthHeightRatio * R2);

        float rightFaceLeft = getMeasuredWidth() - getPaddingRight() - mFaceMargin - S2 - mFaceRadius * 2;
        float rightFaceRight = rightFaceLeft + mFaceRadius * 2;
        float rightFaceTop = getPaddingTop() + mFaceMargin;
        float rightFaceBottom = rightFaceTop + mFaceRadius * 2;
        mRightFaceRectF.set(rightFaceLeft, rightFaceTop, rightFaceRight, rightFaceBottom);

        float rightFaceLeftEyeLeft = mRightFaceRectF.centerX() - eyeCenterMarginRatio * R2 / 2 - eyeWidthRatio * R2;
        float rightFaceLeftEyeTop = mRightFaceRectF.top + eyeTopRatio * R2;
        mRightFaceLeftEyeRectF.set(rightFaceLeftEyeLeft, rightFaceLeftEyeTop, rightFaceLeftEyeLeft + eyeWidthRatio * R2, rightFaceLeftEyeTop + eyeHeightRatio * R2);

        float rightFaceRightEyeLeft = mRightFaceRectF.centerX() + eyeCenterMarginRatio * R2 / 2;
        float rightFaceRightEyeTop = mRightFaceRectF.top + eyeTopRatio * R2;
        mRightFaceRightEyeRectF.set(rightFaceRightEyeLeft, rightFaceRightEyeTop, rightFaceRightEyeLeft + eyeWidthRatio * R2, rightFaceRightEyeTop + eyeHeightRatio * R2);

        float rightFaceMouthLeft = mRightFaceRectF.centerX() - rightMouthWidthRatio * R2 / 2;
        float rightFaceMouthTop = mRightFaceRectF.top + rightMouthTopRatio * R2;
        mRightFaceMouthRectF.set(rightFaceMouthLeft, rightFaceMouthTop, rightFaceMouthLeft + rightMouthWidthRatio * R2, rightFaceMouthTop + rightMouthHeightRatio * R2);

        backgroundRadius = mFaceRadius + mFaceMargin;
        bezierControlWidth1 = 0.25f * mRightFaceMouthRectF.width();
        bezierControlHeight1 = 0.25f * mRightFaceMouthRectF.height();
        bezierControlWidth2 = 0f;
        bezierControlHeight2 = mRightFaceMouthRectF.height() * 1.2f;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float offset = mProcess * S;
        float leftFaceOffset = mProcess * S / (S1 - R2) * S1;
        float rightFaceOffset = (mProcess - (S1 - D) / S) * S / (S2 - R2) * S2;
        if (mProcess >= (S1 - R2) / S) leftFaceOffset = S1;
        if (mProcess <= (S1 - D) / S) rightFaceOffset = 0;
        float controlDistance = backgroundRadius * mBezierControlValue;

        // The background of the hole.
        mPaint.setColor(Utils.calculateMidColor(mLeftFaceColor, mRightFaceColor, mProcess, mColorChangeType));
        canvas.drawRoundRect(mBackgroundRectF, backgroundRadius, backgroundRadius, mPaint);

        // The eyes and mouth on the left face.
        if (mProcess < (S1 - R2) / S) {
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setColor(mLeftEyeColor);
            mLeftFaceLeftEyeRectF.offset(leftFaceOffset, 0);
            canvas.drawOval(mLeftFaceLeftEyeRectF, mPaint);
            mLeftFaceRightEyeRectF.offset(leftFaceOffset, 0);
            canvas.drawOval(mLeftFaceRightEyeRectF, mPaint);
            mPaint.setColor(mLeftMouthColor);
            mLeftFaceMouthRectF.offset(leftFaceOffset, 0);
            canvas.drawRect(mLeftFaceMouthRectF, mPaint);
        }

        // The eyes and mouth on the right face.
        if (mProcess > (S1 - D) / S) {
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setColor(mRightEyeColor);
            mRightFaceLeftEyeRectF.offset(rightFaceOffset, 0);
            canvas.drawOval(mRightFaceLeftEyeRectF, mPaint);
            mRightFaceRightEyeRectF.offset(rightFaceOffset, 0);
            canvas.drawOval(mRightFaceRightEyeRectF, mPaint);

            mPaint.setColor(mRightMouthColor);
            mRightFaceMouthRectF.offset(rightFaceOffset, 0);
            mRightMouthPath.reset();
            mRightMouthPath.moveTo(mRightFaceMouthRectF.left, mRightFaceMouthRectF.top);
            mRightMouthPath.cubicTo(mRightFaceMouthRectF.left + bezierControlWidth1, mRightFaceMouthRectF.top + bezierControlHeight1, mRightFaceMouthRectF.right - bezierControlWidth1, mRightFaceMouthRectF.top + bezierControlHeight1, mRightFaceMouthRectF.right, mRightFaceMouthRectF.top);
            mRightMouthPath.cubicTo(mRightFaceMouthRectF.right - bezierControlWidth2, mRightFaceMouthRectF.top + bezierControlHeight2, mRightFaceMouthRectF.left + bezierControlWidth2, mRightFaceMouthRectF.top + bezierControlHeight2, mRightFaceMouthRectF.left, mRightFaceMouthRectF.top);
            mRightMouthPath.close();
            canvas.drawPath(mRightMouthPath, mPaint);
        }

        mPaint.setColor(Utils.calculateMidColor(mLeftBackgroundColor, mRightBackgroundColor, mProcess, mColorChangeType));
        mPaint.setStyle(Paint.Style.FILL);

        mBackgroundPath.reset();

        // The background.
        mBackgroundPath.moveTo(mBackgroundRectF.left + backgroundRadius, mBackgroundRectF.top);
        mBackgroundPath.lineTo(mBackgroundRectF.right - backgroundRadius, mBackgroundRectF.top);
        mBackgroundPath.cubicTo(mBackgroundRectF.right - backgroundRadius + controlDistance, mBackgroundRectF.top, mBackgroundRectF.right, mBackgroundRectF.top + backgroundRadius - controlDistance, mBackgroundRectF.right, mBackgroundRectF.top + backgroundRadius);
        mBackgroundPath.cubicTo(mBackgroundRectF.right, mBackgroundRectF.top + backgroundRadius + controlDistance, mBackgroundRectF.right - backgroundRadius + controlDistance, mBackgroundRectF.bottom, mBackgroundRectF.right - backgroundRadius, mBackgroundRectF.bottom);
        mBackgroundPath.lineTo(mBackgroundRectF.left + backgroundRadius, mBackgroundRectF.bottom);
        mBackgroundPath.cubicTo(mBackgroundRectF.left + backgroundRadius - controlDistance, mBackgroundRectF.bottom, mBackgroundRectF.left, mBackgroundRectF.top + backgroundRadius + controlDistance, mBackgroundRectF.left, mBackgroundRectF.top + backgroundRadius);
        mBackgroundPath.cubicTo(mBackgroundRectF.left, mBackgroundRectF.top + backgroundRadius - controlDistance, mBackgroundRectF.left + backgroundRadius - controlDistance, mBackgroundRectF.top, mBackgroundRectF.left + backgroundRadius, mBackgroundRectF.top);
        mBackgroundPath.close();

        controlDistance = mFaceRadius * mBezierControlValue;

        // The hole round.
        mBackgroundPath.moveTo(offset + mHoleRectF.left + mFaceRadius, mHoleRectF.top);
        mBackgroundPath.cubicTo(offset + mHoleRectF.left + mFaceRadius + controlDistance, mHoleRectF.top, offset + mHoleRectF.right, mHoleRectF.top + mFaceRadius - controlDistance, offset + mHoleRectF.right, mHoleRectF.top + mFaceRadius);
        mBackgroundPath.cubicTo(offset + mHoleRectF.right, mHoleRectF.top + mFaceRadius + controlDistance, offset + mHoleRectF.left + mFaceRadius + controlDistance, mHoleRectF.bottom, offset + mHoleRectF.left + mFaceRadius, mHoleRectF.bottom);
        mBackgroundPath.cubicTo(offset + mHoleRectF.left + mFaceRadius - controlDistance, mHoleRectF.bottom, offset + mHoleRectF.left, mHoleRectF.top + mFaceRadius + controlDistance, offset + mHoleRectF.left, mHoleRectF.top + mFaceRadius);
        mBackgroundPath.cubicTo(offset + mHoleRectF.left, mHoleRectF.top + mFaceRadius - controlDistance, offset + mHoleRectF.left + mFaceRadius - controlDistance, mHoleRectF.top, offset + mHoleRectF.left + mFaceRadius, mHoleRectF.top);
        mBackgroundPath.close();

        canvas.drawPath(mBackgroundPath, mPaint);

        mBorderPath.reset();

        mBorderPath.moveTo(0, 0);
        mBorderPath.lineTo(getMeasuredWidth(), 0);
        mBorderPath.lineTo(getMeasuredWidth(), getMeasuredHeight());
        mBorderPath.lineTo(0, getMeasuredHeight());
        mBorderPath.close();

        controlDistance = backgroundRadius * mBezierControlValue;

        // The hole of the background.
        mBorderPath.moveTo(mBackgroundRectF.left + backgroundRadius, mBackgroundRectF.top);
        mBorderPath.lineTo(mBackgroundRectF.right - backgroundRadius, mBackgroundRectF.top);
        mBorderPath.cubicTo(mBackgroundRectF.right - backgroundRadius + controlDistance, mBackgroundRectF.top, mBackgroundRectF.right, mBackgroundRectF.top + backgroundRadius - controlDistance, mBackgroundRectF.right, mBackgroundRectF.top + backgroundRadius);
        mBorderPath.cubicTo(mBackgroundRectF.right, mBackgroundRectF.top + backgroundRadius + controlDistance, mBackgroundRectF.right - backgroundRadius + controlDistance, mBackgroundRectF.bottom, mBackgroundRectF.right - backgroundRadius, mBackgroundRectF.bottom);
        mBorderPath.lineTo(mBackgroundRectF.left + backgroundRadius, mBackgroundRectF.bottom);
        mBorderPath.cubicTo(mBackgroundRectF.left + backgroundRadius - controlDistance, mBackgroundRectF.bottom, mBackgroundRectF.left, mBackgroundRectF.top + backgroundRadius + controlDistance, mBackgroundRectF.left, mBackgroundRectF.top + backgroundRadius);
        mBorderPath.cubicTo(mBackgroundRectF.left, mBackgroundRectF.top + backgroundRadius - controlDistance, mBackgroundRectF.left + backgroundRadius - controlDistance, mBackgroundRectF.top, mBackgroundRectF.left + backgroundRadius, mBackgroundRectF.top);
        mBorderPath.close();

        mPaint.setColor(mBackgroundColor);
        canvas.drawPath(mBorderPath, mPaint);

        // Calculate back the offset.
        if (mProcess < (S1 - R2) / S) {
            mLeftFaceLeftEyeRectF.offset(-leftFaceOffset, 0);
            mLeftFaceRightEyeRectF.offset(-leftFaceOffset, 0);
            mLeftFaceMouthRectF.offset(-leftFaceOffset, 0);
        }

        if (mProcess > (S1 - D) / S) {
            mRightFaceLeftEyeRectF.offset(-rightFaceOffset, 0);
            mRightFaceRightEyeRectF.offset(-rightFaceOffset, 0);
            mRightFaceMouthRectF.offset(-rightFaceOffset, 0);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (!isEnabled() || !isClickable()) {
            return false;
        }

        int action = event.getAction();

        float deltaX = event.getX() - mStartX;
        float deltaY = event.getY() - mStartY;

        // status the view going to change to when finger released
        boolean nextStatus;

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                catchView();
                mStartX = event.getX();
                mStartY = event.getY();
                mLastX = mStartX;
                setPressed(true);
                break;

            case MotionEvent.ACTION_MOVE:
                if (!mDraggable) return true;
                float x = event.getX();
                setProcess(getProcess() + (x - mLastX) / (S * mTouchMoveRatioValue), true);
                mLastX = x;
                break;

            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                setPressed(false);
                nextStatus = getStatusBasedOnPos();
                float time = event.getEventTime() - event.getDownTime();
                if (deltaX < mTouchSlop && deltaY < mTouchSlop && time < mClickTimeout) {
                    performClick();
                } else {
                    if (nextStatus != isChecked()) {
                        playSoundEffect(SoundEffectConstants.CLICK);
                        animateToState(nextStatus, true, true);
                    } else {
                        animateToState(nextStatus, mMoveToSameStateCallListener, true);
                    }
                }
                break;

            default:
                break;
        }
        return true;
    }

    private boolean getStatusBasedOnPos() {
        return getProcess() > 0.5f;
    }

    private final float getProcess() {
        return mProcess;
    }

    private void setProcess(float process, boolean callListener) {
        float tp = process;
        if (tp >= 1) {
            tp = 1;
            mState = State.RIGHT;
        } else if (tp <= 0) {
            tp = 0;
            mState = State.LEFT;
        } else {
            if (mState.equals(State.RIGHT)) {
                mState = State.RIGHT_TO_LEFT;
            } else if (mState.equals(State.LEFT)) {
                mState = State.LEFT_TO_RIGHT;
            }
        }
        this.mProcess = tp;
        if (mState.equals(State.LEFT)) {
            super.setChecked(false);
        }
        if (mState.equals(State.RIGHT)) {
            super.setChecked(true);
        }
        if (callListener && mOnStateChangeListener != null) {
            if (mState.equals(State.LEFT) || mState.equals(State.RIGHT)) {
                // at this time, we don't need to call the listener
                if (!mState.equals(lastState)) {
                    mOnStateChangeListener.onStateChange(mProcess, mState, this);
                }
            } else {
                mOnStateChangeListener.onStateChange(mProcess, mState, this);
            }
        }
        lastState = mState;
        invalidate();
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    private void animateToState(boolean checked, boolean callListener, boolean resetToTarget) {
        if (mProcessAnimator == null) {
            return;
        }
        if (mProcessAnimator.isRunning()) {
            return;
        }
        if (checked) {
            setAnimator(1, callListener);
        } else {
            setAnimator(0, callListener);
        }
        int duration = mDuration;
        if (resetToTarget) {
            // this situation happens when user drag the thumb to target,
            // but then leave the target for a bit.
            if (checked) duration *= 1 - mProcess;
            else duration *= mProcess - 0;
        }
        mProcessAnimator.setDuration(duration);
        mProcessAnimator.start();
    }

    private void catchView() {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
    }

    @Override
    public boolean isChecked() {
        return super.isChecked();
    }

    @Override
    public void setChecked(boolean checked) {
        if (mStopRestoreChecked) return;
        setChecked(checked, true);
    }

    public void setChecked(boolean checked, boolean callListener) {
        mProcess = checked ? 0 : 1;
        if (callListener) lastState = checked ? State.LEFT : State.RIGHT;
        animateToState(checked, callListener, false);
        super.setChecked(checked);
    }

    public void setCheckedImmediately(boolean checked) {
        setCheckedImmediately(checked, true);
    }

    public void setCheckedImmediately(boolean checked, boolean callListener) {
        super.setChecked(checked);
        if (mProcessAnimator != null && mProcessAnimator.isRunning()) {
            mProcessAnimator.cancel();
        }
        if (callListener) lastState = null;
        setProcess(checked ? 1 : 0, callListener);
    }

    private void setAnimator(float target, final boolean callListener) {
        mProcessAnimator = ValueAnimator.ofFloat(mProcess, target);
        mProcessAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setProcess((Float) animation.getAnimatedValue(), callListener);
            }
        });
        mProcessAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (mProcess == 0) FaceOffToggleButton.super.setChecked(false);
                if (mProcess == 1) FaceOffToggleButton.super.setChecked(true);
                super.onAnimationEnd(animation);
            }
        });
        mProcessAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
    }

    public void toggle() {
        toggle(true);
    }

    public void toggle(boolean callListener) {
        setChecked(!isChecked(), callListener);
    }

    public void toggleImmediately() {
        toggleImmediately(true);
    }

    public void toggleImmediately(boolean callListener) {
        setCheckedImmediately(!isChecked(), callListener);
    }

    public int getBackgroundColor() {
        return mBackgroundColor;
    }

    public void setBackgroundColor(int mBackgroundColor) {
        this.mBackgroundColor = mBackgroundColor;
        invalidate();
    }

    public int getLeftBackgroundColor() {
        return mLeftBackgroundColor;
    }

    public void setLeftBackgroundColor(int mLeftBackgroundColor) {
        this.mLeftBackgroundColor = mLeftBackgroundColor;
        invalidate();
    }

    public int getRightBackgroundColor() {
        return mRightBackgroundColor;
    }

    public void setRightBackgroundColor(int mRightBackgroundColor) {
        this.mRightBackgroundColor = mRightBackgroundColor;
        invalidate();
    }

    public int getLeftFaceColor() {
        return mLeftFaceColor;
    }

    public void setLeftFaceColor(int mLeftFaceColor) {
        this.mLeftFaceColor = mLeftFaceColor;
        invalidate();
    }

    public int getRightFaceColor() {
        return mRightFaceColor;
    }

    public void setRightFaceColor(int mRightFaceColor) {
        this.mRightFaceColor = mRightFaceColor;
        invalidate();
    }

    public int getLeftEyeColor() {
        return mLeftEyeColor;
    }

    public void setLeftEyeColor(int mLeftEyeColor) {
        this.mLeftEyeColor = mLeftEyeColor;
        invalidate();
    }

    public int getRightEyeColor() {
        return mRightEyeColor;
    }

    public void setRightEyeColor(int mRightEyeColor) {
        this.mRightEyeColor = mRightEyeColor;
        invalidate();
    }

    public int getLeftMouthColor() {
        return mLeftMouthColor;
    }

    public void setLeftMouthColor(int mLeftMouthColor) {
        this.mLeftMouthColor = mLeftMouthColor;
        invalidate();
    }

    public int getRightMouthColor() {
        return mRightMouthColor;
    }

    public void setRightMouthColor(int mRightMouthColor) {
        this.mRightMouthColor = mRightMouthColor;
        invalidate();
    }

    public float getFaceRadius() {
        return mFaceRadius;
    }

    public void setFaceRadius(float mFaceRadius) {
        this.mFaceRadius = mFaceRadius;
        requestLayout();
    }

    public float getWidthRadiusRatio() {
        return mWidthRadiusRatio;
    }

    public void setWidthRadiusRatio(float mWidthRadiusRatio) {
        this.mWidthRadiusRatio = mWidthRadiusRatio;
        requestLayout();
    }

    public float getFaceMargin() {
        return mFaceMargin;
    }

    public void setFaceMargin(float mFaceMargin) {
        this.mFaceMargin = mFaceMargin;
        requestLayout();
    }

    public boolean isDraggable() {
        return mDraggable;
    }

    public void setDraggable(boolean draggable) {
        this.mDraggable = draggable;
    }

    public ColorChangeType getColorChangeType() {
        return mColorChangeType;
    }

    public void setColorChangeType(ColorChangeType mColorChangeType) {
        this.mColorChangeType = mColorChangeType;
    }

    public int getDuration() {
        return mDuration;
    }

    public void setDuration(int duration) {
        mDuration = duration;
        mProcessAnimator.setDuration(duration);
    }

    public float getTouchMoveRatioValue() {
        return mTouchMoveRatioValue;
    }

    public void setTouchMoveRatioValue(float ratio) {
        mTouchMoveRatioValue = ratio;
    }

    public OnStateChangeListener getOnStateChangeListener() {
        return mOnStateChangeListener;
    }

    public void setOnStateChangeListener(OnStateChangeListener onStateChangeListener) {
        mOnStateChangeListener = onStateChangeListener;
    }

    public boolean isMoveToSameStateCallListener() {
        return mMoveToSameStateCallListener;
    }

    public void setMoveToSameStateCallListener(boolean callListener) {
        mMoveToSameStateCallListener = callListener;
    }

    public interface OnStateChangeListener {
        void onStateChange(float process, State state, FaceOffToggleButton fotb);
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);
        ss.isChecked = isChecked();
        return ss;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        SavedState ss = (SavedState) state;
        setCheckedImmediately(ss.isChecked, false);
        mStopRestoreChecked = true;
        super.onRestoreInstanceState(ss.getSuperState());
        mStopRestoreChecked = false;
    }

    static class SavedState extends BaseSavedState {
        boolean isChecked = false;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            isChecked = in.readByte() != 0;
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeByte((byte) (isChecked ? 1 : 0));
        }

        public static final Parcelable.Creator<SavedState> CREATOR
                = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }
}
