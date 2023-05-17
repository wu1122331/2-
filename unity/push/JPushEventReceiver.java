package cn.jiguang.unity.push;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import cn.jpush.android.api.CustomMessage;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.JPushMessage;
import cn.jpush.android.api.NotificationMessage;
import cn.jpush.android.service.JPushMessageReceiver;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.facebook.applinks.AppLinkData;
import com.facebook.internal.security.CertificateUtil;
import com.unity3d.player.UnityPlayer;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class JPushEventReceiver extends JPushMessageReceiver {
    private static final String TAG = "JPushEventReceiver";

    @Override // cn.jpush.android.service.JPushMessageReceiver
    public void onTagOperatorResult(Context context, JPushMessage jPushMessage) {
        super.onTagOperatorResult(context, jPushMessage);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("sequence", jPushMessage.getSequence());
            jSONObject.put("code", jPushMessage.getErrorCode());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (jPushMessage.getErrorCode() == 0) {
            Set<String> tags = jPushMessage.getTags();
            JSONArray jSONArray = new JSONArray();
            for (String str : tags) {
                jSONArray.put(str);
            }
            try {
                if (jSONArray.length() != 0) {
                    jSONObject.put(ViewHierarchyConstants.TAG_KEY, jSONArray);
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        UnityPlayer.UnitySendMessage(JPushBridge.gameObject, "OnJPushTagOperateResult", jSONObject.toString());
    }

    @Override // cn.jpush.android.service.JPushMessageReceiver
    public void onCheckTagOperatorResult(Context context, JPushMessage jPushMessage) {
        super.onCheckTagOperatorResult(context, jPushMessage);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("sequence", jPushMessage.getSequence());
            jSONObject.put("code", jPushMessage.getErrorCode());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (jPushMessage.getErrorCode() == 0) {
            try {
                jSONObject.put(ViewHierarchyConstants.TAG_KEY, jPushMessage.getCheckTag());
                jSONObject.put("isBind", jPushMessage.getTagCheckStateResult());
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        UnityPlayer.UnitySendMessage(JPushBridge.gameObject, "OnJPushTagOperateResult", jSONObject.toString());
    }

    @Override // cn.jpush.android.service.JPushMessageReceiver
    public void onAliasOperatorResult(Context context, JPushMessage jPushMessage) {
        super.onAliasOperatorResult(context, jPushMessage);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("sequence", jPushMessage.getSequence());
            jSONObject.put("code", jPushMessage.getErrorCode());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (jPushMessage.getErrorCode() == 0) {
            try {
                if (!TextUtils.isEmpty(jPushMessage.getAlias())) {
                    jSONObject.put("alias", jPushMessage.getAlias());
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        UnityPlayer.UnitySendMessage(JPushBridge.gameObject, "OnJPushAliasOperateResult", jSONObject.toString());
    }

    @Override // cn.jpush.android.service.JPushMessageReceiver
    public void onRegister(Context context, String str) {
        UnityPlayer.UnitySendMessage(JPushBridge.gameObject, "OnGetRegistrationId", str);
    }

    @Override // cn.jpush.android.service.JPushMessageReceiver
    public void onMessage(Context context, CustomMessage customMessage) {
        String msg2str = msg2str(customMessage.messageId, customMessage.message, customMessage.extra);
        if (!TextUtils.isEmpty(JPushBridge.gameObject)) {
            UnityPlayer.UnitySendMessage(JPushBridge.gameObject, "OnReceiveMessage", msg2str);
        } else {
            JPushBridge.receiveMessageStrCache.add(msg2str);
        }
    }

    @Override // cn.jpush.android.service.JPushMessageReceiver
    public void onNotifyMessageArrived(Context context, NotificationMessage notificationMessage) {
        super.onNotifyMessageArrived(context, notificationMessage);
        String str = notificationMessage.notificationContent;
        String noti2str = noti2str(notificationMessage.msgId, notificationMessage.notificationTitle, str, notificationMessage.notificationExtras);
        Log.i(TAG, "GameObject: " + JPushBridge.gameObject);
        if (!TextUtils.isEmpty(JPushBridge.gameObject)) {
            UnityPlayer.UnitySendMessage(JPushBridge.gameObject, "OnReceiveNotification", noti2str);
        } else {
            JPushBridge.receiveNotiStrCache.add(noti2str);
        }
    }

    @Override // cn.jpush.android.service.JPushMessageReceiver
    public void onNotifyMessageOpened(Context context, NotificationMessage notificationMessage) {
        JPushInterface.reportNotificationOpened(context, notificationMessage.msgId);
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        if (launchIntentForPackage != null) {
            launchIntentForPackage.addCategory("android.intent.category.LAUNCHER");
            launchIntentForPackage.setFlags(805306368);
            context.startActivity(launchIntentForPackage);
        }
        String noti2str = noti2str(notificationMessage.msgId, notificationMessage.notificationTitle, notificationMessage.notificationContent, notificationMessage.notificationExtras);
        Log.i(TAG, "GameObject: " + JPushBridge.gameObject);
        if (!TextUtils.isEmpty(JPushBridge.gameObject)) {
            UnityPlayer.UnitySendMessage(JPushBridge.gameObject, "OnOpenNotification", noti2str);
        } else {
            JPushBridge.openNotiStrCache = noti2str;
        }
    }

    @Override // cn.jpush.android.service.JPushMessageReceiver
    public void onMobileNumberOperatorResult(Context context, JPushMessage jPushMessage) {
        super.onMobileNumberOperatorResult(context, jPushMessage);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("sequence", jPushMessage.getSequence());
            jSONObject.put("code", jPushMessage.getErrorCode());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (jPushMessage.getErrorCode() == 0) {
            try {
                if (!TextUtils.isEmpty(jPushMessage.getMobileNumber())) {
                    jSONObject.put("mobileNumber", jPushMessage.getMobileNumber());
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        UnityPlayer.UnitySendMessage(JPushBridge.gameObject, "OnMobileNumberOperatorResult", jSONObject.toString());
    }

    private static String noti2str(String str, String str2, String str3, String str4) {
        HashMap hashMap = new HashMap();
        hashMap.put("msgId", str);
        hashMap.put("title", str2);
        hashMap.put("content", str3);
        hashMap.put(AppLinkData.ARGUMENTS_EXTRAS_KEY, str4);
        return toJson(hashMap);
    }

    private static String msg2str(String str, String str2, String str3) {
        HashMap hashMap = new HashMap();
        hashMap.put("msgId", str);
        hashMap.put("message", str2);
        hashMap.put(AppLinkData.ARGUMENTS_EXTRAS_KEY, str3);
        return toJson(hashMap);
    }

    private static String toJson(Map<String, String> map) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("{");
        if (!map.isEmpty()) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                stringBuffer.append("\"");
                stringBuffer.append(entry.getKey());
                stringBuffer.append("\"");
                stringBuffer.append(CertificateUtil.DELIMITER);
                stringBuffer.append("\"");
                stringBuffer.append(entry.getValue());
                stringBuffer.append("\"");
                stringBuffer.append(",");
            }
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }
        stringBuffer.append("}");
        return stringBuffer.toString();
    }
}
