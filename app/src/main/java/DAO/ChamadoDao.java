package DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import Model.Chamado;

@Dao
public interface ChamadoDao {

    @Query("SELECT * FROM chamado WHERE cidadao = :pessoa")
    List<Chamado> getAllChamados(String pessoa);

    @Query("SELECT * FROM chamado WHERE cidadao = :pessoa AND situacao = \"Aberto\"")
    List<Chamado> getAllChamadosAbertos(String pessoa);

    @Insert
    void insert(Chamado chamado);
}
