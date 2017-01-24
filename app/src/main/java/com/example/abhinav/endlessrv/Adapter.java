package com.example.abhinav.endlessrv;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

/**
 * Created by ABHINAV on 20-05-2016.
 */
final class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater minflater;
    private int count;
    Context con;
    ArrayList<user> mResults;

    public Adapter(Context context, ArrayList<user> result) {
        minflater = LayoutInflater.from(context);
        con = context;
        mResults = result;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = minflater.inflate(R.layout.row_event, parent, false);
        return new GalleryHol(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)  {
        GalleryHol galleryHol = (GalleryHol) holder;
        galleryHol.largetitle.setText(mResults.get(position).getLargetitle().toString());
        galleryHol.smalltitleleft.setText(mResults.get(position).getSmalltitleleft().toString());
        galleryHol.smalltitleright.setText(mResults.get(position).getSmalltitleright().toString());
        galleryHol.largedate.setText(mResults.get(position).getLargedate().toString());
        galleryHol.smalldateleft.setText(mResults.get(position).getSmalldateleft().toString());
        galleryHol.smalldateright.setText(mResults.get(position).getSmalldateright().toString());
        Glide.with(con).load(mResults.get(position).getLargeimage()).centerCrop().listener((new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                //pb.setVisibility(View.GONE);
                return false;
            }
        })).crossFade().into(galleryHol.Largeimage);
        galleryHol.Largeimage.setOnClickListener(new OnImageClickListener(position));
        Glide.with(con).load(mResults.get(position).getSmallimageright()).centerCrop().listener((new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                //pb.setVisibility(View.GONE);
                return false;
            }
        })).crossFade().into(galleryHol.smallmageright);
        galleryHol.smallmageright.setOnClickListener(new OnImageClickListener(position));
        Glide.with(con).load(mResults.get(position).getSmallimageleft()).centerCrop().listener((new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                //pb.setVisibility(View.GONE);
                return false;
            }
        })).crossFade().into(galleryHol.smallimageleft);
        galleryHol.smallimageleft.setOnClickListener(new OnImageClickListener(position));
    }


    @Override
    public int getItemCount() {
        return mResults.size();
    }


    private class GalleryHol extends RecyclerView.ViewHolder {

        TextView largetitle;
        TextView smalltitleleft;
        TextView smalltitleright;
        TextView largedate;
        TextView smalldateleft;
        TextView smalldateright;
        ImageView Largeimage;
        ImageView smallimageleft;
        ImageView smallmageright;


        public GalleryHol(View itemView) {
            super(itemView);
            largetitle = (TextView) itemView.findViewById(R.id.Largetext);
            smalltitleleft = (TextView) itemView.findViewById(R.id.largetextleft);
            smalltitleright = (TextView) itemView.findViewById(R.id.largetextright);
            largedate = (TextView) itemView.findViewById(R.id.smalltext);
            smalldateleft = (TextView) itemView.findViewById(R.id.smalltextleft);
            smalldateright = (TextView) itemView.findViewById(R.id.smalltextright);
            Largeimage = (ImageView) itemView.findViewById(R.id.Largeimage);
            smallimageleft = (ImageView) itemView.findViewById(R.id.smallimageleft);
            smallmageright = (ImageView) itemView.findViewById(R.id.smallimageright);
        }
    }

        class OnImageClickListener implements View.OnClickListener {

            int _postion;
            String _id;

            // constructor
            public OnImageClickListener(int position) {
                this._postion = position;
            }

            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.Largeimage:
                        String category =mResults.get(_postion).getLargecategory();
                        if(category.equalsIgnoreCase("mediacoverage")){
                            Intent intent=new Intent(con,MediaCoverage.class);
                            con.startActivity(intent);
                            Log.d("ani",_postion+"Hi");
                        }
                        else if (category.equalsIgnoreCase("infograhics")){
                            Intent intent=new Intent(con,Infographics.class);
                            con.startActivity(intent);
                            Log.d("ani",_postion+"Hi");
                        }
                         else if (category.equalsIgnoreCase("Feed")){

                            Intent intent=new Intent(con,Feed.class);
                            con.startActivity(intent);
                            Log.d("ani",_postion+"Hi");
                            }
                        break;
                    case R.id.smallimageleft:
                        String smallleft =mResults.get(_postion).getSmallcategoryleft();
                        if(smallleft.equalsIgnoreCase("mediacoverage")){
                            Intent intent=new Intent(con,MediaCoverage.class);
                            con.startActivity(intent);
                        }
                        else if (smallleft.equalsIgnoreCase("infograhics")){
                            Intent intent=new Intent(con,Infographics.class);
                            con.startActivity(intent);
                            Log.d("ani",_postion+"Hi");
                        }

                        else if (smallleft.equalsIgnoreCase("Feed")){

                            Intent intent=new Intent(con,Feed.class);
                            con.startActivity(intent);
                            Log.d("ani",_postion+"Hi");
                        }
                        break;
                    case R.id.smallimageright:
                        String smalllright =mResults.get(_postion).getSmallcategoryright();
                        if(smalllright.equalsIgnoreCase("mediacoverage")){
                            Intent intent=new Intent(con,MediaCoverage.class);
                            con.startActivity(intent);
                            Log.d("ani",_postion+"Hi");
                        }
                        else if (smalllright.equalsIgnoreCase("infograhics")){
                            Intent intent=new Intent(con,Infographics.class);
                            con.startActivity(intent);
                            Log.d("ani",_postion+"Hi");
                        }

                        else if (smalllright.equalsIgnoreCase("Feed")){

                            Intent intent=new Intent(con,Feed.class);
                            con.startActivity(intent);
                            Log.d("ani",_postion+"Hi");
                        }
                        break;
            }
        }
    }
}
