package com.legendsbsc.wallet.interact;

import com.legendsbsc.wallet.entity.Wallet;
import com.legendsbsc.wallet.repository.EthereumNetworkRepositoryType;
import com.legendsbsc.wallet.repository.WalletRepositoryType;
import com.legendsbsc.wallet.util.BalanceUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.legendsbsc.wallet.C.USD_SYMBOL;
import static com.legendsbsc.wallet.util.BalanceUtils.weiToEth;

public class GetDefaultWalletBalance {

    private final WalletRepositoryType walletRepository;
    private final EthereumNetworkRepositoryType ethereumNetworkRepository;

    public GetDefaultWalletBalance(
            WalletRepositoryType walletRepository,
            EthereumNetworkRepositoryType ethereumNetworkRepository) {
        this.walletRepository = walletRepository;
        this.ethereumNetworkRepository = ethereumNetworkRepository;
    }

    public Single<Map<String, String>> get(Wallet wallet) {
        return walletRepository.balanceInWei(wallet)
                .flatMap(ethBallance -> {
                    Map<String, String> balances = new HashMap<>();
                    balances.put(ethereumNetworkRepository.getDefaultNetwork().symbol, weiToEth(ethBallance, 5));
                    return Single.just(balances);
                })
                .flatMap(balances -> ethereumNetworkRepository
                        .getTicker()
                        .observeOn(Schedulers.io())
                        .flatMap(ticker -> {
                            String ethBallance = balances.get(ethereumNetworkRepository.getDefaultNetwork().symbol);
                            balances.put(USD_SYMBOL, BalanceUtils.ethToUsd(ticker.price, ethBallance));
                            return Single.just(balances);
                        })
                        .onErrorResumeNext(throwable -> Single.just(balances)))
                .observeOn(AndroidSchedulers.mainThread());
    }


}