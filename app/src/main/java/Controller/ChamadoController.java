package Controller;

import android.content.Context;

import androidx.fragment.app.FragmentManager;

import java.util.List;

import Fragment.VisualizacaoSolicitacaoFragment;
import Model.Chamado;
import Observer.ChamadoControllerObserver;
import Observer.ChamadoFragmentObserver;
import Repository.ChamadoRepository;
import VisualComponent.ChamadoRecyclerViewAdapter;
import ddm.ddminfrachange.R;

public class ChamadoController implements ChamadoControllerObserver {

    private ChamadoFragmentObserver fragmentObserver;
    private ChamadoRepository chamadoRepository;
    private FragmentManager fragmentManager;

    public ChamadoController(ChamadoFragmentObserver fragmentObserver, Context context) {
        this.fragmentObserver = fragmentObserver;
        this.chamadoRepository = new ChamadoRepository(context);
    }

    public void setFragmentManager(FragmentManager fragmentManager){
        this.fragmentManager = fragmentManager;
    }

    public void setObserver(ChamadoFragmentObserver observer){
        this.fragmentObserver = observer;
    }

    public void gravarChamado(String... dados) throws Exception{
        try {
            Chamado chamado = new Chamado(dados[0], dados[1]);
            chamado.setLocalizacao(dados[2] == "Sim" ? true : false);
            this.chamadoRepository.insertChamado(chamado);
            this.fragmentObserver.exibindoToast("Chamado registrado com sucesso !");
            this.fragmentObserver.retornandoHome();
        } catch (Exception e){
            this.fragmentObserver.exibindoToast(e.getMessage());
        }
    }

    public void listarChamadosAbertos() throws Exception{
        try {
            //ChamadoRecyclerViewAdapter listaChamados = new ChamadoRecyclerViewAdapter(this.chamadoRepository.retornaChamadosAbertosdaPessoa("Ciclado"));
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
        this.fragmentManager.beginTransaction().replace(R.id.fragment_container, new VisualizacaoSolicitacaoFragment(this.fragmentManager, this)).commit();
        this.fragmentObserver.carregandoChamadoSelecionado(chamadoSelecionado);
    }
}
