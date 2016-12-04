package com.testing.testingrecyclerview;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Nandakumar on 12/4/2016.
 */
public class ItemHolder extends RecyclerView.ViewHolder implements View.OnFocusChangeListener, TextWatcher {
    private final TextView mTitle;
    private final EditText mValue;
    private final CheckBox mCheckBox;
    private final LinearLayout mCheckBoxLayout;
    private final ListCallBack mCallback;
    private Item mItem;

    public ItemHolder(View view, ListCallBack callBack) {
        super(view);
        mCallback = callBack;
        mTitle = (TextView) view.findViewById(R.id.name);
        mValue = (EditText) view.findViewById(R.id.value);
        mCheckBox = (CheckBox) view.findViewById(R.id.checkbox);
        mCheckBoxLayout = (LinearLayout) view.findViewById(R.id.checkbox_layout);
        mValue.clearFocus();
        mValue.setOnFocusChangeListener(this);
        mValue.addTextChangedListener(this);
    }

    public void bindData(final Item item) {
        this.mItem = item;
        mTitle.setText(item.getName());
        mValue.setText("" + item.getValue());
        mCheckBox.setChecked(item.isSelected());
        mValue.setEnabled(item.isSelected());
        mCheckBoxLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mValue.clearFocus();
                mCallback.setItemSelected(item.getCategoryId(), item.getId(), !item.isSelected());
            }
        });

        mValue.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId== EditorInfo.IME_ACTION_DONE){
                    mCallback.updateTotal(item.getCategoryId(), item.getId(), mValue.getText().toString(), true);
                    mValue.clearFocus();
                }
                return false;
            }
        });
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        if (mItem != null && view != null && !hasFocus) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mCallback.updateTotal(mItem.getCategoryId(), mItem.getId(), mValue.getText().toString(), false);
                }
            }, 200);
        } else {
            mCallback.hideKeyBoard();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (mItem != null) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mCallback.updateTotal(mItem.getCategoryId(), mItem.getId(), mValue.getText().toString(), false);
                }
            }, 200);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
