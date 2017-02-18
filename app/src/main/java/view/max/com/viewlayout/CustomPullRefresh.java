package view.max.com.viewlayout;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * @auther MaxLiu
 * @time 2017/2/18
 */

public class CustomPullRefresh extends ViewGroup {
    public CustomPullRefresh(Context context) {
        super(context);
    }

    public CustomPullRefresh(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomPullRefresh(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
