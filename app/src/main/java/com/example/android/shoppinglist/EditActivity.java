package com.example.android.shoppinglist;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.shoppinglist.ShopListDB.ItemEntry;
import com.example.android.shoppinglist.ShopListDB.shopItemDB;
import com.example.android.shoppinglist.Utils.AppExecutors;

public class EditActivity extends AppCompatActivity {

    public static String EXTRA_EDIT_ENTRY ="edit_item";
    public static int EXTRA_ADD_ENTRY =5;

    private ItemEntry itemEntry;

    EditText mEditItemName;
    Button mButton;

    private shopItemDB mDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_edit );
        initViews ();
        itemEntry = getIntent ().getParcelableExtra ( EXTRA_EDIT_ENTRY );
        mDB = shopItemDB.getInstance ( getApplicationContext () );

        mButton.setOnClickListener ( new View.OnClickListener () {


            @Override
            public void onClick(View view) {
                createOrUpdateNote ();
            }
        } );
        populateUI ();

    }
        @Override
        protected void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
        }

        private void initViews() {
            mEditItemName = findViewById(R.id.edit_item);
            mButton = findViewById(R.id.update_item);

        }


        public void populateUI() {
            if (itemEntry == null) {
                return;
            }

            mEditItemName.setText ( itemEntry.getItemName () );
            mButton.setText ( "Update" );

        }


    public void createOrUpdateNote(){
        final  String  item = mEditItemName.getText().toString();

        if (itemEntry == null){
            //create new item
            AppExecutors.getInstance().diskIO().execute( new Runnable() {
                @Override
                public void run() {
                    mDB.shopItemDao ().insertTask(new ItemEntry(item));
                    setResult( Activity.RESULT_OK);
                    finish();
                }
            });

        }else {
            //update item
            AppExecutors.getInstance().diskIO().execute(
                    new Runnable() {
                        @Override
                        public void run() {
                            mDB.shopItemDao ().updateTask(new ItemEntry(itemEntry.getId(),item));
                        }
                    }
            );
            setResult(Activity.RESULT_OK);
            finish();
        }
    }
}

