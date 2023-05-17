package cn.jiguang.ap;

import android.util.SparseArray;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.view.PointerIconCompat;
import cn.jiguang.api.utils.ByteBufferUtils;

/* loaded from: classes.dex */
public final class g {
    private static final SparseArray<String> a;

    static {
        SparseArray<String> sparseArray = new SparseArray<>();
        a = sparseArray;
        sparseArray.put(0, "OK");
        sparseArray.put(-1001, "Exceed buffer size. Please contact support.");
        sparseArray.put(NotificationManagerCompat.IMPORTANCE_UNSPECIFIED, "Connection failed. Please check your connection and retry later!");
        sparseArray.put(-998, "Sending failed or timeout. Please Retry later!");
        sparseArray.put(-997, "Receiving failed or timeout. Please Retry later!");
        sparseArray.put(-996, "Connection is closed. Please Retry later!");
        sparseArray.put(-994, "Response timeout. Please Retry later!");
        sparseArray.put(-993, "Invalid socket. Please Retry later!");
        sparseArray.put(11, "Failed to register!");
        sparseArray.put(1005, "Your appKey and android package name are not matched. Please double check them according to Application you created on Portal.");
        sparseArray.put(PointerIconCompat.TYPE_CELL, "You android package name is not exist, Please register your pacakge name in Portal.");
        sparseArray.put(PointerIconCompat.TYPE_CROSSHAIR, "Invalid Imei, Register again.");
        sparseArray.put(PointerIconCompat.TYPE_TEXT, "Invalid appKey, Please get your Appkey from JIGUANG web console!");
        sparseArray.put(PointerIconCompat.TYPE_VERTICAL_TEXT, "Your appKey is related to a non-Android App.Please use your Android App's appKey, or add an Android app for this appKey.");
        sparseArray.put(ByteBufferUtils.ERROR_CODE, "Receiver data parse error");
        sparseArray.put(102, "102 - Incorrect password");
        sparseArray.put(108, "108 - Incorrect uid");
        sparseArray.put(PointerIconCompat.TYPE_NO_DROP, "Server reject this connection, will clear cache and restart connect.");
    }

    public static String a(int i) {
        SparseArray<String> sparseArray = a;
        if (sparseArray.get(i) == null) {
            cn.jiguang.ai.a.c("StatusCode", "Unknown error code - " + i);
            return null;
        }
        return sparseArray.get(i);
    }
}
