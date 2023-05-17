package cn.jiguang.ag;

import java.io.IOException;
import java.io.Serializable;
import java.text.DecimalFormat;
import kotlin.UByte;

/* loaded from: classes.dex */
public final class h implements Serializable, Comparable {
    public static final h a;
    public static final h b;
    private static final byte[] f = {0};
    private static final byte[] g = {1, 42};
    private static final DecimalFormat h;
    private static final byte[] i;
    private static final h j;
    private byte[] c;
    private long d;
    private int e;

    static {
        DecimalFormat decimalFormat = new DecimalFormat();
        h = decimalFormat;
        i = new byte[256];
        decimalFormat.setMinimumIntegerDigits(3);
        int i2 = 0;
        while (true) {
            byte[] bArr = i;
            if (i2 >= bArr.length) {
                h hVar = new h();
                a = hVar;
                hVar.b(f, 0, 1);
                h hVar2 = new h();
                b = hVar2;
                hVar2.c = new byte[0];
                h hVar3 = new h();
                j = hVar3;
                hVar3.b(g, 0, 1);
                return;
            }
            if (i2 < 65 || i2 > 90) {
                bArr[i2] = (byte) i2;
            } else {
                bArr[i2] = (byte) ((i2 - 65) + 97);
            }
            i2++;
        }
    }

    private h() {
    }

    public h(c cVar) {
        byte[] bArr = new byte[64];
        boolean z = false;
        boolean z2 = false;
        while (!z) {
            int f2 = cVar.f();
            int i2 = f2 & 192;
            if (i2 != 0) {
                if (i2 != 192) {
                    throw new IOException("bad label type");
                }
                int f3 = cVar.f() + ((f2 & (-193)) << 8);
                if (f3 >= cVar.a() - 2) {
                    throw new IOException("bad compression");
                }
                if (!z2) {
                    cVar.d();
                    z2 = true;
                }
                cVar.b(f3);
            } else if (b() >= 128) {
                throw new IOException("too many labels");
            } else {
                if (f2 == 0) {
                    a(f, 0, 1);
                    z = true;
                } else {
                    bArr[0] = (byte) f2;
                    cVar.a(bArr, 1, f2);
                    a(bArr, 0, 1);
                }
            }
        }
        if (z2) {
            cVar.e();
        }
    }

    private h(h hVar, int i2) {
        int b2 = hVar.b();
        if (i2 > b2) {
            throw new IllegalArgumentException("attempted to remove too many labels");
        }
        this.c = hVar.c;
        int i3 = b2 - i2;
        b(i3);
        for (int i4 = 0; i4 < 7 && i4 < i3; i4++) {
            a(i4, hVar.a(i4 + i2));
        }
    }

    private h(String str, h hVar) {
        int i2;
        boolean z;
        int i3;
        h hVar2;
        if (str.equals("")) {
            throw a(str, "empty name");
        }
        if (str.equals("@")) {
            if (hVar != null) {
                b(hVar, this);
                return;
            }
            hVar2 = b;
        } else if (!str.equals(".")) {
            byte[] bArr = new byte[64];
            int i4 = 0;
            boolean z2 = false;
            int i5 = -1;
            int i6 = 1;
            int i7 = 0;
            for (int i8 = 0; i8 < str.length(); i8++) {
                byte charAt = (byte) str.charAt(i8);
                if (z2) {
                    if (charAt >= 48 && charAt <= 57 && i4 < 3) {
                        i4++;
                        i7 = (i7 * 10) + (charAt - 48);
                        if (i7 > 255) {
                            throw a(str, "bad escape");
                        }
                        if (i4 >= 3) {
                            charAt = (byte) i7;
                        } else {
                            continue;
                        }
                    } else if (i4 > 0 && i4 < 3) {
                        throw a(str, "bad escape");
                    }
                    if (i6 > 63) {
                        throw a(str, "label too long");
                    }
                    i3 = i6 + 1;
                    bArr[i6] = charAt;
                    i5 = i6;
                    z2 = false;
                    i6 = i3;
                } else {
                    if (charAt == 92) {
                        i4 = 0;
                        z2 = true;
                        i7 = 0;
                    } else if (charAt != 46) {
                        i5 = i5 == -1 ? i8 : i5;
                        if (i6 > 63) {
                            throw a(str, "label too long");
                        }
                        i3 = i6 + 1;
                        bArr[i6] = charAt;
                        i6 = i3;
                    } else if (i5 == -1) {
                        throw a(str, "invalid empty label");
                    } else {
                        bArr[0] = (byte) (i6 - 1);
                        a(str, bArr, 0, 1);
                        i5 = -1;
                        i6 = 1;
                    }
                }
            }
            if (i4 > 0 && i4 < 3) {
                throw a(str, "bad escape");
            }
            if (z2) {
                throw a(str, "bad escape");
            }
            if (i5 == -1) {
                z = true;
                i2 = 0;
                a(str, f, 0, 1);
            } else {
                i2 = 0;
                bArr[0] = (byte) (i6 - 1);
                a(str, bArr, 0, 1);
                z = false;
            }
            if (hVar == null || z) {
                return;
            }
            a(str, hVar.c, hVar.a(i2), hVar.b());
            return;
        } else {
            hVar2 = a;
        }
        b(hVar2, this);
    }

    private final int a(int i2) {
        if (i2 == 0 && b() == 0) {
            return 0;
        }
        if (i2 < 0 || i2 >= b()) {
            throw new IllegalArgumentException("label out of range");
        }
        if (i2 < 7) {
            return ((int) (this.d >>> ((7 - i2) * 8))) & 255;
        }
        int a2 = a(6);
        for (int i3 = 6; i3 < i2; i3++) {
            a2 += this.c[a2] + 1;
        }
        return a2;
    }

    public static h a(h hVar, h hVar2) {
        if (hVar.a()) {
            return hVar;
        }
        h hVar3 = new h();
        b(hVar, hVar3);
        hVar3.a(hVar2.c, hVar2.a(0), hVar2.b());
        return hVar3;
    }

    public static h a(String str) {
        return a(str, (h) null);
    }

    public static h a(String str, h hVar) {
        return (!str.equals("@") || hVar == null) ? str.equals(".") ? a : new h(str, hVar) : hVar;
    }

    private static IOException a(String str, String str2) {
        return new IOException("'" + str + "': " + str2);
    }

    private static String a(byte[] bArr, int i2) {
        StringBuffer stringBuffer = new StringBuffer();
        int i3 = i2 + 1;
        int i4 = bArr[i2];
        for (int i5 = i3; i5 < i3 + i4; i5++) {
            int i6 = bArr[i5] & UByte.MAX_VALUE;
            if (i6 <= 32 || i6 >= 127) {
                stringBuffer.append('\\');
                stringBuffer.append(h.format(i6));
            } else {
                if (i6 == 34 || i6 == 40 || i6 == 41 || i6 == 46 || i6 == 59 || i6 == 92 || i6 == 64 || i6 == 36) {
                    stringBuffer.append('\\');
                }
                stringBuffer.append((char) i6);
            }
        }
        return stringBuffer.toString();
    }

    private final void a(int i2, int i3) {
        if (i2 >= 7) {
            return;
        }
        int i4 = (7 - i2) * 8;
        long j2 = this.d & (~(255 << i4));
        this.d = j2;
        this.d = (i3 << i4) | j2;
    }

    private final void a(String str, byte[] bArr, int i2, int i3) {
        try {
            a(bArr, i2, i3);
        } catch (Exception unused) {
            throw a(str, "Name too long");
        }
    }

    private final void a(byte[] bArr, int i2, int i3) {
        byte[] bArr2 = this.c;
        int length = bArr2 == null ? 0 : bArr2.length - a(0);
        int i4 = i2;
        int i5 = 0;
        for (int i6 = 0; i6 < i3; i6++) {
            int i7 = bArr[i4];
            if (i7 > 63) {
                throw new IllegalStateException("invalid label");
            }
            int i8 = i7 + 1;
            i4 += i8;
            i5 += i8;
        }
        int i9 = length + i5;
        if (i9 > 255) {
            throw new IOException();
        }
        int b2 = b();
        int i10 = b2 + i3;
        if (i10 > 128) {
            throw new IllegalStateException("too many labels");
        }
        byte[] bArr3 = new byte[i9];
        if (length != 0) {
            System.arraycopy(this.c, a(0), bArr3, 0, length);
        }
        System.arraycopy(bArr, i2, bArr3, length, i5);
        this.c = bArr3;
        for (int i11 = 0; i11 < i3; i11++) {
            a(b2 + i11, length);
            length += bArr3[length] + 1;
        }
        b(i10);
    }

    private final int b() {
        return (int) (this.d & 255);
    }

    private final void b(int i2) {
        long j2 = this.d & (-256);
        this.d = j2;
        this.d = j2 | i2;
    }

    private static final void b(h hVar, h hVar2) {
        if (hVar.a(0) == 0) {
            hVar2.c = hVar.c;
            hVar2.d = hVar.d;
            return;
        }
        int a2 = hVar.a(0);
        int length = hVar.c.length - a2;
        int b2 = hVar.b();
        byte[] bArr = new byte[length];
        hVar2.c = bArr;
        System.arraycopy(hVar.c, a2, bArr, 0, length);
        for (int i2 = 0; i2 < b2 && i2 < 7; i2++) {
            hVar2.a(i2, hVar.a(i2) - a2);
        }
        hVar2.b(b2);
    }

    private final void b(byte[] bArr, int i2, int i3) {
        try {
            a(bArr, 0, 1);
        } catch (Exception unused) {
        }
    }

    public final void a(d dVar) {
        byte[] bArr;
        int b2 = b();
        if (b2 == 0) {
            bArr = new byte[0];
        } else {
            byte[] bArr2 = new byte[this.c.length - a(0)];
            int a2 = a(0);
            int i2 = 0;
            for (int i3 = 0; i3 < b2; i3++) {
                byte[] bArr3 = this.c;
                byte b3 = bArr3[a2];
                if (b3 > 63) {
                    throw new IllegalStateException("invalid label");
                }
                bArr2[i2] = bArr3[a2];
                i2++;
                a2++;
                int i4 = 0;
                while (i4 < b3) {
                    bArr2[i2] = i[this.c[a2] & UByte.MAX_VALUE];
                    i4++;
                    i2++;
                    a2++;
                }
            }
            bArr = bArr2;
        }
        dVar.a(bArr);
    }

    public final void a(d dVar, a aVar) {
        int b2 = b();
        int i2 = 0;
        while (i2 < b2 - 1) {
            h hVar = i2 == 0 ? this : new h(this, i2);
            int a2 = aVar != null ? aVar.a(hVar) : -1;
            if (a2 >= 0) {
                dVar.c(49152 | a2);
                return;
            }
            if (aVar != null) {
                aVar.a(dVar.a(), hVar);
            }
            int a3 = a(i2);
            byte[] bArr = this.c;
            dVar.a(bArr, a3, bArr[a3] + 1);
            i2++;
        }
        dVar.b(0);
    }

    public final void a(d dVar, a aVar, boolean z) {
        if (z) {
            a(dVar);
        } else {
            a(dVar, (a) null);
        }
    }

    public final boolean a() {
        int b2 = b();
        return b2 != 0 && this.c[a(b2 - 1)] == 0;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        h hVar = (h) obj;
        if (this == hVar) {
            return 0;
        }
        int b2 = b();
        int b3 = hVar.b();
        int i2 = b2 > b3 ? b3 : b2;
        for (int i3 = 1; i3 <= i2; i3++) {
            int a2 = a(b2 - i3);
            int a3 = hVar.a(b3 - i3);
            byte b4 = this.c[a2];
            byte b5 = hVar.c[a3];
            for (int i4 = 0; i4 < b4 && i4 < b5; i4++) {
                byte[] bArr = i;
                int i5 = bArr[this.c[(i4 + a2) + 1] & UByte.MAX_VALUE] - bArr[hVar.c[(i4 + a3) + 1] & UByte.MAX_VALUE];
                if (i5 != 0) {
                    return i5;
                }
            }
            if (b4 != b5) {
                return b4 - b5;
            }
        }
        return b2 - b3;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof h)) {
            return false;
        }
        h hVar = (h) obj;
        if (hVar.e == 0) {
            hVar.hashCode();
        }
        if (this.e == 0) {
            hashCode();
        }
        if (hVar.e == this.e && hVar.b() == b()) {
            byte[] bArr = hVar.c;
            int a2 = hVar.a(0);
            int b2 = b();
            int a3 = a(0);
            int i2 = 0;
            while (i2 < b2) {
                byte[] bArr2 = this.c;
                if (bArr2[a3] != bArr[a2]) {
                    return false;
                }
                int i3 = a3 + 1;
                byte b3 = bArr2[a3];
                a2++;
                if (b3 > 63) {
                    throw new IllegalStateException("invalid label");
                }
                int i4 = 0;
                while (i4 < b3) {
                    byte[] bArr3 = i;
                    int i5 = i3 + 1;
                    int i6 = a2 + 1;
                    if (bArr3[this.c[i3] & UByte.MAX_VALUE] != bArr3[bArr[a2] & UByte.MAX_VALUE]) {
                        return false;
                    }
                    i4++;
                    a2 = i6;
                    i3 = i5;
                }
                i2++;
                a3 = i3;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i2 = this.e;
        if (i2 != 0) {
            return i2;
        }
        int i3 = 0;
        int a2 = a(0);
        while (true) {
            byte[] bArr = this.c;
            if (a2 >= bArr.length) {
                this.e = i3;
                return i3;
            }
            i3 += (i3 << 3) + i[bArr[a2] & UByte.MAX_VALUE];
            a2++;
        }
    }

    public final String toString() {
        int b2 = b();
        if (b2 == 0) {
            return "@";
        }
        int i2 = 0;
        if (b2 == 1 && this.c[a(0)] == 0) {
            return ".";
        }
        StringBuffer stringBuffer = new StringBuffer();
        int a2 = a(0);
        while (true) {
            if (i2 >= b2) {
                break;
            }
            byte b3 = this.c[a2];
            if (b3 > 63) {
                throw new IllegalStateException("invalid label");
            }
            if (b3 == 0) {
                stringBuffer.append('.');
                break;
            }
            if (i2 > 0) {
                stringBuffer.append('.');
            }
            stringBuffer.append(a(this.c, a2));
            a2 += b3 + 1;
            i2++;
        }
        return stringBuffer.toString();
    }
}
