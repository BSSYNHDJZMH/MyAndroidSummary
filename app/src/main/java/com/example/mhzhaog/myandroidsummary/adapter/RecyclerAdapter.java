package com.example.mhzhaog.myandroidsummary.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.mhzhaog.myandroidsummary.R;
import com.example.mhzhaog.myandroidsummary.bean.Child;
import com.example.mhzhaog.myandroidsummary.interfaces.ItemDataClickListener;
import com.example.mhzhaog.myandroidsummary.viewholder.BaseViewHolder;
import com.example.mhzhaog.myandroidsummary.viewholder.ParentViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Zheng Haibo
 * @PersonalWebsite http://www.mobctrl.net
 * @Description
 */
public class RecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private Context mContext;
    private List<Child> mDataSet;
//	private OnScrollToListener onScrollToListener;

//	public void setOnScrollToListener(OnScrollToListener onScrollToListener) {
//		this.onScrollToListener = onScrollToListener;
//	}

    public RecyclerAdapter(Context context) {
        mContext = context;
        mDataSet = new ArrayList<Child>();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {

            default:
                view = LayoutInflater.from(mContext).inflate(
                        R.layout.item_recycler_parent, parent, false);
                return new ParentViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case Child.ITEM_TYPE_PARENT:
                ParentViewHolder imageViewHolder = (ParentViewHolder) holder;
                imageViewHolder.bindView(mDataSet.get(position), position,
                        itemDataClickListener);
                break;

            default:
                break;
        }
    }

    private ItemDataClickListener itemDataClickListener;

    public ItemDataClickListener getItemDataClickListener() {
        return itemDataClickListener;
    }

    public void setItemDataClickListener(ItemDataClickListener itemDataClickListener) {
        this.itemDataClickListener = itemDataClickListener;
    }
    //	private ItemDataClickListener itemDataClickListener = new ItemDataClickListener() {
//
//		@Override
//		public void onExpandChildren(Child child) {
//			int position = getCurrentPosition(child.getUuid());
//			List<Child> children = child.getChild();
//			if (children == null) {
//				return;
//			}
//			addAll(children, position + 1);// 插入到点击点的下方
//			child.setChild(children);
////			if (onScrollToListener != null) {
////				onScrollToListener.scrollTo(position + 1);
////			}
//		}
//
//		@Override
//		public void onHideChildren(Child itemData) {
//			int position = getCurrentPosition(itemData.getUuid());
//			List<Child> children = itemData.getChild();
//			if (children == null) {
//				return;
//			}
////			removeAll(position + 1, getChildrenCount(itemData) - 1);
//			removeAll(position + 1, children.size());
////			if (onScrollToListener != null) {
////				onScrollToListener.scrollTo(position);
////			}
//			itemData.setChild(null);
//		}
//	};

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public int getChildrenCount(Child item) {
        List<Child> list = new ArrayList<>();
        printChild(item, list);
        return list.size();
    }

    private void printChild(Child item, List<Child> list) {
        list.add(item);
        if (item.isExpand()) {
            if (item.getChild() != null) {
                for (int i = 0; i < item.getChild().size(); i++) {
                    printChild(item.getChild().get(i), list);
                }
            }
        }
    }

    /**
     * 根据路径获取子目录或文件
     *
     * @param treeDepth
     * @return
     */
//	public List<ItemData> getChildrenByPath(String path, int treeDepth) {
//		treeDepth++;
//		try {
//			List<ItemData> list = new ArrayList<ItemData>();
//			File file = new File(path);
//			File[] children = file.listFiles();
//			List<ItemData> fileList = new ArrayList<ItemData>();
//			for (File child : children) {
//				if (child.isDirectory()) {
//					list.add(new ItemData(ItemData.ITEM_TYPE_PARENT, child
//							.getName(), child.getAbsolutePath(), UUID
//							.randomUUID().toString(), treeDepth, null));
//				} else {
//					fileList.add(new ItemData(ItemData.ITEM_TYPE_CHILD, child
//							.getName(), child.getAbsolutePath(), UUID
//							.randomUUID().toString(), treeDepth, null));
//				}
//			}
//			Collections.sort(list);
//			Collections.sort(fileList);
//			list.addAll(fileList);
//			return list;
//		} catch (Exception e) {
//
//		}
//		return null;
//	}

//	public List<Child> getChildList(int treeDepth) {
//		List<Child> rootList = new ArrayList<>();
//		List<Child> streetList = new ArrayList<>();
//		List<Child> cameraList = new ArrayList<>();
//		for (int i = 0; i < 500; i++) {
//			Child child = new Child();
//			child.setName("摄像头"+i);
//			child.setTreeDepth(treeDepth+3);
//			child.setUuid(UUID.randomUUID().toString());
//			cameraList.add(child);
//		}
//
//		for (int i = 0; i < 10; i++) {
//			Child child = new Child();
//			child.setName("街道"+i);
//			child.setChild(cameraList);
//			child.setTreeDepth(treeDepth+2);
//            child.setUuid(UUID.randomUUID().toString());
//			streetList.add(child);
//		}
//
//		for (int i = 0; i < 10; i++) {
//			Child child =new Child();
//			child.setName("县"+i);
//			child.setChild(streetList);
//			child.setTreeDepth(treeDepth+1);
//            child.setUuid(UUID.randomUUID().toString());
//			rootList.add(child);
//		}
//		return rootList;
//	}

    /**
     * 从position开始删除，删除
     *
     * @param position
     * @param itemCount 删除的数目
     */
    public void removeAll(int position, int itemCount) {
        for (int i = 0; i < itemCount; i++) {
            mDataSet.remove(position);

        }


        notifyDataSetChanged();
//		notifyItemRangeRemoved(position, itemCount);
    }
//    public void removeAll(int position, List<Child> children) {
//        for (int i = 0; i < children.size(); i++) {
//            if (children.get(i).getChild() == null) {
//                mDataSet.remove(position);
//                return;
//            }
//            List<Child> childList = children.get(i).getChild();
//
//            removeAll(position, childList);
//        }
//
//        notifyDataSetChanged();
////		notifyItemRangeRemoved(position, itemCount);
//    }

    public int getCurrentPosition(String uuid) {
        for (int i = 0; i < mDataSet.size(); i++) {
            if (uuid.equalsIgnoreCase(mDataSet.get(i).getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getItemViewType(int position) {
        return mDataSet.get(position).getType();
    }

    public void add(Child text, int position) {
        mDataSet.add(position, text);
//		notifyItemInserted(position);
        notifyDataSetChanged();
    }

    public void addAll(List<Child> list, int position) {
        mDataSet.addAll(position, list);
//		notifyItemRangeInserted(position, list.size());
        notifyDataSetChanged();
    }

    public void delete(int pos) {
        if (pos >= 0 && pos < mDataSet.size()) {
            if (mDataSet.get(pos).getType() == Child.ITEM_TYPE_PARENT
                    && mDataSet.get(pos).isExpand()) {// 父组件并且子节点已经展开
                for (int i = 0; i < mDataSet.get(pos).getChild().size() + 1; i++) {
                    mDataSet.remove(pos);
                }
                notifyItemRangeRemoved(pos, mDataSet.get(pos).getChild()
                        .size() + 1);
            } else {// 孩子节点，或没有展开的父节点
                mDataSet.remove(pos);
//				notifyItemRemoved(pos);
                notifyDataSetChanged();
            }
        }
    }


    public void clearAllData() {
        mDataSet.clear();

        notifyDataSetChanged();
    }
}
