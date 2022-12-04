
package com.example.slambook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    Context context;
    int layout;
    ArrayList<Entry> entryList;
    OnItemClickListener listener;
    Button entryDelete,entryEdit;


    public RecyclerViewAdapter(Context context, int layout, ArrayList<Entry> entryList) {
        this.context = context;
        this.layout = layout;
        this.entryList = entryList;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }


    public void setOnItemClickListener(OnItemClickListener clickListener) {
        listener = clickListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View convertView = inflater.inflate(layout, parent, false);
        ViewHolder vh = new ViewHolder(convertView);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder vh, int position) {
        Entry oneEntry = entryList.get(position);
        vh.entryPicture.setImageResource(oneEntry.getEntryPicture());
        vh.entryFullName.setText(oneEntry.getEntryFullName());
        vh.entryRemark.setText(oneEntry.getEntryRemark());
    }

    @Override
    public int getItemCount() {
        return entryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView entryPicture;
        TextView entryFullName;
        TextView entryRemark;

        public ViewHolder(@NonNull View convertView) {
            super(convertView);
            this.entryPicture = convertView.findViewById(R.id.entryPicture);
            this.entryFullName = convertView.findViewById(R.id.entryFullName);
            this.entryRemark = convertView.findViewById(R.id.entryRemark);
            entryDelete = convertView.findViewById(R.id.btnDeleteEntry);
            entryEdit = convertView.findViewById(R.id.btnEditEntry);

            entryDelete.setOnClickListener(btnDeleteEntry);
            entryEdit.setOnClickListener(btnEditEntry);
//            entryDelete.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (listener != null) {
//                        int position = getAdapterPosition();
//                        if (position != RecyclerView.NO_POSITION) {
//                            listener.onItemClick(position);
//                        }
//                    }
//                }
//            });


        }

        private View.OnClickListener btnDeleteEntry = new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);

                        }
                    }
            }
        };
        private View.OnClickListener btnEditEntry = new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);

                    }
                }
            }
        };
    }
}
