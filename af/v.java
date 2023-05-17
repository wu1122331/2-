package cn.jiguang.af;

import android.content.Context;

/* loaded from: classes.dex */
final class v implements Runnable {
    private byte[] a;
    private Context b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public v(Context context, byte[] bArr) {
        this.b = context;
        this.a = bArr;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            cn.jiguang.ah.i.a().a(this.b, "tcp_a22", null);
            cn.jiguang.ak.a.a(this.b, this.a);
        } catch (Throwable th) {
            cn.jiguang.ai.a.g("TcpRecvAction", "TcpRecvAction failed:" + th.getMessage());
        }
    }
}
