package cn.jiguang.ag;

/* loaded from: classes.dex */
public final class o extends j {
    private int e;
    private int f;
    private int g;
    private h h;

    @Override // cn.jiguang.ag.j
    final String a() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.e + " ");
        stringBuffer.append(this.f + " ");
        stringBuffer.append(this.g + " ");
        stringBuffer.append(this.h);
        return stringBuffer.toString();
    }

    @Override // cn.jiguang.ag.j
    final void a(c cVar) {
        this.e = cVar.g();
        this.f = cVar.g();
        this.g = cVar.g();
        this.h = new h(cVar);
    }

    @Override // cn.jiguang.ag.j
    final void a(d dVar, boolean z) {
        dVar.c(this.e);
        dVar.c(this.f);
        dVar.c(this.g);
        this.h.a(dVar, (a) null, true);
    }

    public final int h() {
        return this.g;
    }

    public final h i() {
        return this.h;
    }
}
