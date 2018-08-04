package com.example.android.shoppinglist.ShopListDB;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface shopItemDao {

    @Query("SELECT * FROM shopListItems")
    LiveData<List<ItemEntry>>loadAllTasks();

    @Insert
    void insertTask(ItemEntry itemsEntry);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTask(ItemEntry itemsEntry);

    @Delete
    void deleteTask(ItemEntry itemsEntry);

    @Query("SELECT * FROM shopListItems WHERE id = :id")
    LiveData<ItemEntry> loadTaskById(int id);

}
