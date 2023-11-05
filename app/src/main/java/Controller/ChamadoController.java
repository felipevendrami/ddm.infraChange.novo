package Controller;

import android.content.Context;

import Model.Chamado;
import Observer.NovaSolicitacaoObserver;
import Repository.ChamadoRepository;

public class ChamadoController {

    private NovaSolicitacaoObserver fragmentNovaSolicitacao;
    private ChamadoRepository chamadoRepository;

    public ChamadoController(NovaSolicitacaoObserver fragmentNovaSolicitacao, Context context) {
        this.fragmentNovaSolicitacao = fragmentNovaSolicitacao;
        this.chamadoRepository = new ChamadoRepository(context);
    }

    public void gravarChamado(String... dados) throws Exception{
        try {
            Chamado chamado = new Chamado(dados[0], dados[1]);
            chamado.setLocalizacao(dados[2] == "Sim" ? true : false);
            this.chamadoRepository.insertChamado(chamado);
            this.fragmentNovaSolicitacao.exibindoToast("Chamado registrado com sucesso !");
            this.fragmentNovaSolicitacao.retornandoHome();
        } catch (Exception e){
            this.fragmentNovaSolicitacao.exibindoToast(e.getMessage());
        }
    }
}
