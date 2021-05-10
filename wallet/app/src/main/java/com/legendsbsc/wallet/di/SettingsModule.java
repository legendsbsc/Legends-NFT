package com.legendsbsc.wallet.di;

import com.legendsbsc.wallet.ui.SettingsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface SettingsModule {
    @FragmentScope
    @ContributesAndroidInjector(modules = {SettingsFragmentModule.class})
    SettingsFragment settingsFragment();
}
