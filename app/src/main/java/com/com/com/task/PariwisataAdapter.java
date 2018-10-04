package com.com.com.task;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

public class PariwisataAdapter extends ArrayAdapter<Model> {

    private List<Model> models;

    public PariwisataAdapter(List<Model> models, Context context) {
        super(context, R.layout.items, models);
        this.models = models;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.items, null);
        }

        Model model = models.get(position);

        if (model != null) {
            TextView namaTextView = view.findViewById(R.id.nama);
            TextView alamatTextView = view.findViewById(R.id.alamat);
            TextView detailTextView = view.findViewById(R.id.detail);
            final ImageView gambarImageView = view.findViewById(R.id.gambar);

            namaTextView.setText(model.getNamaP());
            alamatTextView.setText(model.getAlamatP());
            detailTextView.setText(model.getDetailP());

            Picasso.get().load(model.getGambarP()).into(gambarImageView);

//            Picasso.get().load(model.getGambarP()).into(
//                    new Target() {
//                        @Override
//                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
//                            gambarImageView.setBackground(new BitmapDrawable(getContext().getResources()));
//                        }
//
//                        @Override
//                        public void onBitmapFailed(Exception e, Drawable errorDrawable) {
//                            Log.v("onBitmapFailed", "failed");
//                        }
//
//                        @Override
//                        public void onPrepareLoad(Drawable placeHolderDrawable) {
//                            Log.v("onPrepareLoad", "failed");
//                        }
//                    }
//            );

//            Picasso.get().load(model.getGambarP()).into(gambarImageView);

//            gambarImageView.setImageResource(Integer.parseInt(model.getGambarP()));
        }
        return view;
    }
}
