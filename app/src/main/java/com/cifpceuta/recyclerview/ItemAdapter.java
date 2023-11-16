package com.cifpceuta.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    ArrayList<String> list_item;

    public ItemAdapter(ArrayList<String> list_item) {
        this.list_item = list_item;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        return new ItemAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(list_item.get(position));
    }

    @Override
    public int getItemCount() {
        return list_item.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTexto;
        Button btnBorrar;
        ItemAdapter adapter;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            tvTexto = itemView.findViewById(R.id.textoItem);
            btnBorrar = itemView.findViewById(R.id.btnItem);
            btnBorrar.setOnClickListener(view -> {
                String text = list_item.get(getAdapterPosition());
                Toast.makeText(itemView.getContext(),"Elemento eliminado: "+text,Toast.LENGTH_SHORT).show();
                list_item.remove(getAdapterPosition());
                notifyItemRemoved(getAdapterPosition());
            });
        }
        void bindData(final String item) {
            tvTexto.setText(item);
        }
    }
}
