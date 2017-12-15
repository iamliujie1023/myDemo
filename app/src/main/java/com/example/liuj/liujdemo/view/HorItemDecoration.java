package com.example.liuj.liujdemo.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class HorItemDecoration extends RecyclerView.ItemDecoration {

    private ItemBound mItemBound;

    public HorItemDecoration(ItemBound itemBound) {
        mItemBound = itemBound;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int itemCount = parent.getAdapter().getItemCount();
        if (itemCount == 0) {
            return;
        }

        int position = parent.getChildAdapterPosition(view);

        outRect.top = mItemBound.top;
        outRect.bottom = mItemBound.bottom;

        if (position == 0) {
            outRect.left = mItemBound.firstLeft;
            outRect.right = mItemBound.firstRight;
        } else if (position == itemCount - 1) {
            outRect.left = mItemBound.itemLeft;
            outRect.right = mItemBound.itemRight;
        } else {
            outRect.left = mItemBound.lastLeft;
            outRect.right = mItemBound.lastRight;
        }
    }

    public static class ItemBound {

        public ItemBound(int top, int bottom, int firstLeft, int firstRight, int lastLeft, int lastRight, int itemLeft, int itemRight) {
            this.top = top;
            this.bottom = bottom;
            this.firstLeft = firstLeft;
            this.firstRight = firstRight;
            this.lastRight = lastRight;
            this.lastLeft = lastLeft;
            this.itemRight = itemRight;
            this.itemLeft = itemLeft;
        }

        public int top;
        public int bottom;

        public int firstLeft;
        public int firstRight;

        public int lastRight;
        public int lastLeft;

        public int itemRight;
        public int itemLeft;
    }

}