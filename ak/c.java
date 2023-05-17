package cn.jiguang.ak;

import java.nio.ByteBuffer;
import kotlin.jvm.internal.ShortCompanionObject;

/* loaded from: classes.dex */
public final class c {
    public int a;
    public int b;
    public int c;
    public byte d;
    public long e;
    public int f;
    public long g;
    private boolean h;

    public c(boolean z, byte[] bArr) {
        this.h = false;
        try {
            this.h = false;
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            short s = wrap.getShort();
            this.a = s;
            this.a = s & ShortCompanionObject.MAX_VALUE;
            this.b = wrap.get();
            this.c = wrap.get();
            this.d = wrap.get();
            wrap.get();
            wrap.getInt();
            this.e = wrap.getShort();
            this.g = wrap.getLong();
        } catch (Throwable unused) {
        }
    }

    public final String toString() {
        return "[JHead] - len:" + this.a + ", version:" + this.b + ", command:" + this.c + ", rid:" + this.e + (this.h ? ", sid:" + this.f : "") + ", juid:" + this.g;
    }
}
