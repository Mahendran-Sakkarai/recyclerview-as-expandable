package com.testing.testingrecyclerview;

import java.util.List;

/**
 * Created by Nandakumar on 12/4/2016.
 */

public class Category {
    private int id;

    private String name;

    private int value;

    private boolean isSelected;

    private boolean isExpanded;

    private List<Item> items;

    public Category(int id, String name, int value, boolean isSelected, boolean isExpanded) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.isSelected = isSelected;
        this.isExpanded = isExpanded;
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

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
