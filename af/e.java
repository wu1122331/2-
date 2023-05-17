package cn.jiguang.af;

import android.content.Context;
import java.security.SecureRandom;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;

/* loaded from: classes.dex */
public final class e {
    private static final Comparator<d> a = new f();

    public static LinkedHashSet<h> a(Context context, LinkedHashSet<h> linkedHashSet, long j) {
        if ((linkedHashSet != null ? linkedHashSet.size() : 0) == 0) {
            return new LinkedHashSet<>();
        }
        LinkedList linkedList = new LinkedList();
        Iterator<h> it = linkedHashSet.iterator();
        boolean z = true;
        while (it.hasNext()) {
            h next = it.next();
            if (next.a()) {
                cn.jiguang.ae.b<String> f = cn.jiguang.ae.b.f(next.toString());
                d a2 = d.a((String) cn.jiguang.ae.c.a(context, f));
                if (a2 == null) {
                    a2 = new d(next);
                }
                if (z) {
                    a2.e = 1;
                    z = false;
                }
                if (j > 0) {
                    a2.c = j;
                    cn.jiguang.ae.c.a(context, f.a((cn.jiguang.ae.b<String>) a2.a()));
                }
                linkedList.add(a2);
            }
        }
        LinkedList a3 = a(linkedList, a);
        LinkedHashSet<h> linkedHashSet2 = new LinkedHashSet<>();
        Iterator it2 = a3.iterator();
        while (it2.hasNext()) {
            Iterator it3 = ((LinkedList) it2.next()).iterator();
            while (it3.hasNext()) {
                linkedHashSet2.add(((d) it3.next()).a);
            }
        }
        return linkedHashSet2;
    }

    private static <T> LinkedList<LinkedList<T>> a(Collection<T> collection, Comparator<T> comparator) {
        int size = collection.size();
        if (size == 0) {
            return new LinkedList<>();
        }
        LinkedList<LinkedList<T>> linkedList = new LinkedList<>();
        if (size == 1) {
            linkedList.add(new LinkedList<>(collection));
            return linkedList;
        }
        SecureRandom secureRandom = new SecureRandom();
        for (T t : collection) {
            boolean z = false;
            for (int i = 0; i < linkedList.size(); i++) {
                LinkedList<T> linkedList2 = linkedList.get(i);
                int compare = comparator.compare(t, linkedList2.getFirst());
                if (compare == 0) {
                    linkedList2.add(secureRandom.nextInt(linkedList2.size() + 1), t);
                } else if (compare < 0) {
                    LinkedList<T> linkedList3 = new LinkedList<>();
                    linkedList3.add(t);
                    linkedList.add(i, linkedList3);
                }
                z = true;
            }
            if (!z) {
                LinkedList<T> linkedList4 = new LinkedList<>();
                linkedList4.add(t);
                linkedList.add(linkedList4);
            }
        }
        return linkedList;
    }

    public static void a(Context context, h hVar, int i, long j) {
        if (hVar != null) {
            cn.jiguang.ae.b<String> f = cn.jiguang.ae.b.f(hVar.toString());
            d a2 = d.a((String) cn.jiguang.ae.c.a(context, f));
            if (a2 == null) {
                a2 = new d(hVar);
            }
            a2.d = j;
            a2.b = i;
            cn.jiguang.ae.c.a(context, f.a((cn.jiguang.ae.b<String>) a2.a()));
        }
    }
}
