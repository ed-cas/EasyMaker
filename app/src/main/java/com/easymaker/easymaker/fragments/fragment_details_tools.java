package com.easymaker.easymaker.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.easymaker.easymaker.Objetos.Herramienta;
import com.easymaker.easymaker.R;

public class fragment_details_tools extends Fragment {

    private OnFragmentInteractionListener mListener;
    private String mParam1;
    private String mParam2;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextView title;
    private TextView text_description;
    private ImageView image;


    public fragment_details_tools() {
        // Required empty public constructor
    }


    public static fragment_details_tools newInstance(String param1, String param2) {
        fragment_details_tools fragment = new fragment_details_tools();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista= inflater.inflate(R.layout.fragment_fragment_details_tools, container, false);

        text_description= (TextView) vista.findViewById(R.id.text_desciption);
        title=(TextView)vista.findViewById(R.id.article_name);

        Bundle objeto_herramienta=getArguments();
        Herramienta herramienta=null;
        if (objeto_herramienta != null) {
            herramienta= (Herramienta) objeto_herramienta.getSerializable("objeto");
            asignarInformacion(herramienta);
        }
        return vista;
    }

    public void asignarInformacion(Herramienta herramienta) {
        text_description.setText(herramienta.getDesciption());
        title.setText(herramienta.getName());

        //cargar datos a vista
    }



    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
