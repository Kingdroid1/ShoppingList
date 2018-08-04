package com.example.android.shoppinglist.ShopListDB;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Entity(tableName = "shopListItems")
public class ItemEntry implements Parcelable{

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String itemName;
    private String updatedAt;

    static DateFormat dateFormat = new SimpleDateFormat ("dd/MMM/yyyy");

    public ItemEntry(){}

    @Ignore
    public ItemEntry(String itemName) {
        //use to create
        this.itemName = itemName;
        updatedAt =dateFormat.format( Calendar.getInstance().getTime());
    }
    @Ignore
    public ItemEntry(int id, String itemName) {
        //use to update
        this.id = id;
        this.itemName = itemName;
        this.updatedAt = dateFormat.format(Calendar.getInstance().getTime());
    }

    protected ItemEntry(Parcel in){
        id = in.readInt();
        itemName = in.readString();
        updatedAt = in.readString();
    }

    public static final Creator <ItemEntry> CREATOR = new Creator <ItemEntry> () {
        @Override
        public ItemEntry createFromParcel(Parcel in) {
            return new ItemEntry ( in );
        }

        @Override
        public ItemEntry[] newArray(int size) {
            return new ItemEntry[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(id);
        parcel.writeString(itemName);
        parcel.writeString(updatedAt);
    }
}
