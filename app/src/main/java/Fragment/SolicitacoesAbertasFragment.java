package Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import Controller.ChamadoController;
import Observer.NovaSolicitacaoObserver;
import ddm.ddminfrachange.R;


public class SolicitacoesAbertasFragment extends Fragment implements NovaSolicitacaoObserver {

    // Tela
    private View view;
    private FragmentManager fragmentManager;
    // Componentes da tela
    private RecyclerView rvChamadosAbertos;
    // Controller
    private ChamadoController chamadoController;

    public SolicitacoesAbertasFragment(){}

    public SolicitacoesAbertasFragment(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_solicitacoes_abertas, container, false);
        this.chamadoController = new ChamadoController(this, getContext());

        initRecyclerView();

        return view;
    }

    private void initRecyclerView() {
        this.rvChamadosAbertos = this.view.findViewById(R.id.rvChamadosAbertos);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.view.getContext());
        this.rvChamadosAbertos.setLayoutManager(layoutManager);
    }

    @Override
    public void exibindoToast(String mensagem) {

    }

    @Override
    public void retornandoHome() {

    }
}