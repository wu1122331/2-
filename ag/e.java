package cn.jiguang.ag;

import androidx.core.internal.view.SupportMenu;
import java.util.Random;

/* loaded from: classes.dex */
public final class e implements Cloneable {
    private static Random d = new Random();
    private int a;
    private int b;
    private int[] c;

    public e() {
        this.b = 256;
        b();
    }

    private e(int i) {
        this.b = 256;
        b();
        if (i < 0 || i > 65535) {
            throw new IllegalArgumentException("DNS message ID " + i + " is out of range");
        }
        this.a = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(c cVar) {
        this(cVar.g());
        this.b = cVar.g();
        int i = 0;
        while (true) {
            int[] iArr = this.c;
            if (i >= iArr.length) {
                return;
            }
            iArr[i] = cVar.g();
            i++;
        }
    }

    private void b() {
        this.c = new int[4];
        this.b = 256;
        this.a = -1;
    }

    private int c() {
        int i;
        int i2 = this.a;
        if (i2 >= 0) {
            return i2;
        }
        synchronized (this) {
            if (this.a < 0) {
                this.a = d.nextInt(SupportMenu.USER_MASK);
            }
            i = this.a;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a() {
        return this.b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(int i) {
        int[] iArr = this.c;
        if (iArr[i] == 65535) {
            throw new IllegalStateException("DNS section count cannot be incremented");
        }
        iArr[i] = iArr[i] + 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(d dVar) {
        dVar.c(c());
        dVar.c(this.b);
        int i = 0;
        while (true) {
            int[] iArr = this.c;
            if (i >= iArr.length) {
                return;
            }
            dVar.c(iArr[i]);
            i++;
        }
    }

    public final int b(int i) {
        return this.c[i];
    }

    public final Object clone() {
        e eVar = new e();
        eVar.a = this.a;
        eVar.b = this.b;
        int[] iArr = this.c;
        System.arraycopy(iArr, 0, eVar.c, 0, iArr.length);
        return eVar;
    }

    public final String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(";; ->>HEADER<<- ");
        stringBuffer.append(", id: " + c());
        stringBuffer.append("\n");
        stringBuffer.append("; ");
        for (int i = 0; i < 4; i++) {
            stringBuffer.append(p.a(i) + ": " + this.c[i] + " ");
        }
        return stringBuffer.toString();
    }
}
