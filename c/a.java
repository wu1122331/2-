package cn.jiguang.c;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import cn.jiguang.ad.k;
import cn.jiguang.api.JCoreManager;

/* loaded from: classes.dex */
public final class a implements Application.ActivityLifecycleCallbacks {
    private static int a;

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
        cn.jiguang.ac.d.b("ActivityLifecycle", "onActivityCreated:" + activity.getClass().getCanonicalName());
        try {
            if (cn.jiguang.a.a.b != null) {
                cn.jiguang.a.a.b.dispatchStatus(activity, "onCreate");
            }
        } catch (Throwable unused) {
            cn.jiguang.ac.d.b("ActivityLifecycle", "onActivityCreated failed");
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
        try {
            cn.jiguang.ac.d.d("ActivityLifecycle", "onActivityPaused:" + activity.getClass().getCanonicalName());
            if (cn.jiguang.a.a.b != null) {
                cn.jiguang.a.a.b.dispatchPause(activity);
            }
            if (cn.jiguang.a.a.g) {
                return;
            }
            b.a().c(activity);
        } catch (Throwable unused) {
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
        try {
            cn.jiguang.ac.d.d("ActivityLifecycle", "onActivityResumed:" + activity.getClass().getCanonicalName());
            if (cn.jiguang.a.a.b != null) {
                cn.jiguang.a.a.b.dispatchResume(activity);
            }
            if (cn.jiguang.a.a.g) {
                return;
            }
            b.a().b(activity);
        } catch (Throwable unused) {
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
        cn.jiguang.ac.d.b("ActivityLifecycle", "onActivityStarted:" + activity.getClass().getCanonicalName());
        try {
            if (a == 0) {
                cn.jiguang.ac.d.b("ActivityLifecycle", "isForeground");
                if (activity != null) {
                    cn.jiguang.a.a.a(activity.getApplicationContext(), false, 0L);
                    JCoreManager.onEvent(activity.getApplicationContext(), cn.jiguang.a.a.d, 29, null, null, 1);
                    cn.jiguang.ab.f.a(activity.getApplicationContext(), true);
                }
                if (cn.jiguang.a.a.b != null) {
                    cn.jiguang.a.a.b.dispatchStatus(activity, "onStart");
                }
            }
            a++;
        } catch (Throwable unused) {
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
        try {
            cn.jiguang.ac.d.b("ActivityLifecycle", "onActivityStopped:" + activity.getClass().getCanonicalName());
            int i = a;
            if (i > 0) {
                a = i - 1;
            }
            if (a == 0) {
                cn.jiguang.ac.d.b("ActivityLifecycle", "is not Foreground");
                Context a2 = cn.jiguang.a.a.a(activity);
                k.a(a2, (Object) null);
                k.a(a2, cn.jiguang.a.a.d, (Object) null);
                cn.jiguang.ab.f.a(a2, false);
            }
        } catch (Throwable unused) {
        }
    }
}
