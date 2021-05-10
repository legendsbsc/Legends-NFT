// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package com.legendsbsc.wallet.ui;

import android.app.Fragment;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import javax.inject.Provider;

public final class SettingsActivity_MembersInjector implements MembersInjector<SettingsActivity> {
  private final Provider<DispatchingAndroidInjector<Fragment>> fragmentInjectorProvider;

  public SettingsActivity_MembersInjector(
      Provider<DispatchingAndroidInjector<Fragment>> fragmentInjectorProvider) {
    assert fragmentInjectorProvider != null;
    this.fragmentInjectorProvider = fragmentInjectorProvider;
  }

  public static MembersInjector<SettingsActivity> create(
      Provider<DispatchingAndroidInjector<Fragment>> fragmentInjectorProvider) {
    return new SettingsActivity_MembersInjector(fragmentInjectorProvider);
  }

  @Override
  public void injectMembers(SettingsActivity instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.fragmentInjector = fragmentInjectorProvider.get();
  }

  public static void injectFragmentInjector(
      SettingsActivity instance,
      Provider<DispatchingAndroidInjector<Fragment>> fragmentInjectorProvider) {
    instance.fragmentInjector = fragmentInjectorProvider.get();
  }
}