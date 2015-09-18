package com.moez.QKSMS.ui.whitelist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.moez.QKSMS.R;
import com.moez.QKSMS.common.WhitelistUtils;
import com.moez.QKSMS.ui.base.QKActivity;
import com.moez.QKSMS.ui.base.QKFragment;
import com.moez.QKSMS.ui.base.RecyclerCursorAdapter;
import com.moez.QKSMS.ui.dialog.QKDialog;
import com.moez.QKSMS.ui.view.QKEditText;



public class WhitelistFragment extends QKFragment implements RecyclerCursorAdapter.ItemClickListener<String> {

    private QKActivity mContext;
    private RecyclerView mRecyclerView;
    private WhitelistAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mContext = (QKActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_whitelist, container, false);

        mAdapter = new WhitelistAdapter(mContext);
        mAdapter.setData(WhitelistUtils.getWhitelist(mContext));
        mAdapter.setClickListener(this);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.whitelist);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.whitelist, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add) {
            final QKEditText editText = new QKEditText(mContext);

            new QKDialog()
                    .setContext(mContext)
                    .setTitle(R.string.title_add_whitelist)
                    .setCustomView(editText)
                    .setPositiveButton(R.string.menu_add, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (editText.getText().length() > 0) {
                                mAdapter.setData(WhitelistUtils.addToWhitelist(
                                        mContext, editText.getText().toString()));
                            }
                        }
                    })
                    .setNegativeButton(R.string.cancel, null)
                    .show(getFragmentManager(), "add to whitelist");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemClick(final String object, View view) {
        new QKDialog()
                .setContext(mContext)
                .setTitle(R.string.title_remove_whitelist)
                .setPositiveButton(R.string.yes, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mAdapter.setData(WhitelistUtils.removeFromWhitelist(mContext, object));
                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .show(getFragmentManager(), "remove from whitelist");
    }

    @Override
    public void onItemLongClick(String object, View view) {
        onItemClick(object, view);
    }
}
