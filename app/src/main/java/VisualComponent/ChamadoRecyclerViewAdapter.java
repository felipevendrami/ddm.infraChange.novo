package VisualComponent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import Model.Chamado;
import ddm.ddminfrachange.R;

public class ChamadoRecyclerViewAdapter extends RecyclerView.Adapter<ChamadoRecyclerViewAdapter.ViewHolder> {

    private List<Chamado> chamados;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView idChamado;
        TextView tipoChamado;
        TextView situacaoChamado;
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            this.idChamado = itemView.findViewById(R.id.idChamado);
            this.tipoChamado = itemView.findViewById(R.id.tipoChamado);
            this.situacaoChamado = itemView.findViewById(R.id.situacaoChamado);
        }
    }


    @NonNull
    @Override
    public ChamadoRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chamado_recyclerview_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChamadoRecyclerViewAdapter.ViewHolder holder, int position) {
        Chamado chamado = chamados.get(position);
        holder.idChamado.setText((int) chamado.getId());
        holder.tipoChamado.setText(chamado.getTipoDenuncia());
        holder.situacaoChamado.setText(chamado.getSituacao());
        Context context = holder.itemView.getContext();
        holder.itemView.setOnClickListener(v -> {

        });
    }

    @Override
    public int getItemCount() {
        return chamados.size();
    }
}
