package com.wisdom.hljtzxm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.wisdom.hljtzxm.adapter.ProjectListAdapter;
import com.wisdom.hljtzxm.model.AreaModel;
import com.wisdom.hljtzxm.model.CityModel;
import com.wisdom.hljtzxm.model.ProjectListModel;
import com.wisdom.hljtzxm.model.ProjectListModelWithClass;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HanXueFeng
 * @ProjectName project： Hljtzxm
 * @class package：com.wisdom.hljtzxm
 * @class describe：项目名称选择页面
 * @time 2019/1/31 8:55
 * @change
 */
public class ProjectNameSelectActivity extends Activity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private Spinner spinner_country;
    private Spinner spinner_city;
    private Spinner spinner_area;
    private ImageView iv_back;
    private TextView tv_title;
    private TextView tv_nodata;
    public static String areaCode = "230000";
    private ListView listView;
    private RadioGroup rg;
    private RadioButton rb_1;
    private Button btn_confirm;
    private String projectType = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_name_select);
        initViews();
    }

    /**
     * 初始化界面参数
     */
    private void initViews() {

        rg = findViewById(R.id.rg);
        btn_confirm = findViewById(R.id.btn_confirm);
        rb_1 = findViewById(R.id.rb_1);
        spinner_country = findViewById(R.id.spinner_country);
        listView = findViewById(R.id.listView);
        spinner_city = findViewById(R.id.spinner_city);
        spinner_area = findViewById(R.id.spinner_area);
        iv_back = findViewById(R.id.head_back_iv);
        tv_title = findViewById(R.id.comm_head_title);
        tv_nodata = findViewById(R.id.tv_nodata);


        iv_back.setOnClickListener(this);
        btn_confirm.setOnClickListener(this);
        rg.setOnCheckedChangeListener(this);
        rb_1.setChecked(true);
        tv_title.setText("项目名称");
        spinner_country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: {
                        //省级
                        spinner_city.setVisibility(View.GONE);
                        spinner_area.setVisibility(View.GONE);
                        areaCode = "230000";
                    }
                    break;
                    case 1: {
                        //市级
                        spinner_city.setVisibility(View.VISIBLE);
                        spinner_area.setVisibility(View.GONE);
                        getCityData();
                    }
                    break;
                    case 2: {
                        //区县
                        spinner_city.setVisibility(View.VISIBLE);
                        spinner_area.setVisibility(View.VISIBLE);
                        getCityData();
                        if (!"".equals(areaCode)) {
                            getAreaData(areaCode);
                        } else {
                            getAreaData("230100");
                        }
                    }
                    break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * 取的城市数据
     */
    private void getCityData() {
        String url = U.URl_GENGGAI_CITY + "/v1/itemReply/cities?state_name=黑龙江省&appkey=" + U.APP_KEY;
        U.get(url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Log.i("123", "onSuccess: " + responseInfo.result);
                try {
                    JSONObject jsonObject = new JSONObject(responseInfo.result);
                    List<CityModel> cityList = new Gson().fromJson(jsonObject.getString("results"), new TypeToken<List<CityModel>>() {
                    }.getType());
                    Log.i("123", "lenth: " + cityList.size());
                    List<String> cityNamesList = new ArrayList<>();
                    for (CityModel model : cityList) {
                        cityNamesList.add(model.getArea_name());
                    }
                    spinner_city.setAdapter(new ArrayAdapter<String>(ProjectNameSelectActivity.this,
                            android.R.layout.simple_spinner_item, cityNamesList));
                    final List<CityModel> finalCityList = cityList;
                    spinner_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            areaCode = finalCityList.get(position).getArea_code();
                            getAreaData(finalCityList.get(position).getArea_code());
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

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
                    Toast.makeText(ProjectNameSelectActivity.this, jsonObject.getString("error_msg"), Toast.LENGTH_LONG).show();
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }


    /**
     * 获取地区数据
     *
     * @param cityCode
     */
    private void getAreaData(String cityCode) {
        String url = U.URl_GENGGAI_CITY + "/v1/itemReply/districts?city_code=" + cityCode + "&appkey=" + U.APP_KEY;
        Log.i("123", "getAreaDataURL: " + url);
        U.get(url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Log.i("123", "onSuccess: " + responseInfo.result);
                try {
                    JSONObject jsonObject = new JSONObject(responseInfo.result);
                    List<AreaModel> areaList = new Gson().fromJson(jsonObject.getString("results"), new TypeToken<List<AreaModel>>() {
                    }.getType());
                    List<String> areaNamesList = new ArrayList<>();
                    for (AreaModel model : areaList) {
                        areaNamesList.add(model.getArea_name());
                    }
                    spinner_area.setAdapter(new ArrayAdapter<String>(ProjectNameSelectActivity.this,
                            android.R.layout.simple_spinner_item, areaNamesList));
                    final List<AreaModel> finalAreaList = areaList;
                    spinner_area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            areaCode = finalAreaList.get(position).getArea_code();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

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
                    Toast.makeText(ProjectNameSelectActivity.this, jsonObject.getString("error_msg"), Toast.LENGTH_LONG).show();
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
        });
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
            case R.id.btn_confirm: {
                setProjectList(projectType);
            }
            break;
        }
    }


    /**
     * 获取设置事项列表
     *
     * @param projectType
     */
    private void setProjectList(final String projectType) {
        String url = U.URl_GENGGAI_CITY + "/v1/itemReply/catalogs?appkey=4E96A006-2F41-452C-A929-5C2153C6F221&division_code=" + areaCode + "&type=" + projectType;
        Log.i("123", "setProjectListURL: " + url);
        U.get(url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                try {
                    JSONObject jsonObject = new JSONObject(responseInfo.result);
                    List<ProjectListModel> projectListModel = new ArrayList<>();
                    if (!"A00002".equals(projectType)) {
                        //非核准类布局
                        projectListModel = new Gson().fromJson(jsonObject.getString("results"), new TypeToken<List<ProjectListModel>>() {
                        }.getType());
                        if (projectListModel.size() == 0) {
                            listView.setVisibility(View.GONE);
                            tv_nodata.setVisibility(View.VISIBLE);
                        } else {
                            listView.setVisibility(View.VISIBLE);
                            tv_nodata.setVisibility(View.GONE);
                            ProjectListAdapter adapter = new ProjectListAdapter(ProjectNameSelectActivity.this, projectListModel);
                            listView.setAdapter(adapter);
                        }
                    } else {
                        //核准类布局
                        List<ProjectListModelWithClass> projectListModelWithClass = new Gson().fromJson(jsonObject.getString("results"), new TypeToken<List<ProjectListModelWithClass>>() {
                        }.getType());
                        if (projectListModelWithClass.size() == 0) {
                            listView.setVisibility(View.GONE);
                            tv_nodata.setVisibility(View.VISIBLE);
                        } else {

                            for (int i = 0; i < projectListModelWithClass.size(); i++) {
                                for (int k = 0; k < projectListModelWithClass.get(i).getItems().size(); k++) {
                                    ProjectListModel projectListModel1 = new ProjectListModel();
                                    projectListModel1.setCatalog_code(projectListModelWithClass.get(i).getItems().get(k).getCatalog_code());
                                    projectListModel1.setCatalog_name(projectListModelWithClass.get(i).getItems().get(k).getCatalog_name());
                                    projectListModel.add(projectListModel1);
                                }
                            }
                            listView.setVisibility(View.VISIBLE);
                            tv_nodata.setVisibility(View.GONE);
                            ProjectListAdapter adapter = new ProjectListAdapter(ProjectNameSelectActivity.this, projectListModel);
                            listView.setAdapter(adapter);
                        }
                    }
                    //子项点击事件
                    final List<ProjectListModel> finalProjectListModel = projectListModel;
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent();
                            intent.putExtra("code", finalProjectListModel.get(position).getCatalog_code());
                            intent.putExtra("areaCode", areaCode);
                            intent.putExtra("name", finalProjectListModel.get(position).getCatalog_name());
                            setResult(0x111, intent);
                            ProjectNameSelectActivity.this.finish();
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
                    Toast.makeText(ProjectNameSelectActivity.this, jsonObject.getString("error_msg"), Toast.LENGTH_LONG).show();
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
        });


    }


    /**
     * 核准 备案 等类型选择事件
     *
     * @param group
     * @param checkedId
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_1: {
                setProjectList("A00001");
                projectType = "A00001";
            }
            break;
            case R.id.rb_2: {
                setProjectList("A00002");
                projectType = "A00002";
            }
            break;
            case R.id.rb_3: {
                setProjectList("A00003");
                projectType = "A00003";
            }
            break;
        }
    }
}
