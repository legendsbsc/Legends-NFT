package com.legendsbsc.wallet.router;

import android.content.Context;
import android.content.Intent;

import com.legendsbsc.wallet.entity.Transaction;
import com.legendsbsc.wallet.ui.TransactionDetailActivity;

import static com.legendsbsc.wallet.C.Key.TRANSACTION;

public class TransactionDetailRouter {

    public void open(Context context, Transaction transaction) {
        Intent intent = new Intent(context, TransactionDetailActivity.class);
        intent.putExtra(TRANSACTION, transaction);
        context.startActivity(intent);
    }
}
