package ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import Model.Usuario;
import Repository.UsuarioRepository;

public class CadastroViewModel extends AndroidViewModel {
    private UsuarioRepository usuarioRepository;

    public CadastroViewModel(Application application) {
        super(application);
        usuarioRepository = new UsuarioRepository(application);
    }
    public LiveData<Boolean> cadastrarUsuario(Usuario usuario) {
        MutableLiveData<Boolean> resultLiveData = new MutableLiveData<>();

        // Lógica de validação e cadastro no repositório
        try {
            // Realize a lógica de validação (se necessário)
            if(usuarioRepository.retornaUsuarioEmail(usuario.getEmail()) == false) {
                resultLiveData.setValue(false);
//                return resultLiveData;
            } else {
                // Chame o método de inserção do repositório
                usuarioRepository.insertUsuario(usuario);

                // Indique sucesso
                resultLiveData.setValue(true);
            }
        } catch (Exception e) {
            // Lógica para lidar com falhas durante o cadastro
            e.printStackTrace();
            resultLiveData.setValue(false);
        }

        return resultLiveData;
    }

    public LiveData<Boolean> validarEmail(String email) {
        MutableLiveData<Boolean> resultLiveData = new MutableLiveData<>();

        // Lógica de validação e cadastro no repositório
        try {
            // Realize a lógica de validação (se necessário)
            resultLiveData.setValue(false);
            // Chame o método de inserção do repositório
//            LiveData<Usuario> user = usuarioRepository.retornaUsuarioEmail(email);
            Boolean user = usuarioRepository.retornaUsuarioEmail(email);
            if (user == true) {
                resultLiveData.setValue(true);
            }
            // Indique sucesso
        } catch (Exception e) {
            // Lógica para lidar com falhas durante o cadastro
            e.printStackTrace();
            resultLiveData.setValue(false);
        }

        return resultLiveData;
    }
}
