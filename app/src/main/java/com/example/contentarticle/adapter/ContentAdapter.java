package com.example.contentarticle.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.arch.persistence.room.Delete;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.contentarticle.R;
import com.example.contentarticle.activity.DetailContentActivity;
import com.example.contentarticle.activity.HomeActivity;
import com.example.contentarticle.helper.DatabaseClient;
import com.example.contentarticle.model.room.Content;

import java.util.List;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ContentViewHolder> {

    private Context mContext;
    private List<Content> contentList;

    public ContentAdapter(Context mContext, List<Content> contentList){

        this.mContext = mContext;
        this.contentList = contentList;

    }


    @Override
    public ContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_content, parent, false);
        return new ContentViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ContentViewHolder holder, int position) {

        Content content = contentList.get(position);
        holder.textViewJudul.setText(content.getJudul());
        holder.textViewTanggal.setText((content.getTanggal()));

    }

    @Override
    public int getItemCount() {
        return contentList.size();
    }

    class ContentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView edit, delete;
        TextView textViewJudul, textViewTanggal;

        public ContentViewHolder(View itemView) {
            super(itemView);
            textViewJudul = itemView.findViewById(R.id.judul);
            textViewTanggal = itemView.findViewById(R.id.tgl);
            edit = itemView.findViewById(R.id.edit);
            delete = itemView.findViewById(R.id.delete);

            itemView.setOnClickListener(this);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setTitle("Are You Sure");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                           DeleteContent(contentList.get(getAdapterPosition()));
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });

        }

        @Override
        public void onClick(View view) {

            Content content = contentList.get(getAdapterPosition());

            Intent intent = new Intent(mContext, DetailContentActivity.class);
            intent.putExtra("contentData", content);
            mContext.startActivity(intent);
            ((Activity) mContext).finish();
        }
    }

    private void DeleteContent(final Content content) {

        class delete extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {

                DatabaseClient.getInstance(mContext).getAppDatabase().contentDao().delete(content);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(mContext, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
                mContext.startActivity(new Intent(mContext, HomeActivity.class));
                ((Activity) mContext).finish();
            }
        }

        delete dt = new delete();
        dt.execute();
    }
}
