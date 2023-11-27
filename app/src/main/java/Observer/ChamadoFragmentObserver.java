package Observer;

import Model.Chamado;
import VisualComponent.ChamadoRecyclerViewAdapter;
import VisualComponent.ImagensChamadoRecyclerViewAdapter;

public interface ChamadoFragmentObserver {

    void exibindoToast(String mensagem);
    void retornandoHome();
    void carregandoListaChamados(ChamadoRecyclerViewAdapter adapter);
    void carregandoChamadoSelecionado(Chamado chamado);
    void carregandoBitmapImages(ImagensChamadoRecyclerViewAdapter adapter);
}
