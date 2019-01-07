package com.example.mhzhaog.myandroidsummary.viewholder;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.mhzhaog.myandroidsummary.R;
import com.example.mhzhaog.myandroidsummary.bean.Child;
import com.example.mhzhaog.myandroidsummary.interfaces.ItemDataClickListener;

import java.util.List;

/**
 * @Author Zheng Haibo
 * @PersonalWebsite http://www.mobctrl.net
 * @Description
 */
public class ParentViewHolder extends BaseViewHolder {

//	public ImageView image;
	public TextView text;
	public ImageView expand;
//	public TextView count;
	public RelativeLayout relativeLayout;
	private int itemMargin;

	public ParentViewHolder(View itemView) {
		super(itemView);
//		image = (ImageView) itemView.findViewById(R.id.image);
		text = (TextView) itemView.findViewById(R.id.text);
		expand = (ImageView) itemView.findViewById(R.id.expand);
//		count = (TextView) itemView.findViewById(R.id.count);
		relativeLayout = (RelativeLayout) itemView.findViewById(R.id.container);
//		itemMargin = (int) ISViewUtils.dip2px(itemView.getContext(),8);
		itemMargin = itemView.getContext().getResources()
				.getDimensionPixelSize(R.dimen.item_margin);
	}

	public void bindView(final Child itemData, final int position,
						 final ItemDataClickListener imageClickListener) {
		RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) text
				.getLayoutParams();
		params.leftMargin = itemMargin * (itemData.getTreeDepth()+1);
		text.setLayoutParams(params);
		text.setText(itemData.getName());

		if(itemData.getIsCamera()==0){
			expand.setVisibility(View.GONE);
		}else{
			expand.setVisibility(View.VISIBLE);
		}

		if (itemData.isExpand()) {
			expand.setRotation(180);
			List<Child> children = itemData.getChild();
			if (children != null) {
//				count.setText(String.format("(%s)", itemData.getChild()
//						.size()));
			}
//			count.setVisibility(View.VISIBLE);
		} else {
			expand.setRotation(0);
//			count.setVisibility(View.GONE);
		}
		relativeLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (imageClickListener != null) {
					if(itemData.getIsCamera() == 0){
						imageClickListener.onPlayVideo(itemData);
						return;
					}
					if (itemData.isExpand()) {
						imageClickListener.onHideChildren(itemData);
						itemData.setExpand(false);
						rotationExpandIcon(180, 0);
//						count.setVisibility(View.GONE);
					} else {
						imageClickListener.onExpandChildren(itemData);
						itemData.setExpand(true);
						rotationExpandIcon(0, 180);
						List<Child> children = itemData.getChild();
						if (children != null) {
//							count.setText(String.format("(%s)", itemData
//									.getChild().size()));
						}
//						count.setVisibility(View.VISIBLE);
					}
				}

			}
		});
//		image.setOnLongClickListener(new OnLongClickListener() {
//
//			@Override
//			public boolean onLongClick(View view) {
//				Toast.makeText(view.getContext(), "longclick",
//						Toast.LENGTH_SHORT).show();
//				return false;
//			}
//		});
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void rotationExpandIcon(float from, float to) {
		if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			ValueAnimator valueAnimator = ValueAnimator.ofFloat(from, to);
			valueAnimator.setDuration(150);
			valueAnimator.setInterpolator(new DecelerateInterpolator());
			valueAnimator.addUpdateListener(new AnimatorUpdateListener() {

				@Override
				public void onAnimationUpdate(ValueAnimator valueAnimator) {
					expand.setRotation((Float) valueAnimator.getAnimatedValue());
				}
			});
			valueAnimator.start();
		}
	}
}
