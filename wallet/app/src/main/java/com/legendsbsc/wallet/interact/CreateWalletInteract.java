package com.legendsbsc.wallet.interact;

import com.legendsbsc.wallet.entity.Wallet;
import com.legendsbsc.wallet.interact.rx.operator.Operators;
import com.legendsbsc.wallet.repository.PasswordStore;
import com.legendsbsc.wallet.repository.WalletRepositoryType;

import io.reactivex.Single;

import static com.legendsbsc.wallet.interact.rx.operator.Operators.completableErrorProxy;

public class CreateWalletInteract {

	private final WalletRepositoryType walletRepository;
	private final PasswordStore passwordStore;

	public CreateWalletInteract(WalletRepositoryType walletRepository, PasswordStore passwordStore) {
		this.walletRepository = walletRepository;
		this.passwordStore = passwordStore;
	}

	public Single<Wallet> create() {
	    return passwordStore.generatePassword()
		.flatMap(masterPassword -> walletRepository
			.createWallet(masterPassword)
			.compose(Operators.savePassword(passwordStore, walletRepository, masterPassword))
                       	.flatMap(wallet -> passwordVerification(wallet, masterPassword)));
	}
	
	private Single<Wallet> passwordVerification(Wallet wallet, String masterPassword) {
            return passwordStore
                .getPassword(wallet)
                .flatMap(password -> walletRepository
                        .exportWallet(wallet, password, password)
                        .flatMap(keyStore -> walletRepository.findWallet(wallet.address)))
                .onErrorResumeNext(throwable -> walletRepository
                        .deleteWallet(wallet.address, masterPassword)
                        .lift(completableErrorProxy(throwable))
                        .toSingle(() -> wallet));
	}
}
