package cn.jiguang.as;

import java.text.SimpleDateFormat;
import java.util.Locale;

/* loaded from: classes.dex */
final class c extends ThreadLocal<SimpleDateFormat> {
    private String a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(String str) {
        this.a = str;
    }

    @Override // java.lang.ThreadLocal
    protected final /* synthetic */ SimpleDateFormat initialValue() {
        return new SimpleDateFormat(this.a, Locale.ENGLISH);
    }
}
