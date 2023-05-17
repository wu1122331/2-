package cn.jiguang.aq;

import cn.jiguang.api.utils.ProtocolUtil;
import java.nio.ByteBuffer;
import kotlin.jvm.internal.ShortCompanionObject;

/* loaded from: classes.dex */
public final class c {
    int a;
    int b;
    int c;
    Long d;
    int e;
    long f;
    private boolean g;

    public c(boolean z, int i, int i2, int i3, long j, int i4, long j2) {
        this.g = false;
        this.g = z;
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = Long.valueOf(j);
        this.e = i4;
        this.f = j2;
    }

    public c(boolean z, int i, int i2, long j) {
        this(z, 0, i, i2, j, 0, 0L);
    }

    public c(boolean z, byte[] bArr) {
        this.g = false;
        this.g = z;
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        short s = wrap.getShort();
        this.a = s;
        this.a = s & ShortCompanionObject.MAX_VALUE;
        this.b = wrap.get();
        this.c = wrap.get();
        Long valueOf = Long.valueOf(wrap.getLong());
        this.d = valueOf;
        this.d = Long.valueOf(valueOf.longValue() & 65535);
        if (z) {
            this.e = wrap.getInt();
        }
        this.f = wrap.getLong();
    }

    public final int a() {
        return this.c;
    }

    public final void a(int i) {
        this.a = i;
    }

    public final void a(long j) {
        this.f = j;
    }

    public final Long b() {
        return this.d;
    }

    public final void b(int i) {
        this.e = i;
    }

    public final long c() {
        return this.f;
    }

    public final int d() {
        return this.e;
    }

    public final int e() {
        return this.b;
    }

    public final byte[] f() {
        if (this.a != 0) {
            ByteBuffer allocate = ByteBuffer.allocate(24);
            allocate.putShort((short) this.a);
            allocate.put((byte) this.b);
            allocate.put((byte) this.c);
            allocate.putLong(this.d.longValue());
            if (this.g) {
                allocate.putInt(this.e);
            }
            allocate.putLong(this.f);
            allocate.flip();
            return ProtocolUtil.getBytesConsumed(allocate);
        }
        throw new IllegalStateException("The head is not initialized yet.");
    }

    public final String toString() {
        return "[JHead] - len:" + this.a + ", version:" + this.b + ", command:" + this.c + ", rid:" + this.d + (this.g ? ", sid:" + this.e : "") + ", juid:" + this.f;
    }
}
