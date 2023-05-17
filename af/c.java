package cn.jiguang.af;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import androidx.core.view.ViewCompat;
import cn.jiguang.api.JCoreManager;
import cn.jiguang.api.utils.ByteBufferUtils;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteBuffer;
import java.util.Random;

/* loaded from: classes.dex */
public final class c {
    /* JADX WARN: Removed duplicated region for block: B:15:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00c3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int a(android.content.Context r20, cn.jiguang.an.a r21) {
        /*
            Method dump skipped, instructions count: 617
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.af.c.a(android.content.Context, cn.jiguang.an.a):int");
    }

    public static void a(Context context) {
        cn.jiguang.ae.c.a(context, cn.jiguang.ae.b.p().a((cn.jiguang.ae.b<String>) null), cn.jiguang.ae.b.q().a((cn.jiguang.ae.b<String>) null), cn.jiguang.ae.b.c(false).a((cn.jiguang.ae.b<String>) null), cn.jiguang.ae.b.c(true).a((cn.jiguang.ae.b<String>) null), cn.jiguang.ae.b.u().a((cn.jiguang.ae.b<String>) null), cn.jiguang.ae.b.r().a((cn.jiguang.ae.b<Boolean>) null), cn.jiguang.ae.b.s().a((cn.jiguang.ae.b<Long>) null), cn.jiguang.ae.b.a(true).a((cn.jiguang.ae.b<String>) null), cn.jiguang.ae.b.a(false).a((cn.jiguang.ae.b<String>) null), cn.jiguang.ae.b.b(true).a((cn.jiguang.ae.b<String>) null), cn.jiguang.ae.b.b(false).a((cn.jiguang.ae.b<String>) null));
        cn.jiguang.ae.c.a(context, cn.jiguang.ae.b.d().a((cn.jiguang.ae.b<Long>) null), cn.jiguang.ae.b.e().a((cn.jiguang.ae.b<String>) null), cn.jiguang.ae.b.f().a((cn.jiguang.ae.b<String>) null));
    }

    private static synchronized byte[] a(String str, int i, byte[] bArr, boolean z, int i2) {
        byte[] b;
        synchronized (c.class) {
            if (TextUtils.isEmpty(str) || str.length() != 2 || bArr == null || bArr.length == 0) {
                throw new IllegalArgumentException("flag or body length error");
            }
            cn.jiguang.ap.e eVar = new cn.jiguang.ap.e(300);
            eVar.b(0);
            eVar.a(str.getBytes());
            eVar.a(i);
            eVar.b(i2);
            eVar.a(bArr);
            eVar.b(eVar.a(), 0);
            eVar.a((int) ((byte) ((z ? 1 : 0) | 16)), 4);
            b = eVar.b();
        }
        return b;
    }

    public static byte[] a(String str, String str2) {
        byte[] j = cn.jiguang.ap.h.j(str2);
        boolean z = false;
        try {
            byte[] a = cn.jiguang.ap.k.a(j);
            if (a.length < j.length) {
                z = true;
                j = a;
            }
        } catch (IOException unused) {
        }
        int length = j.length;
        int b = cn.jiguang.al.a.b();
        return a(str, b, cn.jiguang.al.a.a(cn.jiguang.al.a.a(b), j), z, length);
    }

    public static byte[] a(DatagramSocket datagramSocket, DatagramPacket datagramPacket) {
        datagramSocket.setSoTimeout(6000);
        datagramSocket.send(datagramPacket);
        DatagramPacket datagramPacket2 = new DatagramPacket(new byte[1024], 1024);
        cn.jiguang.ai.a.d("ConnectingHelper", "udp Receiving...");
        datagramSocket.receive(datagramPacket2);
        int length = datagramPacket2.getLength();
        byte[] bArr = new byte[length];
        System.arraycopy(datagramPacket2.getData(), 0, bArr, 0, length);
        return bArr;
    }

    public static byte[] a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            throw new cn.jiguang.ah.f(4, "response is empty!");
        }
        try {
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            wrap.getShort();
            wrap.getShort();
            int i = wrap.getInt();
            int i2 = i >>> 24;
            long j = i & ViewCompat.MEASURED_SIZE_MASK;
            wrap.getShort();
            int remaining = wrap.remaining();
            byte[] bArr2 = new byte[remaining];
            wrap.get(bArr2, 0, remaining);
            if (j != 0) {
                try {
                    bArr2 = cn.jiguang.al.a.b(cn.jiguang.al.a.a(j), bArr2);
                    if (bArr2 == null) {
                        throw new cn.jiguang.ah.f(5, "decrypt response error");
                    }
                } catch (Exception unused) {
                    throw new cn.jiguang.ah.f(5, "decrypt response error");
                }
            }
            if ((i2 & 1) == 1) {
                try {
                    return cn.jiguang.ap.k.b(bArr2);
                } catch (IOException unused2) {
                    return bArr2;
                }
            }
            return bArr2;
        } catch (Throwable th) {
            throw new cn.jiguang.ah.f(4, "parse head error:" + th.getMessage());
        }
    }

    public static int b(Context context, cn.jiguang.an.a aVar) {
        String str = b(cn.jiguang.ap.j.a(context), " ") + "$$" + b(cn.jiguang.ap.c.a(context).p, " ") + "$$" + context.getPackageName() + "$$" + cn.jiguang.sdk.impl.b.i(context);
        String str2 = cn.jiguang.ap.c.a(context).a;
        cn.jiguang.ah.b.a();
        String d = cn.jiguang.ah.b.d();
        cn.jiguang.ai.a.c("ConnectingHelper", "regVersion:" + d);
        cn.jiguang.ap.c a = cn.jiguang.ap.c.a(context);
        String str3 = b(a.b, " ") + "$$" + b(a.c, " ") + "$$" + b(a.d, " ") + "$$" + b(a.e, " ") + "$$" + b(cn.jiguang.sdk.impl.b.d(context), " ") + "$$" + d + "$$" + a.g + "$$" + a.h;
        String str4 = cn.jiguang.ap.c.a(context).j;
        if ("unknown".equalsIgnoreCase(str4)) {
            str4 = " ";
        }
        String str5 = cn.jiguang.sdk.impl.b.c(context) + "$$" + b(cn.jiguang.sdk.impl.b.b(context), " ") + "$$" + b(cn.jiguang.ap.c.a(context).q, " ") + "$$" + b(cn.jiguang.ap.c.a(context).i, " ") + "$$" + b(cn.jiguang.sdk.impl.b.r(context) ? cn.jiguang.ap.a.d(context, "") : "", " ") + "$$" + b(str4, " ");
        cn.jiguang.ah.b.a();
        long b = cn.jiguang.ah.b.b();
        String a2 = cn.jiguang.sdk.impl.b.a(context);
        cn.jiguang.ai.a.c("ConnectingHelper", "Register with: key:" + str + ", apkVersion:" + str2 + ", clientInfo:" + str3 + ", extKey:" + str5 + ",reg business:" + b + " accountId:" + a2);
        long b2 = b(context);
        int a3 = cn.jiguang.al.a.a();
        cn.jiguang.ap.e eVar = new cn.jiguang.ap.e(20480);
        eVar.b(0);
        eVar.a(19);
        eVar.a(0);
        eVar.b(b2);
        eVar.a(a3);
        eVar.b(0L);
        eVar.a(str);
        eVar.a(str2);
        eVar.a(str3);
        eVar.a(0);
        eVar.a(str5);
        eVar.a(b);
        eVar.a(a2);
        eVar.b(eVar.a(), 0);
        byte[] a4 = cn.jiguang.ak.b.a(context, eVar.b());
        if (a4 == null) {
            cn.jiguang.ai.a.h("ConnectingHelper", "Register failed - encrytor reg info failed");
            return -1;
        } else if (aVar.a(a4) != 0) {
            cn.jiguang.ai.a.h("ConnectingHelper", "Register failed - send reg info failed");
            return -1;
        } else {
            try {
                Pair<cn.jiguang.ak.c, ByteBuffer> a5 = cn.jiguang.ak.a.a(context, aVar.a(20000).array(), "");
                if (a5 == null || a5.first == null || a5.second == null || ((cn.jiguang.ak.c) a5.first).c != 0) {
                    cn.jiguang.ai.a.h("ConnectingHelper", "Register failed - can't parse a Register Response");
                    return -1;
                }
                cn.jiguang.ak.e eVar2 = new cn.jiguang.ak.e((cn.jiguang.ak.c) a5.first, (ByteBuffer) a5.second);
                cn.jiguang.ai.a.c("ConnectingHelper", "register response:" + eVar2);
                int i = eVar2.a;
                cn.jiguang.ae.c.a(context, cn.jiguang.ae.b.b().a((cn.jiguang.ae.b<Integer>) Integer.valueOf(i)));
                if (i == 0) {
                    long j = eVar2.b;
                    String str6 = eVar2.c;
                    String str7 = eVar2.d;
                    String str8 = eVar2.e;
                    cn.jiguang.ai.a.f("ConnectingHelper", "Register succeed - juid:" + j + ", registrationId:" + str7 + ", deviceId:" + str8);
                    if (cn.jiguang.ap.h.a(str7) || 0 == j) {
                        cn.jiguang.ai.a.j("ConnectingHelper", "Unexpected: registrationId/juid should not be empty. ");
                        return -1;
                    }
                    cn.jiguang.sdk.impl.b.a(context, str8);
                    cn.jiguang.sdk.impl.b.a(context, j, str6, str7);
                }
                return i;
            } catch (cn.jiguang.ah.f e) {
                cn.jiguang.ai.a.h("ConnectingHelper", "Register failed - recv msg failed with error:" + e);
                return -1;
            }
        }
    }

    public static synchronized long b(Context context) {
        long j;
        synchronized (c.class) {
            long longValue = ((Long) cn.jiguang.ae.c.a(context, cn.jiguang.ae.b.n())).longValue();
            if (longValue == -1) {
                longValue = Math.abs(new Random().nextInt(ByteBufferUtils.ERROR_CODE));
            }
            j = (longValue + (longValue % 2 == 0 ? 1L : 2L)) % 10000;
            cn.jiguang.ae.c.a(context, cn.jiguang.ae.b.n().a((cn.jiguang.ae.b<Long>) Long.valueOf(j)));
        }
        return j;
    }

    private static String b(String str, String str2) {
        return !cn.jiguang.ap.h.a(str) ? str : str2;
    }

    private static String c(Context context) {
        String str = null;
        if (JCoreManager.isInternal()) {
            try {
                Object onEvent = JCoreManager.onEvent(context, cn.jiguang.sdk.impl.a.d, 32, "", null, new Object[0]);
                if (onEvent instanceof Bundle) {
                    String string = ((Bundle) onEvent).getString("test_country");
                    try {
                        cn.jiguang.ai.a.c("ConnectingHelper", "test country code : " + string);
                    } catch (Throwable unused) {
                    }
                    str = string;
                }
            } catch (Throwable unused2) {
            }
        }
        return !TextUtils.isEmpty(str) ? str : cn.jiguang.ap.a.k(context);
    }
}
