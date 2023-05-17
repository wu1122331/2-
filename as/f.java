package cn.jiguang.as;

import android.text.TextUtils;
import java.io.File;
import java.io.FileFilter;

/* loaded from: classes.dex */
public final class f implements FileFilter {
    public static final f a = new f(true, false);
    public static final f b = new f(false, true);
    private final boolean c;
    private final boolean d;
    private final String e;
    private final int f;

    private f(boolean z, boolean z2) {
        this.c = z;
        this.d = z2;
        this.e = null;
        this.f = 0;
    }

    public f(boolean z, boolean z2, String str, int i) {
        this.e = str;
        this.f = i;
        this.c = false;
        this.d = true;
    }

    public static f a(String str) {
        return new f(false, true, str, 3);
    }

    @Override // java.io.FileFilter
    public final boolean accept(File file) {
        if (!this.c || file.isFile()) {
            if (!this.d || file.isDirectory()) {
                if (TextUtils.isEmpty(this.e)) {
                    return true;
                }
                int i = this.f;
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            if (i != 4) {
                                return false;
                            }
                            return file.getName().contains(this.e);
                        }
                        return file.getName().equals(this.e);
                    }
                    return file.getName().endsWith(this.e);
                }
                return file.getName().startsWith(this.e);
            }
            return false;
        }
        return false;
    }
}
