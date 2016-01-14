package com.example.kk.ld01.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class CommonAdapter<T> extends BaseAdapter {
	protected LayoutInflater mInflater;
	protected Context mContext;
	protected List<T> mDatas;
	protected final int mItemLayoutId;

	public CommonAdapter(Context context, List<T> mDatas, int itemLayoutId) {
		mInflater = LayoutInflater.from(context);
		this.mContext = context;
		this.mDatas = mDatas;
		this.mItemLayoutId = itemLayoutId;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mDatas.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@SuppressWarnings("unchecked")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final BaseViewHolder viewHolder = getViewHolder(position, convertView,
				parent);
		convert(viewHolder, (T) getItem(position));
		return viewHolder.getConvertView();

	}

	public abstract void convert(BaseViewHolder helper, T item);

	protected BaseViewHolder getViewHolder(int position, View convertView,
			ViewGroup parent) {
		return BaseViewHolder.get(mContext, convertView, parent, mItemLayoutId,
				position);
	}
}
