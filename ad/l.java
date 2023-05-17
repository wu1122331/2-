package cn.jiguang.ad;

import android.content.Context;
import java.util.Set;
import org.json.JSONArray;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class l implements Runnable {
    final /* synthetic */ Object a;
    final /* synthetic */ Context b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public l(Object obj, Context context) {
        this.a = obj;
        this.b = context;
    }

    @Override // java.lang.Runnable
    public final void run() {
        JSONArray c;
        Set c2;
        try {
            c = k.c(this.a);
            if (c != null) {
                c2 = k.c(c);
                k.a(this.b, c, c2);
            } else {
                cn.jiguang.ac.d.b("ReportUtils", "data" + this.a + " is empty");
            }
        } catch (Throwable unused) {
        }
    }
}
