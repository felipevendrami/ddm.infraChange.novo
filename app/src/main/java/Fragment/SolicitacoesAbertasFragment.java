package Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import Controller.ChamadoController;
import Model.Chamado;
import Observer.ChamadoFragmentObserver;
import VisualComponent.ChamadoRecyclerViewAdapter;
import VisualComponent.ImagensChamadoRecyclerViewAdapter;
import ddm.ddminfrachange.R;


public class SolicitacoesAbertasFragment extends Fragment implements ChamadoFragmentObserver {

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
        this.chamadoController.setFragmentManager(this.fragmentManager);

        initRecyclerView();
        return view;
    }

    private void initRecyclerView() {
        this.rvChamadosAbertos = this.view.findViewById(R.id.rvChamadosAbertos);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.view.getContext());
        this.rvChamadosAbertos.setLayoutManager(layoutManager);
        try {
            this.chamadoController.listarChamadosAbertos();
        } catch (Exception e){
            exibindoToast(e.getMessage());
        }
    }

    @Override
    public void exibindoToast(String mensagem) {
        Toast.makeText(this.view.getContext(), mensagem, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void retornandoHome() {
        // SEM IMPLEMENTAÇÃO
    }

    @Override
    public void carregandoListaChamados(ChamadoRecyclerViewAdapter adapter) {
        this.rvChamadosAbertos.setAdapter(adapter);
    }

    @Override
    public void carregandoChamadoSelecionado(Chamado chamado) {
        // SEM IMPLEMENTAÇÃO
    }

    @Override
    public void carregandoBitmapImages(ImagensChamadoRecyclerViewAdapter adapter) {
        // SEM IMPLEMENTAÇÃO
    }
}