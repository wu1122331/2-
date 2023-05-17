package cn.jiguang.ah;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import cn.jiguang.api.JCoreManager;
import cn.jiguang.api.JDispatchAction;
import java.nio.ByteBuffer;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class e extends JDispatchAction {
    @Override // cn.jiguang.api.JDispatchAction
    public void dispatchMessage(Context context, String str, int i, int i2, long j, long j2, ByteBuffer byteBuffer) {
        try {
            if (i == 19) {
                i.a().a(context, "tcp_a18", null);
            } else if (i == 30 || i == 32) {
                cn.jiguang.am.a.a().a(0, i);
            } else if (i == 25) {
                Bundle bundle = new Bundle();
                bundle.putByteArray("RESPONSE_BODY", byteBuffer.array());
                d.a(context, "cmd", bundle);
            } else if (i != 26) {
            } else {
                short s = byteBuffer.getShort();
                if (s == 0) {
                    q.a().a(context, j2);
                } else {
                    q.a().a(j2, s);
                }
            }
        } catch (Throwable th) {
            cn.jiguang.ai.a.g("JCoreDispatchAction", "dispatchMessage failed:" + th.getMessage());
        }
    }

    @Override // cn.jiguang.api.JDispatchAction
    public short getLogPriority(String str) {
        return (short) 0;
    }

    @Override // cn.jiguang.api.JDispatchAction
    public short getLoginFlag(String str) {
        return (short) 0;
    }

    @Override // cn.jiguang.api.JDispatchAction
    public short getRegFlag(String str) {
        return (short) 0;
    }

    @Override // cn.jiguang.api.JDispatchAction
    public short getRegPriority(String str) {
        return (short) 3;
    }

    @Override // cn.jiguang.api.JDispatchAction
    public String getReportVersionKey(String str) {
        return "core_sdk_ver";
    }

    @Override // cn.jiguang.api.JDispatchAction
    public String getSdkVersion(String str) {
        return JCoreManager.SDK_VERSION;
    }

    @Override // cn.jiguang.api.JDispatchAction
    public short getUserCtrlProperty(String str) {
        return (short) 6;
    }

    @Override // cn.jiguang.api.JDispatchAction
    public void handleMessage(Context context, String str, Object obj) {
    }

    @Override // cn.jiguang.api.JDispatchAction
    public boolean isSupportedCMD(String str, int i) {
        return i == 0 || i == 1 || i == 19 || i == 25 || i == 26 || i == 30 || i == 32;
    }

    @Override // cn.jiguang.api.JDispatchAction
    public void onActionRun(Context context, String str, String str2, Bundle bundle) {
        if (bundle != null) {
            try {
                if (TextUtils.isEmpty(str2)) {
                    return;
                }
                if (str2.equals("asm")) {
                    cn.jiguang.am.a.a().a(context, bundle);
                } else if (!str2.equals("asmr")) {
                    if (!str2.equals("lbsenable")) {
                        if (str2.equals("lbsforenry")) {
                            long j = bundle.getLong("forenry");
                            cn.jiguang.ai.a.c("JCoreDispatchAction", "setLbsPermissionDialogShieldDelay=" + j);
                            cn.jiguang.ae.b[] bVarArr = new cn.jiguang.ae.b[1];
                            bVarArr[0] = cn.jiguang.ae.b.m().a((cn.jiguang.ae.b<Long>) (j > 0 ? Long.valueOf(j) : null));
                            cn.jiguang.ae.c.a(context, bVarArr);
                            return;
                        } else if (!str2.equals("notification_state")) {
                            if (str2.equals("old_cmd")) {
                                d.a(context, str2, bundle);
                                return;
                            }
                            return;
                        }
                    }
                    d.a(context, str2, bundle);
                } else {
                    cn.jiguang.am.a a = cn.jiguang.am.a.a();
                    String string = bundle.getString("data");
                    if (TextUtils.isEmpty(string)) {
                        return;
                    }
                    JSONObject jSONObject = new JSONObject(string);
                    String optString = jSONObject.optString("mtmmi");
                    String optString2 = jSONObject.optString("ktmfp");
                    String optString3 = jSONObject.optString("ktma");
                    String optString4 = jSONObject.optString("ktmr");
                    String optString5 = jSONObject.optString("ktmu");
                    String optString6 = jSONObject.optString("asmrc", "0");
                    cn.jiguang.ai.a.c("ShareProcessManager", "msg response,msgId:" + optString + ",fromPkg:" + optString2 + ",appKey:" + optString3 + ",rid:" + optString4 + ",uid:" + optString5 + ",responseCode:" + optString6);
                    if (optString6.equals("0") && !TextUtils.isEmpty(optString5) && !TextUtils.isEmpty(optString) && !TextUtils.isEmpty(optString3) && !TextUtils.isEmpty(optString4)) {
                        cn.jiguang.ai.a.c("ShareProcessManager", "response success,will send msg response to server");
                        long parseLong = Long.parseLong(optString);
                        cn.jiguang.ap.e eVar = new cn.jiguang.ap.e(20480);
                        eVar.b(0);
                        eVar.a(0);
                        eVar.b(parseLong);
                        eVar.a(optString3);
                        cn.jiguang.sdk.impl.b.b(context, cn.jiguang.sdk.impl.a.d, 4, 2, o.b(), Long.parseLong(optString5), eVar.b());
                    } else if (optString6.equals("1") && !TextUtils.isEmpty(optString5)) {
                        a.a(context, Long.parseLong(optString5));
                    } else if (!optString6.equals("3") || TextUtils.isEmpty(optString5)) {
                        cn.jiguang.ai.a.c("ShareProcessManager", "invalid msg response");
                    } else {
                        a.a(context, Long.parseLong(optString5));
                        cn.jiguang.am.a.a(context, optString2);
                    }
                }
            } catch (Throwable th) {
                cn.jiguang.ai.a.g("JCoreDispatchAction", "onActionRun failed:" + th.getMessage());
            }
        }
    }
}
