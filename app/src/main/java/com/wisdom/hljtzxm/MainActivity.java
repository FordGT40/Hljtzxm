package com.wisdom.hljtzxm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

/**
 * @author HanXueFeng
 * @ProjectName project£º Hljtzxm
 * @class package£ºcom.wisdom.hljtzxm
 * @class describe£º
 * @time 2019/2/13 15:14
 * @change
 */
public class MainActivity extends Activity {
    private Button btn_1;
    private Button btn_2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_index);
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ConsultMainActivity.class));
            }
        });
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, QuestionesActivity.class));
            }
        });
    }
}
