package com.legendsbsc.wallet.di;

import com.legendsbsc.wallet.interact.FetchTokensInteract;
import com.legendsbsc.wallet.interact.FindDefaultNetworkInteract;
import com.legendsbsc.wallet.repository.EthereumNetworkRepositoryType;
import com.legendsbsc.wallet.repository.TokenRepositoryType;
import com.legendsbsc.wallet.router.AddTokenRouter;
import com.legendsbsc.wallet.router.SendTokenRouter;
import com.legendsbsc.wallet.router.TransactionsRouter;
import com.legendsbsc.wallet.viewmodel.TokensViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
class TokensModule {

    @Provides
    TokensViewModelFactory provideTokensViewModelFactory(
            FindDefaultNetworkInteract findDefaultNetworkInteract,
            FetchTokensInteract fetchTokensInteract,
            AddTokenRouter addTokenRouter,
            SendTokenRouter sendTokenRouter,
            TransactionsRouter transactionsRouter) {
        return new TokensViewModelFactory(
                findDefaultNetworkInteract,
                fetchTokensInteract,
                addTokenRouter,
                sendTokenRouter,
                transactionsRouter);
    }

    @Provides
    FindDefaultNetworkInteract provideFindDefaultNetworkInteract(
            EthereumNetworkRepositoryType networkRepository) {
        return new FindDefaultNetworkInteract(networkRepository);
    }

    @Provides
    FetchTokensInteract provideFetchTokensInteract(TokenRepositoryType tokenRepository) {
        return new FetchTokensInteract(tokenRepository);
    }

    @Provides
    AddTokenRouter provideAddTokenRouter() {
        return new AddTokenRouter();
    }

    @Provides
    SendTokenRouter provideSendTokenRouter() {
        return new SendTokenRouter();
    }

    @Provides
    TransactionsRouter provideTransactionsRouter() {
        return new TransactionsRouter();
    }
}
