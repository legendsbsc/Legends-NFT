package com.legendsbsc.wallet.interact;

import com.legendsbsc.wallet.entity.Wallet;
import com.legendsbsc.wallet.repository.PasswordStore;
import com.legendsbsc.wallet.repository.WalletRepositoryType;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Delete and fetch wallets
 */
public class DeleteWalletInteract {
	private final WalletRepositoryType walletRepository;
	private final PasswordStore passwordStore;

	public DeleteWalletInteract(WalletRepositoryType walletRepository, PasswordStore passwordStore) {
		this.walletRepository = walletRepository;
		this.passwordStore = passwordStore;
	}

	public Single<Wallet[]> delete(Wallet wallet) {
		return passwordStore.getPassword(wallet)
				.flatMapCompletable(password -> walletRepository.deleteWallet(wallet.address, password))
				.andThen(walletRepository.fetchWallets())
				.observeOn(AndroidSchedulers.mainThread());
	}
}
