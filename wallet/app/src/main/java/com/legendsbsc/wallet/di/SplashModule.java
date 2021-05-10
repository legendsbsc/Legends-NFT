package com.legendsbsc.wallet.di;

import com.legendsbsc.wallet.interact.FetchWalletsInteract;
import com.legendsbsc.wallet.repository.WalletRepositoryType;
import com.legendsbsc.wallet.viewmodel.SplashViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class SplashModule {

    @Provides
    SplashViewModelFactory provideSplashViewModelFactory(FetchWalletsInteract fetchWalletsInteract) {
        return new SplashViewModelFactory(fetchWalletsInteract);
    }

    @Provides
    FetchWalletsInteract provideFetchWalletInteract(WalletRepositoryType walletRepository) {
        return new FetchWalletsInteract(walletRepository);
    }
}
