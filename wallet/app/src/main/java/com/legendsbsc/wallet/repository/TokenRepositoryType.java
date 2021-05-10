package com.legendsbsc.wallet.repository;

import com.legendsbsc.wallet.entity.Token;
import com.legendsbsc.wallet.entity.Wallet;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface TokenRepositoryType {

    Observable<Token[]> fetch(String walletAddress);

    Completable addToken(Wallet wallet, String address, String symbol, int decimals);
}
