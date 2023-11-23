package com.cifpceuta.recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


//import com.google.android.material.search.SearchView;

import java.util.ArrayList;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ItemAdapter adapter;
    private ArrayList<String> list_items;
    private Toolbar toolbar;
    private boolean ordAsc = true;
    private boolean modoLista = true;
    private SearchView barraBusqueda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        list_items = new ArrayList<>();
        list_items.add("Item 1");
        list_items.add("Item 2");
        list_items.add("Item 3");
        list_items.add("Item 4");
        list_items.add("Item 5");
        list_items.add("Item 6");
        list_items.add("Item 7");
        list_items.add("Item 8");
        list_items.add("Item 9");
        list_items.add("Item 10");
        list_items.add("Item 11");
        list_items.add("Item 12");
        list_items.add("Item 13");
        list_items.add("Item 14");
        list_items.add("Item 15");
        list_items.add("Item 16");
        list_items.add("Item 17");
        list_items.add("Item 18");
        list_items.add("Item 19");
        list_items.add("Item 20");
        if (adapter == null){
            adapter = new ItemAdapter(list_items);
        }
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        barraBusqueda = findViewById(R.id.svBusqueda);

        barraBusqueda.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String texto){
                filtrado(texto);
                return false;
            }
        });
    }
    private void filtrado(String texto){
        ArrayList<String> filteredList_items = new ArrayList<>();
        for(String item : list_items){
            if(item.toLowerCase().contains(texto.toLowerCase())){
                filteredList_items.add(item);
            }
        }
        adapter.setList_item(filteredList_items);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.opcion1){
            //Ascendente
            list_items.sort(new Comparator<String>() {
                public int compare(String obj1, String obj2) {
                    return obj1.compareTo(obj2);
                }
            });
            adapter.setList_item(list_items);
            ordAsc = true;

        }
        else if(id == R.id.opcion2){
            //Descendente
            list_items.sort(new Comparator<String>() {
                public int compare(String obj1, String obj2) {
                    return (obj1.compareTo(obj2))*(-1);
                }
            });
            adapter.setList_item(list_items);
            ordAsc = false;

        }
        else if(id == R.id.opcion3){
            //Interactivo
            if (ordAsc){
                list_items.sort(new Comparator<String>() {
                    public int compare(String obj1, String obj2) {
                        return (obj1.compareTo(obj2))*(-1);
                    }
                });
                adapter.setList_item(list_items);
                ordAsc = false;
            }else {
                list_items.sort(new Comparator<String>() {
                    public int compare(String obj1, String obj2) {
                        return obj1.compareTo(obj2);
                    }
                });
                adapter.setList_item(list_items);
                ordAsc = true;
            }

        } else if (id == R.id.opcion4) {
            if(modoLista){
                recyclerView.setLayoutManager(new GridLayoutManager(this,2));
                modoLista = false;
            }else {
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                modoLista = true;
            }

        } else if (id == R.id.opcionPar) {
            adapter.setPar(2);

        } else if (id == R.id.opcionImpar){
            adapter.setPar(1);
        } else if (id == R.id.opcionLimpiar) {
            adapter.setPar(0);
        }
        return true;
    }

}