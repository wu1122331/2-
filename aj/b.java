package cn.jiguang.aj;

import android.content.Context;
import java.net.InetAddress;
import java.util.concurrent.Callable;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class b implements Callable<InetAddress[]> {
    private Context a;
    private String b;
    private a c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(Context context, String str, a aVar) {
        this.a = context;
        this.b = str;
        this.c = aVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x004e  */
    @Override // java.util.concurrent.Callable
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.net.InetAddress[] call() {
        /*
            r14 = this;
            java.lang.String r0 = "DNSLoader"
            r1 = 0
            java.lang.String r2 = r14.b     // Catch: java.net.UnknownHostException -> La
            java.net.InetAddress[] r2 = java.net.InetAddress.getAllByName(r2)     // Catch: java.net.UnknownHostException -> La
            goto L1e
        La:
            r2 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "dns resolve failed:"
            r3.<init>(r4)
            java.lang.StringBuilder r2 = r3.append(r2)
            java.lang.String r2 = r2.toString()
            cn.jiguang.ai.a.g(r0, r2)
            r2 = r1
        L1e:
            if (r2 == 0) goto Lde
            int r3 = r2.length
            if (r3 <= 0) goto Lde
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            int r5 = r2.length
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
        L32:
            r10 = 1
            if (r7 >= r5) goto L65
            r11 = r2[r7]
            r12 = 3
            if (r8 >= r12) goto L42
            boolean r13 = r11 instanceof java.net.Inet4Address
            if (r13 == 0) goto L42
            int r8 = r8 + 1
        L40:
            r13 = 1
            goto L4c
        L42:
            if (r9 >= r12) goto L4b
            boolean r13 = r11 instanceof java.net.Inet6Address
            if (r13 == 0) goto L4b
            int r9 = r9 + 1
            goto L40
        L4b:
            r13 = 0
        L4c:
            if (r13 == 0) goto L5e
            r3.add(r11)
            java.lang.String r11 = r11.getHostAddress()
            java.lang.StringBuilder r11 = r4.append(r11)
            java.lang.String r13 = ","
            r11.append(r13)
        L5e:
            if (r8 != r12) goto L62
            if (r9 == r12) goto L65
        L62:
            int r7 = r7 + 1
            goto L32
        L65:
            boolean r2 = r3.isEmpty()
            if (r2 != 0) goto Ldf
            java.net.InetAddress[] r1 = new java.net.InetAddress[r6]
            java.lang.Object[] r1 = r3.toArray(r1)
            java.net.InetAddress[] r1 = (java.net.InetAddress[]) r1
            long r2 = java.lang.System.currentTimeMillis()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r7 = "update dns cache url="
            r5.<init>(r7)
            java.lang.String r7 = r14.b
            java.lang.StringBuilder r5 = r5.append(r7)
            java.lang.String r7 = " resolved="
            java.lang.StringBuilder r5 = r5.append(r7)
            java.lang.String r7 = java.util.Arrays.toString(r1)
            java.lang.StringBuilder r5 = r5.append(r7)
            java.lang.String r5 = r5.toString()
            cn.jiguang.ai.a.c(r0, r5)
            cn.jiguang.aj.a r0 = r14.c
            java.util.Map r0 = cn.jiguang.aj.a.a(r0)
            java.lang.String r5 = r14.b
            android.util.Pair r7 = new android.util.Pair
            java.lang.Long r8 = java.lang.Long.valueOf(r2)
            r7.<init>(r1, r8)
            r0.put(r5, r7)
            int r0 = r4.length()     // Catch: java.lang.Throwable -> Ldf
            int r0 = r0 - r10
            r4.deleteCharAt(r0)     // Catch: java.lang.Throwable -> Ldf
            android.content.Context r0 = r14.a     // Catch: java.lang.Throwable -> Ldf
            r5 = 2
            cn.jiguang.ae.b[] r5 = new cn.jiguang.ae.b[r5]     // Catch: java.lang.Throwable -> Ldf
            java.lang.String r7 = r14.b     // Catch: java.lang.Throwable -> Ldf
            cn.jiguang.ae.b r7 = cn.jiguang.ae.b.b(r7)     // Catch: java.lang.Throwable -> Ldf
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> Ldf
            cn.jiguang.ae.b r4 = r7.a(r4)     // Catch: java.lang.Throwable -> Ldf
            r5[r6] = r4     // Catch: java.lang.Throwable -> Ldf
            java.lang.String r4 = r14.b     // Catch: java.lang.Throwable -> Ldf
            cn.jiguang.ae.b r4 = cn.jiguang.ae.b.c(r4)     // Catch: java.lang.Throwable -> Ldf
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch: java.lang.Throwable -> Ldf
            cn.jiguang.ae.b r2 = r4.a(r2)     // Catch: java.lang.Throwable -> Ldf
            r5[r10] = r2     // Catch: java.lang.Throwable -> Ldf
            cn.jiguang.ae.c.a(r0, r5)     // Catch: java.lang.Throwable -> Ldf
            goto Ldf
        Lde:
            r1 = r2
        Ldf:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.aj.b.call():java.net.InetAddress[]");
    }
}
