package com.fanhl.irregulargridlayoutmanagersample.ui;

import android.support.v7.widget.IrregularGridLayoutManager;

import com.fanhl.irregulargridlayoutmanagersample.ui.common.AbsActivity;

public class MainActivity extends AbsActivity {

    @Override
    protected void assignViews() {
        super.assignViews();

        IrregularGridLayoutManager layoutManager = new IrregularGridLayoutManager(this, 4);
        layoutManager.setSpanDividers(25, 0, 50);
        mRecyclerView.setLayoutManager(layoutManager);
    }
}
