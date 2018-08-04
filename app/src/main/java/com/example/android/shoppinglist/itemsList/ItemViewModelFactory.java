package com.example.android.shoppinglist.itemsList;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.android.shoppinglist.ShopListDB.shopItemDB;

public class ItemViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final shopItemDB mDb;
    private final int mItemId;

    public ItemViewModelFactory(shopItemDB mDb, int itemId) {
        this.mDb = mDb;
        mItemId = itemId;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new ItemViewModel(mDb, mItemId);
    }
}
