package cn.jiguang.unity.push;

import java.util.LinkedHashSet;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class JsonUtil {
    public static Set<String> jsonToSet(String str) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        try {
            JSONArray jSONArray = new JSONObject(str).getJSONArray("Items");
            for (int i = 0; i < jSONArray.length(); i++) {
                linkedHashSet.add(jSONArray.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return linkedHashSet;
    }

    public static String setToJson(Set<String> set) {
        if (set == null || set.size() == 0) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        for (String str : set) {
            jSONArray.put(str);
        }
        try {
            jSONObject.put("Items", jSONArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }
}
