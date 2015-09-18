package com.moez.QKSMS.ui.whitelist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.moez.QKSMS.R;
import com.moez.QKSMS.data.Contact;
import com.moez.QKSMS.ui.base.RecyclerCursorAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public class WhitelistAdapter extends RecyclerView.Adapter<WhitelistViewHolder> {

    private Context mContext;
    private ArrayList<String> mData;
    private RecyclerCursorAdapter.ItemClickListener<String> mItemClickListener;

    public WhitelistAdapter(Context context) {
        mContext = context;
        setHasStableIds(true);
    }

    public void setData(Set<String> data) {
        mData = new ArrayList<>(data);
        Collections.sort(mData);
        notifyDataSetChanged();
    }

    public void setClickListener(RecyclerCursorAdapter.ItemClickListener<String> clickListener) {
        mItemClickListener = clickListener;
    }

    @Override
    public WhitelistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.list_item_whitelistee, null);
        return new WhitelistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WhitelistViewHolder holder, int position) {
        final String address = mData.get(position);
        Contact contact = Contact.get(address, true);

        holder.address.setText(contact.getNumber());
        holder.name.setText(contact.getName());
        holder.name.setVisibility(TextUtils.isEmpty(contact.getName()) ||
                contact.getName().equals(contact.getNumber()) ? View.GONE : View.VISIBLE);

        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onItemClick(address, v);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }
}
