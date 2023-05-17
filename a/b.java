package cn.jiguang.a;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import cn.jiguang.ac.d;
import cn.jiguang.api.JCoreManager;
import cn.jpush.android.service.DataShare;

/* loaded from: classes.dex */
final class b implements ServiceConnection {
    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        d.b("JCoreGobal", "action - onServiceConnected, ComponentName:" + componentName);
        d.d("JCoreGobal", "Remote Service bind success.");
        try {
            DataShare.init(cn.jiguang.d.b.asInterface(iBinder), cn.jiguang.ab.d.a(a.a(null)));
            if (a.a != null) {
                JCoreManager.init(a.a);
            }
        } catch (Throwable th) {
            d.h("JCoreGobal", "onServiceConnected e:" + th);
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        d.b("JCoreGobal", "action - onServiceDisconnected, ComponentName:" + componentName);
    }
}
