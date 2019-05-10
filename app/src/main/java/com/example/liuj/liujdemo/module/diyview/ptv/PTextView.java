package com.example.liuj.liujdemo.module.diyview.ptv;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatTextView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import com.example.liuj.R;
import com.example.liuj.liujdemo.view.CommonUtil;

/**
 * Created by jliu on 2018/4/12.
 */
public class PTextView extends AppCompatTextView {
    private int mTagSize;
    private int mTagColor;
    private int mPriceColor;
    private int mPriceSize;
    private boolean mIsOriPrice;
    private boolean mIs100Dot = false;
    private boolean mIsBold = false; //是否加粗。
    private boolean mNeedTag = true;
    private Boolean mIsDecimal = true; //priceTag的大小和小数部分的大小是否一致,默认是一样大

    private ColorStateList defaultColor;
    private int mPrice;

    public PTextView(Context context) {
        this(context, null);
    }

    public PTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float defaultTagSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 11f, metrics);
        float defaultPriceSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16f, metrics);
        defaultColor = getTextColors();

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PTextView);
        mTagSize = a.getDimensionPixelSize(R.styleable.PTextView_tag_size, (int) defaultTagSize);
        mPriceSize = a.getDimensionPixelSize(R.styleable.PTextView_price_size, (int) defaultPriceSize);
        mTagColor = a.getColor(R.styleable.PTextView_tag_color, defaultColor.getColorForState(getDrawableState(), 0));
        mPriceColor = a.getColor(R.styleable.PTextView_price_color, defaultColor.getColorForState(getDrawableState(), 0));
        mIsDecimal = a.getBoolean(R.styleable.PTextView_is_decimal, true);
        mIsBold = a.getBoolean(R.styleable.PTextView_is_bold, false);
        a.recycle();
    }

    /**
     * 设置价格 自动添加¥，除百，设置好字体的大小。
     *
     * @param price
     */
    public void setPrice(int price) {
        mPrice = price;
        if (mIsOriPrice) {
            setOrigiPrice(price);
        } else {
            String priceString = mIs100Dot ? "¥" + CommonUtil.deRoundWith100AndDot(price) : "¥" + CommonUtil.deRound(price, 100);

            //整数部分的长度
            int length = String.valueOf(price / 100).length();
            SpannableStringBuilder builder = new SpannableStringBuilder(priceString);
            builder.setSpan(new AbsoluteSizeSpan(mTagSize, false), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.setSpan(new AbsoluteSizeSpan(mPriceSize, false), 1, priceString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.setSpan(new ForegroundColorSpan(mTagColor), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.setSpan(new ForegroundColorSpan(mPriceColor), 1, priceString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            if (mIsDecimal && length + 1 < priceString.length()) {
                builder.setSpan(new AbsoluteSizeSpan(mTagSize, false), length + 1, priceString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            if (mIsBold) {
                setTypeface(null);
                getPaint().setFakeBoldText(true);
            }
            setText(builder);
        }
    }

    /**
     * 设置价格 并且小数点后保持与￥ 大小一致
     * 请使用setPrice(),默认小数点后和￥保持一致，想不一致可以把mIsDecimal设置成false;
     *
     * @param price
     */

    @Deprecated
    public void setPriceText(int price) {
        setPrice(price);
    }

    /**
     * 保留小数点后两位
     *
     * @param price
     */
    public void setPriceTextWith100Dot(int price) {
        mIs100Dot = true;
        setPrice(price);
    }

    /**
     * 设置字体是否要加粗
     *
     * @param isBold
     */
    public void setTextBold(Boolean isBold) {
        this.mIsBold = isBold;
        this.invalidate();
    }

    /**
     * 设置人民币符号的size
     *
     * @param tagSize
     */
    public void setTagSize(int tagSize) {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        mTagSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, tagSize, metrics);
        this.invalidate();
    }

    /**
     * 设置人民币符号的color
     *
     * @param tagColor
     */
    public void setTagColor(int tagColor) {
        mTagColor = tagColor;
        setPrice(mPrice);
        this.invalidate();
    }

    /**
     * 设置价格字体的size
     *
     * @param priceSize
     */
    public void setPriceTextSize(int priceSize) {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        mPriceSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, priceSize, metrics);
    }

    /**
     * 设置价格字体的color
     *
     * @param color
     */
    public void setPriceTextColor(int color) {
        mPriceColor = color;
        setPrice(mPrice);
    }

    /**
     * 设置原价 自动添加￥，除百，加中划线。
     *
     * @param oriPrice
     */
    public void setOrigiPrice(int oriPrice) {
        String OriPriceString = "¥" + CommonUtil.deRound(oriPrice, 100);
        this.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        SpannableStringBuilder builder = new SpannableStringBuilder(OriPriceString);
        this.setText(builder);
        mIsOriPrice = true;
    }

    @Override
    public void setTextColor(int color) {
        mTagColor = color;
        mPriceColor = color;
        super.setTextColor(color);
    }

    /**
     * 根据用户设置的数值，去修改对应的VIew.
     */
    public void onRefeshView() {
        invalidate();
    }

    /**
     * 将传入的字符串转换为正确的价格字符串。
     * 1:判断是否有￥符号，没有就添上。
     *
     * @param price
     * @return
     */
    public String getPriceString(String price) {
        SpannableStringBuilder buider = new SpannableStringBuilder(price);
        return buider.toString();
    }
}
