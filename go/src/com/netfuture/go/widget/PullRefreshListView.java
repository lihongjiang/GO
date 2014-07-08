package com.netfuture.go.widget;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.netfuture.go.R;



import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;

public class PullRefreshListView extends ListView implements OnScrollListener {

	private final int RELEASE_To_REFRESH = 0;
	private final int PULL_To_REFRESH = 1;
	private final int REFRESHING = 2;
	private final int DONE = 3;
	private final int LOADING = 4;
	private final int LOADING_MORE = 5;
	private float mX, mY;
	// 实际的padding的距离与界面上偏移距离的比例
	private final int RATIO = 2;

	private LinearLayout mHeadView;
	private TextView mHeadTipsTextview;
	private TextView mHeadLastUpdatedTextView;
	private ImageView mHeadArrowImageView;
	private ProgressBar mHeadprogressBar;

	private RotateAnimation mAnimation;
	private RotateAnimation mReverseAnimation;

	// 用于保证startY的值在一个完整的touch事件中只被记录一次
	private boolean mIsRecored;

	private int mHeadContentHeight;

	private int mStartY;
	private int mFirstItemIndex;

	private int mState = DONE;

	private boolean mIsBack;

	private PullRefreshListener mListener;

	private boolean mIsRefreshable;

	private RelativeLayout mLoadMoreView;
	private ProgressBar mLoadMoreProgressBar;
	private TextView mLoadMoreTipTextView;
	private int mLoadMoreContentHeight;
	private boolean mCanLoadMore;
	// 滚动的时候自动加载更多
	private boolean mScrollAutoLoadMore = true;

	private Scroller mScroller;

	private final int SCROLL_DURATION = 400;

	public PullRefreshListView(Context context) {
		super(context);
		init(context);
	}

	public PullRefreshListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(Context context) {
		setCacheColorHint(Color.TRANSPARENT);
		LayoutInflater inflater = LayoutInflater.from(context);

		mScroller = new Scroller(context, new DecelerateInterpolator());

		mHeadView = (LinearLayout) inflater.inflate(R.layout.pull_refresh_head,
				this, false);
		mHeadView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
			}
		});

		mHeadArrowImageView = (ImageView) mHeadView
				.findViewById(R.id.head_arrowImageView);
		mHeadArrowImageView.setMinimumWidth(70);
		mHeadArrowImageView.setMinimumHeight(50);
		mHeadprogressBar = (ProgressBar) mHeadView
				.findViewById(R.id.head_progressBar);
		mHeadTipsTextview = (TextView) mHeadView
				.findViewById(R.id.head_tipsTextView);
		mHeadLastUpdatedTextView = (TextView) mHeadView
				.findViewById(R.id.head_lastUpdatedTextView);

		measureView(mHeadView);
		mHeadContentHeight = mHeadView.getMeasuredHeight();

		addHeaderView(mHeadView, null, true);
		changeHeaderViewByState(false);
		setOnScrollListener(this);

		mAnimation = new RotateAnimation(0, -180,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		mAnimation.setInterpolator(new LinearInterpolator());
		mAnimation.setDuration(250);
		mAnimation.setFillAfter(true);

		mReverseAnimation = new RotateAnimation(-180, 0,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		mReverseAnimation.setInterpolator(new LinearInterpolator());
		mReverseAnimation.setDuration(200);
		mReverseAnimation.setFillAfter(true);

		mState = DONE;
		mIsRefreshable = false;

		// 加载更多
		mLoadMoreView = (RelativeLayout) inflater.inflate(
				R.layout.pull_refresh_load_more, this, false);
		
		mLoadMoreView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				tryLoadMore();
			}
		});
		mLoadMoreProgressBar = (ProgressBar) mLoadMoreView
				.findViewById(R.id.progressBar);
		mLoadMoreTipTextView = (TextView) mLoadMoreView
				.findViewById(R.id.prompt_textView);
		measureView(mLoadMoreView);
		mLoadMoreContentHeight = mLoadMoreView.getMeasuredHeight();
		addFooterView(mLoadMoreView, null, true);
		changeLoadMoreViewByState();
	}

	public void onScroll(AbsListView arg0, int firstVisiableItem, int arg2,
			int arg3) {
		mFirstItemIndex = firstVisiableItem;
	}

	public void onScrollStateChanged(AbsListView arg0, int scrollState) {
		if (mScrollAutoLoadMore && scrollState == SCROLL_STATE_IDLE) {
			if (mCanLoadMore
					&& getLastVisiblePosition() == getAdapter().getCount() - 1) {
				tryLoadMore();
			}
		}
	}

	public boolean onTouchEvent(MotionEvent event) {

		if (!mScroller.isFinished()) {
			mScroller.forceFinished(true);
		}

		if (mIsRefreshable) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				if (mFirstItemIndex == 0 && !mIsRecored) {
					mIsRecored = true;
					mStartY = (int) event.getY();
//					LogUtil.v(TAG, "在down时候记录当前位置‘");
				}
				break;
			case MotionEvent.ACTION_CANCEL:
			case MotionEvent.ACTION_UP:

				if (mState != REFRESHING && mState != LOADING_MORE
						&& mState != LOADING) {
					if (mState == DONE) {
						// 什么都不做
						changeHeaderViewByState(true);
					}
					if (mState == PULL_To_REFRESH) {
						mState = DONE;
						changeHeaderViewByState(true);

//						LogUtil.v(TAG, "由下拉刷新状态，到done状态");
					}
					if (mState == RELEASE_To_REFRESH) {
						refresh();

//						LogUtil.v(TAG, "由松开刷新状态，到done状态");
					}
				}

				mIsRecored = false;
				mIsBack = false;

				break;

			case MotionEvent.ACTION_MOVE:
				int tempY = (int) event.getY();

				if (!mIsRecored && mFirstItemIndex == 0) {
//					LogUtil.v(TAG, "在move时候记录下位置");
					mIsRecored = true;
					mStartY = tempY;
				}

				if (mState != REFRESHING && mIsRecored
						&& mState != LOADING_MORE && mState != LOADING) {

					// 保证在设置padding的过程中，当前的位置一直是在head，否则如果当列表超出屏幕的话，当在上推的时候，列表会同时进行滚动

					// 可以松手去刷新了
					if (mState == RELEASE_To_REFRESH) {

						setSelection(0);

						// 往上推了，推到了屏幕足够掩盖head的程度，但是还没有推到全部掩盖的地步
						if (((tempY - mStartY) / RATIO < mHeadContentHeight)
								&& (tempY - mStartY) > 0) {
							mState = PULL_To_REFRESH;
							changeHeaderViewByState(false);

//							LogUtil.v(TAG, "由松开刷新状态转变到下拉刷新状态");
						}
						// 一下子推到顶了
						else if (tempY - mStartY <= 0) {
							mState = DONE;
							changeHeaderViewByState(false);

//							LogUtil.v(TAG, "由松开刷新状态转变到done状态");
						}
						// 往下拉了，或者还没有上推到屏幕顶部掩盖head的地步
						else {
							// 不用进行特别的操作，只用更新paddingTop的值就行了
						}
					}
					// 还没有到达显示松开刷新的时候,DONE或者是PULL_To_REFRESH状态
					if (mState == PULL_To_REFRESH) {

						setSelection(0);

						// 下拉到可以进入RELEASE_TO_REFRESH的状态
						if ((tempY - mStartY) / RATIO >= mHeadContentHeight) {
							mState = RELEASE_To_REFRESH;
							mIsBack = true;
							changeHeaderViewByState(false);

//							LogUtil.v(TAG, "由done或者下拉刷新状态转变到松开刷新");
						}
						// 上推到顶了
						else if (tempY - mStartY <= 0) {
							mState = DONE;
							changeHeaderViewByState(false);

//							LogUtil.v(TAG, "由DOne或者下拉刷新状态转变到done状态");
						}
					}

					// done状态下
					if (mState == DONE) {
						if (tempY - mStartY > 0) {
							mState = PULL_To_REFRESH;
							changeHeaderViewByState(false);
						}
					}

					// 更新headView的size
					if (mState == PULL_To_REFRESH) {
						mHeadView.setPadding(0, -1 * mHeadContentHeight
								+ (tempY - mStartY) / RATIO, 0, 0);

					}

					// 更新headView的paddingTop
					if (mState == RELEASE_To_REFRESH) {
						mHeadView.setPadding(0, (tempY - mStartY) / RATIO
								- mHeadContentHeight, 0, 0);
					}

				}

				break;
			}
		}

		return super.onTouchEvent(event);
	}

	// 当状态改变时候，调用该方法，以更新界面
	private void changeHeaderViewByState(boolean useAnimation) {
//		LogUtil.d(TAG, "changeHeaderViewByState:" + mState);
		int targetPadding = 0;
		boolean needScroll = false;
		switch (mState) {
		case RELEASE_To_REFRESH:
			mHeadArrowImageView.setVisibility(View.VISIBLE);
			mHeadprogressBar.setVisibility(View.GONE);
			mHeadTipsTextview.setVisibility(View.VISIBLE);
			mHeadLastUpdatedTextView.setVisibility(View.VISIBLE);

			mHeadArrowImageView.clearAnimation();
			mHeadArrowImageView.startAnimation(mAnimation);

			mHeadTipsTextview.setText("松开刷新");

//			LogUtil.v(TAG, "当前状态，松开刷新");
			break;
		case PULL_To_REFRESH:
			mHeadprogressBar.setVisibility(View.GONE);
			mHeadTipsTextview.setVisibility(View.VISIBLE);
			mHeadLastUpdatedTextView.setVisibility(View.VISIBLE);
			mHeadArrowImageView.clearAnimation();
			mHeadArrowImageView.setVisibility(View.VISIBLE);
			// 是由RELEASE_To_REFRESH状态转变来的
			if (mIsBack) {
				mIsBack = false;
				mHeadArrowImageView.clearAnimation();
				mHeadArrowImageView.startAnimation(mReverseAnimation);

				mHeadTipsTextview.setText("下拉刷新");
			} else {
				mHeadTipsTextview.setText("下拉刷新");
			}
//			LogUtil.v(TAG, "当前状态，下拉刷新");
			break;

		case REFRESHING:

			if (useAnimation) {
				needScroll = true;
				targetPadding = 0;
			} else {
				mHeadView.setPadding(0, 0, 0, 0);
			}

			mHeadprogressBar.setVisibility(View.VISIBLE);
			mHeadArrowImageView.clearAnimation();
			mHeadArrowImageView.setVisibility(View.GONE);
			mHeadTipsTextview.setText("正在刷新...");
			mHeadLastUpdatedTextView.setVisibility(View.VISIBLE);

//			LogUtil.v(TAG, "当前状态,正在刷新...");
			break;
		default:

			if (useAnimation) {
				needScroll = true;
				targetPadding = -1 * mHeadContentHeight;
			} else {
				mHeadView.setPadding(0, -1 * mHeadContentHeight, 0, 0);
			}

			mHeadprogressBar.setVisibility(View.GONE);
			mHeadArrowImageView.clearAnimation();
			mHeadArrowImageView.setImageResource(R.drawable.refresh);
			mHeadTipsTextview.setText("下拉刷新");
			mHeadLastUpdatedTextView.setVisibility(View.VISIBLE);

//			LogUtil.v(TAG, "当前状态，done");
			break;
		}

		if (useAnimation && needScroll) {
			int startY = mHeadView.getPaddingTop();
			int dy = targetPadding - startY;
			mScroller.forceFinished(true);
			mScroller.startScroll(0, startY, 0, dy, SCROLL_DURATION);
		}

		mHeadView.invalidate();
	}

	// 当状态改变时候，调用该方法，以更新界面
	private void changeLoadMoreViewByState() {
//		LogUtil.d(TAG, "changeLoadMoreViewByState:" + mState);
		switch (mState) {
		case LOADING_MORE: {
			mHeadView.setPadding(0, -mHeadContentHeight, 0, 0);
			mLoadMoreProgressBar.setVisibility(View.VISIBLE);
			mLoadMoreTipTextView.setText("正在加载...");
			break;
		}
		default:
			if (mCanLoadMore) {
				mLoadMoreView.setPadding(0, 0, 0, 0);
				mLoadMoreProgressBar.setVisibility(View.GONE);
				mLoadMoreTipTextView.setText("加载更多");
			} else {
				mLoadMoreView.setPadding(0, -mLoadMoreContentHeight, 0, 0);
				mLoadMoreProgressBar.setVisibility(View.GONE);
			}
			break;
		}
		mLoadMoreView.invalidate();
	}

	public void setPullRefreshListener(PullRefreshListener listener) {
		this.mListener = listener;
	}

	public interface PullRefreshListener {
		public void onRefresh();

		public void onLoadMore();
	}

	public void onRefreshComplete(Date date) {
		if (date != null) {
			setRefreshTime(date);
		}
		if(mState == REFRESHING){
			mState = DONE;
			resetViews(true);
		}
	}

	public void setRefreshTime(Date date) {
		if (date != null) {
			mHeadLastUpdatedTextView.setText("最近更新:"
					+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
		} else {
			mHeadLastUpdatedTextView.setText(null);
		}
	}

	public void onLoadMoreComplete() {
		if(mState == LOADING_MORE){
			mState = DONE;
			resetViews(true);
		}
	}

	private void onRefresh() {
		if (mListener != null) {
			mListener.onRefresh();
		}
	}

	// 此方法直接照搬自网络上的一个下拉刷新的demo，此处是“估计”headView的width以及height
	private void measureView(final View child) {
		ViewGroup.LayoutParams p = child.getLayoutParams();
		if (p == null) {
			p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
		}
		int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width);
		int lpHeight = p.height;
		int childHeightSpec;
		if (lpHeight > 0) {
			childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight,
					MeasureSpec.EXACTLY);
		} else {
			childHeightSpec = MeasureSpec.makeMeasureSpec(0,
					MeasureSpec.UNSPECIFIED);
		}
		child.measure(childWidthSpec, childHeightSpec);
	}

	public void setAdapter(BaseAdapter adapter) {
		super.setAdapter(adapter);
		setRefreshTime(null);
	}

	public void setCanLoadMore(boolean canLoadMore) {
		mCanLoadMore = canLoadMore;
		changeLoadMoreViewByState();
		
	}

	public void setCanRefresh(boolean canRefresh) {
		mIsRefreshable = canRefresh;
	}

	public void tryLoadMore() {
		if (mCanLoadMore && mState == DONE && mListener != null) {
			mState = LOADING_MORE;
			resetViews(false);
			mListener.onLoadMore();
		}
	}

	private void resetViews(boolean animation) {
		changeLoadMoreViewByState();
		changeHeaderViewByState(animation);
	}

	public void triggerRefresh(boolean ignoreState) {
		if (!ignoreState) {
			if (mState != REFRESHING && mState != LOADING_MORE
					&& mState != LOADING) {
				refresh();
			}
		} else {
			refresh();
		}
	}

	private void refresh() {
		mState = REFRESHING;
		changeHeaderViewByState(true);
		onRefresh();
		setSelection(0);
	}

	@Override
	public void computeScroll() {
		if (mScroller.computeScrollOffset()) {
			mHeadView.setPadding(0, mScroller.getCurrY(), 0, 0);
		}
		super.computeScroll();
	}

	public void setScrollAutoLoadMore(boolean flag) {
		mScrollAutoLoadMore = flag;
	}
	@Override
	public boolean onInterceptTouchEvent(MotionEvent event) {
		float offX, offY;
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                mX = event.getX();
                mY = event.getY();
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                offX = event.getX() - mX;
                offY = event.getY() - mY;
                if (Math.abs(offX) > Math.abs(offY)) {// 横向滑动，不对事件进行拦截。
                    if (Math.abs(offY) > 0) { // 过滤掉小的纵向滑动，消除listview的抖动现象。
                        super.onInterceptTouchEvent(event);
                    }
                    return false;
                    
                }
                break;
        }
		return super.onInterceptTouchEvent(event);
	}
}
