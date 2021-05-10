package com.legendsbsc.wallet.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.legendsbsc.wallet.R;
import com.legendsbsc.wallet.entity.ErrorEnvelope;
import com.legendsbsc.wallet.entity.Token;
import com.legendsbsc.wallet.ui.widget.adapter.TokensAdapter;
import com.legendsbsc.wallet.viewmodel.TokensViewModel;
import com.legendsbsc.wallet.viewmodel.TokensViewModelFactory;
import com.legendsbsc.wallet.widget.SystemView;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.legendsbsc.wallet.C.Key.WALLET;

public class TokensActivity extends BaseActivity implements View.OnClickListener {
    @Inject
    TokensViewModelFactory transactionsViewModelFactory;
    private TokensViewModel viewModel;

    private SystemView systemView;
    private TokensAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tokens);

        toolbar();

        adapter = new TokensAdapter(this::onTokenClick);
        SwipeRefreshLayout refreshLayout = findViewById(R.id.refresh_layout);
        systemView = findViewById(R.id.system_view);

        RecyclerView list = findViewById(R.id.list);

        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

        systemView.attachRecyclerView(list);
        systemView.attachSwipeRefreshLayout(refreshLayout);

        viewModel = ViewModelProviders.of(this, transactionsViewModelFactory)
                .get(TokensViewModel.class);
        viewModel.progress().observe(this, systemView::showProgress);
        viewModel.error().observe(this, this::onError);
        viewModel.tokens().observe(this, this::onTokens);
        viewModel.wallet().setValue(getIntent().getParcelableExtra(WALLET));

        refreshLayout.setOnRefreshListener(viewModel::fetchTokens);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add: {
                viewModel.showAddToken(this);
            } break;
            case android.R.id.home: {
                viewModel.showTransactions(this, true);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        viewModel.showTransactions(this, true);
    }

    private void onTokenClick(View view, Token token) {
        Context context = view.getContext();
        viewModel.showSendToken(context, token.tokenInfo.address, token.tokenInfo.symbol, token.tokenInfo.decimals);
    }

    @Override
    protected void onResume() {
        super.onResume();

        viewModel.prepare();
    }

    private void onTokens(Token[] tokens) {
        adapter.setTokens(tokens);
        if (tokens == null || tokens.length == 0) {
            systemView.showEmpty(getString(R.string.no_tokens));
        }
    }

    private void onError(ErrorEnvelope errorEnvelope) {
        systemView.showError(getString(R.string.error_fail_load_transaction), this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.try_again: {
                viewModel.fetchTokens();
            } break;
        }
    }
}
