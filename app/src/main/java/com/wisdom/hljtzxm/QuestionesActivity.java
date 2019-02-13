package com.wisdom.hljtzxm;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.wisdom.hljtzxm.adapter.QuestionesAdapter;
import com.wisdom.hljtzxm.model.QuestionModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * @author HanXueFeng
 * @ProjectName project£º Hljtzxm
 * @class package£ºcom.wisdom.hljtzxm
 * @class describe£º
 * @time 2019/2/13 15:25
 * @change
 */
public class QuestionesActivity extends Activity {
    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questiones);
        listView = findViewById(R.id.listView);
        String url = U.URl_GENGGAI_CITY + "/v1/itemReply/goQuestion?appkey=" + U.APP_KEY;

        U.get(url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                try {
                    JSONObject jsonObject = new JSONObject(responseInfo.result);
                    List<QuestionModel> listData = new Gson().fromJson(jsonObject.getString("results"), new TypeToken<List<QuestionModel>>() {
                    }.getType());
                    listView.setAdapter(new QuestionesAdapter(listData, QuestionesActivity.this));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    Toast.makeText(QuestionesActivity.this, jsonObject.getString("error_msg"), Toast.LENGTH_LONG).show();
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
        });


    }
}
