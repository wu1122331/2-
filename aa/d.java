package cn.jiguang.aa;

import android.content.Context;
import android.text.TextUtils;
import com.facebook.GraphResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class d {
    public static List<cn.jiguang.z.c> a(String str, String str2, String str3, int i) {
        List<cn.jiguang.z.c> list;
        cn.jiguang.z.c cVar = new cn.jiguang.z.c();
        cVar.a = str2;
        cVar.b = str3;
        cVar.c = i;
        if (str == null || TextUtils.isEmpty(str)) {
            list = null;
        } else {
            list = c.a(str);
            cn.jiguang.ai.a.c("JWakePackageHelper", "cache cmd wakeTargets:" + list);
        }
        boolean z = true;
        if (list == null) {
            list = new ArrayList<>();
        } else {
            for (cn.jiguang.z.c cVar2 : list) {
                if (cVar2.a.equals(str2)) {
                    cVar2.b = str3;
                    cVar2.c = i;
                    z = false;
                }
            }
        }
        if (z) {
            list.add(cVar);
        }
        return list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static JSONArray a(Context context, JSONArray jSONArray) {
        HashMap hashMap;
        Integer valueOf;
        int i;
        if (jSONArray != null) {
            try {
                if (jSONArray.length() > 0) {
                    HashMap hashMap2 = new HashMap();
                    HashMap hashMap3 = new HashMap();
                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                        JSONObject jSONObject = jSONArray.getJSONObject(i2);
                        String optString = jSONObject.optString("type");
                        if ("android_awake2".equals(optString)) {
                            JSONArray optJSONArray = jSONObject.optJSONArray("target");
                            if (optJSONArray == null || optJSONArray.length() <= 0) {
                                break;
                            }
                            for (int i3 = 0; i3 < optJSONArray.length(); i3++) {
                                JSONObject optJSONObject = optJSONArray.optJSONObject(i3);
                                String optString2 = optJSONObject.optString("package");
                                int optInt = optJSONObject.optInt("count");
                                hashMap2.put(optString2, hashMap2.containsKey(optString2) ? Integer.valueOf(((Integer) hashMap2.get(optString2)).intValue() + optInt) : Integer.valueOf(optInt));
                            }
                        } else if ("android_awake_target2".equals(optString)) {
                            String optString3 = jSONObject.optString("package");
                            String str = jSONObject.optBoolean("app_alive") ? "active" : "dead";
                            int optInt2 = jSONObject.optInt("wake_type");
                            if (hashMap3.containsKey(optString3)) {
                                Map map = (Map) hashMap3.get(optString3);
                                if (map.containsKey(str)) {
                                    Map map2 = (Map) map.get(str);
                                    if (map2.containsKey(Integer.valueOf(optInt2))) {
                                        i = Integer.valueOf(((Integer) map2.get(Integer.valueOf(optInt2))).intValue() + 1);
                                        valueOf = Integer.valueOf(optInt2);
                                    } else {
                                        valueOf = Integer.valueOf(optInt2);
                                        i = 1;
                                    }
                                    map2.put(valueOf, i);
                                    hashMap = map2;
                                } else {
                                    HashMap hashMap4 = new HashMap();
                                    hashMap4.put(Integer.valueOf(optInt2), 1);
                                    hashMap = hashMap4;
                                }
                                map.put(str, hashMap);
                                hashMap3.put(optString3, map);
                            } else {
                                HashMap hashMap5 = new HashMap();
                                hashMap5.put(Integer.valueOf(optInt2), 1);
                                HashMap hashMap6 = new HashMap();
                                hashMap6.put(str, hashMap5);
                                hashMap3.put(optString3, hashMap6);
                            }
                        } else {
                            cn.jiguang.ai.a.g("JWakePackageHelper", "unkown type :" + optString);
                        }
                    }
                    JSONArray jSONArray2 = new JSONArray();
                    JSONArray jSONArray3 = new JSONArray();
                    for (Map.Entry entry : hashMap2.entrySet()) {
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("package", entry.getKey());
                        jSONObject2.put("count", entry.getValue());
                        jSONArray3.put(jSONObject2);
                    }
                    if (jSONArray3.length() > 0) {
                        JSONObject jSONObject3 = new JSONObject();
                        jSONObject3.put("target", jSONArray3);
                        jSONObject3.put("itime", cn.jiguang.sdk.impl.b.j(context));
                        jSONObject3.put("type", "android_awake2");
                        jSONArray2.put(jSONObject3);
                    }
                    JSONObject jSONObject4 = new JSONObject();
                    JSONArray jSONArray4 = new JSONArray();
                    Iterator it = hashMap3.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry entry2 = (Map.Entry) it.next();
                        JSONObject jSONObject5 = new JSONObject();
                        jSONObject5.put("package", entry2.getKey());
                        Iterator it2 = ((Map) entry2.getValue()).entrySet().iterator();
                        while (it2.hasNext()) {
                            JSONArray jSONArray5 = new JSONArray();
                            Map.Entry entry3 = (Map.Entry) it2.next();
                            for (Map.Entry entry4 : ((Map) entry3.getValue()).entrySet()) {
                                Iterator it3 = it;
                                JSONObject jSONObject6 = new JSONObject();
                                jSONObject6.put("wake_type", entry4.getKey());
                                jSONObject6.put("count", entry4.getValue());
                                jSONArray5.put(jSONObject6);
                                it = it3;
                                it2 = it2;
                            }
                            jSONObject5.put((String) entry3.getKey(), jSONArray5);
                            it = it;
                            it2 = it2;
                        }
                        jSONArray4.put(jSONObject5);
                        it = it;
                    }
                    if (jSONArray4.length() > 0) {
                        jSONObject4.put("from", jSONArray4);
                        jSONObject4.put("itime", cn.jiguang.sdk.impl.b.j(context));
                        jSONObject4.put("type", "android_awake_target2");
                        jSONArray2.put(jSONObject4);
                    }
                    if (jSONArray2.length() > 0) {
                        return jSONArray2;
                    }
                }
            } catch (Throwable th) {
                cn.jiguang.ai.a.h("JWakePackageHelper", "merge wake json failed:" + th.getMessage());
            }
        }
        return jSONArray;
    }

    public static JSONObject a(Context context, List<cn.jiguang.z.b> list) {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            boolean z = false;
            for (cn.jiguang.z.b bVar : list) {
                JSONObject jSONObject2 = new JSONObject();
                HashMap<Integer, Boolean> hashMap = bVar.b;
                for (Integer num : hashMap.keySet()) {
                    if (hashMap.get(Integer.valueOf(num.intValue())).booleanValue()) {
                        z = true;
                    }
                }
                jSONObject2.put("awake_from", context.getApplicationContext().getPackageName());
                jSONObject2.put("awake_to", bVar.a.getPackageName());
                jSONObject2.put("awake_class", bVar.a.getClassName());
                jSONObject2.put("awake_count", 1);
                jSONObject2.put(GraphResponse.SUCCESS_KEY, z);
                jSONArray.put(jSONObject2);
            }
            jSONObject.put("awake_path", jSONArray);
            return jSONObject;
        } catch (JSONException e) {
            cn.jiguang.ai.a.g("JWakePackageHelper", "package cmd report json exception:" + e.getMessage());
            return null;
        }
    }

    public static JSONObject a(String str, int i, boolean z) {
        if (str == null) {
            str = "";
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("wake_type", i);
            jSONObject.put("package", str);
            jSONObject.put("app_alive", z);
            return jSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject a(List<cn.jiguang.z.b> list) {
        if (list == null || list.size() == 0) {
            cn.jiguang.ai.a.c("JWakePackageHelper", "wakeUpResult is empty, no need report");
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (cn.jiguang.z.b bVar : list) {
            try {
                if (bVar.b != null && !bVar.b.isEmpty()) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("package", bVar.a.getPackageName());
                    jSONObject.put("count", bVar.b.size());
                    jSONArray.put(jSONObject);
                }
            } catch (Throwable th) {
                cn.jiguang.ai.a.i("JWakePackageHelper", "formatReportData:" + th);
            }
        }
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("target", jSONArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject2;
    }

    public static void a(Context context, String str, JSONObject jSONObject) {
        JSONObject a;
        if (jSONObject == null) {
            return;
        }
        try {
            cn.jiguang.ai.a.c("JWakePackageHelper", "save wake data:" + jSONObject.toString());
            jSONObject.put("itime", cn.jiguang.sdk.impl.b.j(context));
            jSONObject.put("type", str);
            synchronized ("wakeup_cache_v2.json") {
                a = cn.jiguang.s.b.a(context, "wakeup_cache_v2.json");
            }
            if (a == null) {
                a = new JSONObject();
            }
            JSONArray optJSONArray = a.optJSONArray("content");
            if (optJSONArray == null) {
                optJSONArray = new JSONArray();
            }
            try {
                optJSONArray.put(jSONObject);
                a.put("content", optJSONArray);
                synchronized ("wakeup_cache_v2.json") {
                    cn.jiguang.s.b.a(context, "wakeup_cache_v2.json", a);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (Throwable th) {
            cn.jiguang.ai.a.g("JWakePackageHelper", "saveReportData failed:" + th.getMessage());
        }
    }
}
