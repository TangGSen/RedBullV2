package com.sen.redbull.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sen.redbull.R;
import com.sen.redbull.mode.ResourseKindBean;

import java.util.List;

/**
 * Created by Sen on 2016/2/22.
 */
public class ResoursCatalogAdapter extends RecyclerView.Adapter<ResoursCatalogAdapter.ViewHolder> {
    private List<String> mData;
    private Context mContext;

    public ResoursCatalogAdapter(Context context, List<String> data) {
        mContext = context;
        mData = data;
    }



    private OnItemClickListener onItemClickListener = null;


    //Item click thing
    public interface OnItemClickListener {
        void onItemClick(View view, int position, ResourseKindBean childItemBean);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragment_resource, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        String itemBean = mData.get(position);

        holder.item_catalog_name.setText(itemBean);



    }




    @Override
    public int getItemCount() {
        return mData.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public AppCompatTextView item_catalog_name;

        public ViewHolder(View view) {
            super(view);
            item_catalog_name = (AppCompatTextView) view.findViewById(R.id.tv_catalog);


        }


    }
}
