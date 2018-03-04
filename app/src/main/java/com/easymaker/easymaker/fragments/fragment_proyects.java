package com.easymaker.easymaker.fragments;

        import android.os.Bundle;
        import android.support.design.widget.FloatingActionButton;
        import android.support.v4.app.Fragment;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import com.easymaker.easymaker.Objetos.Proyecto;
        import com.easymaker.easymaker.R;
        import com.easymaker.easymaker.adapters.RecyclerView_Adapter_proyects;
        import java.util.ArrayList;
        import java.util.List;


public class fragment_proyects extends Fragment{

    private RecyclerView recyclerView_proyectos;
    private RecyclerView_Adapter_proyects adapter_proyectos;
    private FloatingActionButton f_butron_add, f_buttom_search;
    private View view;

    public fragment_proyects() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_proyects, container, false);

        recyclerView_proyectos=(RecyclerView)view.findViewById(R.id.recyclerView_proyectos);
        recyclerView_proyectos.setLayoutManager(new LinearLayoutManager(getContext()));


        adapter_proyectos= new RecyclerView_Adapter_proyects(obtener_proyectos());
        recyclerView_proyectos.setAdapter(adapter_proyectos);

        f_buttom_search=(FloatingActionButton)view.findViewById(R.id.floating_Search);
        f_butron_add=(FloatingActionButton)view.findViewById(R.id.floating_plus);

        recyclerView_proyectos.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy > 0 && f_butron_add.getVisibility() == View.VISIBLE){
                    f_butron_add.hide();
                    f_buttom_search.hide();
                }else if(dy < 0 && f_butron_add.getVisibility() != View.VISIBLE){
                    f_butron_add.show();
                    f_buttom_search.show();
                }
            }
        });
        return view;
    }

    public List<Proyecto> obtener_proyectos(){
        List<Proyecto> proyectos= new ArrayList<>();
        proyectos.add(new Proyecto("Vertebra-app","Vertebr-app es ua aplicacion movil que trata de combatir malas posturas atravez de un sistema embebido que combinado con aplicaciones moviles   pensado para deportistas y bla @string/hello_blank_fragment"));
        proyectos.add(new Proyecto("Vertebra-app","playera para mejorar los habitos de psotura jasd jasdjhasda"));
        proyectos.add(new Proyecto("Vertebra-app","playera para mejorar los habitos de psotura jasd jasdjhasda"));
        proyectos.add(new Proyecto("Vertebra-app","playera para mejorar los habitos de psotura jasd jasdjhasda"));
        proyectos.add(new Proyecto("Vertebra-app","playera para mejorar los habitos de psotura jasd jasdjhasda"));
        proyectos.add(new Proyecto("Vertebra-app","playera para mejorar los habitos de psotura jasd jasdjhasda"));
        proyectos.add(new Proyecto("Vertebra-app","playera para mejorar los habitos de psotura jasd jasdjhasda"));
        proyectos.add(new Proyecto("Vertebra-app","playera para mejorar los habitos de psotura jasd jasdjhasda"));
        proyectos.add(new Proyecto("Vertebra-app","playera para mejorar los habitos de psotura jasd jasdjhasda"));
        proyectos.add(new Proyecto("Vertebra-app","playera para mejorar los habitos de psotura jasd jasdjhasda"));
        proyectos.add(new Proyecto("Vertebra-app","playera para mejorar los habitos de psotura jasd jasdjhasda"));
        proyectos.add(new Proyecto("Vertebra-app","playera para mejorar los habitos de psotura jasd jasdjhasda"));


        return proyectos;
    }
}