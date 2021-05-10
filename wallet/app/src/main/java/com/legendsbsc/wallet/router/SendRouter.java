package com.legendsbsc.wallet.router;

import android.content.Context;
import android.content.Intent;

import com.legendsbsc.wallet.ui.SendActivity;

public class SendRouter {

    public void open(Context context) {
        Intent intent = new Intent(context, SendActivity.class);
        context.startActivity(intent);
    }
}
