package com.llkj.creditchecking.jpush;

import cn.jpush.android.api.JPushInterface;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

public class TestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);
        tv.setText("用户自定义打开的Activity");
        Intent intent = getIntent();
        if (null != intent) {
	        Bundle bundle = getIntent().getExtras();
	        String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE,"");
	        String content = bundle.getString(JPushInterface.EXTRA_ALERT,"");
            String title2 = bundle.getString(JPushInterface.EXTRA_TITLE,"");
            String content2 = bundle.getString(JPushInterface.EXTRA_MESSAGE,"");
	        tv.setText("Title : " + title +title2+ "  " + "Content : " + content+content2);
        }
        addContentView(tv, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
    }

}
