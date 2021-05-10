package com.legendsbsc.wallet.repository;

import com.legendsbsc.wallet.entity.Transaction;
import com.legendsbsc.wallet.entity.Wallet;

import io.reactivex.Single;

public interface TransactionLocalSource {
	Single<Transaction[]> fetchTransaction(Wallet wallet);

	void putTransactions(Wallet wallet, Transaction[] transactions);

    void clear();
}
