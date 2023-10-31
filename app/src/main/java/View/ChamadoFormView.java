package View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import Controller.ChamadoController;
import ddm.ddminfrachange.R;

public class ChamadoFormView extends AppCompatActivity {

    // Dados recuperados
    private String tipoDenuncia;
    private String descricao;
    private boolean enviaLocalizacao;
    // Componentes Visuais
    private Spinner spTipoDenuncia;
    private EditText etmDescricao;
    private RadioGroup rgLocalizacao;
    private RadioButton rbLocalizacaoNao;
    private RadioButton rbLocalizacaoSim;
    private Button btCamera;
    private Button btEnviar;
    private Button btVoltar;
    // Controller
    private ChamadoController chamadoController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chamado_form_view);
        this.chamadoController = new ChamadoController();

        initComponents();
        initSpinnerOptions();
        initActions();
    }

    private void initSpinnerOptions() {
        String[] tiposDenuncia = {"Estradas", "Iluminação Pública", "Prédio Público", "Saneamento", "Limpeza"};
        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(this, R.array.tipos_denuncia, android.R.layout.simple_spinner_item);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spTipoDenuncia.setAdapter(adapterSpinner);
        //this.spTipoDenuncia.setSelection(1);
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
                    chamadoController.gravarChamado(recuperarDados());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private String[] recuperarDados() {
        // FAZER O GET DA LISTA
        this.tipoDenuncia = spTipoDenuncia.getSelectedItem().toString();
        this.descricao = etmDescricao.getText().toString();
        return null;
    }

    private void initComponents() {
        this.spTipoDenuncia = findViewById(R.id.spTipoDenuncia);
        this.etmDescricao = findViewById(R.id.etmDescricao);
        this.rgLocalizacao = findViewById(R.id.rgLocalizacao);
        this.rbLocalizacaoNao = findViewById(R.id.rbLocalizacaoNao);
        this.rbLocalizacaoSim = findViewById(R.id.rbLocalizacaoSim);
        this.btCamera = findViewById(R.id.btCamera);
        this.btEnviar = findViewById(R.id.btEnviar);
        this.btVoltar = findViewById(R.id.btVoltar);
    }
}