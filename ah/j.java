package cn.jiguang.ah;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import androidx.core.view.PointerIconCompat;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class j extends cn.jiguang.ao.a {
    final /* synthetic */ i a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public j(i iVar) {
        this.a = iVar;
    }

    @Override // cn.jiguang.ao.a
    public final void a(Message message) {
        Context context;
        Context context2;
        Context context3;
        Context context4;
        Context context5;
        Context context6;
        if (message != null) {
            int i = message.what;
            if (i == 1011) {
                i iVar = this.a;
                context = iVar.k;
                iVar.a(context, "tcp_a14", null);
            } else if (i == 1022) {
                i iVar2 = this.a;
                context2 = iVar2.k;
                iVar2.a(context2, "tcp_a17", null);
            } else if (i == 2000) {
                q a = q.a();
                context3 = this.a.k;
                a.a(context3);
            } else {
                switch (i) {
                    case PointerIconCompat.TYPE_WAIT /* 1004 */:
                        Bundle bundle = new Bundle();
                        bundle.putBoolean("force", true);
                        i iVar3 = this.a;
                        context4 = iVar3.k;
                        iVar3.a(context4, "tcp_a16", bundle);
                        return;
                    case 1005:
                        Bundle bundle2 = new Bundle();
                        bundle2.putBoolean("force", false);
                        i iVar4 = this.a;
                        context5 = iVar4.k;
                        iVar4.a(context5, "tcp_a16", bundle2);
                        return;
                    case PointerIconCompat.TYPE_CELL /* 1006 */:
                    case PointerIconCompat.TYPE_CROSSHAIR /* 1007 */:
                        i iVar5 = this.a;
                        context6 = iVar5.k;
                        iVar5.a(context6, "tcp_a2", null);
                        return;
                    default:
                        return;
                }
            }
        }
    }
}
