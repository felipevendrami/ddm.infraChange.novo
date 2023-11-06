package Model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import org.jetbrains.annotations.NotNull;

@Entity(tableName = "usuario")
public class Usuario {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @NotNull
    private String nome;
    @NotNull
    private String cpf;
    @NotNull
    private String dataNascimento;
    @NotNull
    private String telefone;
    @NotNull
    private String logradouro;
    @NotNull
    private String bairro;
    @NotNull
    private String numeroResidencial;
    @NotNull
    private String email;
    @NotNull
    private String senha;

    public Usuario(@NotNull String nome, @NotNull String cpf, @NotNull String dataNascimento, @NotNull String telefone, @NotNull String logradouro, @NotNull String bairro, @NotNull String numeroResidencial, @NotNull String email, @NotNull String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.numeroResidencial = numeroResidencial;
        this.email = email;
        this.senha = senha;
    }

    @NotNull
    public int getId() {
        return id;
    }

    public void setId(@NotNull int id) {
        this.id = id;
    }
    @NotNull
    public String getNome() {
        return nome;
    }

    public void setNome(@NotNull String nome) {
        this.nome = nome;
    }
    @NotNull
    public String getCpf() {
        return cpf;
    }
    public void setCpf(@NotNull String cpf) {
        this.cpf = cpf;
    }
    @NotNull
    public String getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(@NotNull String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    @NotNull
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(@NotNull String telefone) {
        this.telefone = telefone;
    }
    @NotNull
    public String getLogradouro() {
        return logradouro;
    }
    public void setLogradouro(@NotNull String logradouro) {
        this.logradouro = logradouro;
    }
    @NotNull
    public String getBairro() {
        return bairro;
    }
    public void setBairro(@NotNull String bairro) {
        this.bairro = bairro;
    }
    @NotNull
    public String getNumeroResidencial() {
        return numeroResidencial;
    }
    public void setNumeroResidencial(@NotNull String numeroResidencial) {
        this.numeroResidencial = numeroResidencial;
    }
    @NotNull
    public String getEmail() {
        return email;
    }
    public void setEmail(@NotNull String email) {
        this.email = email;
    }
    @NotNull
    public String getSenha() {
        return senha;
    }

    public void setSenha(@NotNull String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", dataNascimento='" + dataNascimento + '\'' +
                ", telefone='" + telefone + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", bairro='" + bairro + '\'' +
                ", numeroResidencial='" + numeroResidencial + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
