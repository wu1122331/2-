package cn.jiguang.ac;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class c implements Runnable {
    final /* synthetic */ ArrayList a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(ArrayList arrayList) {
        this.a = arrayList;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ArrayList arrayList;
        ArrayList arrayList2;
        String str;
        BufferedWriter bufferedWriter;
        ArrayList arrayList3;
        String str2;
        BufferedWriter bufferedWriter2 = null;
        try {
            try {
                StringBuilder sb = new StringBuilder();
                str = b.c;
                String sb2 = sb.append(str).append("-").append(cn.jiguang.as.b.b()).append("_1.txt").toString();
                File file = new File(sb2);
                file.getParentFile().mkdirs();
                int i = 2;
                while (true) {
                    if (!file.exists()) {
                        break;
                    }
                    StringBuilder sb3 = new StringBuilder();
                    str2 = b.c;
                    sb2 = sb3.append(str2).append("-").append(cn.jiguang.as.b.b()).append("_").append(i).append(".txt").toString();
                    file = new File(sb2);
                    if (i > 10) {
                        d.f("Logger", "Unexpected error here, so many existed error file.");
                        break;
                    }
                    i++;
                }
                d.a("Logger", "Write log file: " + file.getName());
                if (!file.exists()) {
                    file.createNewFile();
                }
                bufferedWriter = new BufferedWriter(new FileWriter(sb2));
            } catch (Throwable th) {
                th = th;
            }
            try {
                Iterator it = this.a.iterator();
                while (it.hasNext()) {
                    bufferedWriter.write(((String) it.next()) + "\n");
                }
                arrayList3 = b.e;
                arrayList3.clear();
                bufferedWriter.close();
            } catch (Throwable th2) {
                bufferedWriter2 = bufferedWriter;
                th = th2;
                try {
                    d.a("Logger", "write logs to file error", th);
                    arrayList2 = b.e;
                    arrayList2.clear();
                    if (bufferedWriter2 != null) {
                        bufferedWriter2.close();
                    }
                    b.c();
                } catch (Throwable th3) {
                    try {
                        arrayList = b.e;
                        arrayList.clear();
                        if (bufferedWriter2 != null) {
                            bufferedWriter2.close();
                        }
                    } catch (Throwable th4) {
                        d.a("Logger", "close file stream error", th4);
                    }
                    throw th3;
                }
            }
        } catch (Throwable th5) {
            d.a("Logger", "close file stream error", th5);
        }
        b.c();
    }
}
