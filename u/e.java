package cn.jiguang.u;

import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;

/* loaded from: classes.dex */
final class e extends PhoneStateListener {
    final /* synthetic */ d a;

    private e(d dVar) {
        this.a = dVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ e(d dVar, byte b) {
        this(dVar);
    }

    @Override // android.telephony.PhoneStateListener
    public final void onSignalStrengthsChanged(SignalStrength signalStrength) {
        cn.jiguang.v.a a;
        int networkId;
        try {
            super.onSignalStrengthsChanged(signalStrength);
            d.a(this.a).f = signalStrength.getGsmSignalStrength();
            CellLocation cellLocation = d.b(this.a).getCellLocation();
            if (cellLocation == null) {
                return;
            }
            if (!(cellLocation instanceof GsmCellLocation)) {
                if (cellLocation instanceof CdmaCellLocation) {
                    CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) cellLocation;
                    d.a(this.a).e = cdmaCellLocation.getBaseStationId();
                    a = d.a(this.a);
                    networkId = cdmaCellLocation.getNetworkId();
                }
                d.b(this.a).listen(this, 0);
            }
            GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
            d.a(this.a).e = gsmCellLocation.getCid();
            a = d.a(this.a);
            networkId = gsmCellLocation.getLac();
            a.d = networkId;
            d.b(this.a).listen(this, 0);
        } catch (Throwable unused) {
        }
    }
}
