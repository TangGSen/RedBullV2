package com.sen.redbull.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.sen.redbull.R;
import com.sen.redbull.imgloader.AnimateFirstDisplayListener;
import com.sen.redbull.mode.LessonItemBean;
import com.sen.redbull.tools.Constants;
import com.sen.redbull.tools.ImageLoadOptions;
import com.sen.redbull.tools.StringUItils;

import java.util.List;

/**
 * Created by Sen on 2016/2/22.
 */
public class StudyRecyclerAdapter extends RecyclerView.Adapter<StudyRecyclerAdapter.ViewHolder> {
    private List<LessonItemBean> mData;
    private Context mContext;
    int width;

    public StudyRecyclerAdapter(Context context, List<LessonItemBean> data) {
        mContext = context;
        mData = data;
    }

//    public void addLessonBeanData(List<LessonItemBean> data){
//        mData.addAll(data);
//        notifyDataSetChanged();
//    }

    private OnItemClickListener onItemClickListener = null;


    //Item click thing
    public interface OnItemClickListener {
        void onItemClick(View view, int position, LessonItemBean childItemBean);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lesson_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    onItemClickListener.onItemClick(holder.itemView, position, mData.get(position));
                }

            });
        }
        LessonItemBean itemBean = mData.get(position);
        String imgUrl = Constants.PATH_PICTURE + itemBean.getPicture();
        holder.img_course_item.setTag(imgUrl);
        holder.tv_course_name.setText( itemBean.getName());
        holder.tv_create_time.setText("上传时间：" + StringUItils.delString(itemBean.getCreatedate(),19));
        holder.tv_course_score.setText( "标准学分："+ delString(itemBean.getScore())+"学分");
        holder.tv_course_hours.setText( "标准学时："+ delString(itemBean.getHour())+"学时");
        ImageLoader.getInstance().displayImage((String) holder.img_course_item.getTag(), holder.img_course_item, ImageLoadOptions.getStudyImageOptions(),new AnimateFirstDisplayListener());

    }

    public  static String delString(String string){
        if (string==null){
            return "0";
        }
        return string;
    }



    @Override
    public int getItemCount() {
        return mData.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public AppCompatTextView tv_course_name, tv_create_time, tv_course_score,tv_course_hours;
        public AppCompatImageView img_course_item;

        public ViewHolder(View view) {
            super(view);
            tv_course_name = (AppCompatTextView) view.findViewById(R.id.tv_course_name);
            tv_course_score = (AppCompatTextView) view.findViewById(R.id.tv_course_score);
            tv_create_time = (AppCompatTextView) view.findViewById(R.id.tv_create_time);
            tv_course_hours = (AppCompatTextView) view.findViewById(R.id.tv_course_hours);
            img_course_item = (AppCompatImageView) view.findViewById(R.id.img_course_item);
        }


    }

    public void removeItem(int position) {
        mData.remove(position);
        notifyDataSetChanged();
    }
}
