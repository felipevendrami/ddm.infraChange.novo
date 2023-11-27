package Fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import VisualComponent.ImagensChamadoRecyclerViewAdapter;
import ddm.ddminfrachange.R;

public class VisualizacaoSolicitacaoFragment extends Fragment implements ChamadoFragmentObserver {

    // Tela
    private View view;
    private FragmentManager fragmentManager;
    // Componentes da tela
    private TextView tvIdChamado,tvTipoDenuncia,tvDescricao,tvSituacao;
    private RadioButton rbLocalizacaoNao, rbLocalizacaoSim;
    private RecyclerView rvImagens;
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
        initRecyclerView();
        initActions();
        return view;
    }

    private void initRecyclerView() {
        try {
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.view.getContext(), LinearLayoutManager.HORIZONTAL, false);
            this.rvImagens.setLayoutManager(layoutManager);
            this.chamadoController.listarImagens();
        } catch (Exception e) {
            exibindoToast("Ocorreu algum erro durante a exibição das imagens.");
        }
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
        this.rvImagens = this.view.findViewById(R.id.rvImagens);
        this.rbLocalizacaoNao = this.view.findViewById(R.id.rbLocalizacaoNao);
        this.rbLocalizacaoSim = this.view.findViewById(R.id.rbLocalizacaoSim);
        this.btVoltar = this.view.findViewById(R.id.btVoltar);
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
        this.tvIdChamado.setText("Chamado: #" + (int) chamado.getId());
        this.tvTipoDenuncia.setText(chamado.getTipoDenuncia());
        this.tvDescricao.setText(chamado.getDescricao());
        if (chamado.isLocalizacao() == true) {
            this.rbLocalizacaoSim.setChecked(true);
        } else {
            this.rbLocalizacaoNao.setChecked(true);
        }
        this.tvSituacao.setText(chamado.getSituacao());
    }

    @Override
    public void carregandoBitmapImages(ImagensChamadoRecyclerViewAdapter adapter) {
        this.rvImagens.setAdapter(adapter);
    }
}