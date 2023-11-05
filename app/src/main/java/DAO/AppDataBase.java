package DAO;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import Model.Chamado;
import Model.Usuario;

@Database(entities = {Chamado.class, Usuario.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    public abstract ChamadoDao chamadoDao();
    public abstract UsuarioDao usuarioDao();
}
