package View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Controller.UsuarioController;
import Repository.UsuarioRepository;
import Model.Usuario;
import ddm.ddminfrachange.MainActivity;
import ddm.ddminfrachange.R;

public class LoginActivity extends AppCompatActivity {

    // Dados recuperados
    private String email;
    private String senha;

    // Componentes Visuais
    private EditText edtxEmail;
    private EditText edtxSenha;
    private Button btnEntrar;
    private Button btnCadastrar;

    // Controller
//    private UsuarioController usuarioController;
    private UsuarioRepository usuarioRepository;

    private List<Usuario> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initComponents();
        initActions();
    }

    private void initActions() {
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String email = edtxEmail.getText().toString();
                    String senha = edtxSenha.getText().toString();

                    if (login(email, senha)) {
                        // Autenticação bem-sucedida, vá para a próxima atividade
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        showMessage("Login falhou. Verifique suas credenciais.");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abra a tela de cadastro
                Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(intent);
            }
        });
    }

//    private boolean login(String email, String senha) {
//        for (Usuario user : userList) {
//            if (user.getEmail().equals(email) && user.getSenha().equals(senha)) {
//                return true;
//            }
//        }
//        return false;
//    }

    // Função para verificar as credenciais de login
    private boolean login(String email, String senha) {
        userList = usuarioRepository.retornaTodosUsuarios();
        for (Usuario user : userList) {
            if (user.getEmail().equals(email) && user.getSenha().equals(senha)) {
                return true;
            }
        }
        return false;
    }

    // Função para exibir uma mensagem
    public void showMessage(String msg) {
        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

//    private String[] recuperarDados() {
//        // FAZER O GET DA LISTA
//        this.email = edtxEmail.getText().toString();
//        this.senha = edtxSenha.getText().toString();
//        return null;
//    }

    private void initComponents() {
        edtxEmail = findViewById(R.id.editTextEmail);
        edtxSenha = findViewById(R.id.editTextSenha);
        btnEntrar = findViewById(R.id.buttonEntrar);
        btnCadastrar = findViewById(R.id.buttonCadastrar);
    }
}