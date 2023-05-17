package cn.jiguang.d;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;

/* loaded from: classes.dex */
public interface a extends IInterface {
    String bind(a aVar, String str);

    Bundle execute(String str, String str2, Bundle bundle);

    IBinder getBinderByType(String str, String str2);

    void onAction(String str, String str2, Bundle bundle);
}
