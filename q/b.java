package cn.jiguang.q;

import android.text.TextUtils;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class b {
    public String a;
    public String b;
    public String c;

    public final JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            String str = "";
            jSONObject.put("imei", TextUtils.isEmpty(this.a) ? "" : this.a);
            jSONObject.put("iccid", TextUtils.isEmpty(this.c) ? "" : this.c);
            if (!TextUtils.isEmpty(this.b)) {
                str = this.b;
            }
            jSONObject.put("imsi", str);
            return jSONObject;
        } catch (Throwable unused) {
            return null;
        }
    }

    public final boolean b() {
        return TextUtils.isEmpty(this.a) && TextUtils.isEmpty(this.b);
    }

    public final String toString() {
        return "JDeviceSimInfo{imei='" + this.a + "', imsi='" + this.b + "', iccid='" + this.c + "'}";
    }
}
