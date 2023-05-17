package cn.jiguang.af;

import android.text.TextUtils;
import java.util.LinkedHashSet;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class n {
    LinkedHashSet<h> a;
    LinkedHashSet<h> b;
    LinkedHashSet<h> c;
    LinkedHashSet<h> d;
    LinkedHashSet<h> e;
    JSONObject f;
    transient h g;
    boolean h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public n(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            JSONObject jSONObject = new JSONObject(str);
            this.a = a(jSONObject, "ips");
            this.b = a(jSONObject, "ssl_ips");
            this.c = a(jSONObject, "http_report");
            this.d = a(jSONObject, "https_report");
            this.e = a(jSONObject, "sis_ips");
            this.h = jSONObject.optBoolean("data_report");
            this.f = jSONObject.optJSONObject("tcp_report");
            cn.jiguang.ai.a.c("sis", "get sis=" + jSONObject.toString(2));
        } catch (Throwable unused) {
        }
    }

    private static LinkedHashSet<h> a(JSONObject jSONObject, String str) {
        LinkedHashSet<h> linkedHashSet = new LinkedHashSet<>();
        try {
            JSONArray optJSONArray = jSONObject.optJSONArray(str);
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    h a = h.a(optJSONArray.optString(i, null));
                    if (a != null && a.a()) {
                        linkedHashSet.add(a);
                    }
                }
            }
        } catch (Throwable unused) {
        }
        return linkedHashSet;
    }

    public final boolean a() {
        LinkedHashSet<h> linkedHashSet = this.a;
        if (linkedHashSet == null || linkedHashSet.isEmpty()) {
            LinkedHashSet<h> linkedHashSet2 = this.b;
            return linkedHashSet2 == null || linkedHashSet2.isEmpty();
        }
        return false;
    }
}
