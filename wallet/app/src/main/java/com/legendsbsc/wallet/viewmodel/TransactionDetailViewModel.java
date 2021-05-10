package com.legendsbsc.wallet.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.legendsbsc.wallet.R;
import com.legendsbsc.wallet.entity.NetworkInfo;
import com.legendsbsc.wallet.entity.Transaction;
import com.legendsbsc.wallet.entity.Wallet;
import com.legendsbsc.wallet.interact.FindDefaultNetworkInteract;
import com.legendsbsc.wallet.interact.FindDefaultWalletInteract;
import com.legendsbsc.wallet.router.ExternalBrowserRouter;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class TransactionDetailViewModel extends BaseViewModel {

    private final FindDefaultNetworkInteract findDefaultNetworkInteract;
    private final ExternalBrowserRouter externalBrowserRouter;

    private final MutableLiveData<NetworkInfo> defaultNetwork = new MutableLiveData<>();
    private final MutableLiveData<Wallet> defaultWallet = new MutableLiveData<>();

    TransactionDetailViewModel(
            FindDefaultNetworkInteract findDefaultNetworkInteract,
            FindDefaultWalletInteract findDefaultWalletInteract,
            ExternalBrowserRouter externalBrowserRouter) {
        this.findDefaultNetworkInteract = findDefaultNetworkInteract;
        this.externalBrowserRouter = externalBrowserRouter;

        findDefaultNetworkInteract
                .find()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(defaultNetwork::postValue);
        disposable = findDefaultWalletInteract
                .find()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(defaultWallet::postValue);
    }


    public LiveData<NetworkInfo> defaultNetwork() {
        return defaultNetwork;
    }

    public void showMoreDetails(Context context, Transaction transaction) {
        NetworkInfo networkInfo = defaultNetwork.getValue();
        if (networkInfo != null && !TextUtils.isEmpty(networkInfo.etherscanUrl)) {
            Uri uri = Uri.parse(networkInfo.etherscanUrl)
                    .buildUpon()
                    .appendEncodedPath("tx")
                    .appendEncodedPath(transaction.hash)
                    .build();
            externalBrowserRouter.open(context, uri);
        }
    }

    public void shareTransactionDetail(Context context, Transaction transaction) {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.subject_transaction_detail));
        sharingIntent.putExtra(Intent.EXTRA_TEXT, buildEtherscanUri(transaction).toString());
        context.startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    private Uri buildEtherscanUri(Transaction transaction) {
        NetworkInfo networkInfo = defaultNetwork.getValue();
        if (networkInfo != null && !TextUtils.isEmpty(networkInfo.etherscanUrl)) {
            return Uri.parse(networkInfo.etherscanUrl)
                    .buildUpon()
                    .appendEncodedPath("tx")
                    .appendEncodedPath(transaction.hash)
                    .build();
        }
        return null;
    }

    public LiveData<Wallet> defaultWallet() {
        return defaultWallet;
    }
}
