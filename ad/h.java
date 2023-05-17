package cn.jiguang.ad;

import java.io.File;
import java.util.Comparator;

/* loaded from: classes.dex */
final class h implements Comparator<File> {
    @Override // java.util.Comparator
    public final /* synthetic */ int compare(File file, File file2) {
        int i = ((file.lastModified() - file2.lastModified()) > 0L ? 1 : ((file.lastModified() - file2.lastModified()) == 0L ? 0 : -1));
        if (i < 0) {
            return 1;
        }
        return i == 0 ? 0 : -1;
    }
}
