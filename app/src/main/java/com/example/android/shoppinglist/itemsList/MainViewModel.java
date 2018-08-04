package com.example.android.shoppinglist.itemsList;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.android.shoppinglist.ShopListDB.ItemEntry;
import com.example.android.shoppinglist.ShopListDB.shopItemDB;

import java.util.List;

public class MainViewModel extends AndroidViewModel{
    // Constant for logging
    private static final String TAG = MainViewModel.class.getSimpleName();

    private LiveData<List<ItemEntry>> items;

    public MainViewModel(@NonNull Application application) {
        super ( application );

        shopItemDB database = shopItemDB.getInstance ( this.getApplication () );
        Log.d(TAG, "Actively retrieving the note entries from the DataBase");
        items = database.shopItemDao ().loadAllTasks();
    }

    public LiveData<List<ItemEntry>> getTasks() {
        return items;
    }

}
