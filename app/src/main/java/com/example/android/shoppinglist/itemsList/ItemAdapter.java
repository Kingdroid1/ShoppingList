package com.example.android.shoppinglist.itemsList;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.shoppinglist.R;
import com.example.android.shoppinglist.ShopListDB.ItemEntry;
import com.example.android.shoppinglist.DetailActivity;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List <ItemEntry> mShopItemList;
    private Context mContext;

    public ItemAdapter(List<ItemEntry> itemList, Context context) {
        mShopItemList = itemList;
        mContext = context;
    }
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate( R.layout.item_list,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, int position) {
        ItemEntry itemEntry = mShopItemList.get(position);
        holder.mItem.setText(itemEntry.getItemName ());
        holder.mDate.setText(itemEntry.getUpdatedAt());
    }



    @Override
    public int getItemCount() {
        return mShopItemList.size ();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView mItem;
        TextView mDate;
        LinearLayout mLinearLayout;

        public ViewHolder(View itemView) {
            super ( itemView );
            mItem = itemView.findViewById(R.id.itemTitle);
            mDate = itemView.findViewById(R.id.noteUpdatedAt);
            mLinearLayout = itemView.findViewById(R.id.root_list);
            mLinearLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            ItemEntry itemEntry = mShopItemList.get(getAdapterPosition());
            Intent intent = new Intent(mContext, DetailActivity.class);
            intent.putExtra( DetailActivity.EXTRA_REQUEST_EDIT,itemEntry);
            mContext.startActivity(intent);

        }
    }
}
