package com.moez.QKSMS.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import com.moez.QKSMS.common.utils.PhoneNumberUtils;
import com.moez.QKSMS.ui.settings.SettingsFragment;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class WhitelistUtils {

    private static final Set<String> DEFAULT_SET = new HashSet<>(Arrays.asList(
            "number1",
            "number2"));

    public static boolean isWhitelisted(Context context, String address) {
        Set<String> whitelist = getWhitelist(context);
        for (String s : whitelist) {
            if (PhoneNumberUtils.compareLoosely(address, s)) {
                return true;
            }
        }

        return false;
    }

    public static Set<String> addToWhitelist(Context context, String address) {
        Set<String> whitelist = getWhitelist(context);
        whitelist.add(address);
        saveWhitelist(context, whitelist);
        return whitelist;
    }

    public static Set<String> removeFromWhitelist(Context context, String address) {
        Set<String> whitelist = getWhitelist(context);
        for (String s : whitelist) {
            if (PhoneNumberUtils.compareLoosely(address, s)) {
                whitelist.remove(s);
            }
        }

        saveWhitelist(context, whitelist);
        return whitelist;
    }

    public static Set<String> getWhitelist(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getStringSet(SettingsFragment.WHITELIST, DEFAULT_SET);
    }

    @SuppressLint("CommitPrefEdits")
    public static void saveWhitelist(Context context, Set<String> whitelist) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putStringSet(SettingsFragment.WHITELIST, whitelist).commit();
    }

    public static Uri getRingtone(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String uri = prefs.getString(SettingsFragment.WHITELIST_RINGTONE, SettingsFragment.DEFAULT_NOTIFICATION_TONE);
        return Uri.parse(uri);
    }

}
