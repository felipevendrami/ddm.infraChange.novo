package Repository;

import android.content.Context;

import androidx.room.Room;

import java.util.List;

import DAO.AppDataBase;
import Model.Chamado;

public class ChamadoRepository {

    private Context context;
    private AppDataBase appDataBase;

    public ChamadoRepository(Context context) {
        this.context = context;
        this.appDataBase = Room.databaseBuilder(context,
                AppDataBase.class, "ddm.infraChange").allowMainThreadQueries().build();
    }

    public void insertChamado(Chamado chamado){
        try {
            appDataBase.chamadoDao().insert(chamado);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return;
        }
    }

    public List<Chamado> retornaChamadosAbertosdaPessoa(String pessoa){
        try {
            return appDataBase.chamadoDao().getAllChamadosAbertos(pessoa);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
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
}