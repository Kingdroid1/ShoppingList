package com.example.android.shoppinglist.ShopListDB;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

@Database( entities = {ItemEntry.class}, version = 1, exportSchema = false)
public abstract class shopItemDB extends RoomDatabase {

        private static final String LOG_TAG = shopItemDB.class.getSimpleName();
        private static final Object LOCK = new Object();
        private static final String DATABASE_NAME = "ShopListItems";
        private static shopItemDB sInstance;

        public static shopItemDB getInstance(Context context) {
            if (sInstance == null) {
                synchronized (LOCK) {
                    Log.d(LOG_TAG, "Creating new database instance");
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            shopItemDB.class, shopItemDB.DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
            Log.d(LOG_TAG, "Getting the database instance");
            return sInstance;
        }

        public abstract shopItemDao shopItemDao();

    }
