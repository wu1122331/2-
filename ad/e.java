package cn.jiguang.ad;

import java.io.File;
import java.io.FileFilter;

/* loaded from: classes.dex */
final class e implements FileFilter {
    @Override // java.io.FileFilter
    public final boolean accept(File file) {
        return (file.getName().length() == 24 || file.getName().equals("0")) ? false : true;
    }
}
