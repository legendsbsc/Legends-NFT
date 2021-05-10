package com.legendsbsc.wallet.di;

import android.content.Context;

import com.google.gson.Gson;
import com.legendsbsc.wallet.repository.EthereumNetworkRepository;
import com.legendsbsc.wallet.repository.EthereumNetworkRepositoryType;
import com.legendsbsc.wallet.repository.PreferenceRepositoryType;
import com.legendsbsc.wallet.repository.RealmTokenSource;
import com.legendsbsc.wallet.repository.SharedPreferenceRepository;
import com.legendsbsc.wallet.repository.TokenLocalSource;
import com.legendsbsc.wallet.repository.TokenRepository;
import com.legendsbsc.wallet.repository.TokenRepositoryType;
import com.legendsbsc.wallet.repository.TransactionInMemorySource;
import com.legendsbsc.wallet.repository.TransactionLocalSource;
import com.legendsbsc.wallet.repository.TransactionRepository;
import com.legendsbsc.wallet.repository.TransactionRepositoryType;
import com.legendsbsc.wallet.repository.WalletRepository;
import com.legendsbsc.wallet.repository.WalletRepositoryType;
import com.legendsbsc.wallet.service.AccountKeystoreService;
import com.legendsbsc.wallet.service.BlockExplorerClient;
import com.legendsbsc.wallet.service.BlockExplorerClientType;
import com.legendsbsc.wallet.service.EthplorerTokenService;
import com.legendsbsc.wallet.service.GethKeystoreAccountService;
import com.legendsbsc.wallet.service.TickerService;
import com.legendsbsc.wallet.service.TokenExplorerClientType;
import com.legendsbsc.wallet.service.TrustWalletTickerService;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module
public class RepositoriesModule {
	@Singleton
	@Provides
	PreferenceRepositoryType providePreferenceRepository(Context context) {
		return new SharedPreferenceRepository(context);
	}

	@Singleton
	@Provides
	AccountKeystoreService provideAccountKeyStoreService(Context context) {
        File file = new File(context.getFilesDir(), "keystore/keystore");
		return new GethKeystoreAccountService(file);
	}

	@Singleton
    @Provides
    TickerService provideTickerService(OkHttpClient httpClient, Gson gson) {
	    return new TrustWalletTickerService(httpClient, gson);
    }

	@Singleton
	@Provides
	EthereumNetworkRepositoryType provideEthereumNetworkRepository(
            PreferenceRepositoryType preferenceRepository,
            TickerService tickerService) {
		return new EthereumNetworkRepository(preferenceRepository, tickerService);
	}

	@Singleton
	@Provides
    WalletRepositoryType provideWalletRepository(
            OkHttpClient okHttpClient,
			PreferenceRepositoryType preferenceRepositoryType,
			AccountKeystoreService accountKeystoreService,
			EthereumNetworkRepositoryType networkRepository) {
		return new WalletRepository(
		        okHttpClient, preferenceRepositoryType, accountKeystoreService, networkRepository);
	}

	@Singleton
	@Provides
	TransactionRepositoryType provideTransactionRepository(
			EthereumNetworkRepositoryType networkRepository,
			AccountKeystoreService accountKeystoreService,
			BlockExplorerClientType blockExplorerClient) {
		TransactionLocalSource inMemoryCache = new TransactionInMemorySource();
		TransactionLocalSource inDiskCache = null;
		return new TransactionRepository(
				networkRepository,
				accountKeystoreService,
				inMemoryCache,
				inDiskCache,
				blockExplorerClient);
	}

	@Singleton
	@Provides
	BlockExplorerClientType provideBlockExplorerClient(
			OkHttpClient httpClient,
			Gson gson,
			EthereumNetworkRepositoryType ethereumNetworkRepository) {
		return new BlockExplorerClient(httpClient, gson, ethereumNetworkRepository);
	}

	@Singleton
    @Provides
    TokenRepositoryType provideTokenRepository(
            OkHttpClient okHttpClient,
            EthereumNetworkRepositoryType ethereumNetworkRepository,
            TokenExplorerClientType tokenExplorerClientType,
            TokenLocalSource tokenLocalSource) {
	    return new TokenRepository(
	            okHttpClient,
	            ethereumNetworkRepository,
	            tokenExplorerClientType,
                tokenLocalSource);
    }

	@Singleton
    @Provides
    TokenExplorerClientType provideTokenService(OkHttpClient okHttpClient, Gson gson) {
	    return new EthplorerTokenService(okHttpClient, gson);
    }

    @Singleton
    @Provides
    TokenLocalSource provideRealmTokenSource() {
	    return new RealmTokenSource();
    }
}
