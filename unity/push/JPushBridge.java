package cn.jiguang.unity.push;

import android.content.Context;
import android.text.TextUtils;
import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.CustomPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.data.JPushLocalNotification;
import com.unity3d.player.UnityPlayer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class JPushBridge {
    static String gameObject;
    private static JPushBridge jpushBridge;
    static String openNotiStrCache;
    private Context mContext;
    static List<String> receiveNotiStrCache = new ArrayList();
    static List<String> receiveMessageStrCache = new ArrayList();

    public static JPushBridge getInstance() {
        if (jpushBridge == null) {
            jpushBridge = new JPushBridge();
        }
        return jpushBridge;
    }

    public void setDebug(boolean z) {
        JPushInterface.setDebugMode(z);
    }

    public void initPush(String str) {
        gameObject = str;
        Context applicationContext = UnityPlayer.currentActivity.getApplicationContext();
        this.mContext = applicationContext;
        JPushInterface.init(applicationContext);
        if (!receiveNotiStrCache.isEmpty()) {
            for (String str2 : receiveNotiStrCache) {
                UnityPlayer.UnitySendMessage(gameObject, "OnReceiveNotification", str2);
            }
            receiveNotiStrCache.clear();
        }
        if (!TextUtils.isEmpty(openNotiStrCache)) {
            UnityPlayer.UnitySendMessage(gameObject, "OnOpenNotification", openNotiStrCache);
            openNotiStrCache = null;
        }
        if (receiveMessageStrCache.isEmpty()) {
            return;
        }
        for (String str3 : receiveMessageStrCache) {
            UnityPlayer.UnitySendMessage(gameObject, "OnReceiveMessage", str3);
        }
        receiveMessageStrCache.clear();
    }

    public void stopPush() {
        JPushInterface.stopPush(this.mContext);
    }

    public void resumePush() {
        JPushInterface.resumePush(this.mContext);
    }

    public boolean isPushStopped() {
        return JPushInterface.isPushStopped(this.mContext);
    }

    public String getRegistrationId() {
        return JPushInterface.getRegistrationID(this.mContext);
    }

    public void initCrashHandler() {
        JPushInterface.initCrashHandler(this.mContext);
    }

    public void stopCrashHandler() {
        JPushInterface.stopCrashHandler(this.mContext);
    }

    public void setTags(int i, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        JPushInterface.setTags(this.mContext, i, JsonUtil.jsonToSet(str));
    }

    public void addTags(int i, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        JPushInterface.addTags(this.mContext, i, JsonUtil.jsonToSet(str));
    }

    public void deleteTags(int i, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        JPushInterface.deleteTags(this.mContext, i, JsonUtil.jsonToSet(str));
    }

    public void cleanTags(int i) {
        JPushInterface.cleanTags(this.mContext, i);
    }

    public void getAllTags(int i) {
        JPushInterface.getAllTags(this.mContext, i);
    }

    public void checkTagBindState(int i, String str) {
        JPushInterface.checkTagBindState(this.mContext, i, str);
    }

    public void setAlias(int i, String str) {
        JPushInterface.setAlias(this.mContext, i, str);
    }

    public void deleteAlias(int i) {
        JPushInterface.deleteAlias(this.mContext, i);
    }

    public void getAlias(int i) {
        JPushInterface.getAlias(this.mContext, i);
    }

    public void setPushTime(String str, int i, int i2) {
        String[] split;
        HashSet hashSet = str == null ? null : new HashSet();
        if (!TextUtils.isEmpty(str)) {
            for (String str2 : str.split(",")) {
                if (!isNumeric(str2)) {
                    return;
                }
                hashSet.add(Integer.valueOf(Integer.parseInt(str2)));
            }
        }
        JPushInterface.setPushTime(this.mContext, hashSet, i, i2);
    }

    public void setSilenceTime(int i, int i2, int i3, int i4) throws Exception {
        if (i < 0 || i > 23 || i2 < 0 || i2 > 59) {
            throw new IllegalArgumentException("开始时间不正确");
        }
        if (i3 < 0 || i3 > 23 || i4 < 0 || i4 > 59) {
            throw new IllegalArgumentException("结束时间不正确");
        }
        JPushInterface.setSilenceTime(this.mContext, i, i2, i3, i4);
    }

    public void addLocalNotification(int i, String str, String str2, int i2, int i3, String str3) {
        JPushLocalNotification jPushLocalNotification = new JPushLocalNotification();
        jPushLocalNotification.setBuilderId(i);
        jPushLocalNotification.setContent(str);
        jPushLocalNotification.setTitle(str2);
        jPushLocalNotification.setNotificationId(i2);
        jPushLocalNotification.setBroadcastTime(System.currentTimeMillis() + (i3 * 1000));
        if (!TextUtils.isEmpty(str3)) {
            jPushLocalNotification.setExtras(str3);
        }
        JPushInterface.addLocalNotification(this.mContext, jPushLocalNotification);
    }

    public void addLocalNotificationByDate(int i, String str, String str2, int i2, int i3, int i4, int i5, int i6, int i7, int i8, String str3) {
        JPushLocalNotification jPushLocalNotification = new JPushLocalNotification();
        jPushLocalNotification.setBuilderId(i);
        jPushLocalNotification.setContent(str);
        jPushLocalNotification.setTitle(str2);
        jPushLocalNotification.setNotificationId(i2);
        jPushLocalNotification.setBroadcastTime(i3, i4, i5, i6, i7, i8);
        if (!TextUtils.isEmpty(str3)) {
            jPushLocalNotification.setExtras(str3);
        }
        JPushInterface.addLocalNotification(this.mContext, jPushLocalNotification);
    }

    public void removeLocalNotification(int i) {
        JPushInterface.removeLocalNotification(this.mContext, i);
    }

    public void clearLocalNotifications() {
        JPushInterface.clearLocalNotifications(this.mContext);
    }

    public void clearAllNotifications() {
        JPushInterface.clearAllNotifications(this.mContext);
    }

    public void clearNotificationById(int i) {
        JPushInterface.clearNotificationById(this.mContext, i);
    }

    public void requestPermission() {
        UnityPlayer.currentActivity.runOnUiThread(new Runnable() { // from class: cn.jiguang.unity.push.JPushBridge.1
            @Override // java.lang.Runnable
            public void run() {
                JPushInterface.requestPermission(UnityPlayer.currentActivity);
            }
        });
    }

    public void setBasicPushNotificationBuilder(int i, int i2, int i3, String str) {
        BasicPushNotificationBuilder basicPushNotificationBuilder = new BasicPushNotificationBuilder(this.mContext);
        if (i2 != -1) {
            basicPushNotificationBuilder.notificationDefaults = i2;
        }
        if (i3 != 16) {
            basicPushNotificationBuilder.notificationFlags = i3;
        }
        if (!TextUtils.isEmpty(str)) {
            basicPushNotificationBuilder.statusBarDrawable = getResourceId(str, "drawable");
        }
        JPushInterface.setPushNotificationBuilder(Integer.valueOf(i), basicPushNotificationBuilder);
    }

    public void setCustomPushNotificationBuilder(int i, String str, String str2, String str3) {
        int resourceId = getResourceId(str, "layout");
        int resourceId2 = getResourceId("icon", "id");
        int resourceId3 = getResourceId("title", "id");
        int resourceId4 = getResourceId("text", "id");
        int resourceId5 = getResourceId(str2, "drawable");
        int resourceId6 = getResourceId(str3, "drawable");
        CustomPushNotificationBuilder customPushNotificationBuilder = new CustomPushNotificationBuilder(this.mContext, resourceId, resourceId2, resourceId3, resourceId4);
        if (resourceId5 != 0) {
            customPushNotificationBuilder.statusBarDrawable = resourceId5;
        }
        if (resourceId6 != 0) {
            customPushNotificationBuilder.layoutIconDrawable = resourceId6;
        }
        JPushInterface.setPushNotificationBuilder(Integer.valueOf(i), customPushNotificationBuilder);
    }

    public void setLatestNotificationNumber(int i) {
        JPushInterface.setLatestNotificationNumber(this.mContext, i);
    }

    public boolean getConnectionState() {
        return JPushInterface.getConnectionState(this.mContext);
    }

    private boolean isNumeric(String str) {
        return Pattern.compile("[0-9]*").matcher(str).matches();
    }

    private int getResourceId(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        return this.mContext.getResources().getIdentifier(str, str2, this.mContext.getPackageName());
    }

    public void setChannel(String str) {
        JPushInterface.setChannel(this.mContext, str);
    }

    public String filterValidTags(String str) {
        if (str == null) {
            return null;
        }
        return JsonUtil.setToJson(JPushInterface.filterValidTags(JsonUtil.jsonToSet(str)));
    }

    public void reportNotificationOpened(String str) {
        JPushInterface.reportNotificationOpened(this.mContext, str);
    }

    public void setGeofenceInterval(long j) {
        JPushInterface.setGeofenceInterval(this.mContext, j);
    }

    public void setMaxGeofenceNumber(int i) {
        JPushInterface.setMaxGeofenceNumber(this.mContext, i);
    }

    public void deleteGeofence(String str) {
        JPushInterface.deleteGeofence(this.mContext, str);
    }

    public void setMobileNumber(int i, String str) {
        JPushInterface.setMobileNumber(this.mContext, i, str);
    }

    public void setPowerSaveMode(boolean z) {
        JPushInterface.setPowerSaveMode(this.mContext, z);
    }
}
