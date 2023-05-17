package cn.jiguang.ak;

import cn.jiguang.api.JCoreManager;
import cn.jiguang.api.utils.ByteBufferUtils;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public final class d {
    public int a;
    public int b;
    public int c;
    public int d;
    private final c e;
    private ByteBuffer f;
    private int g;
    private String h;
    private String i;

    public d(c cVar, ByteBuffer byteBuffer) {
        String str;
        this.e = cVar;
        if (byteBuffer != null) {
            this.f = byteBuffer;
            try {
                this.a = byteBuffer.getShort();
            } catch (Throwable unused) {
                this.a = ByteBufferUtils.ERROR_CODE;
            }
            if (this.a > 0) {
                cn.jiguang.ai.a.i("LoginResponse", "Response error - code:" + this.a);
            }
            ByteBuffer byteBuffer2 = this.f;
            this.d = -1;
            int i = this.a;
            if (i != 0) {
                if (i == 1012) {
                    try {
                        this.i = b.a(byteBuffer2);
                    } catch (Throwable unused2) {
                        this.a = ByteBufferUtils.ERROR_CODE;
                    }
                    cn.jiguang.ae.a.a(JCoreManager.getAppContext(null), this.i);
                    return;
                }
                return;
            }
            try {
                this.b = byteBuffer2.getInt();
                this.g = byteBuffer2.getShort();
                this.h = b.a(byteBuffer2);
                this.c = byteBuffer2.getInt();
            } catch (Throwable unused3) {
                this.a = ByteBufferUtils.ERROR_CODE;
            }
            try {
                this.d = byteBuffer2.get();
                cn.jiguang.ai.a.c("LoginResponse", "idc parse success, value:" + this.d);
                return;
            } catch (Throwable th) {
                str = "parse idc failed, error:" + th;
            }
        } else {
            str = "No body to parse.";
        }
        cn.jiguang.ai.a.g("LoginResponse", str);
    }

    public final String toString() {
        return "[LoginResponse] - code:" + this.a + ",sid:" + this.b + ", serverVersion:" + this.g + ", sessionKey:" + this.h + ", serverTime:" + this.c + ", idc:" + this.d + ", connectInfo:" + this.i;
    }
}
