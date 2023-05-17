package cn.jiguang.ah;

import cn.jiguang.api.ReportCallBack;
import java.io.File;
import java.util.LinkedHashSet;
import java.util.Set;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class n {
    int a;
    JSONObject b;
    byte[] c;
    File d;
    Set<String> e;
    int f = -1;
    cn.jiguang.af.h g;
    LinkedHashSet<cn.jiguang.af.h> h;
    boolean i;
    transient ReportCallBack j;

    public final String toString() {
        return "TcpReportData{types=" + this.e + ", seqId=" + this.f + ", ipPort=" + this.g + ", restUrls=" + this.h + '}';
    }
}
