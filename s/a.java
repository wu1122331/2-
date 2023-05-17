package cn.jiguang.s;

import android.os.Bundle;
import java.nio.ByteBuffer;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class a {
    private static long a;

    static {
        cn.jiguang.sdk.impl.b.a("JCommon");
        cn.jiguang.sdk.impl.b.a("JLocation");
        cn.jiguang.sdk.impl.b.a("JArp");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:168:0x02f1  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x02f2 A[Catch: all -> 0x02fa, TRY_LEAVE, TryCatch #0 {all -> 0x02fa, blocks: (B:4:0x000d, B:6:0x0013, B:8:0x0019, B:10:0x001f, B:11:0x002a, B:38:0x007f, B:77:0x0158, B:79:0x015c, B:82:0x0163, B:86:0x016d, B:118:0x01ca, B:119:0x01d5, B:120:0x01de, B:121:0x01f2, B:123:0x01f8, B:125:0x0230, B:126:0x0242, B:140:0x026d, B:142:0x0271, B:143:0x0279, B:144:0x0281, B:145:0x0289, B:146:0x028d, B:147:0x0295, B:148:0x029d, B:149:0x02a5, B:151:0x02ab, B:152:0x02b3, B:153:0x02bb, B:154:0x02c4, B:89:0x0177, B:92:0x0181, B:95:0x018b, B:98:0x0193, B:101:0x019d, B:104:0x01a7, B:107:0x01b1, B:155:0x02cf, B:157:0x02d3, B:160:0x02da, B:169:0x02f2, B:163:0x02e4, B:40:0x0084, B:42:0x008e, B:44:0x0094, B:46:0x009a, B:47:0x009e, B:49:0x00a4, B:51:0x00b2, B:54:0x00b7, B:55:0x00c0, B:56:0x00c9, B:58:0x00d2, B:60:0x00d9, B:63:0x00ec, B:65:0x00f5, B:67:0x00fe, B:69:0x010b, B:70:0x0112, B:72:0x0116, B:76:0x0124, B:13:0x002e, B:16:0x0038, B:19:0x0042, B:22:0x004c, B:25:0x0056, B:28:0x0060, B:31:0x006a, B:34:0x0074), top: B:175:0x000b }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Object a(android.content.Context r16, java.lang.String r17, java.lang.Object r18) {
        /*
            Method dump skipped, instructions count: 886
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.s.a.a(android.content.Context, java.lang.String, java.lang.Object):java.lang.Object");
    }

    private static JSONObject a(Bundle bundle) {
        try {
            ByteBuffer wrap = ByteBuffer.wrap(bundle.getByteArray("RESPONSE_BODY"));
            a = wrap.getLong();
            byte[] bArr = new byte[wrap.getShort()];
            wrap.get(bArr);
            String str = new String(bArr, "UTF-8");
            cn.jiguang.ai.a.c("JCommonActionHelper", "parseBundle2Json content: " + str);
            return new JSONObject(str);
        } catch (Exception e) {
            cn.jiguang.ai.a.g("JCommonActionHelper", "parseBundle2Json exception:" + e.getMessage());
            return null;
        }
    }
}
