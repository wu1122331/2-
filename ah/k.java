package cn.jiguang.ah;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.core.view.PointerIconCompat;
import com.facebook.gamingservices.cloudgaming.internal.SDKConstants;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class k implements Runnable {
    final /* synthetic */ i a;
    private Context b;
    private String c;
    private Bundle d;

    public k(i iVar, Context context, String str, Bundle bundle) {
        this.a = iVar;
        this.b = context;
        this.c = str;
        this.d = bundle;
    }

    @Override // java.lang.Runnable
    public final void run() {
        cn.jiguang.af.i iVar;
        i iVar2;
        cn.jiguang.af.i iVar3;
        long j;
        String str;
        cn.jiguang.af.i iVar4;
        try {
            if (this.c.equals("tcp_a1")) {
                iVar4 = this.a.a;
                if (iVar4 != null) {
                    return;
                }
                iVar2 = this.a;
            } else {
                if (!this.c.equals("tcp_a3") && !this.c.equals("tcp_a4") && !this.c.equals("tcp_a20")) {
                    if (this.c.equals("tcp_a5")) {
                        Bundle bundle = this.d;
                        if (bundle != null) {
                            byte[] byteArray = bundle.getByteArray(SDKConstants.PARAM_A2U_BODY);
                            int i = this.d.getInt("cmd", -1);
                            int i2 = this.d.getInt("ver", -1);
                            long j2 = this.d.getLong("rid", -1L);
                            String string = this.d.getString("sdk_type");
                            long j3 = this.d.getLong("timeout");
                            cn.jiguang.ai.a.c("JCoreTCPManager", "send quest,cmd:" + i + ",ver:" + i2 + ",rid:" + j2 + ",body size:" + byteArray.length);
                            if (i >= 0 && i2 >= 0 && j2 >= 0 && !TextUtils.isEmpty(string)) {
                                o.a().a(this.b, j2, i, i2, byteArray, string, j3);
                                return;
                            }
                            return;
                        }
                        return;
                    } else if (this.c.equals("tcp_a19")) {
                        i.d(this.a);
                        return;
                    } else if (this.c.equals("tcp_a11")) {
                        cn.jiguang.ai.a.d("JCoreTCPManager", "resgiter success:" + cn.jiguang.sdk.impl.b.g(this.b));
                        b a = b.a();
                        Context context = this.b;
                        a.a(context, 0, 0, cn.jiguang.sdk.impl.b.g(context));
                        d.a(this.b, "on_register", null);
                        return;
                    } else if (this.c.equals("tcp_a10")) {
                        i.e(this.a);
                        return;
                    } else if (this.c.equals("tcp_a9")) {
                        i.a(this.a, this.b);
                        return;
                    } else if (this.c.equals("tcp_a8")) {
                        i.b(this.a, this.b);
                        return;
                    } else if (this.c.equals("tcp_a2")) {
                        g.a().a(this.b, false);
                        this.a.a(this.d);
                        return;
                    } else if (this.c.equals("tcp_a13")) {
                        cn.jiguang.ai.a.d("JCoreTCPManager", "resgiter failed");
                        Bundle bundle2 = this.d;
                        if (bundle2 != null) {
                            int i3 = bundle2.getInt("resCode", 0);
                            this.a.c = i3;
                            Context context2 = this.b;
                            String a2 = cn.jiguang.ap.g.a(i3);
                            cn.jiguang.ai.a.j("ConnectingHelper", "Register Failed with server error - code:" + i3);
                            if (!TextUtils.isEmpty(a2)) {
                                cn.jiguang.ai.a.h("ConnectingHelper", "Local error description: " + a2);
                            }
                            b.a().a(context2, 0, i3, a2);
                            String i4 = cn.jiguang.sdk.impl.b.i(context2);
                            if (i3 != 11) {
                                if (i3 == 1012) {
                                    cn.jiguang.af.c.a(context2);
                                    return;
                                }
                                if (i3 != 10001) {
                                    switch (i3) {
                                        case 1005:
                                            str = "包名: " + context2.getPackageName() + " 与 AppKey:" + i4 + "不匹配";
                                            break;
                                        case PointerIconCompat.TYPE_CELL /* 1006 */:
                                            str = "包名: " + context2.getPackageName() + " 不存在";
                                            break;
                                        case PointerIconCompat.TYPE_CROSSHAIR /* 1007 */:
                                            cn.jiguang.ai.a.e("ConnectingHelper", "IMEI is duplicated reported by server. Give up now. ");
                                            return;
                                        case PointerIconCompat.TYPE_TEXT /* 1008 */:
                                            str = " AppKey:" + i4 + " 是无效的AppKey,请确认与JIGUANG web端的AppKey一致";
                                            break;
                                        case PointerIconCompat.TYPE_VERTICAL_TEXT /* 1009 */:
                                            str = " AppKey:" + i4 + " 非android AppKey";
                                            break;
                                        default:
                                            cn.jiguang.ai.a.e("ConnectingHelper", "Unhandled server response error code - " + i3);
                                            return;
                                    }
                                } else {
                                    str = " 未在manifest中配置AppKey";
                                }
                                cn.jiguang.ap.a.a(context2, str, -1);
                                return;
                            }
                            return;
                        }
                        return;
                    } else if (this.c.equals("tcp_a12")) {
                        cn.jiguang.ai.a.d("JCoreTCPManager", "login failed");
                        Bundle bundle3 = this.d;
                        if (bundle3 != null) {
                            i.b(this.a, bundle3.getInt("resCode", 0));
                            return;
                        }
                        return;
                    } else if (!this.c.equals("tcp_a14")) {
                        if (this.c.equals("tcp_a15")) {
                            this.a.b(this.d);
                            return;
                        } else if (this.c.equals("tcp_a16")) {
                            this.a.c(this.d);
                            return;
                        } else if (this.c.equals("tcp_a17")) {
                            i.f(this.a);
                            return;
                        } else if (this.c.equals("tcp_a18")) {
                            i.g(this.a);
                            return;
                        } else if (this.c.equals("tcp_a6")) {
                            Bundle bundle4 = this.d;
                            if (bundle4 != null) {
                                long j4 = bundle4.getLong("rid", -1L);
                                if (j4 > 0) {
                                    o.a().a(this.b, j4);
                                    return;
                                }
                                return;
                            }
                            return;
                        } else if (!this.c.equals("tcp_a7")) {
                            if (this.c.equals("tcp_a21")) {
                                this.a.e();
                                return;
                            } else if (this.c.equals("tcp_a22")) {
                                this.a.b();
                                return;
                            } else {
                                return;
                            }
                        } else {
                            Bundle bundle5 = this.d;
                            if (bundle5 != null) {
                                long j5 = bundle5.getLong("rid", -1L);
                                if (j5 > 0) {
                                    o.a().a(j5);
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                    } else {
                        iVar2 = this.a;
                    }
                }
                iVar = this.a.a;
                if (iVar != null) {
                    iVar3 = this.a.a;
                    if (iVar3.c() != null) {
                        byte[] byteArray2 = this.d.getByteArray(SDKConstants.PARAM_A2U_BODY);
                        int i5 = this.d.getInt("cmd", -1);
                        int i6 = this.d.getInt("ver", -1);
                        long j6 = this.d.getLong("rid", -1L);
                        String string2 = this.d.getString("sdk_type");
                        cn.jiguang.ai.a.c("JCoreTCPManager", "send data,cmd:" + i5 + ",ver:" + i6 + ",rid:" + j6 + ",body size:" + byteArray2.length);
                        if (i5 >= 0 && i6 >= 0 && j6 >= 0) {
                            if (this.c.equals("tcp_a3")) {
                                o.a().a(this.b, j6, i5, i6, byteArray2, string2);
                                return;
                            }
                            if (this.c.equals("tcp_a20")) {
                                long j7 = this.d.getLong("uid", 0L);
                                if (j7 == 0) {
                                    cn.jiguang.ai.a.g("JCoreTCPManager", "share response uid is 0");
                                    return;
                                }
                                j = j7;
                            } else {
                                j = 0;
                            }
                            i.a().c().c().a(cn.jiguang.ak.b.a(this.b, i5, i6, j6, byteArray2, j));
                            return;
                        }
                        return;
                    }
                    return;
                }
                cn.jiguang.ai.a.c("JCoreTCPManager", "send data failed:tcp breaked,will restart");
                iVar2 = this.a;
            }
            iVar2.h();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
