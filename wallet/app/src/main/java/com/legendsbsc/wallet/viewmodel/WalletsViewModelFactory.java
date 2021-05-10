package com.legendsbsc.wallet.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.legendsbsc.wallet.interact.CreateWalletInteract;
import com.legendsbsc.wallet.interact.DeleteWalletInteract;
import com.legendsbsc.wallet.interact.ExportWalletInteract;
import com.legendsbsc.wallet.interact.FetchWalletsInteract;
import com.legendsbsc.wallet.interact.FindDefaultWalletInteract;
import com.legendsbsc.wallet.interact.SetDefaultWalletInteract;
import com.legendsbsc.wallet.router.ImportWalletRouter;
import com.legendsbsc.wallet.router.TransactionsRouter;

import javax.inject.Inject;

public class WalletsViewModelFactory implements ViewModelProvider.Factory {

	private final CreateWalletInteract createWalletInteract;
	private final SetDefaultWalletInteract setDefaultWalletInteract;
	private final DeleteWalletInteract deleteWalletInteract;
	private final FetchWalletsInteract fetchWalletsInteract;
	private final FindDefaultWalletInteract findDefaultWalletInteract;
    private final ExportWalletInteract exportWalletInteract;

	private final ImportWalletRouter importWalletRouter;
    private final TransactionsRouter transactionsRouter;


    @Inject
	public WalletsViewModelFactory(
            CreateWalletInteract createWalletInteract,
            SetDefaultWalletInteract setDefaultWalletInteract,
            DeleteWalletInteract deleteWalletInteract,
            FetchWalletsInteract fetchWalletsInteract,
            FindDefaultWalletInteract findDefaultWalletInteract,
            ExportWalletInteract exportWalletInteract,
            ImportWalletRouter importWalletRouter,
            TransactionsRouter transactionsRouter) {
		this.createWalletInteract = createWalletInteract;
		this.setDefaultWalletInteract = setDefaultWalletInteract;
		this.deleteWalletInteract = deleteWalletInteract;
		this.fetchWalletsInteract = fetchWalletsInteract;
		this.findDefaultWalletInteract = findDefaultWalletInteract;
		this.exportWalletInteract = exportWalletInteract;
		this.importWalletRouter = importWalletRouter;
		this.transactionsRouter = transactionsRouter;
	}

	@NonNull
	@Override
	public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
		return (T) new WalletsViewModel(
                createWalletInteract,
                setDefaultWalletInteract,
                deleteWalletInteract,
                fetchWalletsInteract,
                findDefaultWalletInteract,
                exportWalletInteract,
                importWalletRouter,
                transactionsRouter);
	}
}
