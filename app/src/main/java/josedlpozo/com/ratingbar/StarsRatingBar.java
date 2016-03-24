package josedlpozo.com.ratingbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by josedelpozo on 23/3/16.
 */
public class StarsRatingBar extends LinearLayout {


    public StarsRatingBarListener getOnRateChanged() {
        return starsRateBarListener;
    }

    public void setStarsRatingBarListener(StarsRatingBarListener onScoreChanged) {
        this.starsRateBarListener = onScoreChanged;
    }

    public interface StarsRatingBarListener {
        void rateChanged(float score);
    }

    private ImageView[] starsImages;

    private boolean indicator = true;

    private int mNumMaxStars = 5;
    private float mCurrentRate = 2.5f;

    private boolean stepByHalf = true;

    private int mIconStarHalf = R.drawable.icon_star_half;
    private int mIconStarFull = R.drawable.icon_star_full;
    private int mIconStarEmpty = R.drawable.icon_star_empty;

    private int mPositionStar = 0;

    private StarsRatingBarListener starsRateBarListener;

    private float mSizeOfAnimation = 1.2f;


    public StarsRatingBar(Context context) {
        super(context);
        initialize();
    }

    public StarsRatingBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
        initialize();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray attrsArray = context.obtainStyledAttributes(attrs,
                R.styleable.StarsRatingBar);
        final int N = attrsArray.getIndexCount();
        for (int i = 0; i < N; ++i) {
            int attr = attrsArray.getIndex(i);
            switch (attr){
                case R.styleable.StarsRatingBar_num_max_stars:
                    mNumMaxStars = attrsArray.getInt(attr, 5);
                    break;
                case R.styleable.StarsRatingBar_current_rate:
                    mCurrentRate = attrsArray.getFloat(attr, 2.5f);
                    break;
                case R.styleable.StarsRatingBar_icon_star_half:
                    mIconStarHalf = attrsArray.getResourceId(attr, android.R.drawable.star_on);
                    break;
                case R.styleable.StarsRatingBar_icon_star_full:
                    mIconStarFull = attrsArray.getResourceId(attr, android.R.drawable.star_on);
                    break;
                case R.styleable.StarsRatingBar_icon_star_empty:
                    mIconStarEmpty = attrsArray.getResourceId(attr, android.R.drawable.star_off);
                    break;
                case R.styleable.StarsRatingBar_indicator:
                    indicator = attrsArray.getBoolean(attr, false);
                    break;
                case R.styleable.StarsRatingBar_step_by_half:
                    stepByHalf = attrsArray.getBoolean(attr, true);
                    break;
                case R.styleable.StarsRatingBar_size_animation:
                    mSizeOfAnimation = attrsArray.getFloat(attr, 1.2f);
                default:
            }
        }
        attrsArray.recycle();
    }

    private void initialize(){
        removeAllViews();
        starsImages = new ImageView[mNumMaxStars];
        for(int i = 0; i < mNumMaxStars; i++){
            starsImages[i] = getDefaultStar();
            addView(starsImages[i]);
        }
        refreshStars();
    }

    private ImageView getDefaultStar() {
        ImageView v = new ImageView(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.weight = 1;
        //v.setPadding((int) mStarPadding, 0, (int) mStarPadding, 0);
        v.setAdjustViewBounds(true);
        v.setScaleType(ImageView.ScaleType.FIT_CENTER);
        v.setLayoutParams(params);
        v.setImageResource(mIconStarEmpty);
        return v;
    }

    private void refreshStars() {
        boolean currentRateIsHalf = (mCurrentRate != 0 && (mCurrentRate % 0.5 == 0)) && stepByHalf;
        for (int i = 1; i <= mNumMaxStars; i++) {
            if (i <= mCurrentRate)
                starsImages[i - 1].setImageResource(mIconStarFull);
            else {
                if (currentRateIsHalf && i - 0.5 <= mCurrentRate)
                    starsImages[i - 1].setImageResource(mIconStarHalf);
                else
                    starsImages[i - 1].setImageResource(mIconStarEmpty);
            }
        }

    }

    public void refresh(){
        refreshStars();
    }

    private ImageView getImageView(int position){
        if(position < 0 || position > starsImages.length) return starsImages[0];
        return starsImages[position];
    }

    public void setIndicator(boolean indicator) {
        this.indicator = indicator;
    }

    public void setNumStars(int numStars) {
        this.mNumMaxStars = numStars;
        initialize();
    }

    public void setCurrentRate(float currentRate) {
        this.mCurrentRate = currentRate;
    }

    public void setStepByHalf(boolean stepByHalf) {
        this.stepByHalf = stepByHalf;
    }

    public boolean isIndicator() {
        return indicator;
    }

    public int getNumStars() {
        return mNumMaxStars;
    }

    public float getCurrentRate() {
        return mCurrentRate;
    }

    public boolean isStepByHalf() {
        return stepByHalf;
    }

    public float getSizeOfAnimation() {
        return mSizeOfAnimation;
    }

    public void setSizeOfAnimation(float sizeOfAnimation) {
        this.mSizeOfAnimation = sizeOfAnimation;
    }

    public void setIconStarHalf(int iconStarHalf) {
        this.mIconStarHalf = iconStarHalf;
    }

    public void setIconStarFull(int iconStarFull) {
        this.mIconStarFull = iconStarFull;
    }

    public void setIconStarEmpty(int iconStarEmpty) {
        this.mIconStarEmpty = iconStarEmpty;
    }

    private void animateStarPressed(ImageView star) {
        if (star != null)
            ViewCompat.animate(star).scaleX(mSizeOfAnimation).scaleY(mSizeOfAnimation).setDuration(100).start();
    }

    private void animateStarRelease(ImageView star) {
        if (star != null)
            ViewCompat.animate(star).scaleX(1f).scaleY(1f).setDuration(100).start();
    }

    private float getScoreForPosition(float x) {
        if (isStepByHalf())
            return (float) Math.round(((x / ((float) getWidth() / (mNumMaxStars * 3f))) / 3f) * 2f) / 2;
        float value = (float) Math.round((x / ((float) getWidth() / (mNumMaxStars))));
        return value < 0 ? 1 : value;
    }

    private int getPositionForScore(float score) {
        if(score > 0)
            return Math.round(score) - 1;
        else return -1;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        if (indicator)
            return true;
        float lastscore = mCurrentRate;
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                animateStarRelease(getImageView(getPositionForScore(mCurrentRate)));
                mPositionStar = 0;
                break;
            case MotionEvent.ACTION_MOVE:
                mCurrentRate = getScoreForPosition(event.getX());
                if (lastscore != mCurrentRate) {
                    animateStarRelease(getImageView(mPositionStar));
                    animateStarPressed(getImageView(getPositionForScore(mCurrentRate)));
                    mPositionStar = getPositionForScore(mCurrentRate);
                    refreshStars();
                    if (starsRateBarListener != null) starsRateBarListener.rateChanged(mCurrentRate);
                }
                break;
            case MotionEvent.ACTION_DOWN:
                mCurrentRate = getScoreForPosition(event.getX());
                animateStarPressed(getImageView(getPositionForScore(mCurrentRate)));
                mPositionStar = getPositionForScore(mCurrentRate);
                if (lastscore != mCurrentRate) {
                    refreshStars();
                    if (starsRateBarListener != null) starsRateBarListener.rateChanged(mCurrentRate);
                }
        }
        return true;
    }

}
