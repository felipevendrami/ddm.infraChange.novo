package Observer;

import Model.Chamado;
import VisualComponent.ChamadoRecyclerViewAdapter;

public interface ChamadoFragmentObserver {

    void exibindoToast(String mensagem);
    void retornandoHome();
    void carregandoListaChamados(ChamadoRecyclerViewAdapter adapter);
    void carregandoChamadoSelecionado(Chamado chamado);
}
