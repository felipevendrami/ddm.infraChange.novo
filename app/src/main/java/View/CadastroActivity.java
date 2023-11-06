package View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Controller.UsuarioController;
import Model.Usuario;
import Repository.UsuarioRepository;
import ddm.ddminfrachange.R;

public class CadastroActivity extends AppCompatActivity {
    private EditText edtxNome;
    private EditText edtxCpf;
    private EditText edtxDataNascimento;
    private EditText edtxTelefone;
    private EditText edtxLogradouro;
    private EditText edtxBairro;
    private EditText edtxNumeroResidencial;
    private EditText edtxEmail;
    private EditText edtxSenha;
    private EditText edtxConfirmarSenha;
    private Button btnCadastrar;

    private UsuarioController usuarioController;
    private UsuarioRepository usuarioRepository;

    private List<Usuario> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        this.usuarioController = new UsuarioController();
        this.usuarioRepository = new UsuarioRepository(this);

        initComponents();
        initActions();
    }

    private void initActions() {
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String nome = edtxNome.getText().toString();
                    String cpf = edtxCpf.getText().toString();
                    String dataNascimento = edtxDataNascimento.getText().toString();
                    String telefone = edtxTelefone.getText().toString();
                    String logradouro = edtxLogradouro.getText().toString();
                    String bairro = edtxBairro.getText().toString();
                    String numeroResidencial = edtxNumeroResidencial.getText().toString();
                    String email = edtxEmail.getText().toString();
                    String senha = edtxSenha.getText().toString();
                    String confirmar = edtxConfirmarSenha.getText().toString();

                    if (!isValidEmail(email)) {
                        showMessage("Informe um e-mail válido.");
                        return;
                    }

                    if (!isValidPassword(senha)) {
                        return;
                    }

                    if (!passwordConfirm(senha, confirmar)) {
                        return;
                    }

                    if (isValidInput(nome, email, senha)) {
                        // Crie um novo usuário e adicione-o à lista de usuários
                        Usuario newUser = new Usuario(nome, cpf, dataNascimento, telefone, logradouro, bairro, numeroResidencial, email, senha);
                        if (!usuarioRepository.insertUsuario(newUser)) {
                            showMessage("E-mail já cadastrado.");
                            return;
                        }

                        showMessage("Cadastro bem-sucedido.");
                        // Volte para a tela de login
                        finish();
                    } else {
                        showMessage("Preencha todos os campos corretamente.");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
    }

//    private boolean emBranco(String ) {
//
//    }

    // Por enquanto, para ser mais simples realizar o cadastro, apenas será necessário informar o nome, email e senha
    private boolean isValidInput(String nome, String email, String senha) {
        return !nome.trim().isEmpty() && !email.trim().isEmpty() && !senha.trim().isEmpty();
    }

    // Valida a confirmação da senha
    private boolean passwordConfirm(String senha, String confirma) {
        if (confirma.trim().isEmpty()) {
            showMessage("Confirme sua senha.");
            return false;
        }

        if (!senha.equals(confirma)) {
            showMessage("Confirmação incorreta.");
            return false;
        }

        return true;
    }

    // Verifica se a senha tem pelo menos 8 caracteres, uma letra maiúscula e um caractere especial
    private boolean isValidPassword(String password) {
        if (password.length() < 8) {
            showMessage("A senha deve conter no mínimo 8 caracteres.");
            return false;
        }

        if (!password.matches(".*[A-Z].*")) {
            showMessage("A senha deve conter uma letra maiuscula.");
            return false;
        }

        if (!password.matches(".*[!@#$%^&*()].*")) {
            showMessage("A senha deve conter um caractere especial.");
            return false;
        }

        return password.length() >= 8 && password.matches(".*[A-Z].*") && password.matches(".*[!@#$%^&*()].*");
    }

    // Valida E-mail
    private boolean isValidEmail(String email) {
        // Use uma expressão regular para validar o formato do e-mail
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        // Crie um objeto Pattern com a expressão regular
        Pattern pattern = Pattern.compile(emailPattern);

        // Crie um objeto Matcher para comparar o e-mail com a expressão regular
        Matcher matcher = pattern.matcher(email);

        // Verifique se o e-mail corresponde à expressão regular
        return matcher.matches();
    }

    // Função para exibir uma mensagem
    public void showMessage(String msg) {
        Toast.makeText(CadastroActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    // Inicializa os componentes
    private void initComponents() {
        edtxNome = findViewById(R.id.editTextNomeCompleto);
        edtxCpf = findViewById(R.id.editTextCPF);
        edtxDataNascimento = findViewById(R.id.editTextDataNascimento);
        edtxTelefone = findViewById(R.id.editTextTelefone);
        edtxLogradouro = findViewById(R.id.editTextLogradouro);
        edtxBairro = findViewById(R.id.editTextBairro);
        edtxNumeroResidencial = findViewById(R.id.editTextNumeroResidencial);
        edtxEmail = findViewById(R.id.editTextEmailCadastro);
        edtxSenha = findViewById(R.id.editTextSenhaCadastro);
        edtxConfirmarSenha = findViewById(R.id.editTextConfirmarSenha);
        btnCadastrar = findViewById(R.id.buttonCadastrarCadastro);
    }
}