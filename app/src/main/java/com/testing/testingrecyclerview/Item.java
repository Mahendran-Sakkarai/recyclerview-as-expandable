package com.testing.testingrecyclerview;

/**
 * Created by Nandakumar on 12/4/2016.
 */

public class Item {
    private int id;

    private String name;

    private int value;

    private boolean isSelected;

    private int categoryId;

    public Item(int id, String name, int value, boolean isSelected, int categoryId) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.isSelected = isSelected;
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
