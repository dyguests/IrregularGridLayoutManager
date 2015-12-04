package com.fanhl.irregulargridlayoutmanagersample.ui;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.IrregularGridLayoutManager;

import com.fanhl.irregulargridlayoutmanagersample.ui.common.AbsActivity;

public class MainActivity extends AbsActivity {

    @Override
    protected void assignViews() {
        super.assignViews();

        IrregularGridLayoutManager layoutManager = new IrregularGridLayoutManager(this, 4);
        mRecyclerView.setLayoutManager(layoutManager);
    }
}
