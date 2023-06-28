package com.yorkys.handlers;

public class ChestItem {
    private String itemName;
    private int itemAmount;

    public ChestItem(String itemName, int itemAmount) {
        this.itemName = itemName;
        this.itemAmount = itemAmount;
    }

    public String getName() {
        return itemName;
    }

    public int getAmount() {
        return itemAmount;
    }
}
