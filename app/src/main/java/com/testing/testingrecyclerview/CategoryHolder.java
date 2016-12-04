package com.testing.testingrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Nandakumar on 12/4/2016.
 */
public class CategoryHolder extends RecyclerView.ViewHolder {
    private final TextView mTitle;
    private final TextView mValue;
    private final CheckBox mCheckBox;
    private final ImageView mExpandableImage;
    private final LinearLayout mCheckBoxLayout;
    private final ListCallBack mCallBack;
    private final LinearLayout mCategoryView;

    public CategoryHolder(View view, ListCallBack callBack) {
        super(view);
        mCallBack = callBack;
        mTitle = (TextView) view.findViewById(R.id.title);
        mValue = (TextView) view.findViewById(R.id.value);
        mCheckBox = (CheckBox) view.findViewById(R.id.checkbox);
        mCheckBoxLayout = (LinearLayout) view.findViewById(R.id.checkbox_layout);
        mExpandableImage = (ImageView) view.findViewById(R.id.expandable);
        mCategoryView = (LinearLayout) view.findViewById(R.id.category);
    }

    public void bindData(final Category category) {
        mTitle.setText(category.getName());
        mValue.setText(""+category.getValue());
        mCheckBox.setChecked(category.isSelected());
        mCheckBoxLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallBack.setGroupSelected(category.getId(), !category.isSelected());
            }
        });
        mCategoryView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallBack.setExpanded(category.getId(), !category.isExpanded());
            }
        });
    }
}
