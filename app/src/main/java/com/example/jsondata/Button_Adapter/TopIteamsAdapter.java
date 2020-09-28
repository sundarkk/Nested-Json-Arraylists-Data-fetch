package com.example.jsondata.Button_Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jsondata.DataAdapter;
import com.example.jsondata.Model.Categroy;
import com.example.jsondata.Model.TopItem;
import com.example.jsondata.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TopIteamsAdapter extends RecyclerView.Adapter<TopIteamsAdapter.MyViewHolder> {
    private ArrayList<Categroy> dataModelList;
    private Context context;
    int counter = 0;


    public TopIteamsAdapter(ArrayList<Categroy> dataModelList, Context context) {
        this.dataModelList = dataModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public TopIteamsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.items_list, parent, false);
        return new TopIteamsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {

        if (!dataModelList.get(position).getImage().isEmpty()) {
            Picasso.with(context)
                    .load(dataModelList.get(position).getImage())
                    .placeholder(R.drawable.ic_launcher_background) //this is optional the image to display while the url image is downloading
                    .error(R.drawable.ic_launcher_background)         //this is also optional if some error has occurred in downloading the image this image would be displayed
                    .into(holder.imgproducts);
        }


        holder.tv_Product_Name.setText(String.valueOf(dataModelList.get(position).getName()));
        holder.tv_Product_Description.setVisibility(View.GONE);
      //  holder.tv_Product_Description.setText(String.valueOf(dataModelList.get(position).getDescription()));
        holder.tv_Product_Quantity.setText(counter+"");
       holder.Btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              counter++;
              holder.tv_Product_Quantity.setText(counter+"");

            }
        });

        holder.Btn_Remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(counter!=0){
                    counter--;
                }
                holder.tv_Product_Quantity.setText(counter+"");

            }
        });

    }



    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgproducts;
        TextView tv_Product_Name, tv_Product_Description,tv_Product_Quantity;
        Button Btn_Add, Btn_Remove;
       // TextView tvAddCate;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_Product_Name = (TextView) itemView.findViewById(R.id.tv_Product_Name);
            tv_Product_Description = (TextView) itemView.findViewById(R.id.tv_Product_Description);
            imgproducts = (ImageView) itemView.findViewById(R.id.imgproducts);

            tv_Product_Quantity = (TextView) itemView.findViewById(R.id.tv_Product_Quantity);

            Btn_Add = (Button) itemView.findViewById(R.id.Btn_Add);
            Btn_Remove = (Button) itemView.findViewById(R.id.Btn_Remove);


        }
    }
}