package com.legendsbsc.wallet.viewmodel;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.legendsbsc.wallet.entity.GasSettings;
import com.legendsbsc.wallet.entity.Wallet;
import com.legendsbsc.wallet.interact.CreateTransactionInteract;
import com.legendsbsc.wallet.interact.FetchGasSettingsInteract;
import com.legendsbsc.wallet.interact.FindDefaultWalletInteract;
import com.legendsbsc.wallet.repository.TokenRepository;
import com.legendsbsc.wallet.router.GasSettingsRouter;

import java.math.BigInteger;

public class ConfirmationViewModel extends BaseViewModel {
    private final MutableLiveData<String> newTransaction = new MutableLiveData<>();
    private final MutableLiveData<Wallet> defaultWallet = new MutableLiveData<>();
    private final MutableLiveData<GasSettings> gasSettings = new MutableLiveData<>();

    private final FindDefaultWalletInteract findDefaultWalletInteract;
    private final FetchGasSettingsInteract fetchGasSettingsInteract;
    private final CreateTransactionInteract createTransactionInteract;

    private final GasSettingsRouter gasSettingsRouter;

    boolean confirmationForTokenTransfer = false;

    public ConfirmationViewModel(FindDefaultWalletInteract findDefaultWalletInteract,
                                 FetchGasSettingsInteract fetchGasSettingsInteract,
                                 CreateTransactionInteract createTransactionInteract,
                                 GasSettingsRouter gasSettingsRouter) {
        this.findDefaultWalletInteract = findDefaultWalletInteract;
        this.fetchGasSettingsInteract = fetchGasSettingsInteract;
        this.createTransactionInteract = createTransactionInteract;
        this.gasSettingsRouter = gasSettingsRouter;
    }

    public void createTransaction(String from, String to, BigInteger amount, BigInteger gasPrice, BigInteger gasLimit) {
        progress.postValue(true);
        disposable = createTransactionInteract
                .create(new Wallet(from), to, amount, gasPrice, gasLimit, null)
                .subscribe(this::onCreateTransaction, this::onError);
    }

    public void createTokenTransfer(String from, String to, String contractAddress, BigInteger amount, BigInteger gasPrice, BigInteger gasLimit) {
        progress.postValue(true);
        final byte[] data = TokenRepository.createTokenTransferData(to, amount);
        disposable = createTransactionInteract
                .create(new Wallet(from), contractAddress, BigInteger.valueOf(0), gasPrice, gasLimit, data)
                .subscribe(this::onCreateTransaction, this::onError);
    }

    public LiveData<Wallet> defaultWallet() {
        return defaultWallet;
    }

    public MutableLiveData<GasSettings> gasSettings() {
        return gasSettings;
    }

    public LiveData<String> sendTransaction() { return newTransaction; }

    public void prepare(boolean confirmationForTokenTransfer) {
        this.confirmationForTokenTransfer = confirmationForTokenTransfer;
        disposable = findDefaultWalletInteract
                .find()
                .subscribe(this::onDefaultWallet, this::onError);
    }

    private void onCreateTransaction(String transaction) {
        progress.postValue(false);
        newTransaction.postValue(transaction);
    }

    private void onDefaultWallet(Wallet wallet) {
        defaultWallet.setValue(wallet);
        if (gasSettings.getValue() == null) {
            onGasSettings(fetchGasSettingsInteract.fetch(confirmationForTokenTransfer));
        }
    }

    private void onGasSettings(GasSettings gasSettings) {
        this.gasSettings.setValue(gasSettings);
    }

    public void openGasSettings(Activity context) {
        gasSettingsRouter.open(context, gasSettings.getValue());
    }
}
