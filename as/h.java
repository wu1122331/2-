package cn.jiguang.as;

import java.util.Iterator;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class h {
    public static String a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return "null";
        }
        try {
            return jSONObject.toString(2);
        } catch (JSONException unused) {
            return jSONObject.toString();
        }
    }

    public static JSONObject a(JSONObject jSONObject, Set<String> set) {
        JSONObject jSONObject2 = new JSONObject();
        if (set != null && !set.isEmpty()) {
            for (String str : set) {
                try {
                    jSONObject2.put(str, jSONObject.opt(str));
                } catch (JSONException unused) {
                }
            }
        }
        return jSONObject2;
    }

    public static void a(JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject2 == null || jSONObject2.length() == 0 || jSONObject == null) {
            return;
        }
        Iterator<String> keys = jSONObject2.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            try {
                jSONObject.put(next, jSONObject2.get(next));
            } catch (JSONException unused) {
            }
        }
    }

    public static int b(JSONObject jSONObject) {
        if (jSONObject != null && jSONObject.length() != 0) {
            try {
                return j.h(jSONObject.toString()).length;
            } catch (Throwable unused) {
            }
        }
        return 0;
    }
}
