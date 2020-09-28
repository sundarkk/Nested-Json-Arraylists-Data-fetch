package com.example.jsondata.Button_Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jsondata.DataAdapter;
import com.example.jsondata.Model.Banner;
import com.example.jsondata.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.MyViewHolder> {

private List<Banner> dataModelList;
private Context context;



public BannerAdapter(List<Banner> dataModelList, Context context) {
        this.dataModelList = dataModelList;
        this.context = context;
        }

@NonNull
@Override
public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.items_list, parent, false);
        return new MyViewHolder(view);
        }

@Override
public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


       /* Picasso.with(context).load(dataModelList.get(position).getImage())
               .error(R.drawable.ic_launcher_background)
              .into(holder.imgproducts);*/
        if(!dataModelList.get(position).getImage().isEmpty()){
        Picasso.with(context)
        .load(dataModelList.get(position).getImage())
        .placeholder(R.drawable.ic_launcher_background) //this is optional the image to display while the url image is downloading
        .error(R.drawable.ic_launcher_background)         //this is also optional if some error has occurred in downloading the image this image would be displayed
        .into(holder.imgproducts);
        }



        holder.tv_Product_Name.setText(String.valueOf(dataModelList.get(position).getName()));
      //  holder.tv_Product_Description.setText(String.valueOf(dataModelList.get(position).getItemCount()));




        }

@Override
public int getItemCount() {
        return dataModelList.size();
        }

public class MyViewHolder extends RecyclerView.ViewHolder {
    ImageView imgproducts;
    TextView tv_Product_Name,tv_Product_Description;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        tv_Product_Name = (TextView) itemView.findViewById(R.id.tv_Product_Name);
    //    tv_Product_Description = (TextView) itemView.findViewById(R.id.tv_Product_Description);
        imgproducts = (ImageView) itemView.findViewById(R.id.imgproducts);

    }
    }
}