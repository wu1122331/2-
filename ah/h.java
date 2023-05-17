package cn.jiguang.ah;

import android.content.Context;
import android.os.Message;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class h extends cn.jiguang.ao.a {
    final /* synthetic */ g a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public h(g gVar) {
        this.a = gVar;
    }

    @Override // cn.jiguang.ao.a
    public final void a(Message message) {
        Context context;
        cn.jiguang.ai.a.c("PeriodWorker", "time is up, next period=" + (cn.jiguang.sdk.impl.a.b * 1000));
        g gVar = this.a;
        context = gVar.c;
        gVar.c(context);
    }
}
