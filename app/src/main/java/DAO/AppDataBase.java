package DAO;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import Model.Chamado;
import Model.ImagemChamado;
import Model.Usuario;

@Database(entities = {Chamado.class, Usuario.class, ImagemChamado.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    public abstract ChamadoDao chamadoDao();
    public abstract UsuarioDao usuarioDao();
    public abstract ImagemChamadoDao imagemChamadoDao();
}
