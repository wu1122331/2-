package cn.jiguang.d;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;

/* loaded from: classes.dex */
final class c implements a {
    private IBinder a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(IBinder iBinder) {
        this.a = iBinder;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.a;
    }

    @Override // cn.jiguang.d.a
    public final String bind(a aVar, String str) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("cn.jiguang.android.IDataShare");
            obtain.writeStrongBinder(aVar != null ? aVar.asBinder() : null);
            obtain.writeString(str);
            this.a.transact(4, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readString();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // cn.jiguang.d.a
    public final Bundle execute(String str, String str2, Bundle bundle) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("cn.jiguang.android.IDataShare");
            obtain.writeString(str);
            obtain.writeString(str2);
            if (bundle != null) {
                obtain.writeInt(1);
                bundle.writeToParcel(obtain, 0);
            } else {
                obtain.writeInt(0);
            }
            this.a.transact(3, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // cn.jiguang.d.a
    public final IBinder getBinderByType(String str, String str2) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("cn.jiguang.android.IDataShare");
            obtain.writeString(str);
            obtain.writeString(str2);
            this.a.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readStrongBinder();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // cn.jiguang.d.a
    public final void onAction(String str, String str2, Bundle bundle) {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("cn.jiguang.android.IDataShare");
            obtain.writeString(str);
            obtain.writeString(str2);
            if (bundle != null) {
                obtain.writeInt(1);
                bundle.writeToParcel(obtain, 0);
            } else {
                obtain.writeInt(0);
            }
            this.a.transact(2, obtain, null, 1);
        } finally {
            obtain.recycle();
        }
    }
}
