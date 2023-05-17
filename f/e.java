package cn.jiguang.f;

import android.content.Context;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class e implements Runnable {
    final /* synthetic */ a a;
    private Context b;
    private String c;
    private JSONObject d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(a aVar, Context context, String str, JSONObject jSONObject) {
        this.a = aVar;
        this.b = context;
        this.c = str;
        this.d = jSONObject;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            a.a(this.a, this.b, this.c, this.d);
        } catch (Throwable unused) {
        }
    }
}
