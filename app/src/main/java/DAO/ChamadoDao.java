package DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;
import Model.Chamado;
import Model.ChamadoComImagemChamado;

@Dao
public interface ChamadoDao {

    @Query("SELECT * FROM chamado WHERE cidadao = :pessoa")
    List<Chamado> getAllChamados(String pessoa);

    @Query("SELECT * FROM chamado WHERE cidadao = :pessoa AND situacao = \"Aberto\"")
    List<Chamado> getAllChamadosAbertos(String pessoa);

    @Query("SELECT * FROM chamado")
    List<Chamado> getAll();

    @Query("SELECT * FROM chamado WHERE id = (SELECT COUNT(id) FROM chamado)")
    Chamado getUltimoChamado();

    @Insert
    void insert(Chamado chamado);

    @Transaction
    @Query("SELECT * FROM chamado WHERE id = :idChamado")
    public List<ChamadoComImagemChamado> getChamadoComImagemChamado(long idChamado);
}
