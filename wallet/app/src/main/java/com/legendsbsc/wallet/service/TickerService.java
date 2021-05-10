package com.legendsbsc.wallet.service;

import com.legendsbsc.wallet.entity.Ticker;

import io.reactivex.Observable;

public interface TickerService {

    Observable<Ticker> fetchTickerPrice(String ticker);
}
