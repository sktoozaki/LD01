package com.example.kk.ld01.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class BaseViewHolder {
	private Context mContext;
	private final SparseArray<View> mViews;
	private int mPosition;
	private View mConvertView;

	private BaseViewHolder(Context context, ViewGroup parent, int layoutId,
                           int position) {
		this.mViews = new SparseArray<View>();
		this.mContext = context;
		mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,
				false);
		mConvertView.setTag(this);
	}

	/**
	 * 拿到一个BaseViewHolder对象
	 * 
	 * @param context
	 * @param convertView
	 * @param parent
	 * @param layoutId
	 * @param position
	 * @return
	 */
	public static BaseViewHolder get(Context context, View convertView,
			ViewGroup parent, int layoutId, int position) {
		if (convertView == null) {
			return new BaseViewHolder(context, parent, layoutId, position);
		}
		return (BaseViewHolder) convertView.getTag();
	}

	/**
	 * 通过控件的Id获取对于的控件，如果没有则加入views
	 * 
	 * @param viewId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends View> T getView(int viewId) {

		View view = mViews.get(viewId);
		if (view == null) {
			view = mConvertView.findViewById(viewId);
			mViews.put(viewId, view);
		}
		return (T) view;
	}

	public View getConvertView() {
		return mConvertView;
	}

	/**
	 * 为TextView设置字符串
	 * 
	 * @param viewId
	 * @param text
	 * @return
	 */
	public BaseViewHolder setText(int viewId, String text) {
		TextView view = getView(viewId);
		view.setText(text);
		return this;
	}

	/**
	 * 为ImageView设置图片
	 * 
	 * @param viewId
	 * @param drawableId
	 * @return
	 */
	public BaseViewHolder setImageResource(int viewId, int drawableId) {
		ImageView view = getView(viewId);
		view.setImageResource(drawableId);

		return this;
	}

	/**
	 * 为ImageView设置图片
	 * 
	 * @param viewId
	 * @param bm
	 * @return
	 */
	public BaseViewHolder setImageBitmap(int viewId, Bitmap bm) {
		ImageView view = getView(viewId);
		view.setImageBitmap(bm);
		return this;
	}

	/**
	 * 为ImageView设置网络图片
	 *
	 * @param viewId
	 * @param url
	 * @return
	 */
	public BaseViewHolder setImageByUrl(int viewId, String url) {
		// ImageLoader.getInstance(3, Type.LIFO).loadImage(url, (ImageView)
		// getView(viewId), true);
		/*if (mContext instanceof MeLaiApplication) {
			((MeLaiApplication) mContext).utils.display(
					(ImageView) getView(viewId), url);
		} else {
			Log.e("test", "传入MelaiApplication作为Context");
		}*/
		return this;
	}

	public int getPosition() {
		return mPosition;
	}
}
