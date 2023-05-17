package cn.jiguang.x;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import cn.jiguang.f.f;
import cn.jiguang.f.i;
import com.facebook.internal.ServerProtocol;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class a extends cn.jiguang.f.a {
    private static volatile a b;
    private Context a;
    private Bundle c;
    private String d = "";
    private int e = 0;
    private int f = 0;

    private static JSONObject a(String str, int i, int i2) {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject2 = new JSONObject();
            jSONArray.put(i);
            jSONArray.put(i2);
            jSONObject2.put(str, jSONArray);
            jSONObject.put("type", "sdk_type");
            jSONObject.put(ServerProtocol.DIALOG_PARAM_SDK_VERSION, jSONObject2);
            return jSONObject;
        } catch (JSONException e) {
            cn.jiguang.ai.a.g("JType", "package json exception: " + e.getMessage());
            return null;
        }
    }

    public static a d() {
        if (b == null) {
            synchronized (a.class) {
                b = new a();
            }
        }
        return b;
    }

    @Override // cn.jiguang.f.a
    protected final void a(String str, Bundle bundle) {
        this.c = bundle;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // cn.jiguang.f.a
    public final void c(Context context, String str) {
    }

    @Override // cn.jiguang.f.a
    protected final boolean c() {
        Bundle bundle = this.c;
        boolean z = false;
        if (bundle == null) {
            return false;
        }
        this.d = bundle.getString("name");
        this.e = this.c.getInt("custom", 0);
        this.f = this.c.getInt("dynamic", 0);
        cn.jiguang.ai.a.c("JType", "parseBundle type:" + this.d + ",custom:" + this.e + ",dynamic:" + this.f);
        Context context = this.a;
        String str = this.d;
        int i = this.e;
        int i2 = this.f;
        if (!TextUtils.isEmpty(str) && i >= 0 && i2 >= 0 && !f.j(context, str).equals(i + "," + i2)) {
            z = true;
        }
        if (z) {
            f.a(this.a, this.d, this.e + "," + this.f);
        } else {
            cn.jiguang.ai.a.c("JType", "type [" + this.d + "] data not change");
        }
        return z;
    }

    @Override // cn.jiguang.f.a
    protected final String d(Context context) {
        this.a = context;
        return "JType";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // cn.jiguang.f.a
    public final void d(Context context, String str) {
        JSONObject a = a(this.d, this.e, this.f);
        if (a == null) {
            cn.jiguang.ai.a.g("JType", "there are no data to report");
        } else {
            i.a(context, (Object) a);
        }
    }
}
