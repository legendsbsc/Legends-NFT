package com.legendsbsc.wallet.di;

import com.legendsbsc.wallet.ui.AddTokenActivity;
import com.legendsbsc.wallet.ui.ConfirmationActivity;
import com.legendsbsc.wallet.ui.GasSettingsActivity;
import com.legendsbsc.wallet.ui.ImportWalletActivity;
import com.legendsbsc.wallet.ui.MyAddressActivity;
import com.legendsbsc.wallet.ui.SendActivity;
import com.legendsbsc.wallet.ui.SettingsActivity;
import com.legendsbsc.wallet.ui.SplashActivity;
import com.legendsbsc.wallet.ui.TokensActivity;
import com.legendsbsc.wallet.ui.TransactionDetailActivity;
import com.legendsbsc.wallet.ui.TransactionsActivity;
import com.legendsbsc.wallet.ui.WalletsActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BuildersModule {
	@ActivityScope
	@ContributesAndroidInjector(modules = SplashModule.class)
	abstract SplashActivity bindSplashModule();

	@ActivityScope
	@ContributesAndroidInjector(modules = AccountsManageModule.class)
	abstract WalletsActivity bindManageWalletsModule();

	@ActivityScope
	@ContributesAndroidInjector(modules = ImportModule.class)
	abstract ImportWalletActivity bindImportWalletModule();

	@ActivityScope
	@ContributesAndroidInjector(modules = TransactionsModule.class)
	abstract TransactionsActivity bindTransactionsModule();

    @ActivityScope
    @ContributesAndroidInjector(modules = TransactionDetailModule.class)
    abstract TransactionDetailActivity bindTransactionDetailModule();

	@ActivityScope
	@ContributesAndroidInjector(modules = SettingsModule.class)
	abstract SettingsActivity bindSettingsModule();

	@ActivityScope
	@ContributesAndroidInjector(modules = SendModule.class)
	abstract SendActivity bindSendModule();

	@ActivityScope
	@ContributesAndroidInjector(modules = ConfirmationModule.class)
	abstract ConfirmationActivity bindConfirmationModule();
    @ContributesAndroidInjector
	abstract MyAddressActivity bindMyAddressModule();

	@ActivityScope
    @ContributesAndroidInjector(modules = TokensModule.class)
	abstract TokensActivity bindTokensModule();

	@ActivityScope
	@ContributesAndroidInjector(modules = GasSettingsModule.class)
	abstract GasSettingsActivity bindGasSettingsModule();

	@ActivityScope
    @ContributesAndroidInjector(modules = AddTokenModule.class)
	abstract AddTokenActivity bindAddTokenActivity();
}
