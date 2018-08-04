package com.example.android.shoppinglist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.android.shoppinglist.ShopListDB.ItemEntry;
import com.example.android.shoppinglist.ShopListDB.shopItemDB;
import com.example.android.shoppinglist.Utils.AppExecutors;

public class DetailActivity extends Activity {

    public static String EXTRA_REQUEST_EDIT = "ITEM_DETAIL";
    private shopItemDB mshopItemDB;

    private TextView mUpdateDate;
    private TextView mItem;
    private ItemEntry mItemEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_detail );
        mItemEntry = getIntent ().getParcelableExtra ( EXTRA_REQUEST_EDIT );
        mItem = findViewById ( R.id.tv_item );
        mUpdateDate = findViewById ( R.id.tv_date );
        mshopItemDB = shopItemDB.getInstance ( this );
        populateUi ();
    }

    private void populateUi() {
        if (mItemEntry == null) {
            return;
        }
        mUpdateDate.setText ( mItemEntry.getUpdatedAt () );
        mItem.setText ( mItemEntry.getItemName () );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater ().inflate ( R.menu.menu_main, menu );
        return super.onCreateOptionsMenu ( menu );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId ();
        if (id == R.id.action_edit) {
            Intent intent = new Intent ( this, EditActivity.class );
            intent.putExtra ( EditActivity.EXTRA_EDIT_ENTRY, mItemEntry );
            startActivityForResult ( intent, EditActivity.EXTRA_ADD_ENTRY );

        }

        if (id == R.id.action_delete) {
            if (mItemEntry == null) {

            } else {
                AppExecutors.getInstance ().diskIO ().execute ( new Runnable () {
                    @Override
                    public void run() {
                        mshopItemDB.shopItemDao ().deleteTask ( mItemEntry );
                        finish ();
                    }
                } );
            }
        }
        return super.onOptionsItemSelected ( item );
    }

}
