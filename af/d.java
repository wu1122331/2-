package cn.jiguang.af;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class d {
    final h a;
    int b;
    long c;
    long d;
    int e;

    public d(h hVar) {
        this.a = hVar;
    }

    public static d a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            d dVar = new d(new h(jSONObject.getString("ip"), jSONObject.getInt("port")));
            dVar.b = jSONObject.optInt("status");
            dVar.c = jSONObject.optLong("fetch_time");
            dVar.d = jSONObject.optLong("cost");
            dVar.e = jSONObject.optInt("prefer");
            return dVar;
        } catch (JSONException unused) {
            return null;
        }
    }

    public final String a() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("ip", this.a.a);
            jSONObject.put("port", this.a.b);
            jSONObject.put("status", this.b);
            jSONObject.put("fetch_time", this.c);
            jSONObject.put("cost", this.d);
            jSONObject.put("prefer", this.e);
            return jSONObject.toString();
        } catch (JSONException unused) {
            return null;
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof d) {
            d dVar = (d) obj;
            if (this.b == dVar.b && this.c == dVar.c && this.d == dVar.d && this.e == dVar.e) {
                h hVar = this.a;
                h hVar2 = dVar.a;
                return hVar != null ? hVar.equals(hVar2) : hVar2 == null;
            }
            return false;
        }
        return false;
    }

    public final int hashCode() {
        h hVar = this.a;
        int hashCode = hVar != null ? hVar.hashCode() : 0;
        long j = this.c;
        long j2 = this.d;
        return (((((((hashCode * 31) + this.b) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + this.e;
    }

    public final String toString() {
        return "IpInfo{ipPort=" + this.a + ", status=" + this.b + ", fetchTime=" + this.c + ", cost=" + this.d + ", prefer=" + this.e + '}';
    }
}
