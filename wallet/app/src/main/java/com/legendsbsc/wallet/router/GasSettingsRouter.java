package com.legendsbsc.wallet.router;


import android.app.Activity;
import android.content.Intent;

import com.legendsbsc.wallet.C;
import com.legendsbsc.wallet.entity.GasSettings;
import com.legendsbsc.wallet.ui.GasSettingsActivity;
import com.legendsbsc.wallet.viewmodel.GasSettingsViewModel;

public class GasSettingsRouter {
    public void open(Activity context, GasSettings gasSettings) {
        Intent intent = new Intent(context, GasSettingsActivity.class);
        intent.putExtra(C.EXTRA_GAS_PRICE, gasSettings.gasPrice.toString());
        intent.putExtra(C.EXTRA_GAS_LIMIT, gasSettings.gasLimit.toString());
        context.startActivityForResult(intent, GasSettingsViewModel.SET_GAS_SETTINGS);
    }
}
