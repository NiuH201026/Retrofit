package com.example.skr.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.skr.retrofit.adapter.MyAdapter;
import com.example.skr.retrofit.api.ApiService;
import com.example.skr.retrofit.api.UserApiService;
import com.example.skr.retrofit.bean.SearchBean;
import com.example.skr.retrofit.bean.ShouYeBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShouYeActivity extends AppCompatActivity {

    @BindView(R.id.edit_search)
    EditText editSearch;
    @BindView(R.id.text_search)
    TextView textSearch;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shou_ye);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.text_search)
    public void onViewClicked() {
        String search = editSearch.getText().toString();
        if (TextUtils.isEmpty(search)) {
            Toast.makeText(ShouYeActivity.this, "内容不能为空", Toast.LENGTH_SHORT).show();
        } else {
            //第一步创建Retrofit管理器
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ApiService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            UserApiService userApiService = retrofit.create(UserApiService.class);
            Call<SearchBean> search1= userApiService.shou(search);
            search1.enqueue(new Callback<SearchBean>() {
                @Override
                public void onResponse(Call<SearchBean> call, Response<SearchBean> response) {
                    SearchBean searchBean = response.body();
                    List<SearchBean.ResultBean> list = searchBean.getResult();
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(ShouYeActivity.this, 2);
                    recyclerView.setLayoutManager(gridLayoutManager);
                    MyAdapter myAdapter = new MyAdapter(ShouYeActivity.this,list);
                    recyclerView.setAdapter(myAdapter);
                }

                @Override
                public void onFailure(Call<SearchBean> call, Throwable t) {

                }
            });
        }
    }
}
