package com.example.android.shoppinglist.itemsList;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.android.shoppinglist.ShopListDB.ItemEntry;
import com.example.android.shoppinglist.ShopListDB.shopItemDB;

public class ItemViewModel extends ViewModel {
    private LiveData<ItemEntry> shopListItem;

    public ItemViewModel(shopItemDB database, int itemId) {
        shopListItem = database.shopItemDao ().loadTaskById(itemId);
    }


    public LiveData<ItemEntry> getTask() {
        return shopListItem;
    }
}
