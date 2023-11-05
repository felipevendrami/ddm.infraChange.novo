package Repository;

import android.content.Context;

import androidx.room.Room;

import java.util.List;

import DAO.AppDataBase;
import Model.Usuario;

public class UsuarioRepository {
    private Context context;
    private AppDataBase appDataBase;

    public UsuarioRepository(Context context) {
        this.context = context;
        this.appDataBase = Room.databaseBuilder(context,
                AppDataBase.class, "ddm.infraChange").allowMainThreadQueries().build();
    }

    public boolean insertUsuario(Usuario usuario) {
        try {
            // Verifique se o email já está cadastrado
            Usuario existingUser = appDataBase.usuarioDao().getUsuarioByEmail(usuario.getEmail());
            if (existingUser != null) {
                // O email já está cadastrado, retorne falso
                return false;
            } else {
                // O email não está cadastrado, prossiga com a inserção
                appDataBase.usuarioDao().insert(usuario);
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Usuario retornaUsuarioEmail(String email) {
        try {
            appDataBase.usuarioDao().getUsuarioByEmail(email);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return null; // Não sei pq, mas tive q add esse return.
    }

    public List<Usuario> retornaTodosUsuarios() {
        try {
            return appDataBase.usuarioDao().getAllUsuarios();
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Usuario> retornaUsuarioNome(String nome) {
        try {
            return appDataBase.usuarioDao().getUsuarioByName(nome);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
