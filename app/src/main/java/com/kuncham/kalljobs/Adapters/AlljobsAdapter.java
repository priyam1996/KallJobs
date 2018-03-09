package com.kuncham.kalljobs.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kuncham.kalljobs.Alljobs;
import com.kuncham.kalljobs.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

/**
 * Created by admin on 3/8/2018.
 */

public class AlljobsAdapter extends BaseAdapter {

    Context mAlljobsContext;
    List<Alljobs> alljobsList;
    ImageLoader mAlljobsImage;
    int layout;

    public AlljobsAdapter(Context mAlljobsContext, List<Alljobs> alljobsList, int layout) {
        this.mAlljobsContext = mAlljobsContext;
        this.alljobsList = alljobsList;
        this.layout = layout;

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(mAlljobsContext).build();
        mAlljobsImage=ImageLoader.getInstance();
        mAlljobsImage.init(config);
    }

    @Override
    public int getCount() {
        return alljobsList.size();
    }

    @Override
    public Object getItem(int i) {
        return alljobsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater alljobsLayout = (LayoutInflater)mAlljobsContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View alljobsView = alljobsLayout.inflate(layout,null);

        ImageView imageAlljob = (ImageView)alljobsView.findViewById(R.id.image_alljobs);
        TextView headAlljob = (TextView)alljobsView.findViewById(R.id.heading_alljobs);
        TextView contentAlljob = (TextView)alljobsView.findViewById(R.id.content_alljobs);
        //Button readAlljob = (Button) alljobsView.findViewById(R.id.readmore_alljobs);

        Alljobs aj=alljobsList.get(i);

        headAlljob.setText(aj.getHeadAlljobs());
        contentAlljob.setText(aj.getContentALljobs());
       // readAlljob.setText(aj.getReadMore());
        mAlljobsImage.displayImage(aj.getImageAlljobs(),imageAlljob);

        return alljobsView;
    }
}
