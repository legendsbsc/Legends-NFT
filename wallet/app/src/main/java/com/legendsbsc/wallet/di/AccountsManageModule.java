package com.legendsbsc.wallet.di;

import com.legendsbsc.wallet.interact.CreateWalletInteract;
import com.legendsbsc.wallet.interact.DeleteWalletInteract;
import com.legendsbsc.wallet.interact.ExportWalletInteract;
import com.legendsbsc.wallet.interact.FetchWalletsInteract;
import com.legendsbsc.wallet.interact.FindDefaultWalletInteract;
import com.legendsbsc.wallet.interact.SetDefaultWalletInteract;
import com.legendsbsc.wallet.repository.PasswordStore;
import com.legendsbsc.wallet.repository.WalletRepositoryType;
import com.legendsbsc.wallet.router.ImportWalletRouter;
import com.legendsbsc.wallet.router.TransactionsRouter;
import com.legendsbsc.wallet.viewmodel.WalletsViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
class AccountsManageModule {

	@Provides
    WalletsViewModelFactory provideAccountsManageViewModelFactory(
			CreateWalletInteract createWalletInteract,
			SetDefaultWalletInteract setDefaultWalletInteract,
			DeleteWalletInteract deleteWalletInteract,
			FetchWalletsInteract fetchWalletsInteract,
			FindDefaultWalletInteract findDefaultWalletInteract,
			ExportWalletInteract exportWalletInteract,
			ImportWalletRouter importWalletRouter,
            TransactionsRouter transactionsRouter) {
		return new WalletsViewModelFactory(createWalletInteract,
                setDefaultWalletInteract,
                deleteWalletInteract,
                fetchWalletsInteract,
                findDefaultWalletInteract,
                exportWalletInteract,
                importWalletRouter,
                transactionsRouter);
	}

	@Provides
    CreateWalletInteract provideCreateAccountInteract(
            WalletRepositoryType accountRepository, PasswordStore passwordStore) {
		return new CreateWalletInteract(accountRepository, passwordStore);
	}

	@Provides
    SetDefaultWalletInteract provideSetDefaultAccountInteract(WalletRepositoryType accountRepository) {
		return new SetDefaultWalletInteract(accountRepository);
	}

	@Provides
    DeleteWalletInteract provideDeleteAccountInteract(
            WalletRepositoryType accountRepository, PasswordStore store) {
		return new DeleteWalletInteract(accountRepository, store);
	}

	@Provides
    FetchWalletsInteract provideFetchAccountsInteract(WalletRepositoryType accountRepository) {
		return new FetchWalletsInteract(accountRepository);
	}

	@Provides
    FindDefaultWalletInteract provideFindDefaultAccountInteract(WalletRepositoryType accountRepository) {
		return new FindDefaultWalletInteract(accountRepository);
	}

	@Provides
    ExportWalletInteract provideExportWalletInteract(
            WalletRepositoryType walletRepository, PasswordStore passwordStore) {
	    return new ExportWalletInteract(walletRepository, passwordStore);
    }

	@Provides
    ImportWalletRouter provideImportAccountRouter() {
		return new ImportWalletRouter();
	}

	@Provides
    TransactionsRouter provideTransactionsRouter() {
	    return new TransactionsRouter();
    }
}
