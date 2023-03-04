package com.terfezio.appsqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdaptadorUsuario extends BaseAdapter {
    private Context context;
    private List<Usuario> usuarios;

    public AdaptadorUsuario(Context context, List<Usuario> usuarios) {
        this.context = context;
        this.usuarios = usuarios;
    }
    @Override
    public int getCount() {
        return usuarios.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageViewUsuario;
        TextView textViewNombre;
        TextView textViewInfo;

        Usuario usuario = usuarios.get(i);

        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.listview_usuario, null);
        }
        imageViewUsuario = view.findViewById(R.id.imageViewUsuario);
        textViewNombre = view.findViewById(R.id.textViewNombre);
        textViewInfo = view.findViewById(R.id.textViewInfo);

        imageViewUsuario.setImageResource(R.drawable.user);
        textViewNombre.setText(usuario.getNombre());
        textViewInfo.setText(usuario.getDni());
        return view;
    }
}
