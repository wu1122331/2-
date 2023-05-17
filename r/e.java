package cn.jiguang.r;

import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public final class e {
    private static cn.jiguang.q.b a(String str, int i, List<cn.jiguang.q.b> list) {
        if (list != null && !list.isEmpty() && !TextUtils.isEmpty(str)) {
            for (cn.jiguang.q.b bVar : list) {
                if (a(str, i, bVar)) {
                    return bVar;
                }
            }
        }
        return null;
    }

    public static List<cn.jiguang.q.b> a(Context context) {
        cn.jiguang.q.b bVar;
        cn.jiguang.q.b a;
        ArrayList<cn.jiguang.q.b> arrayList = null;
        if (cn.jiguang.sdk.impl.b.a(context, false, "need get getDefaultApiSimInfo")) {
            bVar = null;
        } else {
            bVar = new cn.jiguang.q.b();
            bVar.b = cn.jiguang.ap.a.c(context, "");
            bVar.c = c.b(context, "");
            bVar.a = cn.jiguang.ap.a.b(context, "");
        }
        ArrayList<cn.jiguang.q.b> a2 = f.a(context);
        ArrayList<cn.jiguang.q.b> a3 = c.a(context);
        if (a2 != null && !a2.isEmpty()) {
            for (int size = a2.size() - 1; size >= 0; size--) {
                if (a2.get(size).b()) {
                    a2.remove(size);
                }
            }
        }
        if (a2 != null && !a2.isEmpty()) {
            for (cn.jiguang.q.b bVar2 : a2) {
                if (TextUtils.isEmpty(bVar2.a) && !TextUtils.isEmpty(bVar2.b)) {
                    cn.jiguang.q.b a4 = a(bVar2.b, 1, a3);
                    if (a4 != null) {
                        bVar2.a = a4.a;
                    }
                } else if (TextUtils.isEmpty(bVar2.b) && !TextUtils.isEmpty(bVar2.a) && (a = a(bVar2.a, 1, a3)) != null) {
                    bVar2.b = a.b;
                    bVar2.c = a.c;
                }
            }
            return a2;
        } else if (bVar == null || bVar.b()) {
            return a3;
        } else {
            if (a3 != null && a3.size() == 1) {
                if (TextUtils.isEmpty(bVar.a) || !bVar.a.equals(a3.get(0).a)) {
                    if (!TextUtils.isEmpty(bVar.b) && bVar.b.equals(a3.get(0).b)) {
                        bVar.b = "";
                        bVar.c = "";
                        if (bVar.b()) {
                            return a3;
                        }
                    }
                } else if (TextUtils.isEmpty(bVar.b) || bVar.b.equals(a3.get(0).b)) {
                    return a3;
                }
                a3.add(bVar);
                return a3;
            }
            cn.jiguang.q.b a5 = a(bVar.a, 0, a3);
            if (a5 != null && TextUtils.isEmpty(a5.b) && a(bVar.b, 1, a3) == null) {
                String str = bVar.a;
                if (a3 != null && !a3.isEmpty() && !TextUtils.isEmpty(str) && bVar != null) {
                    Iterator<cn.jiguang.q.b> it = a3.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        cn.jiguang.q.b next = it.next();
                        if (a(str, 0, next)) {
                            next.b = bVar.b;
                            next.c = bVar.c;
                            break;
                        }
                    }
                    arrayList = a3;
                }
                return arrayList;
            }
            return a3;
        }
    }

    private static boolean a(String str, int i, cn.jiguang.q.b bVar) {
        return i != 0 ? i != 1 ? i == 2 && str.equals(bVar.c) : str.equals(bVar.b) : str.equals(bVar.a);
    }
}
