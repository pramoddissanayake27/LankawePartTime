package com.example.pramod_shash.lankaweparttime;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class NotificationGenerator2 {

    private   static  final int NOTIFICATION_ID_OPEN_ACTIVITY =9;

    public  static void openActivityNotification(Context context){
        NotificationCompat.Builder nc=new NotificationCompat.Builder(context);
        NotificationManager nm=(NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        Intent notifyTntent =new Intent(context ,JobShowActivity.class);
        notifyTntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent=PendingIntent.getActivity(context ,0,notifyTntent,PendingIntent.FLAG_CANCEL_CURRENT);
        nc.setContentIntent(pendingIntent);
        nc.setSmallIcon(R.mipmap.ic_launcher);
        nc.setAutoCancel(true);
        nc.setContentTitle("Notification from Employer");
        nc.setContentText("New Qualification not required Job Is available");
        nm.notify(NOTIFICATION_ID_OPEN_ACTIVITY,nc.build());



    }

}
