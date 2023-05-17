package cn.jiguang.ag;

import java.util.HashMap;

/* loaded from: classes.dex */
final class g {
    private static Integer[] a = new Integer[64];
    private String d;
    private String f;
    private boolean h;
    private int e = 3;
    private HashMap b = new HashMap();
    private HashMap c = new HashMap();
    private int g = Integer.MAX_VALUE;

    static {
        int i = 0;
        while (true) {
            Integer[] numArr = a;
            if (i >= numArr.length) {
                return;
            }
            numArr[i] = Integer.valueOf(i);
            i++;
        }
    }

    public g(String str, int i) {
        this.d = str;
    }

    private static Integer c(int i) {
        if (i >= 0) {
            Integer[] numArr = a;
            if (i < numArr.length) {
                return numArr[i];
            }
        }
        return Integer.valueOf(i);
    }

    private void d(int i) {
        if (i < 0 || i > this.g) {
            throw new IllegalArgumentException(this.d + " " + i + "is out of range");
        }
    }

    public final void a(int i) {
        this.g = 3;
    }

    public final void a(int i, String str) {
        d(i);
        Integer c = c(i);
        int i2 = this.e;
        if (i2 == 2) {
            str = str.toUpperCase();
        } else if (i2 == 3) {
            str = str.toLowerCase();
        }
        this.b.put(str, c);
        this.c.put(c, str);
    }

    public final void a(boolean z) {
        this.h = true;
    }

    public final String b(int i) {
        d(i);
        String str = (String) this.c.get(c(i));
        if (str != null) {
            return str;
        }
        String num = Integer.toString(i);
        return this.f != null ? this.f + num : num;
    }
}
