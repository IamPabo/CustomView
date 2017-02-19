package view.max.com.viewlayout;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Scroller;
import android.widget.TextView;

/**
 * @auther MaxLiu
 * @time 2017/2/18
 */

@TargetApi(Build.VERSION_CODES.M)
public abstract class CustomPullRefresh<T extends View> extends ViewGroup
        implements View.OnScrollChangeListener{

    //空闲状态
    public static final int STATUS_IDLE = 0;
    //下拉或者上拉状态，还没有到达可刷新的地步
    public static final int STATUS_PULL_TO_REFRESH = 1;
    //下拉或者上拉状态
    public static final int STATUS_RELEASE_TO_REFRESH = 2;
    //刷新中
    public static final int STATUS_REFRESHING = 3;
    //loading 中
    public static final int STATUS_LOADING = 4;
    //滚动控制器
    protected Scroller mScroller;
    //下拉刷新时显示的 header View
    protected View mHeaderView;
    //上拉加载更多时显示的 footer View
    protected View mFooterView;
    //本次触摸滑动 y 坐标上的偏移量
    protected int mYOffset;
    //内容视图，即用户触摸导致下拉刷新、上拉加载的主视图，如ListView、GridView等
    protected T mContentView;
    //最初的滚动位置，第一次布局时滚动 header 高度的距离
    protected int mInitScrollY = 0;
    //最后一次触摸事件的 y 轴坐标
    protected int mLastY = 0;
    //当前状态
    protected int mCurrentStatus = STATUS_IDLE;
    //下拉刷新的回调
    protected OnRefreshListener mOnRefreshListener;
    //加载更多的回调
    protected OnLoadListener mLoadListener;
    //header中的箭头图标
    private ImageView mArrowImageView;
    //箭头是否向上
    private boolean isArrowUp;
    //header 中的文本标签
    private TextView mTipsTextView;
    //header 中的时间标签
    private TextView mTimeTextView;
    //header 中的进度条
    private ProgressBar mProgressBar;
    //屏幕的高度
    private int mScreenHeight;
    //header的高度
    private int mHeaderHeight;

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
