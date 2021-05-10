package com.legendsbsc.wallet.ui.widget;

import android.view.View;

import com.legendsbsc.wallet.entity.Transaction;

public interface OnTransactionClickListener {
    void onTransactionClick(View view, Transaction transaction);
}
