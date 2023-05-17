package cn.jiguang.ak;

import android.content.Context;
import java.nio.ByteBuffer;
import kotlin.jvm.internal.ByteCompanionObject;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class b {
    public static String a(ByteBuffer byteBuffer) {
        byte[] bArr = new byte[byteBuffer.getShort()];
        byteBuffer.get(bArr);
        try {
            return new String(bArr, "UTF-8");
        } catch (Throwable unused) {
            return null;
        }
    }

    public static byte[] a(long j, String str, long[] jArr) {
        cn.jiguang.ap.e eVar = new cn.jiguang.ap.e(20480);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("device_id", str);
            JSONArray jSONArray = new JSONArray();
            if (jArr != null) {
                jSONArray.put(j);
                for (long j2 : jArr) {
                    jSONArray.put(j2);
                }
            }
            jSONObject.put("uids", jSONArray);
            cn.jiguang.ai.a.c("CorePackage", "attach uids:" + jSONArray.toString());
            eVar.a(jSONObject.toString());
            return eVar.b();
        } catch (Throwable th) {
            cn.jiguang.ai.a.g("CorePackage", "packageAttachInfo:" + th);
            return null;
        }
    }

    public static byte[] a(Context context, int i, int i2, long j, byte[] bArr, long j2) {
        cn.jiguang.ap.e eVar = new cn.jiguang.ap.e(20480);
        eVar.b(0);
        eVar.a(i2);
        eVar.a(i);
        eVar.b(j);
        eVar.a(cn.jiguang.sdk.impl.a.f);
        cn.jiguang.ai.a.c("CorePackage", "packageSendData uid:" + j2);
        if (j2 == 0) {
            j2 = cn.jiguang.sdk.impl.b.e(context);
            cn.jiguang.ai.a.c("CorePackage", "use mine uid:" + j2);
        }
        eVar.b(j2);
        eVar.a(bArr);
        eVar.b(eVar.a(), 0);
        return a(context, eVar.b());
    }

    public static byte[] a(Context context, byte[] bArr) {
        byte[] a;
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        int length = bArr.length - 24;
        byte[] bArr2 = new byte[24];
        byte[] bArr3 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, 24);
        System.arraycopy(bArr, 24, bArr3, 0, length);
        String a2 = cn.jiguang.al.a.a(context);
        try {
            byte b = cn.jiguang.sdk.impl.a.e;
            cn.jiguang.ai.a.c("CorePackage", "encryptBuf algorithm=" + ((int) b) + ", key=" + a2);
            if (b == 2) {
                cn.jiguang.al.b bVar = new cn.jiguang.al.b();
                byte[] a3 = cn.jiguang.al.b.a(a2);
                String substring = a2.substring(0, 16);
                cn.jiguang.ai.a.c("SM4", "seed=" + a2);
                a = bVar.a(bArr3, a3, substring.getBytes("utf-8"));
            } else {
                a = cn.jiguang.al.a.a(a2, bArr3);
            }
            int length2 = a.length + 24;
            byte[] bArr4 = new byte[length2];
            System.arraycopy(bArr2, 0, bArr4, 0, 24);
            System.arraycopy(a, 0, bArr4, 24, a.length);
            bArr4[0] = (byte) ((length2 >>> 8) & 255);
            bArr4[1] = (byte) (length2 & 255);
            bArr4[0] = (byte) (bArr4[0] | ByteCompanionObject.MIN_VALUE);
            bArr4[4] = b;
            return bArr4;
        } catch (Exception e) {
            cn.jiguang.ai.a.g("CorePackage", "e:" + e);
            cn.jiguang.ai.a.h("CorePackage", "encrpt data failed");
            return null;
        }
    }

    public static byte[] a(String str, long[] jArr) {
        cn.jiguang.ap.e eVar = new cn.jiguang.ap.e(20480);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("device_id", str);
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i <= 0; i++) {
                jSONArray.put(jArr[0]);
            }
            jSONObject.put("uids", jSONArray);
            eVar.a(jSONObject.toString());
            return eVar.b();
        } catch (Throwable th) {
            cn.jiguang.ai.a.g("CorePackage", "packageDetachInfo:" + th);
            return null;
        }
    }
}
