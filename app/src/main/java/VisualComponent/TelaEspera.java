package VisualComponent;

import android.app.ProgressDialog;
import android.content.Context;

public class TelaEspera extends ProgressDialog {

    public TelaEspera(Context context, String mensagem) {
        super(context);
        setMessage(mensagem);
    }

    @Override
    public void show() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        super.show();
    }

    @Override
    public void dismiss() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        super.dismiss();
    }
}
