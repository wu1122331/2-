package cn.jiguang.u;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class g implements LocationListener {
    final /* synthetic */ f a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public g(f fVar) {
        this.a = fVar;
    }

    @Override // android.location.LocationListener
    public final void onLocationChanged(Location location) {
        String str;
        try {
            StringBuilder append = new StringBuilder("onLocationChanged:").append(location).append(",provider:");
            str = this.a.g;
            cn.jiguang.ai.a.c("JLocationGps", append.append(str).toString());
            this.a.a(location);
            f.b(this.a);
        } catch (Throwable th) {
            cn.jiguang.ai.a.g("JLocationGps", "onLocationChanged failed:" + th.getMessage());
        }
    }

    @Override // android.location.LocationListener
    public final void onProviderDisabled(String str) {
        cn.jiguang.ai.a.c("JLocationGps", "onProviderDisabled:" + str);
        f.b(this.a);
    }

    @Override // android.location.LocationListener
    public final void onProviderEnabled(String str) {
        cn.jiguang.ai.a.c("JLocationGps", "onProviderEnabled:" + str);
    }

    @Override // android.location.LocationListener
    public final void onStatusChanged(String str, int i, Bundle bundle) {
        cn.jiguang.ai.a.c("JLocationGps", "onStatusChanged status:" + i);
        if (i == 0) {
            f.b(this.a);
        }
    }
}
