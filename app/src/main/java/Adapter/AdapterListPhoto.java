package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import model.Photos;

public class AdapterListPhoto extends RecyclerView.Adapter<AdapterListPhoto.ViewHolder>{

    Context context;
    List<Photos> photosList;

    public AdapterListPhoto(Context context, List<Photos> photosList){
     this.context = context;
     this.photosList = photosList;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.items_photos_layout, null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterListPhoto.ViewHolder holder, int position) {

        String thumbnailUrl = photosList.get(position).getThumbnailUrl();
        Picasso.with(context).load(thumbnailUrl).fit().centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.icon);

        String id = photosList.get(position).getId().toString();
        holder.txtId.setText(id);

        holder.txtName.setText(photosList.get(position).getTitle());
        /**Do not treat position as fixed; only use immediately and call holder.getAdapterPosition() to look it up later
        //TOO BAD
        //Maybe need better implementations??
         // how does this thing still running with error*/
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, photosList.get(position).getTitle(),Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return photosList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView icon;
        TextView txtId, txtName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            icon = itemView.findViewById(R.id.icon);
            txtId = itemView.findViewById(R.id.txtId);
            txtName = itemView.findViewById(R.id.txtName);

        }
    }

}
