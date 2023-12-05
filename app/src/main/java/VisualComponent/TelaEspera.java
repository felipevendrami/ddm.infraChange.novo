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
        super.show();
    }
}
