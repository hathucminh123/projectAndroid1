package com.example.ecommerceapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecommerceapp.R;
import com.example.ecommerceapp.activities.DetailedActivity;
import com.example.ecommerceapp.models.NewProductsModel;

import java.util.List;
import com.example.ecommerceapp.models.PopularProductsModel;
import  com.example.ecommerceapp.adapters.PopularProductsAdapter;

public class PopularProductsAdapter  extends RecyclerView.Adapter<PopularProductsAdapter.ViewHolder> {

    private Context context;
    private List<PopularProductsModel> list;

    public PopularProductsAdapter(Context context, List<PopularProductsModel> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public PopularProductsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PopularProductsAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_items,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull PopularProductsAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImg_url()).into(holder.img);
        holder.name.setText(list.get(position).getName());
        holder.price.setText(String.valueOf(list.get(position).getPrice()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DetailedActivity.class);
                intent.putExtra("detailed",list.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return  list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,price;
        ImageView img;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            name  =itemView.findViewById(R.id.all_product_name);
            price=itemView.findViewById(R.id.all_price);
            img=itemView.findViewById(R.id.all_img);
        }
    }
}
