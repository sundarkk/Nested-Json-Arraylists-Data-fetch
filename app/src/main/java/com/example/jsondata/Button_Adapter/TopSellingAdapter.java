package com.example.jsondata.Button_Adapter;

import android.content.Context;
import android.opengl.Visibility;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jsondata.DataAdapter;
import com.example.jsondata.Model.Categroy;
import com.example.jsondata.Model.TopItem;
import com.example.jsondata.Model.TopSelling;
import com.example.jsondata.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TopSellingAdapter  extends RecyclerView.Adapter<TopSellingAdapter.MyViewHolder> {

    private ArrayList<TopSelling> dataModelList;
    private Context context;


    public TopSellingAdapter(ArrayList<TopSelling> dataModelList, Context context) {
        this.dataModelList = dataModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public TopSellingAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.items_list, parent, false);
        return new TopSellingAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        if (!dataModelList.get(position).getImage().isEmpty()) {
            Picasso.with(context)
                    .load(dataModelList.get(position).getImage())
                    .placeholder(R.drawable.ic_launcher_background) //this is optional the image to display while the url image is downloading
                    .error(R.drawable.ic_launcher_background)         //this is also optional if some error has occurred in downloading the image this image would be displayed
                    .into(holder.imgproducts);
        }


        holder.tv_Product_Name.setText(String.valueOf(dataModelList.get(position).getName()));
        holder.tv_Product_Description.setText(String.valueOf(dataModelList.get(position).getDescription()));

    }




    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgproducts;
        TextView tv_Product_Name, tv_Product_Description;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_Product_Name = (TextView) itemView.findViewById(R.id.tv_Product_Name);
            tv_Product_Description = (TextView) itemView.findViewById(R.id.tv_Product_Description);
            imgproducts = (ImageView) itemView.findViewById(R.id.imgproducts);


        }
    }

}