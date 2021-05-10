package com.legendsbsc.wallet.router;

import android.content.Context;
import android.content.Intent;

import com.legendsbsc.wallet.entity.Wallet;
import com.legendsbsc.wallet.ui.TokensActivity;

import static com.legendsbsc.wallet.C.Key.WALLET;

public class MyTokensRouter {

    public void open(Context context, Wallet wallet) {
        Intent intent = new Intent(context, TokensActivity.class);
        intent.putExtra(WALLET, wallet);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);
    }
}
