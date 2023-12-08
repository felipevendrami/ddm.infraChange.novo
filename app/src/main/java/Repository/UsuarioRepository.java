package Repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

import DAO.AppDataBase;
import Model.Usuario;

public class UsuarioRepository {
    private Context context;
    private AppDataBase appDataBase;
//    private MutableLiveData<Boolean> resultLiveData;
    private MutableLiveData<Usuario> resultLiveData;

    public UsuarioRepository(Context context) {
        this.context = context;
        this.appDataBase = Room.databaseBuilder(context,
                AppDataBase.class, "ddm.infraChange").allowMainThreadQueries().build();
    }

//    private <T> LiveData<T> executeOperation(Operation<T> operation) {
//        MutableLiveData<T> resultLiveData = new MutableLiveData<>();
//        try {
//            T result = operation.run();
//            resultLiveData.setValue(result);
//        } catch (Exception e) {
//            Log.e(null, e.getMessage());
//            resultLiveData.setValue(null);
//        }
//        return resultLiveData;
//    }

//    public LiveData<Boolean> insertUsuario(Usuario usuario) {
//        this.resultLiveData = new MutableLiveData<>();
//
//        try {
//            appDataBase.usuarioDao().insert(usuario);
//            resultLiveData.setValue(true);
//        } catch (Exception e) {
//            Log.e("insertUsuario", e.getMessage());
//            resultLiveData.setValue(false);
//        }
//
//        return resultLiveData;
//    }

    public void insertUsuario(Usuario usuario) {
        try {
            appDataBase.usuarioDao().insert(usuario);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

//    public LiveData<Usuario> retornaUsuarioEmail(String email) {
//        this.resultLiveData = new MutableLiveData<>();
//
//        try {
//            Usuario usuario = appDataBase.usuarioDao().getUsuarioByEmail(email);
//            if (usuario == null) {
//                resultLiveData.setValue(null);
//            } else {
//                resultLiveData.setValue(usuario);
//            }
//        } catch (Exception e) {
//            Log.e("retornaUsuarioEmail", e.getMessage());
//            resultLiveData.setValue(null);
//        }
//
//        return resultLiveData;
//    }

//    public LiveData<Boolean> retornaUsuarioEmail(String email) {
//        MutableLiveData<Usuario> resultLiveData = new MutableLiveData<>();
//
//        try {
//            Usuario usuario = appDataBase.usuarioDao().getUsuarioByEmail(email);
//            if (usuario != null) {
//                resultLiveData.setValue(false);
//            } else {
//                resultLiveData.setValue(true);
//            }
//        } catch (Exception e) {
//            Log.e("retornaUsuarioEmail", e.getMessage());
//            resultLiveData.setValue(null);
//        }
//
//        return resultLiveData;
//    }

    public Boolean retornaUsuarioEmail(String email) {
        try {
            Usuario usuario = appDataBase.usuarioDao().getUsuarioByEmail(email);
            if (usuario != null) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            Log.e("retornaUsuarioEmail", e.getMessage());
        }
        return false;
    }

//    public LiveData<Usuario> retornaUsuarioEmail(String email) {
//        return appDataBase.usuarioDao().getUsuarioByEmail(email);
//    }


//    public Usuario retornaUsuarioEmail(String email) {
//        try {
//            appDataBase.usuarioDao().getUsuarioByEmail(email);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            return null;
//        }
//        return null; // Não sei pq, mas tive q add esse return.
//    }

//    public LiveData<List<Usuario>> retornaTodosUsuarios() {
//        this.resultLiveDataUsuarios = new MutableLiveData<>();
//
//        try {
//            usuarios = appDataBase.usuarioDao().getAllUsuarios();
//            resultLiveDataUsuarios.setValue(usuarios);
//        } catch (Exception e) {
//            Log.e("retornaTodosUsuarios", e.getMessage());
//            resultLiveDataUsuarios.setValue(null);
//        }
//
//        return resultLiveDataUsuarios;
//    }

//    public Boolean retornaTodosUsuarios() {
////        this.resultLiveDataUsuarios = new MutableLiveData<>();
//
//        try {
//            usuarios = appDataBase.usuarioDao().getAllUsuarios();
//            resultLiveDataUsuarios.setValue(usuarios);
//        } catch (Exception e) {
//            Log.e("retornaTodosUsuarios", e.getMessage());
//            resultLiveDataUsuarios.setValue(null);
//        }
//
//        return resultLiveDataUsuarios;
//    }

    public List<Usuario> retornaTodosUsuarios() {
        try {
            return appDataBase.usuarioDao().getAllUsuarios();
//            return usuarios;
        } catch (Exception e){
//            System.out.println(e.getMessage());
            Log.e("retornaTodosUsuarios", e.getMessage());
            return null;
        }
    }

//    public LiveData<Usuario> retornaUsuarioNome(String nome) {
//        MutableLiveData<Usuario> resultLiveData = new MutableLiveData<>();
//
//        try {
//            Usuario usuario = appDataBase.usuarioDao().getUsuarioByEmail(nome);
//            resultLiveData.setValue(usuario);
//        } catch (Exception e) {
//            Log.e("retornaUsuarioNome", e.getMessage());
//            resultLiveData.setValue(null);
//        }
//
//        return resultLiveData;
//    }


//    public List<Usuario> retornaUsuarioNome(String nome) {
//        try {
//            return appDataBase.usuarioDao().getUsuarioByName(nome);
//        } catch (Exception e){
//            System.out.println(e.getMessage());
//            return null;
//        }
//    }
}
