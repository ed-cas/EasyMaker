package com.easymaker.easymaker.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.easymaker.easymaker.Objetos.Proyecto;
import com.easymaker.easymaker.R;

import java.util.List;
/***
 * ReyclerView de Proyectos
 * */
public class RecyclerView_Adapter_proyects extends RecyclerView.Adapter<RecyclerView_Adapter_proyects.ViewHolder>{

    public static class  ViewHolder extends RecyclerView.ViewHolder{
        private TextView titulo, descipcion;
        private ImageView imagen_proyecto;
        private CardView cardView;

        public ViewHolder(View view){
            super(view);
            titulo =(TextView)view.findViewById(R.id.title_proyect);
            descipcion=(TextView)view.findViewById(R.id.text_desciption);
            imagen_proyecto=(ImageView)view.findViewById(R.id.img_proyecto);
            cardView=(CardView)view.findViewById(R.id.card_view_proyect_id);
        }
    }

    public List<Proyecto> proyectos_listas;


    public RecyclerView_Adapter_proyects(List<Proyecto> proyectos_listas){
        this.proyectos_listas=proyectos_listas;
    }


    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_proyecto,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.titulo.setText(proyectos_listas.get(position).getTitile());
        holder.descipcion.setText(proyectos_listas.get(position).getDescription());
        //holder.image_proyecto()
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return proyectos_listas.size();
    }
}
