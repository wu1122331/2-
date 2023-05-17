package cn.jiguang.ag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes.dex */
public final class f implements Cloneable {
    private static j[] d = new j[0];
    private static i[] e = new i[0];
    private e a;
    private List[] b;
    private int c;

    public f() {
        this(new e());
    }

    private f(c cVar) {
        this(new e(cVar));
        for (int i = 0; i < 4; i++) {
            try {
                int b = this.a.b(i);
                if (b > 0) {
                    this.b[i] = new ArrayList(b);
                }
                for (int i2 = 0; i2 < b; i2++) {
                    this.b[i].add(j.a(cVar, i));
                }
            } catch (IOException e2) {
                throw e2;
            }
        }
        this.c = cVar.a();
    }

    private f(e eVar) {
        this.b = new List[4];
        this.a = eVar;
    }

    public f(byte[] bArr) {
        this(new c(bArr));
    }

    public static f a(j jVar) {
        f fVar = new f();
        List[] listArr = fVar.b;
        if (listArr[0] == null) {
            listArr[0] = new LinkedList();
        }
        fVar.a.a(0);
        fVar.b[0].add(jVar);
        return fVar;
    }

    public final j a() {
        List list = this.b[0];
        if (list == null || list.size() == 0) {
            return null;
        }
        return (j) list.get(0);
    }

    public final i[] a(int i) {
        j[] jVarArr;
        boolean z;
        if (this.b[1] == null) {
            return e;
        }
        LinkedList linkedList = new LinkedList();
        List[] listArr = this.b;
        if (listArr[1] == null) {
            jVarArr = d;
        } else {
            List list = listArr[1];
            jVarArr = (j[]) list.toArray(new j[list.size()]);
        }
        HashSet hashSet = new HashSet();
        for (int i2 = 0; i2 < jVarArr.length; i2++) {
            h b = jVarArr[i2].b();
            if (hashSet.contains(b)) {
                for (int size = linkedList.size() - 1; size >= 0; size--) {
                    i iVar = (i) linkedList.get(size);
                    if (iVar.b().d() == jVarArr[i2].d() && iVar.b().e() == jVarArr[i2].e() && iVar.b().b().equals(b)) {
                        iVar.a(jVarArr[i2]);
                        z = false;
                        break;
                    }
                }
            }
            z = true;
            if (z) {
                linkedList.add(new i(jVarArr[i2]));
                hashSet.add(b);
            }
        }
        return (i[]) linkedList.toArray(new i[linkedList.size()]);
    }

    public final byte[] b(int i) {
        d dVar = new d();
        this.a.a(dVar);
        a aVar = new a();
        this.a.a();
        for (int i2 = 0; i2 < 4; i2++) {
            List[] listArr = this.b;
            if (listArr[i2] != null) {
                int size = listArr[i2].size();
                int a = dVar.a();
                j jVar = null;
                int i3 = 0;
                while (true) {
                    if (i3 < size) {
                        j jVar2 = (j) this.b[i2].get(i3);
                        if (i2 != 3) {
                            if (jVar != null) {
                                if (!(jVar2.d() == jVar.d() && jVar2.e() == jVar.e() && jVar2.b().equals(jVar.b()))) {
                                    a = dVar.a();
                                }
                            }
                            jVar2.a(dVar, aVar);
                            if (dVar.a() > 65535) {
                                dVar.a(a);
                                break;
                            }
                            jVar = jVar2;
                        }
                        i3++;
                    }
                }
            }
        }
        this.c = dVar.a();
        return dVar.b();
    }

    public final Object clone() {
        f fVar = new f();
        int i = 0;
        while (true) {
            List[] listArr = this.b;
            if (i >= listArr.length) {
                fVar.a = (e) this.a.clone();
                fVar.c = this.c;
                return fVar;
            }
            if (listArr[i] != null) {
                fVar.b[i] = new LinkedList(this.b[i]);
            }
            i++;
        }
    }
}
