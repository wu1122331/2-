package cn.jiguang.i;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class b {
    public String a;
    public String b;
    public String c;
    public String d;

    public final JSONObject a(int i) {
        try {
            JSONObject put = new JSONObject().put("uid", this.a).put("pid", this.b).put("ppid", this.c);
            CharSequence charSequence = this.d;
            if (128 < charSequence.length()) {
                charSequence = charSequence.subSequence(0, 128);
            }
            return put.put("proc_name", charSequence);
        } catch (JSONException unused) {
            return null;
        }
    }
}
