// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package com.legendsbsc.wallet.ui;

import com.legendsbsc.wallet.viewmodel.SendViewModelFactory;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class SendActivity_MembersInjector implements MembersInjector<SendActivity> {
  private final Provider<SendViewModelFactory> sendViewModelFactoryProvider;

  public SendActivity_MembersInjector(Provider<SendViewModelFactory> sendViewModelFactoryProvider) {
    assert sendViewModelFactoryProvider != null;
    this.sendViewModelFactoryProvider = sendViewModelFactoryProvider;
  }

  public static MembersInjector<SendActivity> create(
      Provider<SendViewModelFactory> sendViewModelFactoryProvider) {
    return new SendActivity_MembersInjector(sendViewModelFactoryProvider);
  }

  @Override
  public void injectMembers(SendActivity instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.sendViewModelFactory = sendViewModelFactoryProvider.get();
  }

  public static void injectSendViewModelFactory(
      SendActivity instance, Provider<SendViewModelFactory> sendViewModelFactoryProvider) {
    instance.sendViewModelFactory = sendViewModelFactoryProvider.get();
  }
}
