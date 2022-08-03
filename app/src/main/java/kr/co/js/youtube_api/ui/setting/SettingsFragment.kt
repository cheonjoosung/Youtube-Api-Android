package kr.co.js.youtube_api.ui.setting

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import kr.co.js.youtube_api.R

class SettingsFragment : PreferenceFragmentCompat() {

    companion object {
        val TAG = SettingsFragment::class.java.simpleName
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        /**
        val version: Preference? = findPreference("pref_info_version")
        val appName = getString(R.string.app_name)
        version?.summary = "$appName ${BuildConfig.VERSION_NAME}"
         */
    }
}