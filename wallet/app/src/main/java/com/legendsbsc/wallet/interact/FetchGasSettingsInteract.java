package com.legendsbsc.wallet.interact;


import com.legendsbsc.wallet.entity.GasSettings;
import com.legendsbsc.wallet.repository.PreferenceRepositoryType;

public class FetchGasSettingsInteract {
    private final PreferenceRepositoryType repository;

    public FetchGasSettingsInteract(PreferenceRepositoryType repository) {
        this.repository = repository;
    }

    public GasSettings fetch(boolean forTokenTransfer) {
        return repository.getGasSettings(forTokenTransfer);
    }

}
