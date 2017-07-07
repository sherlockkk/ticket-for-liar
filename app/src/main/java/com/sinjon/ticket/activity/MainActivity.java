package com.sinjon.ticket.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.sinjon.ticket.R;
import com.sinjon.ticket.adapter.GoodAdapter;
import com.sinjon.ticket.clawer.core.ClawerCoreService;
import com.sinjon.ticket.pojo.Good;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, GoodAdapter.OnItemActionListener, NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener {

    private ClawerCoreService clawerCoreService;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private RecyclerView rcy_main;
    private SwipeRefreshLayout srl_main;
    private GoodAdapter goodAdapter;
    int catgoryFlag = 0;

    private String keyWord = "";

    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drawer);
        initView();
        fetchData(0);
    }

    /**
     * 初始化View
     */
    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        goodAdapter = new GoodAdapter(this);
        goodAdapter.setOnItemActionListener(this);
        srl_main = (SwipeRefreshLayout) findViewById(R.id.srl_main);
        srl_main.setOnRefreshListener(this);
        rcy_main = (RecyclerView) findViewById(R.id.rcy_main);
        rcy_main.setLayoutManager(new GridLayoutManager(this, 2));
        rcy_main.setAdapter(goodAdapter);
    }


    private void fetchData(int flag) {
        DataAsyncTask task = new DataAsyncTask();
        task.execute(flag);
    }

    /**
     * SwipeRefreshLayout点击事件的监听
     */
    @Override
    public void onRefresh() {
        fetchData(catgoryFlag);
    }


    /**
     * RecyclerView点击事件的监听
     *
     * @param good
     * @param pos
     */
    @Override
    public void onItemClickListener(Good good, int pos) {
        if (good.getTicket().startsWith("http://") || good.getTicket().startsWith("https://")) {
            Intent intent = new Intent(this, TicketActivity.class);
            intent.putExtra("good", good);
            startActivity(intent);
        } else {
            // Otherwise allow the OS to handle things like taobao, tel, mailto, etc.
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(good.getTicket()));
            startActivity(intent);
        }

    }

    /**
     * 侧边栏NavigationView点击事件的监听
     *
     * @param item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_0:
                fetchData(0);
                catgoryFlag = 0;
                break;
            case R.id.nav_1:
                fetchData(1);
                catgoryFlag = 1;
                break;
            case R.id.nav_2:
                fetchData(2);
                catgoryFlag = 2;
                break;
            case R.id.nav_3:
                fetchData(3);
                catgoryFlag = 3;
                break;
            case R.id.nav_4:
                fetchData(4);
                catgoryFlag = 4;
                break;
            case R.id.nav_5:
                fetchData(5);
                catgoryFlag = 5;
                break;
            case R.id.nav_6:
                fetchData(6);
                catgoryFlag = 6;
                break;
            case R.id.nav_7:
                fetchData(7);
                catgoryFlag = 7;
                break;
            case R.id.nav_8:
                fetchData(8);
                catgoryFlag = 8;
                break;
            case R.id.nav_9:
                fetchData(9);
                catgoryFlag = 9;
                break;
            case R.id.nav_10:
                fetchData(10);
                catgoryFlag = 10;
                break;
            case R.id.nav_11:
                fetchData(11);
                catgoryFlag = 11;
                break;
            case R.id.nav_12:
                fetchData(12);
                catgoryFlag = 12;
                break;
            case R.id.nav_13:
                fetchData(13);
                catgoryFlag = 13;
                break;
            case R.id.nav_14:
                fetchData(14);
                catgoryFlag = 14;
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_view, menu);
        MenuItem searchItem = menu.findItem(R.id.menu_search);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.onActionViewExpanded();
        searchView.clearFocus();//searchview初始化的时候清除焦点以防止一开始就弹出软键盘
        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * SearchView输入框中文字提交时的监听
     * @param query
     * @return
     */
    @Override
    public boolean onQueryTextSubmit(String query) {
        keyWord = query;
        catgoryFlag = 15;
        fetchData(15);//搜索
        return false;
    }

    /**
     * SearchView输入框中文字改变时的监听
     * @param newText
     * @return
     */
    @Override
    public boolean onQueryTextChange(String newText) {
        keyWord = newText;
        return false;
    }

    /**
     * 从网络异步请求数据,防止ANR
     */
    class DataAsyncTask extends AsyncTask<Integer, Void, List<Good>> {
        @Override
        protected void onPreExecute() {
            srl_main.setRefreshing(true);
        }

        @Override
        protected List<Good> doInBackground(Integer... integers) {
            List<Good> goodList = null;
            clawerCoreService = new ClawerCoreService();
            switch (integers[0]) {
                case 0:
                    goodList = clawerCoreService.fetchHomeGoodInfo();
                    break;
                case 15:
                    goodList = clawerCoreService.fetchDataFromSearch(keyWord);
                    break;
                default:
                    goodList = clawerCoreService.fetchCatgoryGoodInfo(integers[0]);
                    break;
            }
            return goodList;
        }

        @Override
        protected void onPostExecute(List<Good> goods) {
            srl_main.setRefreshing(false);
            goodAdapter.addList(goods);
        }
    }

}
