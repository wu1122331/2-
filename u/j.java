package cn.jiguang.u;

import android.net.wifi.ScanResult;
import java.util.Comparator;

/* loaded from: classes.dex */
final class j implements Comparator<ScanResult> {
    final /* synthetic */ i a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public j(i iVar) {
        this.a = iVar;
    }

    @Override // java.util.Comparator
    public final /* bridge */ /* synthetic */ int compare(ScanResult scanResult, ScanResult scanResult2) {
        return scanResult2.level - scanResult.level;
    }
}
