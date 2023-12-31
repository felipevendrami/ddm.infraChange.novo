package Model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "chamado")
public class Chamado {

    @PrimaryKey(autoGenerate = true)
    private long id;
    @NotNull
    private String cidadao;
    @NotNull
    private String tipoDenuncia;
    @NotNull
    private String descricao;
    @NotNull
    private boolean localizacao;
    @Ignore
    private List<ImagemChamado> imagensChamado;
    @NotNull
    private String situacao = "Aberto";

    public Chamado() {
    }

    public Chamado(String descricao, String tipo) {
        this.cidadao = "Ciclano da Silva";
        this.descricao = descricao;
        this.tipoDenuncia = tipo;
        this.imagensChamado = new ArrayList<>();
    }

    @NotNull
    public String getTipoDenuncia() {
        return tipoDenuncia;
    }

    public void setTipoDenuncia(@NotNull String tipoDenuncia) {
        this.tipoDenuncia = tipoDenuncia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public List<ImagemChamado> getImagensChamado() {
        return imagensChamado;
    }

    public void setImagensChamado(List<ImagemChamado> imagens) {
        this.imagensChamado = imagens;
    }

    public void addImagemChamado(ImagemChamado img){
        this.imagensChamado.add(img);
    }

    @NotNull
    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(@NotNull String situacao) {
        this.situacao = situacao;
    }
}
