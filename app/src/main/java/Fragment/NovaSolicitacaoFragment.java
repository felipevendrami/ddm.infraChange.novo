package Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
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
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import Controller.ChamadoController;
import Model.Chamado;
import Model.LocalizacaoChamado;
import Observer.ChamadoFragmentObserver;
import VisualComponent.ChamadoRecyclerViewAdapter;
import VisualComponent.ImagensChamadoRecyclerViewAdapter;
import VisualComponent.TelaEspera;
import ddm.ddminfrachange.R;

public class NovaSolicitacaoFragment extends Fragment implements ChamadoFragmentObserver {

    private static final int CAPTURE_CODE = 0;
    public static final int PERMISSION_CODE = 1234;
    private Uri image_uri;
    private List<Uri> pathsImagensCapturadas;
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
    private RecyclerView rvImagens;
    // Controller
    private ChamadoController chamadoController;
    private LocalizacaoChamado localizacaoChamado;

    public NovaSolicitacaoFragment() {
    }

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

        this.pathsImagensCapturadas = new ArrayList<>();
        this.chamadoController = new ChamadoController(this, getContext());
        initComponents();
        initSpinnerOptions();
        initRecyclerView();
        initActions();
        return view;
    }

    private void initRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.view.getContext(), LinearLayoutManager.HORIZONTAL, false);
        this.rvImagens.setLayoutManager(layoutManager);
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
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if ((ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) ||
                            (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)) == PackageManager.PERMISSION_DENIED) {
                        String[] permision = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permision, PERMISSION_CODE);
                    } else {
                        abrirCamera();
                    }
                } else {
                    abrirCamera();
                }
            }
        });
        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    recuperarDados();
                    validaDados();
                    telaEspera.show();
                    chamadoController.gravarChamado(pathsImagensCapturadas, descricao, tipoDenuncia, enviaLocalizacao);
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

    private void abrirCamera() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "new image");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From InfraChenge Camera");
        this.image_uri = getContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, this.image_uri);
        this.pathsImagensCapturadas.add(this.image_uri);
        exibindoToast(this.image_uri.toString());
        startActivityForResult(intentCamera, CAPTURE_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case PERMISSION_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    abrirCamera();
                } else {
                    exibindoToast("Permissão negada !");
                }
        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            if (resultCode == Activity.RESULT_OK) {
                this.chamadoController.listarImagens(this.pathsImagensCapturadas);
            }
        } catch (Exception e) {
            exibindoToast(e.getMessage());
        }
    }

    private void validaDados() throws Exception {
        if (this.descricao.split(" ").length < 4) {
            throw new Exception("Sua descrição precisa ter 5 ou mais palavras.");
        }
        if (this.rbSelecionado == null) {
            throw new Exception("É necessario selecionar a Localização.");
        }
        if (this.pathsImagensCapturadas.size() == 0) {
            throw new Exception("É necessario adicionar ao menos uma imagem.");
        }
    }

    private void recuperarDados() {
        this.tipoDenuncia = spTipoDenuncia.getSelectedItem().toString();
        this.descricao = etmDescricao.getText().toString();
        this.rbSelecionado = this.rgLocalizacao.findViewById(this.rgLocalizacao.getCheckedRadioButtonId());
        this.enviaLocalizacao = this.rbSelecionado.getText().toString();
        exibindoToast(this.enviaLocalizacao);
    }

    private void initComponents() {
        this.spTipoDenuncia = this.view.findViewById(R.id.spTipoDenuncia);
        this.etmDescricao = this.view.findViewById(R.id.etmDescricao);
        this.rgLocalizacao = this.view.findViewById(R.id.rgLocalizacao);
        this.btCamera = this.view.findViewById(R.id.btCamera);
        this.rvImagens = this.view.findViewById(R.id.rvImagens);
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

    @Override
    public void carregandoBitmapImages(ImagensChamadoRecyclerViewAdapter adapter) {
        this.rvImagens.setAdapter(adapter);
    }

    public void buscarInformacoesGPS() {

        if (enviaLocalizacao.equals("Sim")) {
            if (ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                String[] permision = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
                requestPermissions(permision, PERMISSION_CODE);

              /*  ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_COARSE_LOCATION}, 1); */
                return;
            }

            LocationManager mLocManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            this.localizacaoChamado = new LocalizacaoChamado();

            //  mLocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, localizacaoChamado);

            if (mLocManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                String texto = "Latitude: " + LocalizacaoChamado.latitude + "\n" +
                        "Longitude: " + LocalizacaoChamado.longitude + "\n";
                exibindoToast(texto);
            } else {
                exibindoToast("GPS Desabilitado");
            }
        }
    }
}
