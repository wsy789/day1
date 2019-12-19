package com.example.day1;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar mMainTool;
    private RecyclerView mMainRecy;
    private ArrayList<WanBean.DataBean.DatasBean> list;
    private WanAdapter adapter;
    private Wan2Adapter wan2Adapter;
    private Button mBt1MainLl;
    private Button mBt2MainLl;
    private Button mBt3MainLl;
    private int posi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initDate();
    }

    private void initDate() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<WanBean> onDate = apiService.onDate();
        onDate.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WanBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WanBean wanBean) {
                        List<WanBean.DataBean.DatasBean> datas = wanBean.getData().getDatas();
                        list.addAll(datas);
                        wan2Adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void initView() {
        mMainTool = (Toolbar) findViewById(R.id.tool_main);
        mMainRecy = (RecyclerView) findViewById(R.id.recy_main);
        mBt1MainLl = (Button) findViewById(R.id.ll_bt1_main);
        mBt1MainLl.setOnClickListener(this);
        mBt2MainLl = (Button) findViewById(R.id.ll_bt2_main);
        mBt2MainLl.setOnClickListener(this);
        mBt3MainLl = (Button) findViewById(R.id.ll_bt3_main);
        mBt3MainLl.setOnClickListener(this);

        onTool();
        onRecy();

    }

    private void onRecy() {
        list = new ArrayList<>();
        mMainRecy.setLayoutManager(new LinearLayoutManager(this));
        mMainRecy.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        wan2Adapter = new Wan2Adapter(this, list);
        mMainRecy.setAdapter(wan2Adapter);
    }

    private void onTool() {
        mMainTool.setTitle("ToolBar");
        setSupportActionBar(mMainTool);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_check:
                break;
            case R.id.menu_item_content:


                break;
            case R.id.menu_item_delete:

                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_bt1_main://全选
                // TODO 19/12/17
                wan2Adapter.selectaAll();
                //wan2Adapter.setInt(3);
                wan2Adapter.notifyDataSetChanged();
                break;
            case R.id.ll_bt2_main://编辑
                // TODO 19/12/17
                String s = mBt2MainLl.getText().toString();

                if (s.equals("编辑")) {
                    mBt2MainLl.setText("完成");
                    mBt1MainLl.setVisibility(View.VISIBLE);
                    mBt3MainLl.setVisibility(View.VISIBLE);
                    // wan2Adapter.setInt(1);
                    wan2Adapter.showOrHint(true);
                    wan2Adapter.notifyDataSetChanged();
                } else {
                    mBt2MainLl.setText("编辑");
                    wan2Adapter.showOrHint(false);
                    mBt1MainLl.setVisibility(View.INVISIBLE);
                    mBt3MainLl.setVisibility(View.INVISIBLE);
                    // wan2Adapter.setInt(2);
                    wan2Adapter.notifyDataSetChanged();
                }

                break;
            case R.id.ll_bt3_main://删除
                // TODO 19/12/17
                //wan2Adapter.setInt(4);
//                wan2Adapter.setIntItem(4,posi);
                wan2Adapter.delete();
               /* for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).isChecks()==true){
                        list.remove(i);
                        wan2Adapter.notifyDataSetChanged();
                    }*/
//list.get(po)
                // }

         /*       wan2Adapter.setOnClickItemLis(new Wan2Adapter.OnClickItemLis() {
                    @Override
                    public void onClickItem(View view, final int position) {
                        view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                list.remove(position);
                                wan2Adapter.notifyDataSetChanged();
                            }
                        });

                    }
                });*/
                break;
            default:
                break;
        }
    }
}
