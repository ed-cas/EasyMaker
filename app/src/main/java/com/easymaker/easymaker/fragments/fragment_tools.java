package com.easymaker.easymaker.fragments;

import android.app.Activity;
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
import com.easymaker.easymaker.Objetos.Herramienta;
import com.easymaker.easymaker.R;
import com.easymaker.easymaker.adapters.Recycler_Adapter_tools;
import com.easymaker.easymaker.interfaces.Comunica_fragment;

import java.util.ArrayList;
import java.util.List;


public class fragment_tools extends Fragment {

    private RecyclerView recyclerView_herramientas;
    private Recycler_Adapter_tools adapter_herramientas;
    private FloatingActionButton f_button_add_tool, f_buttom_search_tool;
    private List<Herramienta> herramientas;
    private Activity activity;
    private Comunica_fragment icomunica;
    private View view;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public fragment_tools() {
        // Required empty public constructor
    }

    public  static fragment_tools newInstance(String param1, String param2){
        fragment_tools fragment= new fragment_tools();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1,param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 =  getArguments().getString(ARG_PARAM1);
            mParam2 =  getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_tools, container, false);

        f_buttom_search_tool=(FloatingActionButton)view.findViewById(R.id.floating_Search_tools);
        f_button_add_tool=(FloatingActionButton)view.findViewById(R.id.floating_plus_tools);


        recyclerView_herramientas=(RecyclerView)view.findViewById(R.id.recyclerView_tools);
        recyclerView_herramientas.setLayoutManager(new GridLayoutManager(getContext(),3));

        adapter_herramientas = new Recycler_Adapter_tools(this.getContext(),obtener_herramientas());
        recyclerView_herramientas.setAdapter(adapter_herramientas);

        adapter_herramientas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                icomunica.enviar_herramienta(herramientas.get(recyclerView_herramientas.getChildAdapterPosition(view)));
            }
        });

        recyclerView_herramientas.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy > 0 && f_button_add_tool.getVisibility() == View.VISIBLE){
                    f_button_add_tool.hide();
                    f_buttom_search_tool.hide();
                }else if(dy < 0 && f_button_add_tool.getVisibility() != View.VISIBLE){
                    f_button_add_tool.show();
                    f_buttom_search_tool.show();
                }
            }
        });

        return view;
    }

    private List<Herramienta> obtener_herramientas() {
        herramientas = new ArrayList<Herramienta>();
        herramientas.add(new Herramienta("CNC"));
        herramientas.add(new Herramienta("taladro"));
        herramientas.add(new Herramienta("impresora 3D"));
        herramientas.add(new Herramienta("Cautin "));
        herramientas.add(new Herramienta("estacion de soldado"));
        herramientas.add(new Herramienta("Dron"));
        herramientas.add(new Herramienta("camara reflex"));
        herramientas.add(new Herramienta("iMac"));
        herramientas.add(new Herramienta("CNC"));
        herramientas.add(new Herramienta("CNC"));
        herramientas.add(new Herramienta("CNC"));
        herramientas.add(new Herramienta("CNC"));
        herramientas.add(new Herramienta("CNC"));
        herramientas.add(new Herramienta("CNC"));
        herramientas.add(new Herramienta("CNC"));
        herramientas.add(new Herramienta("CNC"));
        herramientas.add(new Herramienta("CNC"));
        herramientas.add(new Herramienta("CNC"));
        herramientas.add(new Herramienta("CNC"));
        herramientas.add(new Herramienta("CNC"));
        herramientas.add(new Herramienta("CNC"));
        herramientas.add(new Herramienta("CNC"));
        herramientas.add(new Herramienta("CNC"));
        herramientas.add(new Herramienta("CNC"));
        return herramientas;
    }


    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof Activity){
            this.activity= (Activity) context;
            icomunica= (Comunica_fragment) this.activity;
        }

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()+" must implement OnFragmentInteractionListener");
        }
    }

    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
