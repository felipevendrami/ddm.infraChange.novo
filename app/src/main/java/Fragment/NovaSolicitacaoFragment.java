package Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import Controller.ChamadoController;
import Model.Chamado;
import Observer.ChamadoFragmentObserver;
import VisualComponent.ChamadoRecyclerViewAdapter;
import VisualComponent.TelaEspera;
import ddm.ddminfrachange.R;

public class NovaSolicitacaoFragment extends Fragment implements ChamadoFragmentObserver {

    // Dados recuperados
    private String tipoDenuncia, descricao, enviaLocalizacao;
    // Tela
    private View view;
    private FragmentManager fragmentManager;
    private TelaEspera telaEspera;
    // Componentes da tela
    private Spinner spTipoDenuncia;
    private EditText etmDescricao;
    private RadioGroup rgLocalizacao;
    private RadioButton rbSelecionado;
    private Button btCamera, btEnviar, btVoltar;
    // Controller
    private ChamadoController chamadoController;

    public NovaSolicitacaoFragment() {}

    public NovaSolicitacaoFragment(FragmentManager fragmentManager, TelaEspera telaDeEspera) {
        this.fragmentManager = fragmentManager;
        this.telaEspera = telaDeEspera;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_nova_solicitacao, container, false);

        this.chamadoController = new ChamadoController(this, getContext());
        initComponents();
        initSpinnerOptions();
        initActions();
        return view;
    }

    private void initSpinnerOptions() {
        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(this.view.getContext(), R.array.tipos_denuncia, android.R.layout.simple_spinner_item);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spTipoDenuncia.setAdapter(adapterSpinner);
        this.spTipoDenuncia.setSelection(0);
    }

    private void initActions() {
        btCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    recuperarDados();
                    validaDados();
                    telaEspera.show();
                    chamadoController.gravarChamado(descricao, tipoDenuncia, enviaLocalizacao);
                } catch (Exception e) {
                    exibindoToast(e.getMessage());
                }
            }
        });
        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retornandoHome();
            }
        });
    }

    private void validaDados() throws Exception {
        if(this.descricao.split(" ").length < 0){
            throw new Exception("Sua descrição precisa ter 5 ou mais palavras.");
        }
        if(this.rbSelecionado == null){
            throw new Exception("É necessario selecionar a Localização.");
        }
    }

    private void recuperarDados() {
        this.tipoDenuncia = spTipoDenuncia.getSelectedItem().toString();
        this.descricao = etmDescricao.getText().toString();
        this.rbSelecionado = this.rgLocalizacao.findViewById(this.rgLocalizacao.getCheckedRadioButtonId());
    }

    private void initComponents() {
        this.spTipoDenuncia = this.view.findViewById(R.id.spTipoDenuncia);
        this.etmDescricao = this.view.findViewById(R.id.etmDescricao);
        this.rgLocalizacao = this.view.findViewById(R.id.rgLocalizacao);
        this.btCamera = this.view.findViewById(R.id.btCamera);
        this.btEnviar = this.view.findViewById(R.id.btEnviar);
        this.btVoltar = this.view.findViewById(R.id.btVoltar);
    }

    @Override
    public void exibindoToast(String mensagem) {
        this.telaEspera.dismiss();
        Toast.makeText(this.view.getContext(), mensagem, Toast.LENGTH_LONG).show();
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
        // SEM IMPLEMENTAÇÃO
    }
}