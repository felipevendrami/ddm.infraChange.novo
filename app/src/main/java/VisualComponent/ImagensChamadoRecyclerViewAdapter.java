package VisualComponent;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.io.IOException;
import java.util.List;
import Observer.ChamadoControllerObserver;
import ddm.ddminfrachange.R;

public class ImagensChamadoRecyclerViewAdapter extends RecyclerView.Adapter<ImagensChamadoRecyclerViewAdapter.ViewHolder> {

    private List<Uri> pathsImagens;
    private ChamadoControllerObserver chamadoControllerObserver;
    private Context context;

    public ImagensChamadoRecyclerViewAdapter(List<Uri> pathsImagens, Context context, ChamadoControllerObserver chamadoControllerObserver) {
        this.pathsImagens = pathsImagens;
        this.context = context;
        this.chamadoControllerObserver = chamadoControllerObserver;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImagem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.ivImagem = itemView.findViewById(R.id.ivImagem);
        }
    }

    @NonNull
    @Override
    public ImagensChamadoRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.img_chamado_recyclerview_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImagensChamadoRecyclerViewAdapter.ViewHolder holder, int position) {
        try {
            Uri uriImage = pathsImagens.get(position);
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.context.getContentResolver(), uriImage);
            holder.ivImagem.setImageBitmap(bitmap);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return this.pathsImagens.size();
    }
}
