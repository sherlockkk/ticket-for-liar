package com.sinjon.ticket.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.sinjon.ticket.R;
import com.sinjon.ticket.adapter.GoodAdapter;
import com.sinjon.ticket.clawer.core.ClawerCoreService;
import com.sinjon.ticket.pojo.Good;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, GoodAdapter.OnItemActionListener {

    private ClawerCoreService clawerCoreService;

    private RecyclerView rcy_main;
    private SwipeRefreshLayout srl_main;
    private GoodAdapter goodAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fetchData();
        initView();
    }

    private void initView() {
        goodAdapter = new GoodAdapter(this);
        goodAdapter.setOnItemActionListener(this);
        srl_main = (SwipeRefreshLayout) findViewById(R.id.srl_main);
        srl_main.setOnRefreshListener(this);
        rcy_main = (RecyclerView) findViewById(R.id.rcy_main);
        rcy_main.setLayoutManager(new GridLayoutManager(this, 2));
        rcy_main.setAdapter(goodAdapter);
    }


    private void fetchData() {
        DataAsyncTask task = new DataAsyncTask();
        task.execute();
    }

    @Override
    public void onRefresh() {
        fetchData();
    }

    @Override
    public void onItemClickListener(Good good, int pos) {
        Intent intent = new Intent(this, TicketActivity.class);
        intent.putExtra("good", good);
        startActivity(intent);
    }

    class DataAsyncTask extends AsyncTask<Void, Void, List<Good>> {
        @Override
        protected List<Good> doInBackground(Void... voids) {
            clawerCoreService = new ClawerCoreService();
            List<Good> goodList = clawerCoreService.fetchHomeGoodInfo();
            return goodList;
        }

        @Override
        protected void onPostExecute(List<Good> goods) {
            srl_main.setRefreshing(false);
            goodAdapter.addList(goods);
        }
    }

}
