package com.wisdom.hljtzxm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.wisdom.hljtzxm.adapter.ThingsListAdapter;
import com.wisdom.hljtzxm.model.ThingsModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * @author HanXueFeng
 * @ProjectName project： Hljtzxm
 * @class package：com.wisdom.hljtzxm
 * @class describe：
 * @time 2019/1/31 15:21
 * @change
 */
public class ThingsItemsActivity extends Activity implements View.OnClickListener {
    private TextView tv_title;
    private ImageView iv_back;
    private ListView listView;
    private String projectCode;
    private String areaCode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_things);
        tv_title = findViewById(R.id.comm_head_title);
        iv_back = findViewById(R.id.head_back_iv);
        listView = findViewById(R.id.listView);

        iv_back.setOnClickListener(this);
        tv_title.setText("事项名称");
        if (getIntent() != null) {
            projectCode = getIntent().getStringExtra("projectCode");
            areaCode = getIntent().getStringExtra("areaCode");
            getData();
        }

    }

    /**
     * 页面内的点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.head_back_iv: {
                this.finish();
            }
            break;
        }
    }

    /**
     * 获得界面数据
     */
    private void getData() {
        String url = U.URl_GENGGAI_CITY + "/v1/itemReply/getMatterName?appkey=4E96A006-2F41-452C-A929-5C2153C6F221&division_code=" + areaCode + "&permit_item_code=" + projectCode;
        Log.i("123", "url: " + url);
        U.get(url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                try {
                    JSONObject jsonObject = new JSONObject(responseInfo.result);
                    final List<ThingsModel> thingsModelList = new Gson().fromJson(jsonObject.getString("results"), new TypeToken<List<ThingsModel>>() {
                    }.getType());
                    ThingsListAdapter adapter = new ThingsListAdapter(ThingsItemsActivity.this, thingsModelList);
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent();
                            intent.putExtra("sxmc", thingsModelList.get(position).getITEM_NAME());
                            intent.putExtra("zxbm", thingsModelList.get(position).getTzxm_depdisname());
                            setResult(0x114, intent);
                            ThingsItemsActivity.this.finish();
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    Toast.makeText(ThingsItemsActivity.this, jsonObject.getString("error_msg"), Toast.LENGTH_LONG).show();
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
        });


    }

}
