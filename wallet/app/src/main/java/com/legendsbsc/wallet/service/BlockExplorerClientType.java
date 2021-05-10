package com.legendsbsc.wallet.service;

import com.legendsbsc.wallet.entity.Transaction;

import io.reactivex.Observable;

public interface BlockExplorerClientType {
	Observable<Transaction[]> fetchTransactions(String forAddress);
}
