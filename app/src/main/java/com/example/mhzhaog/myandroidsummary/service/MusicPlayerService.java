package com.example.mhzhaog.myandroidsummary.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import com.example.mhzhaog.myandroidsummary.MainActivity;
import com.example.mhzhaog.myandroidsummary.R;

public class MusicPlayerService extends Service {

    private static final String TAG = MusicPlayerService.class.getSimpleName();

    public MusicPlayerService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        init();
        Log.e(TAG, "onCreate()");
    }

    private void init() {
        Notification.Builder builder = new Notification.Builder(this.getApplicationContext()); //获取一个Notification构造器
        Intent nfIntent = new Intent(this, MainActivity.class);

        builder.setContentIntent(PendingIntent.getActivity(this, 0, nfIntent, 0)) // 设置PendingIntent
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher_round)) // 设置下拉列表中的图标(大图标)
                .setContentTitle("下拉列表中的Title") // 设置下拉列表里的标题
                .setSmallIcon(R.mipmap.ic_launcher) // 设置状态栏内的小图标
                .setContentText("要显示的内容") // 设置上下文内容
                .setWhen(System.currentTimeMillis()); // 设置该通知发生的时间

        Notification notification = builder.build(); // 获取构建好的Notification
        notification.defaults = Notification.DEFAULT_SOUND; //设置为默认的声音


        // 参数一：唯一的通知标识；参数二：通知消息。
        startForeground(1,notification);
        Log.e(TAG, "startForeground()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand()");
//        return super.onStartCommand(intent, flags, startId);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.e(TAG, "onBind()");
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy()");
        stopForeground(true);
        Log.e(TAG, "stopForeground");
        super.onDestroy();

    }
}
