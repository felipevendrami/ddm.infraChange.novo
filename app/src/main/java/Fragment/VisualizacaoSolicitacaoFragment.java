package Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ddm.ddminfrachange.R;

public class VisualizacaoSolicitacaoFragment extends Fragment {

    // Tela
    private View view;
    public VisualizacaoSolicitacaoFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.view = inflater.inflate(R.layout.fragment_visualizacao_solicitacao, container, false);
        return view;
    }
}