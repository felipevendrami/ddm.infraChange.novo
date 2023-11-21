package DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import Model.Usuario;

@Dao
public interface UsuarioDao {

    @Insert
    void insert(Usuario usuario);
    @Query("SELECT * FROM usuario")
    List<Usuario> getAllUsuarios();
    @Query("SELECT * FROM usuario WHERE nome = :nome")
    List<Usuario> getUsuarioByName(String nome);
    @Query("SELECT * FROM usuario WHERE email = :email")
    Usuario getUsuarioByEmail(String email);
}
