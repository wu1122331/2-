package cn.jiguang.u;

import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.core.view.PointerIconCompat;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class h extends Handler {
    final /* synthetic */ f a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(f fVar, Looper looper) {
        super(looper);
        this.a = fVar;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        String str;
        LocationManager locationManager;
        String str2;
        LocationListener locationListener;
        String str3;
        StringBuilder append;
        String str4;
        String str5;
        String str6;
        LocationManager locationManager2;
        String str7;
        LocationListener locationListener2;
        String str8;
        String str9;
        int i = message.what;
        int i2 = PointerIconCompat.TYPE_CONTEXT_MENU;
        switch (i) {
            case PointerIconCompat.TYPE_CONTEXT_MENU /* 1001 */:
                try {
                    str4 = this.a.g;
                    if (str4 != null) {
                        str6 = this.a.g;
                        if (str6.equals("network")) {
                            cn.jiguang.ai.a.g("JLocationGps", "get gps with network time out ");
                            this.a.g = "gps";
                            this.a.f();
                            locationManager2 = this.a.c;
                            str7 = this.a.g;
                            locationListener2 = this.a.i;
                            locationManager2.requestLocationUpdates(str7, 2000L, 0.0f, locationListener2);
                            StringBuilder sb = new StringBuilder("request ");
                            str8 = this.a.g;
                            cn.jiguang.ai.a.c("JLocationGps", sb.append(str8).append(" location").toString());
                            this.a.a.sendEmptyMessageDelayed(PointerIconCompat.TYPE_CONTEXT_MENU, 10000L);
                            return;
                        }
                    }
                    StringBuilder sb2 = new StringBuilder("get ");
                    str5 = this.a.g;
                    cn.jiguang.ai.a.g("JLocationGps", sb2.append(str5).append(" time out ").toString());
                    f.b(this.a);
                    return;
                } catch (Throwable th) {
                    append = new StringBuilder("when location time out ").append(th.getMessage());
                    break;
                }
            case PointerIconCompat.TYPE_HAND /* 1002 */:
            default:
                return;
            case PointerIconCompat.TYPE_HELP /* 1003 */:
                try {
                    locationManager = this.a.c;
                    str2 = this.a.g;
                    locationListener = this.a.i;
                    locationManager.requestLocationUpdates(str2, 2000L, 0.0f, locationListener);
                    StringBuilder sb3 = new StringBuilder("request ");
                    str3 = this.a.g;
                    cn.jiguang.ai.a.c("JLocationGps", sb3.append(str3).append(" location").toString());
                    this.a.a.sendEmptyMessageDelayed(i2, 20000L);
                    return;
                } catch (SecurityException unused) {
                    str = "No suitable permission when get last known location!";
                    break;
                } catch (Throwable unused2) {
                    cn.jiguang.ai.a.g("JLocationGps", "The provider is illegal argument!");
                    f.b(this.a);
                    return;
                }
            case PointerIconCompat.TYPE_WAIT /* 1004 */:
                cn.jiguang.ai.a.c("JLocationGps", "LOAD_GPS_ACTION_REQUEST_ONLY_NETWORK");
                i2 = 1005;
                locationManager = this.a.c;
                str2 = this.a.g;
                locationListener = this.a.i;
                locationManager.requestLocationUpdates(str2, 2000L, 0.0f, locationListener);
                StringBuilder sb32 = new StringBuilder("request ");
                str3 = this.a.g;
                cn.jiguang.ai.a.c("JLocationGps", sb32.append(str3).append(" location").toString());
                this.a.a.sendEmptyMessageDelayed(i2, 20000L);
                return;
            case 1005:
                StringBuilder sb4 = new StringBuilder("get only network ");
                str9 = this.a.g;
                append = sb4.append(str9).append(" time out ");
                str = append.toString();
                cn.jiguang.ai.a.g("JLocationGps", str);
                f.b(this.a);
                return;
        }
    }
}
