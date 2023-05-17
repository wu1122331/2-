package cn.jiguang.n;

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
        if (i != 0) {
            return;
        }
        f.e(this.a, this.b);
        str = this.c.d;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Context context = this.a;
        str2 = this.c.d;
        f.o(context, str2);
        super/*cn.jiguang.f.a*/.d(this.a, this.b);
    }
}
