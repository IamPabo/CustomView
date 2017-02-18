package view.max.com.viewlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @auther MaxLiu
 * @time 2017/2/18
 */

public class CustomView extends View {
    //画笔
    private Paint paint;
    //图片
    private Drawable drawable;
    private Bitmap bitmap;
    //View的宽度
    private int mWidth;
    //View的高度
    private int mHeight;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //根据属性初始化
        initAttrs(attrs);
    }

    private void initAttrs(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray array = null;
            try {
                array = getContext().obtainStyledAttributes(
                        attrs,
                        R.styleable.CustomView
                );
                //根据图片id获取Drawable对象
                drawable = array.getDrawable(R.styleable.CustomView_src);
                //测量Drawable对象的宽高
                measureDrawable();
            } finally {
                if (array != null) {
                    array.recycle();
                }
            }
        }
    }

    private void measureDrawable() {
        if (drawable == null) {
            throw new RuntimeException("drawable 不能为空!!");
        }
        mWidth = drawable.getIntrinsicWidth();
        mHeight = drawable.getIntrinsicHeight();
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //宽度的模式与大小
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        //高度的模式与大小
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int height = MeasureSpec.getMode(widthMeasureSpec);

        setMeasuredDimension(
                measureWidth(widthMode, width),
                measureHeigth(heightMode, height)
        );
    }

    private int measureHeigth(int heightMode, int height) {
        switch (heightMode) {
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                break;
            case MeasureSpec.EXACTLY:
                mHeight = height;
                break;
        }
        return mHeight;
    }

    private int measureWidth(int widthMode, int width) {
        switch (widthMode) {
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                break;
            case MeasureSpec.EXACTLY:
                mWidth = width;
                break;
        }
        return mWidth;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if(bitmap == null){
            bitmap = Bitmap.createScaledBitmap(
                    drawableToBitamp(drawable),
                    getMeasuredWidth(),
                    getMeasuredHeight(),
                    true
            );
        }
        //绘制图片
        canvas.drawBitmap(bitmap, getLeft(), getTop(), paint);
        canvas.save();
        canvas.rotate(90);
        paint.setColor(Color.RED);
        paint.setTextSize(30);
        canvas.drawText("I am text!!",getLeft() + 50,getTop() - 50,paint);
        canvas.restore();
    }

    private Bitmap drawableToBitamp(Drawable drawable) {
        Bitmap bitmap = null;
        System.out.println("Drawable转Bitmap");
        Bitmap.Config config = drawable.getOpacity()
                != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
        bitmap = Bitmap.createBitmap(mWidth, mHeight, config);
        //注意，下面三行代码要用到，否在在View或者surfaceview里的canvas.drawBitmap会看不到图
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, mWidth, mHeight);
        drawable.draw(canvas);
        return bitmap;
    }
}
