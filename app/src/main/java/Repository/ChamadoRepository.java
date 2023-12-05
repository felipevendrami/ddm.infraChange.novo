package Repository;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;

import androidx.room.Room;

import java.util.List;

import DAO.AppDataBase;
import Model.Chamado;
import Model.ImagemChamado;
import Model.LocalizacaoChamado;

public class ChamadoRepository {

    private Context context;
    private AppDataBase appDataBase;

    public ChamadoRepository(Context context) {
        this.context = context;
        this.appDataBase = Room.databaseBuilder(context,
                AppDataBase.class, "ddm.infraChange").allowMainThreadQueries().build();
    }

    public void insertChamado(Chamado chamado, LocalizacaoChamado localizacaoChamado) throws Exception{
        try {
            appDataBase.chamadoDao().insert(chamado);
            localizacaoChamado.setId(appDataBase.chamadoDao().getUltimoChamado().getId());
            appDataBase.localizacaoChamadoDAO().insert(localizacaoChamado);
            for(ImagemChamado imagemChamado : chamado.getImagensChamado()){
                imagemChamado.setIdChamado(appDataBase.chamadoDao().getUltimoChamado().getId());
                appDataBase.imagemChamadoDao().insert(imagemChamado);
            }
        } catch (Exception e){
            throw new Exception(e.getMessage()/*"Não foi possível registrar o chamado. Tente novamente."*/);
        }
    }

    public List<Chamado> retornaChamadosAbertosdaPessoa(String pessoa) throws Exception{
        try {
            return appDataBase.chamadoDao().getAllChamadosAbertos(pessoa);
        } catch (Exception e){
            Log.e(null, e.getMessage());
            throw new Exception("Não foi possível carregar os chamados. Tente novamente.");
        }
    }

    public List<Chamado> retornaTodosChamadosdaPessoa(String pessoa){
        try {
            return appDataBase.chamadoDao().getAllChamados(pessoa);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Chamado> getAll(){
        return appDataBase.chamadoDao().getAll();
    }

    public List<ImagemChamado> getImagensChamado(long idChamado) throws Exception{
        try {
            return appDataBase.imagemChamadoDao().getImagensChamadoByIdChamado(idChamado);
        } catch (Exception e){
            throw new Exception("Não foi possível buscar as imagens do chamado #" + idChamado + ".");
        }
    }
}
