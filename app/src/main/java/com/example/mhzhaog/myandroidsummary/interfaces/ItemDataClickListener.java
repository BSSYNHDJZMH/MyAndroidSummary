package com.example.mhzhaog.myandroidsummary.interfaces;

import com.example.mhzhaog.myandroidsummary.bean.Child;
import org.jetbrains.annotations.NotNull;

/**
 * @Author Zheng Haibo
 * @PersonalWebsite http://www.mobctrl.net
 * @Description
 */
public interface ItemDataClickListener {

	public void onExpandChildren(Child child);

	public void onHideChildren(Child child);

	public void onPlayVideo(Child child);

	void invoke(@NotNull ItemDataClickListener itemDataClickListener);
}
