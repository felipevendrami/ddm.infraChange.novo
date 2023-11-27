package Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import org.jetbrains.annotations.NotNull;

@Entity(tableName = "imagem_chamado")
public class ImagemChamado {

    @PrimaryKey(autoGenerate = true)
    private long id;
    @NotNull
    private String path;
    private long idChamado;

    public ImagemChamado() {}

    public ImagemChamado(String path){
        this.path = path;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NotNull
    public String getPath() {
        return path;
    }

    public void setPath(@NotNull String path) {
        this.path = path;
    }

    public long getIdChamado() {
        return idChamado;
    }

    public void setIdChamado(long idChamado) {
        this.idChamado = idChamado;
    }
}
