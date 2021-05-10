package com.legendsbsc.wallet.di;

import com.legendsbsc.wallet.interact.ImportWalletInteract;
import com.legendsbsc.wallet.repository.PasswordStore;
import com.legendsbsc.wallet.repository.WalletRepositoryType;
import com.legendsbsc.wallet.viewmodel.ImportWalletViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
class ImportModule {
    @Provides
    ImportWalletViewModelFactory provideImportWalletViewModelFactory(
            ImportWalletInteract importWalletInteract) {
        return new ImportWalletViewModelFactory(importWalletInteract);
    }

    @Provides
    ImportWalletInteract provideImportWalletInteract(
            WalletRepositoryType walletRepository, PasswordStore passwordStore) {
        return new ImportWalletInteract(walletRepository, passwordStore);
    }
}
