package Model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class ChamadoComImagemChamado {
    @Embedded
    public Chamado chamado;
    @Relation(parentColumn = "id", entityColumn = "idChamado")
    public List<ImagemChamado> imagemChamados;

    public Chamado getChamado() {
        return chamado;
    }

    public void setChamado(Chamado chamado) {
        this.chamado = chamado;
    }

    public List<ImagemChamado> getImagemChamados() {
        return imagemChamados;
    }

    public void setImagemChamados(List<ImagemChamado> imagemChamados) {
        this.imagemChamados = imagemChamados;
    }
}
