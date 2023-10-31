package Model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import org.jetbrains.annotations.NotNull;
import java.util.List;

@Entity(tableName = "chamado")
public class Chamado {

    @PrimaryKey(autoGenerate = true)
    private long id;
    @NotNull
    private String cidadao;
    @NotNull
    private boolean localizacao;
    @Ignore
    private List<ImagemChamado> imagens;
    @NotNull
    private String situacao = "Em análise";
    @NotNull
    private String tipo;

    public Chamado() {
    }

    public Chamado(String cidadao, String situacao, String tipo, boolean localizacao) {
        this.cidadao = cidadao;
        this.situacao = situacao;
        this.tipo = tipo;
        this.localizacao = localizacao;
        //this.imagens = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NotNull
    public String getCidadao() {
        return cidadao;
    }

    public void setCidadao(@NotNull String cidadao) {
        this.cidadao = cidadao;
    }

    public boolean isLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(boolean localizacao) {
        this.localizacao = localizacao;
    }

    public List<ImagemChamado> getImagens() {
        return imagens;
    }

    public void setImagens(List<ImagemChamado> imagens) {
        this.imagens = imagens;
    }

    @NotNull
    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(@NotNull String situacao) {
        this.situacao = situacao;
    }

    @NotNull
    public String getTipo() {
        return tipo;
    }

    public void setTipo(@NotNull String tipo) {
        this.tipo = tipo;
    }
}
