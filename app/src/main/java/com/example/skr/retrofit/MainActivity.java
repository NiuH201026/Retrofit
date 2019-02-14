package com.example.skr.retrofit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.skr.retrofit.api.ApiService;
import com.example.skr.retrofit.api.UserApiService;
import com.example.skr.retrofit.bean.LoginBean;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.edit_mobile)
    EditText editMobile;
    @BindView(R.id.edit_pwd)
    EditText editPwd;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_regist)
    Button btnRegist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_login, R.id.btn_regist})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.btn_regist:
                break;
        }
    }

    private void login() {
        String phone = editMobile.getText().toString();
        String pwd = editPwd.getText().toString();
        if (TextUtils.isEmpty(phone)&&TextUtils.isEmpty(pwd)){
            Toast.makeText(MainActivity.this, "内容不能为空", Toast.LENGTH_SHORT).show();
        }else{
            //第一步创建retrofit管理器
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ApiService.BASE_URL)//主机名
                    .addConverterFactory(GsonConverterFactory.create())//数据解析器
                    .build();

            //第二步,实现接口
            UserApiService userApiService = retrofit.create(UserApiService.class);

            HashMap<String,String> parms = new HashMap<>();
            parms.put("phone",phone);
            parms.put("pwd",pwd);
            Call<LoginBean> login = userApiService.login(parms);
            login.enqueue(new Callback<LoginBean>() {
                @Override
                public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                    LoginBean body = response.body();
                    Toast.makeText(MainActivity.this, body.getMessage(), Toast.LENGTH_SHORT).show();
                    if (body.getStatus().equals("0000")){
                        Intent intent = new Intent(MainActivity.this,ShouYeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<LoginBean> call, Throwable t) {

                }
            });

        }
    }
}
