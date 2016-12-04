package com.testing.testingrecyclerview;

/**
 * Created by Nandakumar on 12/4/2016.
 */

public interface ListCallBack {
    void setItemSelected(int groupId, int itemId, boolean selected);

    void setExpanded(int groupId, boolean expanded);

    void setGroupSelected(int groupId, boolean selected);

    void updateTotal(int categoryId, int itemId, String s, boolean isGeneration);

    void hideKeyBoard();
}
