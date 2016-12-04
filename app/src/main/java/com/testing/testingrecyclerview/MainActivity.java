package com.testing.testingrecyclerview;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ActivityCallBack {
    private ListItemAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView list = (RecyclerView) findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));

        mListAdapter = new ListItemAdapter(this, this);
        list.setAdapter(mListAdapter);

        mListAdapter.setData(getListItems());
    }

    public ArrayList<Category> getListItems() {
        ArrayList<Category> categoryList = new ArrayList<>();

        Category category1 = new Category(1, "category 1", 0, false, false);
        List<Item> item1 = new ArrayList<>();
        item1.add(new Item(1, "item 1", 5000, false, 1));
        item1.add(new Item(2, "item 2", 8000, false, 1));
        item1.add(new Item(3, "item 3", 10000, false, 1));
        item1.add(new Item(4, "item 4", 2000, false, 1));
        item1.add(new Item(5, "item 5", 9000, false, 1));
        item1.add(new Item(6, "item 6", 3000, false, 1));
        category1.setItems(item1);
        categoryList.add(category1);

        Category category2 = new Category(2, "category 2", 0, false, false);
        List<Item> item2 = new ArrayList<>();
        item2.add(new Item(7, "item 7", 5000, false, 2));
        item2.add(new Item(8, "item 8", 8000, false, 2));
        item2.add(new Item(9, "item 9", 10000, false, 2));
        item2.add(new Item(10, "item 10", 2000, false, 2));
        item2.add(new Item(11, "item 11", 9000, false, 2));
        item2.add(new Item(12, "item 12", 3000, false, 2));
        category2.setItems(item2);
        categoryList.add(category2);

        Category category3 = new Category(3, "category 3", 0, false, false);
        List<Item> item3 = new ArrayList<>();
        item3.add(new Item(13, "item 13", 5000, false, 3));
        item3.add(new Item(14, "item 14", 8000, false, 3));
        item3.add(new Item(15, "item 15", 10000, false, 3));
        item3.add(new Item(16, "item 16", 2000, false, 3));
        item3.add(new Item(17, "item 17", 9000, false, 3));
        item3.add(new Item(18, "item 18", 3000, false, 3));
        category3.setItems(item3);
        categoryList.add(category3);

        return categoryList;
    }

    @Override
    public void hideKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }
}
