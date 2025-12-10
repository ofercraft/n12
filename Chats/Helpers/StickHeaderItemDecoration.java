package com.channel2.mobile.ui.Chats.Helpers;

import android.graphics.Canvas;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes2.dex */
public class StickHeaderItemDecoration extends RecyclerView.ItemDecoration {
    View currentHeader;
    private StickyHeaderInterface mListener;
    private int mStickyHeaderHeight;

    public interface StickyHeaderInterface {
        void bindHeaderData(View view, int i);

        int getHeaderLayout(int i);

        int getHeaderPositionForItem(int i);

        boolean isHeader(int i);
    }

    public StickHeaderItemDecoration(StickyHeaderInterface stickyHeaderInterface) {
        this.mListener = stickyHeaderInterface;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        int childAdapterPosition;
        super.onDrawOver(canvas, recyclerView, state);
        View childAt = recyclerView.getChildAt(0);
        if (childAt == null || (childAdapterPosition = recyclerView.getChildAdapterPosition(childAt)) == -1) {
            return;
        }
        int headerPositionForItem = this.mListener.getHeaderPositionForItem(childAdapterPosition);
        View headerViewForItem = getHeaderViewForItem(headerPositionForItem, recyclerView);
        this.currentHeader = headerViewForItem;
        fixLayoutSize(recyclerView, headerViewForItem);
        View childInContact = getChildInContact(recyclerView, this.currentHeader.getBottom(), headerPositionForItem);
        if (childInContact != null && this.mListener.isHeader(recyclerView.getChildAdapterPosition(childInContact))) {
            moveHeader(canvas, this.currentHeader, childInContact);
        } else {
            drawHeader(canvas, this.currentHeader);
        }
    }

    private View getHeaderViewForItem(int i, RecyclerView recyclerView) {
        View viewInflate = LayoutInflater.from(recyclerView.getContext()).inflate(this.mListener.getHeaderLayout(i), (ViewGroup) recyclerView, false);
        this.mListener.bindHeaderData(viewInflate, i);
        return viewInflate;
    }

    private void drawHeader(Canvas canvas, View view) {
        canvas.save();
        canvas.translate(0.0f, 0.0f);
        view.draw(canvas);
        canvas.restore();
    }

    private void moveHeader(Canvas canvas, View view, View view2) {
        canvas.save();
        canvas.translate(0.0f, view2.getTop() - view.getHeight());
        view.draw(canvas);
        canvas.restore();
    }

    private View getChildInContact(RecyclerView recyclerView, int i, int i2) {
        int bottom;
        int i3 = 0;
        while (i3 < recyclerView.getChildCount()) {
            View childAt = recyclerView.getChildAt(i3);
            int height = (i2 == i3 || !this.mListener.isHeader(recyclerView.getChildAdapterPosition(childAt))) ? 0 : this.mStickyHeaderHeight - childAt.getHeight();
            if (childAt.getTop() > 0) {
                bottom = childAt.getBottom() + height;
            } else {
                bottom = childAt.getBottom();
            }
            if (bottom > i && childAt.getTop() <= i) {
                return childAt;
            }
            i3++;
        }
        return null;
    }

    private void fixLayoutSize(ViewGroup viewGroup, View view) {
        view.measure(ViewGroup.getChildMeasureSpec(View.MeasureSpec.makeMeasureSpec(viewGroup.getWidth(), 1073741824), viewGroup.getPaddingLeft() + viewGroup.getPaddingRight(), view.getLayoutParams().width), ViewGroup.getChildMeasureSpec(View.MeasureSpec.makeMeasureSpec(viewGroup.getHeight(), 0), viewGroup.getPaddingTop() + viewGroup.getPaddingBottom(), view.getLayoutParams().height));
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        this.mStickyHeaderHeight = measuredHeight;
        view.layout(0, 0, measuredWidth, measuredHeight);
    }
}
