package com.testing.testingrecyclerview;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nandakumar on 12/4/2016.
 */
public class ListItemAdapter extends RecyclerView.Adapter implements ListCallBack {
    private static final int CATEGORY = 1;
    private static final int ITEM = 2;
    private final Context mContext;
    private final ActivityCallBack mCallback;
    private ArrayList<Category> baseItems = new ArrayList<>();
    private ArrayList<Object> mItems = new ArrayList<>();

    public ListItemAdapter(Context context, ActivityCallBack callBack) {
        this.mContext = context;
        this.mCallback = callBack;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder viewHolder;
        switch (viewType) {
            case CATEGORY:
                viewHolder = new CategoryHolder(inflater.inflate(R.layout.category_item, parent, false), this);
                break;
            case ITEM:
                viewHolder = new ItemHolder(inflater.inflate(R.layout.list_item, parent, false), this);
                break;
            default:
                viewHolder = new ErrorHolder(inflater.inflate(android.R.layout.simple_list_item_1, parent, false));
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == CATEGORY) {
            ((CategoryHolder) holder).bindData((Category) mItems.get(position));
        } else if (getItemViewType(position) == ITEM) {
            ((ItemHolder) holder).bindData((Item) mItems.get(position));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List payloads) {
        if (payloads != null && !payloads.isEmpty() && (payloads.get(0) instanceof Category)) {
            ((CategoryHolder) holder).bindData((Category) mItems.get(position));
        } else {
            super.onBindViewHolder(holder, position, payloads);
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (getItem(position) instanceof Category)
            return CATEGORY;
        else if (getItem(position) instanceof Item)
            return ITEM;
        else
            return -1;
    }

    private Object getItem(int position) {
        return mItems.get(position);
    }

    public void setData(ArrayList<Category> listItems) {
        this.baseItems = listItems;

        generateItems();
    }

    private void generateItems() {
        mCallback.hideKeyboard();
        mItems = new ArrayList<>();

        for (Category category : baseItems) {
            mItems.add(category);
            if (category.isExpanded()) {
                for (Item item : category.getItems()) {
                    mItems.add(item);
                }
            }
        }

        notifyDataSetChanged();
    }

    @Override
    public void setItemSelected(int groupId, int itemId, boolean selected) {
        for (Category category : baseItems) {
            if (category.getId() == groupId) {
                int value = 0;
                boolean isAllSelected = true;
                for (Item item : category.getItems()) {
                    if (item.getId() == itemId)
                        item.setSelected(selected);

                    if (!item.isSelected()) {
                        isAllSelected = false;
                    } else {
                        value += item.getValue();
                    }
                }

                category.setSelected(isAllSelected);
                category.setValue(value);
            }
        }

        notifyItem(groupId, itemId);
        notifyGroup(groupId);
    }

    @Override
    public void setExpanded(int groupId, boolean expanded) {
        for (Category category : baseItems) {
            if (category.getId() == groupId)
                category.setExpanded(expanded);
        }

        generateItems();
    }

    @Override
    public void setGroupSelected(int groupId, boolean selected) {
        for (Category category : baseItems) {
            if (category.getId() == groupId) {
                int value = 0;
                category.setSelected(selected);
                for (Item item : category.getItems()) {
                    item.setSelected(selected);
                    if (item.isSelected()) {
                        value += item.getValue();
                    }
                }
                category.setValue(value);
            }
        }

        generateItems();
    }

    @Override
    public void updateTotal(int groupId, int itemId, String s, boolean isGeneration) {
        for (Category category : baseItems) {
            if (category.getId() == groupId) {
                int value = 0;
                for (Item item : category.getItems()) {
                    if (item.getId() == itemId)
                        item.setValue(s.length() > 0 ? Integer.parseInt(s) : 0);

                    if (item.isSelected())
                        value += item.getValue();
                }
                category.setValue(value);
            }
        }

        notifyGroup(groupId);
    }

    @Override
    public void hideKeyBoard() {
        mCallback.hideKeyboard();
    }

    private void notifyGroup(int groupId) {
        for (int i = 0; i < mItems.size(); i++) {
            Object mItem = mItems.get(i);
            if (mItem instanceof Category && ((Category) mItem).getId() == groupId) {
                notifyItemChanged(i, mItem);
            }
        }
    }

    private void notifyItem(int groupId, int itemId) {
        for (int i = 0; i < mItems.size(); i++) {
            Object mItem = mItems.get(i);
            if (mItem instanceof Item && ((Item) mItem).getId() == itemId && ((Item) mItem).getCategoryId() == groupId) {
                notifyItemChanged(i, mItem);
            }
        }
    }
}
