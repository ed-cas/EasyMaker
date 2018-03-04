package com.easymaker.easymaker.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.easymaker.easymaker.Objetos.Herramienta;
import com.easymaker.easymaker.R;

import java.util.List;

public class Recycler_Adapter_tools extends RecyclerView.Adapter<Recycler_Adapter_tools.herramientas_ViewHolder> implements View.OnClickListener{

    private View.OnClickListener listener;
    private List<Herramienta> herramientas;


    public Recycler_Adapter_tools(Context mContext, List<Herramienta> herramientas) {
        this.herramientas = herramientas;
    }

    public herramientas_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_herramienta,null,false);

        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(5, 5, 5, 5); ///////Parametros visuales
        view.setLayoutParams(params);
        view.setOnClickListener(this);
        return new herramientas_ViewHolder(view);
    }

    public void onBindViewHolder(herramientas_ViewHolder holder, final int position) {
        holder.text_herramienta.setText(herramientas.get(position).getName());
        //holder.image_curso.setImageResource(cursos.get());

    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    public void onClick(View view) {
        if (listener!=null){
            listener.onClick(view);
        }
    }

    public int getItemCount() {
        return herramientas.size();
    }



    public class herramientas_ViewHolder extends RecyclerView.ViewHolder {
        private TextView text_herramienta;
        private ImageView image_herramienta;

        public herramientas_ViewHolder(View itemView) {
            super(itemView);
            ////Utilities.portraid
            text_herramienta=(TextView)itemView.findViewById(R.id.title_tool);
            image_herramienta=(ImageView)itemView.findViewById(R.id.image_tool);

        }
    }

}

