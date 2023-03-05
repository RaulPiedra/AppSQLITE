package com.terfezio.appsqlite;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorIncidencia extends RecyclerView.Adapter<AdaptadorIncidencia.ViewHolder> {
    private Context context;
    private List<Incidencia> incidencias;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        Context context;
        ImageView imageViewIncidencia;
        TextView textViewIncidencia;
        TextView textViewInfoIncidencia;
        public ViewHolder(View view) {
            super(view);
            /*if(view == null) {
                view = LayoutInflater.from(context).inflate(R.layout.recyclerview_incidencia, null);
            }*/
            imageViewIncidencia = view.findViewById(R.id.imageViewIncidencia);
            textViewIncidencia = view.findViewById(R.id.textViewIncidencia);
            textViewInfoIncidencia = view.findViewById(R.id.textViewInfoIncidencia);

        }
        public ImageView getImageViewIncidencia() {
            return imageViewIncidencia;
        }
        public TextView getTextViewIncidencia() {
            return textViewIncidencia;
        }
        public TextView getTextViewInfoIncidencia() {
            return textViewInfoIncidencia;
        }

    }

    public AdaptadorIncidencia(List<Incidencia> incidencias) {
        this.incidencias = incidencias;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_incidencia, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getImageViewIncidencia().setImageResource(R.drawable.incidencia);
        viewHolder.getTextViewIncidencia().setText(incidencias.get(position).getDni());
        viewHolder.getTextViewInfoIncidencia().setText(incidencias.get(position).getObservaciones());

    }

    @Override
    public int getItemCount() {
        return incidencias.size();
    }


}
