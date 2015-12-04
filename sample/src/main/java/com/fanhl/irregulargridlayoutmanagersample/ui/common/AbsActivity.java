package com.fanhl.irregulargridlayoutmanagersample.ui.common;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.IrregularGridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.fanhl.irregulargridlayoutmanagersample.MockUtil;
import com.fanhl.irregulargridlayoutmanagersample.R;
import com.fanhl.irregulargridlayoutmanagersample.model.Book;
import com.fanhl.irregulargridlayoutmanagersample.ui.adapter.BookAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by fanhl on 15/12/3.
 */
public abstract class AbsActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar              toolbar;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout   mSwipeRefreshLayout;
    @Bind(R.id.recycler_view)
    protected RecyclerView               mRecyclerView;
    protected IrregularGridLayoutManager layoutManager;
    private   BookAdapter                mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        fab.setOnClickListener(view -> {
            if (layoutManager.getOrientation() == LinearLayoutManager.VERTICAL) {
                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            } else {
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            }
        });

        assignViews();
        refreshData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void assignViews() {
        mSwipeRefreshLayout.setOnRefreshListener(this::refreshData);

        mAdapter = new BookAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    protected void refreshData() {
        new AsyncTask<Void, Void, List<Book>>() {
            @Override
            protected void onPreExecute() {
                if (!mSwipeRefreshLayout.isRefreshing()) mSwipeRefreshLayout.setRefreshing(true);
            }

            @Override
            protected List<Book> doInBackground(Void... params) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return MockUtil.books();
            }

            @Override
            protected void onPostExecute(List<Book> books) {
                mSwipeRefreshLayout.setRefreshing(false);
                mAdapter.clear();
                mAdapter.addItems(books);
            }
        }.execute();
    }
}
