package com.llkj.creditchecking.jpush;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import com.llkj.creditchecking.R;
import com.llkj.creditchecking.utils.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.Iterator;

import cn.jpush.android.api.JPushInterface;


/**
 * 2016年8月19日
 */
public class MyRecevier extends BroadcastReceiver {

    private static final String ACTION_OPEARATE_ORDER = "ido_action_opearate_order";
    private NotificationManager nm;
    private Handler handler;
    Context mcontext ;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (null == nm) {
             mcontext = context;
              nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        }
        Bundle bundle = intent.getExtras();
        LogUtils.e("onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));

        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            LogUtils.e( "JPush用户注册成功");

        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            LogUtils.e( "接受到推送下来的自定义消息");
            // Push Talk messages are push down by custom message format
          //  processCustomMessage(context, bundle);
            receivingNotification(context, bundle);

        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            LogUtils.e( "接受到推送下来的通知");

        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            LogUtils.e( "用户点击打开了通知");
            //打开自定义的Activity
            Intent i = new Intent(context, TestActivity.class);
            i.putExtras(bundle);
            //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
            context.startActivity(i);

        }else if(JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())){
            LogUtils.e("[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
            //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..

        }else if(JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())){
            boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
            LogUtils.e("[MyReceiver]" + intent.getAction() +" connected state change to "+connected);

        } else {
            LogUtils.e( "Unhandled intent - " + intent.getAction());
        }
    }

    private String printBundle(Bundle bundle)
    {
        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
            }else if(key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)){
                sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
            } else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
                if (bundle.getString(JPushInterface.EXTRA_EXTRA).isEmpty()) {
                    LogUtils.e( "This message has no Extra data");
                    continue;
                }
                try {
                    JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                    Iterator<String> it =  json.keys();

                    while (it.hasNext()) {
                        String myKey = it.next().toString();
                        sb.append("\nkey:" + key + ", value: [" +
                                myKey + " - " +json.optString(myKey) + "]");
                    }
                } catch (JSONException e) {
                    LogUtils.e( "Get message extra JSON error!");
                }

            } else {
                sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
            }
        }
        return sb.toString();

    }
    private void receivingNotification(Context context, Bundle bundle)
    {
        try {

            String extra = bundle.getString(JPushInterface.EXTRA_EXTRA);
            LogUtils.d(extra);
           // PushBean notifyEntity = new Gson().fromJson(extra, PushBean.class);
           //  LogUtils.d("notifyEntity = " + notifyEntity);
            String  type = bundle.getString(JPushInterface.EXTRA_CONTENT_TYPE);
            String title = bundle.getString(JPushInterface.EXTRA_TITLE);
            String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);

            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);


            Intent i = new Intent(context, TestActivity.class);
            i.putExtras(bundle);
            //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
            //context.startActivity(i);


            LogUtils.e("showNotification",title);
            LogUtils.e("showNotification",message);
            LogUtils.e("showNotification",""+type);

            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_ONE_SHOT);
            Notification notification = null;
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {//兼容 4.0
                Notification.Builder builder = new Notification.Builder(context)
                        .setAutoCancel(true)
                        .setContentTitle(title)
                        .setContentText(message)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentIntent(pendingIntent)
                        .setWhen(System.currentTimeMillis());
                notification = builder.getNotification();

            } else {//兼容4.1及以上
                notification = new Notification.Builder(context)
                        .setAutoCancel(true)
                        .setContentTitle(title)
                        .setContentText(message)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentIntent(pendingIntent)
                        .setWhen(System.currentTimeMillis())
                        .build();

            }
            notification.defaults = Notification.DEFAULT_ALL;
            manager.notify(0, notification);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
