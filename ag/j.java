package cn.jiguang.ag;

import java.io.IOException;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Arrays;
import kotlin.UByte;

/* loaded from: classes.dex */
public abstract class j implements Serializable, Cloneable, Comparable {
    private static final DecimalFormat e;
    protected h a;
    protected int b;
    protected int c;
    protected long d;

    static {
        DecimalFormat decimalFormat = new DecimalFormat();
        e = decimalFormat;
        decimalFormat.setMinimumIntegerDigits(3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static j a(c cVar, int i) {
        h hVar = new h(cVar);
        int g = cVar.g();
        int g2 = cVar.g();
        if (i == 0) {
            return a(hVar, g, g2);
        }
        long h = cVar.h();
        int g3 = cVar.g();
        j a = a(hVar, g, g2, h);
        if (cVar != null) {
            if (cVar.b() < g3) {
                throw new IOException("truncated record");
            }
            cVar.a(g3);
            a.a(cVar);
            if (cVar.b() > 0) {
                throw new IOException("invalid record length");
            }
            cVar.c();
        }
        return a;
    }

    public static j a(h hVar, int i, int i2) {
        if (hVar.a()) {
            return a(hVar, i, i2, 0L);
        }
        throw new k(hVar);
    }

    private static final j a(h hVar, int i, int i2, long j) {
        o oVar = new o();
        oVar.a = hVar;
        oVar.b = i;
        oVar.c = i2;
        oVar.d = j;
        return oVar;
    }

    private byte[] h() {
        d dVar = new d();
        a(dVar, true);
        return dVar.b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract String a();

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(long j) {
        this.d = j;
    }

    abstract void a(c cVar);

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(d dVar, a aVar) {
        this.a.a(dVar, aVar);
        dVar.c(this.b);
        dVar.c(this.c);
    }

    abstract void a(d dVar, boolean z);

    public final boolean a(j jVar) {
        return this.b == jVar.b && this.c == jVar.c && this.a.equals(jVar.a);
    }

    public final h b() {
        return this.a;
    }

    public final int c() {
        return this.b;
    }

    @Override // java.lang.Comparable
    public int compareTo(Object obj) {
        j jVar = (j) obj;
        if (this == jVar) {
            return 0;
        }
        int compareTo = this.a.compareTo(jVar.a);
        if (compareTo != 0) {
            return compareTo;
        }
        int i = this.c - jVar.c;
        if (i != 0) {
            return i;
        }
        int i2 = this.b - jVar.b;
        if (i2 != 0) {
            return i2;
        }
        byte[] h = h();
        byte[] h2 = jVar.h();
        for (int i3 = 0; i3 < h.length && i3 < h2.length; i3++) {
            int i4 = (h[i3] & UByte.MAX_VALUE) - (h2[i3] & UByte.MAX_VALUE);
            if (i4 != 0) {
                return i4;
            }
        }
        return h.length - h2.length;
    }

    public final int d() {
        return this.b;
    }

    public final int e() {
        return this.c;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof j)) {
            j jVar = (j) obj;
            if (this.b == jVar.b && this.c == jVar.c && this.a.equals(jVar.a)) {
                return Arrays.equals(h(), jVar.h());
            }
        }
        return false;
    }

    public final long f() {
        return this.d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final j g() {
        try {
            return (j) clone();
        } catch (CloneNotSupportedException unused) {
            throw new IllegalStateException();
        }
    }

    public int hashCode() {
        d dVar = new d();
        this.a.a(dVar);
        dVar.c(this.b);
        dVar.c(this.c);
        dVar.a(0L);
        int a = dVar.a();
        dVar.c(0);
        a(dVar, true);
        dVar.a((dVar.a() - a) - 2, a);
        int i = 0;
        for (byte b : dVar.b()) {
            i += (i << 3) + (b & UByte.MAX_VALUE);
        }
        return i;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.a);
        if (stringBuffer.length() < 8) {
            stringBuffer.append("\t");
        }
        if (stringBuffer.length() < 16) {
            stringBuffer.append("\t");
        }
        stringBuffer.append("\t");
        String a = a();
        if (!a.equals("")) {
            stringBuffer.append("\t");
            stringBuffer.append(a);
        }
        return stringBuffer.toString();
    }
}
