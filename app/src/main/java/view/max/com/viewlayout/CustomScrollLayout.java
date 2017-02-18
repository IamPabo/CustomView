package view.max.com.viewlayout;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.Scroller;

/**
 * @auther MaxLiu
 * @time 2017/2/18
 */

public class CustomScrollLayout extends FrameLayout {
    private static final String TAG = CustomScrollLayout.class.getSimpleName();
    Scroller mScroller;

    public CustomScrollLayout(@NonNull Context context) {
        super(context);
        mScroller = new Scroller(context);
    }

    public CustomScrollLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomScrollLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 该函数会在View重绘之时被调用
     */
    @Override
    public void computeScroll() {
        //computeScrollOffset()判断是否滚动完成 true:滚动未完成  false:滚动完成
        if (mScroller.computeScrollOffset()) {
            //滚动到此，View应该滚动到x,y坐标上
            this.scrollTo(
                    mScroller.getCurrX(),//获取当前View应该滚动的X坐标位置
                    mScroller.getCurrY()//获取当前View应该滚动的Y坐标位置
            );
            //请求重绘该View( postInvalidate ),从而又导致 computeScroll 被调用,然后继续滚动，
            //直到 computeScrollOffset 返回false
            this.postInvalidate();
        }
    }

    /**
     * 调用这个方法进行滚动，这里我们只滚动竖直方向
     *
     * @param y 需要滚动的y坐标
     */
    public void scrollTo(int y) {
        //参数 1 和参数 2 分别为滚动的起始点水平、竖直方向的滚动偏移量
        //参数 3 和参数 4 为在水平和竖直方向上的滚动距离
        mScroller.startScroll(getScrollX(), getScrollY(), 0, y);
        this.invalidate();
    }
}
