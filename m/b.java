package cn.jiguang.m;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import cn.jiguang.s.d;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class b {
    HandlerThread a;
    Handler b;
    private int c = 300;
    private String d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(String str, int i) {
        this.d = str;
        HandlerThread handlerThread = new HandlerThread("ping timer");
        this.a = handlerThread;
        handlerThread.start();
        this.b = new Handler(this.a.getLooper(), new c(this));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(byte[] bArr, int i, int i2) {
        byte[] bArr2 = {bArr[0], bArr[1], bArr[2], 0};
        Thread currentThread = Thread.currentThread();
        for (int i3 = 0; i3 < 255; i3++) {
            bArr2[3] = (byte) i3;
            if (bArr2[3] != bArr[3]) {
                String a = a.a(bArr2);
                if (!a.equalsIgnoreCase(this.d)) {
                    this.b.removeCallbacksAndMessages(null);
                    Message obtainMessage = this.b.obtainMessage(1);
                    obtainMessage.obj = currentThread;
                    Bundle bundle = new Bundle();
                    bundle.putString("ip", a);
                    obtainMessage.setData(bundle);
                    this.b.sendMessageDelayed(obtainMessage, this.c);
                    d.a(new String[]{"ping -c 1 -w 1 " + a}, 0);
                }
            }
        }
        this.a.quit();
    }
}
