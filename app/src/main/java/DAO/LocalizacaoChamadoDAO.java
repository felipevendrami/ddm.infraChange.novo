package DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import Model.ImagemChamado;
import Model.LocalizacaoChamado;

@Dao
public interface LocalizacaoChamadoDAO {

    @Insert
    void insert(LocalizacaoChamado localizacaoChamado);
    @Query("SELECT * FROM localizacao_chamado WHERE idChamado = :idChamado")
    LocalizacaoChamado getLocalizacaoChamado(long idChamado);
}

