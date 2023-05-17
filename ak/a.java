package cn.jiguang.ak;

import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public final class a {
    public static Pair<c, ByteBuffer> a(Context context, byte[] bArr, String str) {
        ByteBuffer wrap;
        if (bArr.length < 20) {
            cn.jiguang.ai.a.h("JCommands", "Error: received body-length short than common head-length, return null");
            return null;
        }
        byte[] bArr2 = new byte[20];
        System.arraycopy(bArr, 0, bArr2, 0, 20);
        c cVar = new c(false, bArr2);
        cn.jiguang.ai.a.c("JCommands", "parsed head - " + cVar.toString());
        int i = cVar.a - 20;
        if (i < 0) {
            cn.jiguang.ai.a.h("JCommands", "Error: totalBytes length error with no encrypted, return null");
            return null;
        }
        cn.jiguang.ai.a.a("JCommands", "body size:" + i);
        if (TextUtils.isEmpty(str)) {
            str = cn.jiguang.al.a.a(context);
        }
        byte[] bArr3 = new byte[i];
        System.arraycopy(bArr, 20, bArr3, 0, i);
        cn.jiguang.ai.a.c("JCommands", "decode algorithm:" + ((int) cVar.d) + ", pwd:" + str);
        if (TextUtils.isEmpty(str)) {
            System.arraycopy(bArr, 20, bArr3, 0, i);
            wrap = ByteBuffer.wrap(bArr3);
        } else {
            try {
                wrap = ByteBuffer.wrap(cVar.d == 2 ? new cn.jiguang.al.b().b(bArr3, cn.jiguang.al.b.a(str), str.substring(0, 16).getBytes("utf-8")) : cn.jiguang.al.a.b(str, bArr3));
            } catch (Exception e) {
                cn.jiguang.ai.a.c("JCommands", "decryptBytes error:" + e.getMessage());
                return null;
            }
        }
        return new Pair<>(cVar, wrap);
    }

    public static boolean a(Context context, byte[] bArr) {
        try {
            Pair<c, ByteBuffer> a = a(context, bArr, "");
            if (a != null) {
                long e = cn.jiguang.sdk.impl.b.e(context);
                long j = ((c) a.first).g;
                cn.jiguang.ai.a.c("JCommands", "uid:" + e + ",msgUid:" + j + ",cmd:" + ((c) a.first).c);
                if (e == 0 || j == 0 || e == j) {
                    cn.jiguang.ah.b.a();
                    cn.jiguang.ah.b.a(context, (c) a.first, (ByteBuffer) a.second);
                    return true;
                }
                cn.jiguang.ai.a.c("JCommands", "recv other app msg");
                cn.jiguang.am.a.a().a(context, j, bArr);
                return true;
            }
            return false;
        } catch (Throwable th) {
            cn.jiguang.ai.a.h("JCommands", "dispatchMessage error:" + th.getMessage());
            th.printStackTrace();
            return false;
        }
    }
}
