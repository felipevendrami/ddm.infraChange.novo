package Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import Controller.ChamadoController;
import Model.Chamado;
import Observer.ChamadoFragmentObserver;
import VisualComponent.ChamadoRecyclerViewAdapter;
import ddm.ddminfrachange.R;

public class VisualizacaoSolicitacaoFragment extends Fragment implements ChamadoFragmentObserver {

    // Tela
    private View view;
    private FragmentManager fragmentManager;
    // Componentes da tela
    private TextView tvIdChamado,tvTipoDenuncia,tvDescricao,tvSituacao;
    private RadioGroup rgLocalizacao;
    private RadioButton rbSelecionado;
    private Button btVoltar;
    // Controller
    private ChamadoController chamadoController;

    public VisualizacaoSolicitacaoFragment() { }

    public VisualizacaoSolicitacaoFragment(FragmentManager fragmentManager, ChamadoController chamadoController) {
        this.fragmentManager = fragmentManager;
        this.chamadoController = chamadoController;
        this.chamadoController.setObserver(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_visualizacao_solicitacao, container, false);
        initComponents();
        initActions();
        return view;
    }

    private void initActions() {
        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retornandoHome();
            }
        });
    }

    private void initComponents() {
        this.tvIdChamado = this.view.findViewById(R.id.tvIdChamado);
        this.tvTipoDenuncia = this.view.findViewById(R.id.tvTipoDenuncia);
        this.tvDescricao = this.view.findViewById(R.id.tvDescricao);
        this.tvSituacao = this.view.findViewById(R.id.tvSituacao);
    }

    @Override
    public void exibindoToast(String mensagem) {
        // SEM IMPLEMENTAÇÃO
    }

    @Override
    public void retornandoHome() {
        this.fragmentManager.beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
    }

    @Override
    public void carregandoListaChamados(ChamadoRecyclerViewAdapter adapter) {
        // SEM IMPLEMENTAÇÃO
    }

    @Override
    public void carregandoChamadoSelecionado(Chamado chamado) {
        this.tvIdChamado.setText("Chamado: #" + chamado.getId());
        this.tvTipoDenuncia.setText(chamado.getTipoDenuncia());
        this.tvDescricao.setText(chamado.getDescricao());
        this.tvSituacao.setText(chamado.getSituacao());
    }
}