package cn.jiguang.net;

import android.text.TextUtils;
import cn.jiguang.ac.d;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/* loaded from: classes.dex */
public class DefaultHostVerifier implements HostnameVerifier {
    public String a;

    public DefaultHostVerifier(String str) {
        this.a = null;
        this.a = str;
    }

    @Override // javax.net.ssl.HostnameVerifier
    public boolean verify(String str, SSLSession sSLSession) {
        d.b("DefaultHostVerifier", "host:" + str + ",checkHost:" + this.a);
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return TextUtils.equals(this.a, str);
    }
}
