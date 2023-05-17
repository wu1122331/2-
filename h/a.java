package cn.jiguang.h;

import android.content.Context;
import android.text.TextUtils;
import cn.jiguang.f.i;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class a extends cn.jiguang.f.a {
    private static volatile a c;
    private Context a;
    private List<cn.jiguang.i.a> b;

    public static a d() {
        if (c == null) {
            synchronized (a.class) {
                c = new a();
            }
        }
        return c;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // cn.jiguang.f.a
    public final boolean a() {
        cn.jiguang.ai.a.c("JAppAll", "for googlePlay:false");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // cn.jiguang.f.a
    public final void c(Context context, String str) {
        List<cn.jiguang.i.a> a = cn.jiguang.j.a.a(context, true);
        this.b = a;
        if (a == null || a.isEmpty()) {
            cn.jiguang.ai.a.g("JAppAll", "collect failed, installedAppList is empty");
            return;
        }
        cn.jiguang.ai.a.c("JAppAll", "collect success");
        String a2 = cn.jiguang.j.a.a(this.b);
        if (TextUtils.isEmpty(a2)) {
            return;
        }
        cn.jiguang.ai.a.c("JAppAll", "save appList [" + a2 + "]");
        if (context != null && !TextUtils.isEmpty("bal.catch")) {
            try {
                synchronized ("bal.catch") {
                    File a3 = i.a(context, "bal.catch");
                    if (a3 != null) {
                        cn.jiguang.ap.d.a(a3, "");
                    }
                }
            } catch (Throwable th) {
                cn.jiguang.ai.a.g("JCommonFileHelper", "cleanString throwable:" + th.getMessage());
            }
        }
        cn.jiguang.s.b.a(context, "bal.catch", a2);
    }

    @Override // cn.jiguang.f.a
    protected final String d(Context context) {
        this.a = context;
        return "JAppAll";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // cn.jiguang.f.a
    public final void d(Context context, String str) {
        List<cn.jiguang.i.a> list;
        ArrayList<JSONArray> a;
        try {
            list = this.b;
        } catch (JSONException e) {
            cn.jiguang.ai.a.g("JAppAll", "package json exception:" + e.getMessage());
        }
        if (list != null && !list.isEmpty()) {
            List<cn.jiguang.i.a> list2 = this.b;
            JSONArray jSONArray = new JSONArray();
            for (cn.jiguang.i.a aVar : list2) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("name", aVar.a);
                jSONObject.put("pkg", aVar.b);
                jSONObject.put("ver_name", aVar.c);
                jSONObject.put("ver_code", aVar.d);
                jSONObject.put("install_type", aVar.e);
                jSONArray.put(jSONObject);
            }
            if (jSONArray.length() != 0 && (a = cn.jiguang.j.a.a(jSONArray)) != null && !a.isEmpty()) {
                int i = 0;
                int size = a.size();
                while (i < size) {
                    JSONObject jSONObject2 = new JSONObject();
                    i++;
                    jSONObject2.put("slice_index", i);
                    jSONObject2.put("slice_count", size);
                    jSONObject2.put("data", a.get(i));
                    i.a(context, jSONObject2, "app_list");
                    i.a(context, (Object) jSONObject2);
                    super.d(context, str);
                }
                this.b = null;
                return;
            }
            return;
        }
        cn.jiguang.ai.a.g("JAppAll", "there are no data to report");
    }
}
