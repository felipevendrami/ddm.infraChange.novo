package DAO;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import Model.ChamadoComImagemChamado;

@Dao
public interface ChamadoComImagemChamadoDao {
    @Transaction
    @Query("SELECT * FROM chamado")
    public List<ChamadoComImagemChamado> getChamadoComImagemChamado();
}
