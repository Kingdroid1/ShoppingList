package com.example.android.shoppinglist;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.android.shoppinglist.ShopListDB.ItemEntry;
import com.example.android.shoppinglist.itemsList.ItemAdapter;
import com.example.android.shoppinglist.itemsList.MainViewModel;

import java.util.List;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName ();


    private RecyclerView mRecyclerView;
    private ItemAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        mRecyclerView = findViewById(R.id.recycler_view);
        adapterInit();

        FloatingActionButton fab = findViewById ( R.id.fab );
        fab.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                //Create a new intent to start an new_edit_activity
                Intent addTaskIntent = new Intent(MainActivity.this, EditActivity.class);
                startActivityForResult(addTaskIntent, EditActivity.EXTRA_ADD_ENTRY );
            }
        } );
    }


    public void adapterInit(){
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager (this));
        DividerItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), VERTICAL);
        mRecyclerView.addItemDecoration(decoration);

        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.getTasks().observe(this, new Observer<List<ItemEntry>> () {
            @Override
            public void onChanged(@Nullable List<ItemEntry> itemList) {
                mAdapter = new ItemAdapter(itemList,getApplicationContext());
                mRecyclerView.setAdapter(mAdapter);
            }
        });

    }
}
