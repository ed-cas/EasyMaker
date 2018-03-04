package com.easymaker.easymaker.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.easymaker.easymaker.Objetos.Curso;
import com.easymaker.easymaker.R;

import java.util.List;

public class Recycler_Adapter_Knowledge extends RecyclerView.Adapter<Recycler_Adapter_Knowledge.cursos_ViewHolder>{

    private Context mContext;
    private List<Curso> cursos;

    public static class cursos_ViewHolder extends RecyclerView.ViewHolder{
        private TextView text_curso;
        private ImageView image_curso;
        private CardView cardView;

        public cursos_ViewHolder(View itemView) {
            super(itemView);

            text_curso=(TextView)itemView.findViewById(R.id.title_curso);
            image_curso=(ImageView)itemView.findViewById(R.id.image_curso);
            cardView=(CardView)itemView.findViewById(R.id.card_view_curso_id);
        }
    }

    public Recycler_Adapter_Knowledge(Context mContext, List<Curso> cursos){
        this.mContext = mContext;
        this.cursos=cursos;
    }

    @Override
    public cursos_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater=LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.card_view_curso,parent,false);
        return new cursos_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(cursos_ViewHolder holder, int position) {
        holder.text_curso.setText(cursos.get(position).getNombre());
        //holder.image_curso.setImageResource(cursos.get());
        holder.cardView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return cursos.size();
    }

}
