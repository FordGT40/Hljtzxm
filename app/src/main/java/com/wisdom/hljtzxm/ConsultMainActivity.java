package com.wisdom.hljtzxm;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

import org.json.JSONException;
import org.json.JSONObject;

public class ConsultMainActivity extends Activity implements View.OnClickListener {
    private TextView tv_title;
    private ImageView iv_back;
    private Button bt_submit;
    private Button bt_reset;
    private EditText et_zxxm;
    private TextView et_xmmc;
    private TextView et_sxmc;
    private TextView et_zxbm;
    private EditText et_zxnr;
    private EditText et_sjhm;
    private EditText et_lxdh;
    private EditText et_dzyx;
    private EditText et_xxdz;
    private String projectCode = "";
    private String projectName = "";
    private String areaCode = "";
    private String thingsName = "";
    private String departmentName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        tv_title = findViewById(R.id.comm_head_title);
        iv_back = findViewById(R.id.head_back_iv);
        bt_submit = findViewById(R.id.bt_submit);
        bt_reset = findViewById(R.id.bt_rest);
        et_zxxm = findViewById(R.id.et_zxxm);
        et_xmmc = findViewById(R.id.et_xmmc);
        et_sxmc = findViewById(R.id.et_sxmc);
        et_zxbm = findViewById(R.id.et_zxbm);
        et_zxnr = findViewById(R.id.et_zxnr);
        et_sjhm = findViewById(R.id.et_sjhm);
        et_lxdh = findViewById(R.id.et_lxdh);
        et_dzyx = findViewById(R.id.et_dzyx);
        et_xxdz = findViewById(R.id.et_xxdz);


        tv_title.setText("咨询");
        iv_back.setOnClickListener(this);
        bt_submit.setOnClickListener(this);
        bt_reset.setOnClickListener(this);
        et_xmmc.setOnClickListener(this);
        et_sxmc.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.head_back_iv: {//返回
                this.finish();
            }
            break;
            case R.id.bt_submit: {//提交
                if (checkPageData()) {
                    submitData();
                }
            }
            break;
            case R.id.bt_rest: {//重置
                resetPageData(et_zxxm,
                        et_zxnr,
                        et_sjhm,
                        et_lxdh,
                        et_dzyx,
                        et_xxdz);
                et_xmmc.setText("请选择项目名称");
                et_sxmc.setText("请选择事项名称");
                et_zxbm.setText("");
            }
            break;
            case R.id.et_xmmc: {
//                项目名称
                startActivityForResult(new Intent(this, ProjectNameSelectActivity.class), 0x123);

            }
            break;
            case R.id.et_sxmc: {
//                事项名称
                if (!"".equals(projectCode) && !"".equals(areaCode)) {
                    Intent intent = new Intent();
                    intent.putExtra("projectCode", projectCode);
                    intent.putExtra("areaCode", areaCode);
                    intent.setClass(this, ThingsItemsActivity.class);
                    startActivityForResult(intent, 0x12);
                    et_sxmc.setText("请选择事项名称");
                    et_zxbm.setText("");
                } else {
                    Toast.makeText(this, "请先选择项目名称", Toast.LENGTH_SHORT).show();
                }
            }
            break;
        }
    }


    /**
     * 检查界面空值
     */
    private Boolean checkPageData() {
        Boolean isChecked = true;
        if ("".equals(getEtText(et_zxxm))) {
            U.toast(this, "投资姓名不能为空");
            isChecked = false;
        } else if ("".equals(et_xmmc.getText())) {
            U.toast(this, "项目名称不能为空");
            isChecked = false;
        } else if ("".equals(et_sxmc.getText())) {
            U.toast(this, "事项名称不能为空");
            isChecked = false;
        } else if ("".equals(et_zxbm.getText())) {
            U.toast(this, "咨询部门不能为空");
            isChecked = false;
        } else if ("".equals(getEtText(et_zxnr))) {
            U.toast(this, "咨询内容不能为空");
            isChecked = false;
        } else if ("".equals(getEtText(et_sjhm))) {
            U.toast(this, "手机号码不能为空");
            isChecked = false;
        } else if ("".equals(getEtText(et_lxdh))) {
            U.toast(this, "联系电话不能为空");
            isChecked = false;
        } else if ("".equals(getEtText(et_dzyx))) {
            U.toast(this, "电子邮箱不能为空");
            isChecked = false;
        } else if ("".equals(getEtText(et_xxdz))) {
            U.toast(this, "详细地址不能为空");
            isChecked = false;
        }
        return isChecked;
    }


    /**
     * 取得控件内的文字
     *
     * @param editText
     * @return
     */
    private String getEtText(EditText editText) {
        if (editText != null) {
            return editText.getText().toString().trim();
        } else {
            return "";
        }
    }

    /**
     * 重置页面控件数据
     *
     * @param editText
     */
    private void resetPageData(EditText... editText) {
        for (int i = 0; i < editText.length; i++) {
            editText[i].setText("");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 0x111: {
                //选择项目回调
                if (data != null) {
                    et_xmmc.setText(data.getStringExtra("name"));
                    projectName = data.getStringExtra("name");
                    projectCode = data.getStringExtra("code");
                    areaCode = data.getStringExtra("areaCode");
                }
            }
            break;
            case 0x114: {
                //选择事项名
                if (data != null) {
                    et_sxmc.setText(data.getStringExtra("sxmc"));
                    et_zxbm.setText(data.getStringExtra("zxbm"));
                    thingsName = data.getStringExtra("sxmc");
                    departmentName = data.getStringExtra("zxbm");
                }
            }
            break;
        }
    }


    /**
     * 提交数据到接口
     */
    private void submitData() {
        String name = getEtText(et_zxxm);
        String content = getEtText(et_zxnr);
        String phone = getEtText(et_sjhm);
        String tel = getEtText(et_lxdh);
        String email = getEtText(et_dzyx);
        String address = getEtText(et_xxdz);
        String url = U.URl_GENGGAI_CITY + "/v1/itemReply/zixun?appkey=4E96A006-2F41-452C-A929-5C2153C6F221" +
                "&user_name=" + name +
                "&project_name=" + projectName +
                "&item_name=" + thingsName +
                "&zx_department=" + departmentName +
                "&zx_content=" + content +
                "&phone=" + phone +
                "&mobile=" + tel +
                "&mail_box=" + email +
                "&address=" + address;
        Log.i("123", "url: " + url);
        U.get(url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                try {
                    JSONObject jsonObject = new JSONObject(responseInfo.result);
                    JSONObject results = jsonObject.getJSONObject("results");
                    String consult_num = results.getString("consult_num");
                    new AlertDialog.Builder(ConsultMainActivity.this)
                            .setTitle("提示")
                            .setMessage("咨询成功！您的咨询编码为：\n" + consult_num)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    ConsultMainActivity.this.finish();
                                }
                            })
                            .create().show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    Toast.makeText(ConsultMainActivity.this, jsonObject.getString("error_msg"), Toast.LENGTH_LONG).show();
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

}
