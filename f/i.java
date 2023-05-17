package cn.jiguang.f;

import android.content.Context;
import cn.jiguang.api.JCoreManager;
import com.facebook.internal.ServerProtocol;
import java.io.File;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class i {
    public static File a(Context context, String str) {
        File filesDir;
        if (context == null || (filesDir = context.getFilesDir()) == null) {
            cn.jiguang.ai.a.g("FileUtils", "can't get file :" + str);
            return null;
        }
        return new File(filesDir, str);
    }

    public static String a(int i) {
        cn.jiguang.ah.b.a();
        return cn.jiguang.ah.b.b(i);
    }

    public static JSONObject a(Context context, JSONObject jSONObject, String str) {
        Object onEvent = JCoreManager.onEvent(context, "JCOMMON", 26, null, null, jSONObject, str);
        return onEvent instanceof JSONObject ? (JSONObject) onEvent : jSONObject;
    }

    public static void a(Context context, Object obj) {
        JCoreManager.onEvent(context, "JCOMMON", 39, null, null, obj);
    }

    public static void a(Context context, Object obj, Object obj2) {
        JCoreManager.onEvent(context, "JCOMMON", 15, null, null, obj, obj2);
    }

    public static void a(Context context, JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("content");
            cn.jiguang.ai.a.c("JCommonPresenter", "jsonContent:" + jSONObject2);
            int optInt = jSONObject2.optInt(ServerProtocol.DIALOG_PARAM_STATE, -1);
            if (optInt == -1) {
                cn.jiguang.ai.a.g("JCommonPresenter", "unknow state");
            } else if (optInt == 0) {
                cn.jiguang.ai.a.c("JCommonPresenter", "turn on share process");
                cn.jiguang.sdk.impl.b.a(context, optInt);
            } else if (optInt != 1) {
                cn.jiguang.ai.a.g("JCommonPresenter", "#exception - unsupport state:" + optInt);
            } else {
                cn.jiguang.ai.a.c("JCommonPresenter", "turn off share process");
                cn.jiguang.sdk.impl.b.a(context, optInt);
                cn.jiguang.am.a.a().a(context, cn.jiguang.sdk.impl.b.e(context));
                cn.jiguang.am.a.a(context, context.getPackageName());
            }
        } catch (Exception e) {
            cn.jiguang.ai.a.g("JCommonPresenter", "configReportRunningApp exception:" + e.getMessage());
        }
    }

    public static boolean a(Context context) {
        if (!cn.jiguang.sdk.impl.b.a(context, true, "canGetLbsInBackGround") || cn.jiguang.sdk.impl.b.a || cn.jiguang.ap.a.a(context, "android.permission.ACCESS_BACKGROUND_LOCATION")) {
            return true;
        }
        cn.jiguang.ai.a.h("JCommonPresenter", "app is not in foreground and no android.permission.ACCESS_BACKGROUND_LOCATION");
        return false;
    }
}
