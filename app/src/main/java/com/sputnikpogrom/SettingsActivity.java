package com.sputnikpogrom;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

import org.jsoup.helper.StringUtil;

/**
 * Created by veinhorn on 14.10.14.
 */
public class SettingsActivity extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences); // this method is deprecated, replace it later
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final EditTextPreference editTextPreference = (EditTextPreference)findPreference("num_of_articles");
        editTextPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                if(StringUtil.isNumeric((String)newValue))
                    return true;
                else
                    return false;
            }
        });

        updateSummary(PreferenceManager.getDefaultSharedPreferences(this), (EditTextPreference) findPreference("num_of_articles"));
        // when user will change default value of that setting we'll update the summary
    }

    public void updateSummary(SharedPreferences preferences, EditTextPreference editTextPreference) {
        String newSummary = getString(R.string.settings_articles_number_summary_begin) + " " +
                preferences.getString("num_of_articles", getString(R.string.default_num_of_articles)) + " " + getString(R.string.settings_articles_number_summary_end);
        editTextPreference.setSummary(newSummary);
    }
}