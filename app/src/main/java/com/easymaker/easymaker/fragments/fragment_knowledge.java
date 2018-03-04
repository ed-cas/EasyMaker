package com.easymaker.easymaker.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.easymaker.easymaker.Objetos.Curso;
import com.easymaker.easymaker.R;
import com.easymaker.easymaker.adapters.Recycler_Adapter_Knowledge;

import java.util.ArrayList;
import java.util.List;


public class fragment_knowledge extends Fragment {

    private RecyclerView recyclerView_cursos;
    private Recycler_Adapter_Knowledge adapter_cursos;
    private FloatingActionButton f_butron_add_curso, f_buttom_search_curso;
    private View view;


    public fragment_knowledge() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_knowledge, container, false);

        f_buttom_search_curso=(FloatingActionButton)view.findViewById(R.id.floating_Search_cursos);
        f_butron_add_curso=(FloatingActionButton)view.findViewById(R.id.floating_plus_cursos);
        recyclerView_cursos=(RecyclerView)view.findViewById(R.id.recycler_cursos);
        recyclerView_cursos.setLayoutManager(new GridLayoutManager(getContext(),3));

        adapter_cursos = new Recycler_Adapter_Knowledge(this.getContext(),obtener_cursos());
        recyclerView_cursos.setAdapter(adapter_cursos);

        recyclerView_cursos.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy > 0 && f_butron_add_curso.getVisibility() == View.VISIBLE){
                    f_butron_add_curso.hide();
                    f_buttom_search_curso.hide();
                }else if(dy < 0 && f_butron_add_curso.getVisibility() != View.VISIBLE){
                    f_butron_add_curso.show();
                    f_buttom_search_curso.show();
                }
            }
        });
        return view;
    }


    public List<Curso> obtener_cursos(){
        List<Curso> cursos= new ArrayList<>();
        cursos.add(new Curso("Xamarin"));
        cursos.add(new Curso("Java Basico"));
        cursos.add(new Curso("BioMecatronica Basica"));
        cursos.add(new Curso("Soldadura TIG"));
        cursos.add(new Curso("Arquitectura Funcional"));
        cursos.add(new Curso("Termodinamica"));
        cursos.add(new Curso("Aplicaciones Moviles"));
        cursos.add(new Curso("Soldadura MIG"));
        cursos.add(new Curso("Estructuras de datos"));
        cursos.add(new Curso("Fotografia"));
        cursos.add(new Curso("Robotica Avanzada"));
        cursos.add(new Curso("Domotica"));
        cursos.add(new Curso("Computo Ubicuo"));
        cursos.add(new Curso("Principios de Fisioterapia"));
        cursos.add(new Curso("Litografia"));
        cursos.add(new Curso("Electrica"));
        return cursos;
    }

}
