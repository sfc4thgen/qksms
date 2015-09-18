package com.moez.QKSMS.ui.whitelist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.moez.QKSMS.R;
import com.moez.QKSMS.ui.view.QKTextView;

public class WhitelistViewHolder extends RecyclerView.ViewHolder {

    View root;
    QKTextView name;
    QKTextView address;

    public WhitelistViewHolder(View view) {
        super(view);

        root = view.findViewById(R.id.root);
        name = (QKTextView) view.findViewById(R.id.name);
        address = (QKTextView) view.findViewById(R.id.address);
    }
}
