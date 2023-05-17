package cn.jiguang.ad;

import android.content.Context;
import java.io.File;
import java.util.Set;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class m implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ Set b;
    final /* synthetic */ JSONObject c;
    final /* synthetic */ File d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public m(Context context, Set set, JSONObject jSONObject, File file) {
        this.a = context;
        this.b = set;
        this.c = jSONObject;
        this.d = file;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            k.a(this.a, this.b, this.c, this.d, null);
        } catch (Throwable unused) {
        }
    }
}
