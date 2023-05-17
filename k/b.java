package cn.jiguang.k;

import android.content.Context;
import android.text.TextUtils;
import cn.jiguang.api.ReportCallBack;
import cn.jiguang.f.f;

/* loaded from: classes.dex */
final class b implements ReportCallBack {
    final /* synthetic */ Context a;
    final /* synthetic */ String b;
    final /* synthetic */ a c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(a aVar, Context context, String str) {
        this.c = aVar;
        this.a = context;
        this.b = str;
    }

    @Override // cn.jiguang.api.ReportCallBack
    public final void onFinish(int i) {
        String str;
        String str2;
        String str3;
        str = this.c.d;
        if (TextUtils.isEmpty(str)) {
            this.c.d = "";
        }
        Context context = this.a;
        StringBuilder sb = new StringBuilder("JArp");
        str2 = this.c.d;
        f.e(context, sb.append(str2).toString());
        if (f.g(this.a, "JArp") == 0) {
            Context context2 = this.a;
            str3 = this.c.d;
            f.k(context2, str3);
        }
        super/*cn.jiguang.f.a*/.d(this.a, this.b);
    }
}
