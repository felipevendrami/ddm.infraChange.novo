package Controller;

import android.content.Context;
import android.net.Uri;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;
import Fragment.VisualizacaoSolicitacaoFragment;
import Model.Chamado;
import Model.ImagemChamado;
import Observer.ChamadoControllerObserver;
import Observer.ChamadoFragmentObserver;
import Repository.ChamadoRepository;
import VisualComponent.ChamadoRecyclerViewAdapter;
import VisualComponent.ImagensChamadoRecyclerViewAdapter;
import ddm.ddminfrachange.R;

public class ChamadoController implements ChamadoControllerObserver {

    private ChamadoFragmentObserver fragmentObserver;
    private ChamadoRepository chamadoRepository;
    private FragmentManager fragmentManager;
    private Context context;
    private Chamado chamadoSelecionado;

    public ChamadoController(ChamadoFragmentObserver fragmentObserver, Context context) {
        this.fragmentObserver = fragmentObserver;
        this.chamadoRepository = new ChamadoRepository(context);
        this.context = context;
    }

    public void setFragmentManager(FragmentManager fragmentManager){
        this.fragmentManager = fragmentManager;
    }

    public void setObserver(ChamadoFragmentObserver observer){
        this.fragmentObserver = observer;
    }

    public void gravarChamado(List<Uri> pathsImagensCapturadas, String... dados) throws Exception{
        try {
            Chamado chamado = new Chamado(dados[0], dados[1]);
            chamado.setLocalizacao(dados[2].equals("Sim") ? true : false);
            for(Uri uri : pathsImagensCapturadas){
                ImagemChamado imagemChamado = new ImagemChamado(uri.toString());
                chamado.addImagemChamado(imagemChamado);
            }
            this.chamadoRepository.insertChamado(chamado);
            this.fragmentObserver.exibindoToast("Chamado registrado com sucesso !");
            this.fragmentObserver.retornandoHome();
        } catch (Exception e){
            this.fragmentObserver.exibindoToast(e.getMessage());
        }
    }

    public void listarChamadosAbertos() throws Exception{
        try {
            //ChamadoRecyclerViewAdapter listaChamados = new ChamadoRecyclerViewAdapter(this.chamadoRepository.retornaChamadosAbertosdaPessoa("Ciclano da Silva"));
            List<Chamado> listChamados = this.chamadoRepository.retornaChamadosAbertosdaPessoa("Ciclano da Silva");
            ChamadoRecyclerViewAdapter adapterChamados = new ChamadoRecyclerViewAdapter(listChamados, this);
            this.fragmentObserver.carregandoListaChamados(adapterChamados);
            this.fragmentObserver.exibindoToast("Chamados carregados com sucesso !");
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public void listarTodosChamados() throws Exception{
        try {
            List<Chamado> listChamados = this.chamadoRepository.getAll();
            ChamadoRecyclerViewAdapter adapterChamados = new ChamadoRecyclerViewAdapter(listChamados, this);
            this.fragmentObserver.carregandoListaChamados(adapterChamados);
            this.fragmentObserver.exibindoToast("Chamados carregados com sucesso !");
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void selecionandoChamado(Chamado chamadoSelecionado) {
        this.chamadoSelecionado = chamadoSelecionado;
        FragmentTransaction fragmentTransaction = this.fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, new VisualizacaoSolicitacaoFragment(this.fragmentManager, this)).commitNow();
        this.fragmentObserver.carregandoChamadoSelecionado(chamadoSelecionado);
    }

    public void listarImagens(List<Uri> pathsImagens) throws Exception{
        try {
            ImagensChamadoRecyclerViewAdapter adapterImagens = new ImagensChamadoRecyclerViewAdapter(pathsImagens, this.context, this);
            this.fragmentObserver.carregandoBitmapImages(adapterImagens);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public void listarImagens() throws Exception{
        try {
            List<ImagemChamado> pathsImagens = this.chamadoRepository.getImagensChamado(chamadoSelecionado.getId());
            List<Uri> urisImagens = new ArrayList<>();
            for(ImagemChamado imagemChamado : pathsImagens){
                Uri uri = Uri.parse(imagemChamado.getPath());
                urisImagens.add(uri);
            }
            ImagensChamadoRecyclerViewAdapter adapterImagens = new ImagensChamadoRecyclerViewAdapter(urisImagens, this.context, this);
            this.fragmentObserver.carregandoBitmapImages(adapterImagens);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
