package com.legendsbsc.wallet.di;


import com.legendsbsc.wallet.interact.CreateTransactionInteract;
import com.legendsbsc.wallet.interact.FetchGasSettingsInteract;
import com.legendsbsc.wallet.interact.FindDefaultWalletInteract;
import com.legendsbsc.wallet.repository.PasswordStore;
import com.legendsbsc.wallet.repository.PreferenceRepositoryType;
import com.legendsbsc.wallet.repository.TransactionRepositoryType;
import com.legendsbsc.wallet.repository.WalletRepositoryType;
import com.legendsbsc.wallet.router.GasSettingsRouter;
import com.legendsbsc.wallet.viewmodel.ConfirmationViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class ConfirmationModule {
    @Provides
    public ConfirmationViewModelFactory provideConfirmationViewModelFactory(
            FindDefaultWalletInteract findDefaultWalletInteract,
            FetchGasSettingsInteract fetchGasSettingsInteract,
            CreateTransactionInteract createTransactionInteract,
            GasSettingsRouter gasSettingsRouter
    ) {
        return new ConfirmationViewModelFactory(findDefaultWalletInteract, fetchGasSettingsInteract, createTransactionInteract, gasSettingsRouter);
    }

    @Provides
    FindDefaultWalletInteract provideFindDefaultWalletInteract(WalletRepositoryType walletRepository) {
        return new FindDefaultWalletInteract(walletRepository);
    }

    @Provides
    FetchGasSettingsInteract provideFetchGasSettingsInteract(PreferenceRepositoryType preferenceRepositoryType) {
        return new FetchGasSettingsInteract(preferenceRepositoryType);
    }

    @Provides
    CreateTransactionInteract provideCreateTransactionInteract(TransactionRepositoryType transactionRepository, PasswordStore passwordStore) {
        return new CreateTransactionInteract(transactionRepository, passwordStore);
    }

    @Provides
    GasSettingsRouter provideGasSettingsRouter() {
        return new GasSettingsRouter();
    }
}
