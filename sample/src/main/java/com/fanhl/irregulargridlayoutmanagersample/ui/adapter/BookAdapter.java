package com.fanhl.irregulargridlayoutmanagersample.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fanhl.irregulargridlayoutmanagersample.R;
import com.fanhl.irregulargridlayoutmanagersample.model.Book;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by fanhl on 15/12/3.
 */
public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    private final Context    context;
    private final List<Book> list;

    public BookAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItem(Book item) {
        int position = list.size();
        list.add(item);
        notifyItemInserted(position);
    }

    public void addItems(List<Book> items) {
        int position = list.size();
        list.addAll(items);
        notifyItemRangeInserted(position, items.size());
    }

    public void removeItem(Book item) {
        int position = list.indexOf(item);
        list.remove(item);
        notifyItemRemoved(position);
    }

    public void clear() {
        int itemCount = list.size();
        list.clear();
        notifyItemRangeRemoved(0, itemCount);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.preview)
        ImageView mPreview;
        @Bind(R.id.title)
        TextView  mTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Book book) {
            mTitle.setText(book.title);
        }
    }
}
