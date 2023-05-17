package cn.jiguang.ak;

import cn.jiguang.api.JCoreManager;
import cn.jiguang.api.utils.ByteBufferUtils;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public final class e {
    public int a;
    public long b;
    public String c;
    public String d;
    public String e;
    private final c f;
    private ByteBuffer g;
    private String h;
    private String i;

    public e(c cVar, ByteBuffer byteBuffer) {
        this.f = cVar;
        if (byteBuffer == null) {
            cn.jiguang.ai.a.g("RegisterResponse", "No body to parse.");
            return;
        }
        this.g = byteBuffer;
        try {
            this.a = byteBuffer.getShort();
        } catch (Throwable unused) {
            this.a = ByteBufferUtils.ERROR_CODE;
        }
        if (this.a > 0) {
            cn.jiguang.ai.a.i("RegisterResponse", "Response error - code:" + this.a);
        }
        ByteBuffer byteBuffer2 = this.g;
        int i = this.a;
        try {
            if (i == 0) {
                this.b = byteBuffer2.getLong();
                this.c = b.a(byteBuffer2);
                this.d = b.a(byteBuffer2);
            } else if (i != 1007) {
                if (i == 1012) {
                    try {
                        this.i = b.a(byteBuffer2);
                    } catch (Throwable unused2) {
                        this.a = ByteBufferUtils.ERROR_CODE;
                    }
                    cn.jiguang.ae.a.a(JCoreManager.getAppContext(null), this.i);
                }
            } else {
                this.h = b.a(byteBuffer2);
            }
        } catch (Throwable unused3) {
            this.a = ByteBufferUtils.ERROR_CODE;
        }
    }

    public final String toString() {
        return "[RegisterResponse] - code:" + this.a + ", juid:" + this.b + ", password:" + this.c + ", regId:" + this.d + ", deviceId:" + this.e + ", connectInfo:" + this.i;
    }
}
