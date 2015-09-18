package com.moez.QKSMS.ui.whitelist;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import com.moez.QKSMS.R;
import com.moez.QKSMS.common.LiveViewManager;
import com.moez.QKSMS.interfaces.LiveView;
import com.moez.QKSMS.ui.ThemeManager;
import com.moez.QKSMS.ui.base.QKActivity;
import com.moez.QKSMS.ui.settings.SettingsFragment;


public class WhitelistActivity extends QKActivity implements LiveView {

    private View mBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_whitelist);
        mBackground = findViewById(R.id.background);

        setTitle(R.string.title_whitelist);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getFragmentManager().beginTransaction().add(R.id.content_frame, new WhitelistFragment()).commit();

        LiveViewManager.registerView(this);
        LiveViewManager.registerPreference(this, SettingsFragment.THEME);
        refresh();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onKeyUp(KeyEvent.KEYCODE_BACK, null);
                return true;
        }
        
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }

        return false;
    }

    @Override
    public void refresh() {
        mBackground.setBackgroundColor(ThemeManager.getBackgroundColor());
    }
}
