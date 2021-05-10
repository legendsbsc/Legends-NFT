package com.legendsbsc.wallet.ui.widget.holder;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.legendsbsc.wallet.R;
import com.legendsbsc.wallet.entity.Token;
import com.legendsbsc.wallet.ui.widget.OnTokenClickListener;

import java.math.BigDecimal;

public class TokenHolder extends BinderViewHolder<Token> implements View.OnClickListener {

    private final TextView symbol;
    private final TextView balance;

    private Token token;
    private OnTokenClickListener onTokenClickListener;

    public TokenHolder(int resId, ViewGroup parent) {
        super(resId, parent);

        symbol = findViewById(R.id.symbol);
        balance = findViewById(R.id.balance);
        itemView.setOnClickListener(this);
    }

    @Override
    public void bind(@Nullable Token data, @NonNull Bundle addition) {
        this.token = data;
        if (data == null) {
            fillEmpty();
            return;
        }
        try {
            symbol.setText(token.tokenInfo.symbol);

            BigDecimal decimalDivisor = new BigDecimal(Math.pow(10, token.tokenInfo.decimals));
            BigDecimal ethBalance = token.tokenInfo.decimals > 0
                    ? token.balance.divide(decimalDivisor) : token.balance;
            String value = ethBalance.compareTo(BigDecimal.ZERO) == 0
                    ? "0"
                    : ethBalance.toPlainString();
            this.balance.setText(value);
        } catch (Exception e) {
            fillEmpty();
        }
    }

    private void fillEmpty() {
        balance.setText(R.string.NA);
        balance.setText(R.string.minus);
    }

    @Override
    public void onClick(View v) {
        if (onTokenClickListener != null) {
            onTokenClickListener.onTokenClick(v, token);
        }
    }

    public void setOnTokenClickListener(OnTokenClickListener onTokenClickListener) {
        this.onTokenClickListener = onTokenClickListener;
    }
}
