package cn.jiguang.n;

import android.content.Context;
import android.text.TextUtils;
import com.facebook.internal.ServerProtocol;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class d extends cn.jiguang.f.a {
    private static volatile d c;
    private Context a;
    private boolean b;

    public static d d() {
        if (c == null) {
            synchronized (d.class) {
                c = new d();
            }
        }
        return c;
    }

    @Override // cn.jiguang.f.a
    protected final void a(JSONObject jSONObject) {
        try {
            int optInt = jSONObject.optInt("cmd");
            JSONObject optJSONObject = jSONObject.optJSONObject("content");
            if (optInt != 51) {
                return;
            }
            this.b = optJSONObject.optBoolean("uploadnumber", false);
            String optString = optJSONObject.optString(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, "");
            String optString2 = optJSONObject.optString("app_id", "");
            String optString3 = optJSONObject.optString("app_secret", "");
            JSONArray optJSONArray = optJSONObject.optJSONArray("carriers");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                    if (jSONObject2 != null) {
                        String optString4 = jSONObject2.optString("carrier", "");
                        String optString5 = jSONObject2.optString("url", "");
                        if (!TextUtils.isEmpty(optString4) && !TextUtils.isEmpty(optString5)) {
                            int a = cn.jiguang.r.a.a(optString4);
                            if (!optString5.startsWith("http://")) {
                                optString5 = "http://" + optString5;
                            }
                            if (!optString5.endsWith("/")) {
                                optString5 = optString5 + "/";
                            }
                            if (a != -1) {
                                Context context = this.a;
                                if (a >= 0 && a < 3) {
                                    cn.jiguang.o.c.a(context, "number_url" + a, optString5);
                                }
                            }
                            cn.jiguang.ai.a.c("JDevicePhoneNumber", "carrier:" + optString4 + " url:" + optString5 + " providerIndex:" + a);
                        }
                    }
                }
            }
            if (!TextUtils.isEmpty(optString)) {
                cn.jiguang.o.c.a(this.a, "number_version", optString);
            }
            if (TextUtils.isEmpty(optString2)) {
                cn.jiguang.o.c.a(this.a, "number_appid", optString2);
            }
            if (TextUtils.isEmpty(optString3)) {
                cn.jiguang.o.c.a(this.a, "number_appsecret", optString3);
            }
        } catch (Throwable th) {
            cn.jiguang.ai.a.g("JDevicePhoneNumber", "parse throwable:" + th.getMessage());
        }
    }

    @Override // cn.jiguang.f.a
    protected final boolean b() {
        return this.b;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // cn.jiguang.f.a
    public final void c(Context context, String str) {
    }

    @Override // cn.jiguang.f.a
    protected final String d(Context context) {
        this.a = context;
        return "JDevicePhoneNumber";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // cn.jiguang.f.a
    public final void d(Context context, String str) {
        try {
            cn.jiguang.o.c.a(context, true);
            if (cn.jiguang.ap.a.i(context).toUpperCase().startsWith(com.aliyun.security.yunceng.android.sdk.traceroute.d.c)) {
                cn.jiguang.ai.a.g("JDevicePhoneNumber", "collect failed because current networkType is  wifi");
            } else {
                new cn.jiguang.r.d(context).a();
            }
        } catch (Throwable th) {
            cn.jiguang.ai.a.g("JDevicePhoneNumber", "report throwable:" + th.getMessage());
        }
    }
}
