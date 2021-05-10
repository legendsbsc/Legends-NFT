package com.legendsbsc.wallet.repository;

import com.legendsbsc.wallet.entity.NetworkInfo;

public interface OnNetworkChangeListener {
	void onNetworkChanged(NetworkInfo networkInfo);
}
