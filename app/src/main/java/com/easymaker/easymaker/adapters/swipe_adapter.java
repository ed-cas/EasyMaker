package com.easymaker.easymaker.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.easymaker.easymaker.R;

/**
 * Created by eduardo on 01/02/2018.
 * adaptador para cambiar ente los 4 fragments principales de navigationBAR inferior
 */

public class swipe_adapter extends PagerAdapter{

    private  int[] image_resources={R.drawable.i1,R.drawable.i2,R.drawable.i3,R.drawable.i4};
    private Context ctx;
    private LayoutInflater layoutInflater;

    public swipe_adapter(Context ctx){
        this.ctx = ctx;
    }

    public int getCount() {
        return image_resources.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (LinearLayout)object);
    }

    public Object instantiateItem(ViewGroup container, int position){
        layoutInflater=(LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view =  layoutInflater.inflate(R.layout.swipe_layout,container,false);
        ImageView imageView = (ImageView) item_view.findViewById(R.id.imageView);
        TextView textView = (TextView)item_view.findViewById(R.id.image_title);
        imageView.setImageResource(image_resources[position]);
        textView.setText("Image: "+position);
        container.addView(item_view);
        return  item_view;
    }

    public void destroyItem(ViewGroup container, int position, Object object){
        container.removeView((LinearLayout)object);
    }
}
