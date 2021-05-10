package com.legendsbsc.wallet.repository;

import com.legendsbsc.wallet.entity.NetworkInfo;
import com.legendsbsc.wallet.entity.TokenInfo;
import com.legendsbsc.wallet.entity.Wallet;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface TokenLocalSource {
    Completable put(NetworkInfo networkInfo, Wallet wallet, TokenInfo tokenInfo);
    Single<TokenInfo[]> fetch(NetworkInfo networkInfo, Wallet wallet);
}
