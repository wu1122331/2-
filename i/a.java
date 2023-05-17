package cn.jiguang.i;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class a {
    public String a;
    public String b;
    public String c;
    public int d;
    public int e;

    public final JSONObject a(int i) {
        try {
            JSONObject put = new JSONObject().put("name", this.a);
            CharSequence charSequence = this.b;
            if (128 < charSequence.length()) {
                charSequence = charSequence.subSequence(0, 128);
            }
            return put.put("pkg", charSequence).put("ver_name", this.c).put("ver_code", this.d).put("install_type", this.e);
        } catch (JSONException unused) {
            return null;
        }
    }
}
