package com.nanda.rxandroidexample.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.nanda.rxandroidexample.R;
import com.nanda.rxandroidexample.databinding.ItemActivityListBinding;
import com.nanda.rxandroidexample.models.entity.ActivityListItem;

import java.util.List;

public class SimpleListAdapter extends RecyclerView.Adapter<SimpleListAdapter.ListViewHolder> {

    private List<ActivityListItem> mItemList;
    private Context mContext;
    private LayoutInflater layoutInflater;
    private AdapterView.OnItemClickListener onItemClickListener;

    public SimpleListAdapter(Context context) {
        this.mContext = context;
    }

    public void setListData(List<ActivityListItem> mItemList) {
        if (mItemList != null)
            this.mItemList = mItemList;
        notifyDataSetChanged();
    }


    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(mContext);
        ItemActivityListBinding item_layout_binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_activity_list, parent, false);
        return new ListViewHolder(item_layout_binding, this);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {

        ActivityListItem item = mItemList.get(position);

        holder.setDataToView(item);

    }

    @Override
    public int getItemCount() {
        if (mItemList != null)
            return mItemList.size();
        else
            return 0;
    }


    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;

    }

    private void onItemHolderClick(ListViewHolder holder) {
        if (onItemClickListener != null)
            onItemClickListener.onItemClick(null, holder.mViewBinding.getRoot(), holder.getAdapterPosition(), holder.getItemId());
    }


    public static class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private SimpleListAdapter mAdapter;
        private ItemActivityListBinding mViewBinding;

        public ListViewHolder(ItemActivityListBinding viewBinding, SimpleListAdapter adapter) {
            super(viewBinding.getRoot());
            this.mViewBinding = viewBinding;
            this.mAdapter = adapter;
            viewBinding.setOnclickListener(this);
        }

        public void setDataToView(ActivityListItem item) {
            mViewBinding.activityName.setText(item.getActivityName());
        }

        @Override
        public void onClick(View v) {
            mAdapter.onItemHolderClick(this);
        }
    }

}
