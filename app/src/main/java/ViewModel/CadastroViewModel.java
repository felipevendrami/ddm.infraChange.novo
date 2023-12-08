package ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import Model.Usuario;
import Repository.UsuarioRepository;

public class CadastroViewModel extends AndroidViewModel {
    private UsuarioRepository usuarioRepository;
    private MutableLiveData<Boolean> resultLiveData;
    private List<Usuario> usuarios;

    public CadastroViewModel(Application application) {
        super(application);
        usuarioRepository = new UsuarioRepository(application);
    }

    public LiveData<Boolean> cadastrarUsuario(Usuario usuario) {
        this.resultLiveData = new MutableLiveData<>();

        try {
            if (usuarioRepository.retornaUsuarioEmail(usuario.getEmail()) == false) {
                resultLiveData.setValue(false);
            } else {
                usuarioRepository.insertUsuario(usuario);
                resultLiveData.setValue(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultLiveData.setValue(false);
        }
        return resultLiveData;
    }

    public LiveData<Boolean> validarLogin(String email, String senha) {
        this.resultLiveData = new MutableLiveData<>();

        try {
            usuarios = usuarioRepository.retornaTodosUsuarios();
            if (usuarios == null) {
                resultLiveData.setValue(false);
            } else {
                resultLiveData.setValue(false);
                for (Usuario user : usuarios) {
                    if (user.getEmail().equals(email) && user.getSenha().equals(senha)) {
                        resultLiveData.setValue(true);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultLiveData.setValue(false);
        }

        return resultLiveData;
    }
}
