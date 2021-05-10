package com.legendsbsc.wallet.router;

import android.content.Context;
import android.content.Intent;

import com.legendsbsc.wallet.entity.Wallet;
import com.legendsbsc.wallet.ui.MyAddressActivity;

import static com.legendsbsc.wallet.C.Key.WALLET;

public class MyAddressRouter {

    public void open(Context context, Wallet wallet) {
        Intent intent = new Intent(context, MyAddressActivity.class);
        intent.putExtra(WALLET, wallet);
        context.startActivity(intent);
    }
}
