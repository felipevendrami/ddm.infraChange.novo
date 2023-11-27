package DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import Model.ImagemChamado;

@Dao
public interface ImagemChamadoDao {

    @Insert
    void insert(ImagemChamado imagemChamado);
    @Query("SELECT * FROM imagem_chamado WHERE idChamado = :idChamado")
    List<ImagemChamado> getImagensChamadoByIdChamado(long idChamado);
}
