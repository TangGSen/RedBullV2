package com.sen.redbull.activity.study;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.sen.redbull.R;
import com.sen.redbull.adapter.SectionsAdapter;
import com.sen.redbull.base.BaseActivity;
import com.sen.redbull.mode.LessonCommentCounts;
import com.sen.redbull.mode.LessonCourseDetails;
import com.sen.redbull.mode.LessonHomeCourseDatails;
import com.sen.redbull.mode.LessonItemBean;
import com.sen.redbull.mode.SectionBean;
import com.sen.redbull.mode.SectionItemBean;
import com.sen.redbull.tools.AcountManager;
import com.sen.redbull.tools.Constants;
import com.sen.redbull.tools.DataTool;
import com.sen.redbull.tools.DialogUtils;
import com.sen.redbull.tools.NetUtil;
import com.sen.redbull.tools.ToastUtils;
import com.sen.redbull.widget.RecyleViewItemDecoration;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

public class LessonDetailActivity extends BaseActivity {


    @Bind(R.id.btn_studydetail_back)
    AppCompatTextView btn_studydetail_back;
    @Bind(R.id.tv_head_name)
    AppCompatTextView tv_head_name;
    @Bind(R.id.btn_startPlayer)
    AppCompatImageView btn_startPlayer;
    /*课程介绍*/
    @Bind(R.id.tv_course_name)
    AppCompatTextView tv_course_name;
    @Bind(R.id.tv_standard_time)
    AppCompatTextView tv_standard_time;
    @Bind(R.id.tv_standard_scorce)
    AppCompatTextView tv_standard_scorce;
    @Bind(R.id.tv_all_time)
    AppCompatTextView tv_all_time;
    @Bind(R.id.tv_get_sorce)
    AppCompatTextView tv_get_sorce;
    @Bind(R.id.tv_learn_progress)
    AppCompatTextView tv_learn_progress;
    @Bind(R.id.tv_content_introduction)
    AppCompatTextView tv_content_introduction;
    @Bind(R.id.tv_commons_count)
    AppCompatTextView tv_commons_count;
    @Bind(R.id.tv_data_null_tip)
    AppCompatTextView tv_data_null_tip;

    @Bind(R.id.listview_lesson)
    RecyclerView listview_lesson;


    private LessonItemBean childItemBean;
    private int itemPosition;

    private static final int SHOW_COMMENT_DATA = 0;
    private static final int GET_COMMENT_DATA_ERROR = 1;
    private static final int SHOW_SECTION_DATA = 2;
    private static final int GET_SECTION_DATA_ERROR = 3;
    private static final int SHOW_LESSDETAIL_DATA = 4;
    private static final int GET_LESSDETAIL_DATA_ERROR = 5;

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            DialogUtils.closeDialog();
            switch (msg.what) {

                case 0:
                    String counts = (String) msg.obj;
                    tv_commons_count.setText("用户评论(" + counts + ")");
                    break;
                case 1:
                    ToastUtils.showTextToast(LessonDetailActivity.this, "获取评论个数失败，请稍后重试");
                    break;

                case 2:
                    SectionBean sectionBean = (SectionBean) msg.obj;
                    if (sectionBean==null){
                        return false;
                    }

                    List<SectionItemBean> setionList = sectionBean.getSectionlist();
                    if (setionList.size()==0){

                    }else {
                        //创建并设置Adapter
                        SectionsAdapter adapter = new SectionsAdapter(LessonDetailActivity.this, setionList, childItemBean.getLeid());
                        listview_lesson.setAdapter(adapter);
                    }
                    break;
                case 3:
                    ToastUtils.showTextToast(LessonDetailActivity.this, "获取课程章节失败，请稍后重试");

                    break;
                case 4:
                    LessonCourseDetails detail = (LessonCourseDetails) msg.obj;
                    if (detail != null) {
                        showLessDetail(detail);
                    }
                    break;
                case 5:
                    ToastUtils.showTextToast(LessonDetailActivity.this, "获取课程详情失败，请稍后重试");

                    break;

            }

            return false;
        }
    });

    private void showLessDetail(LessonCourseDetails courseDetails) {

        String lessName = courseDetails.getLe_name();
        if (TextUtils.isEmpty(lessName)) {
            lessName = "";
        }
        String hour = courseDetails.getHour();
        if (TextUtils.isEmpty(hour)) {
            hour = 0 + "学时";
        } else {
            hour += "学时";
        }
        String lessScore = courseDetails.getLescore();
        if (TextUtils.isEmpty(lessScore)) {
            lessScore = 0 + "学分";
        } else {
            lessScore += "学分";
        }
        String studyTme = courseDetails.getStudytime();
        if (TextUtils.isEmpty(studyTme)) {
            studyTme += "00:00:00";
        } else {
            int studyTimes = Integer.parseInt(studyTme);
            studyTme = DataTool.secToTime(studyTimes);
        }
        String score = courseDetails.getScore();
        if (TextUtils.isEmpty(score)) {
            score = 0 + "学分";
        } else {
            score += "学分";
        }
        String studyPlan = courseDetails.getStudyplan();
        if (TextUtils.isEmpty(studyPlan)) {
            studyPlan = "0%";
        } else {
            studyPlan += "%";
        }
        tv_course_name.setText(lessName);
        tv_standard_scorce.setText(lessScore);
        tv_standard_time.setText(hour);
        tv_all_time.setText(studyTme);
        tv_get_sorce.setText(score);
        tv_learn_progress.setText(studyPlan);
    }


    @Override
    protected void init() {
        super.init();
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("FragmentStudyBundle");
        childItemBean = (LessonItemBean) bundle.getSerializable("itemLessonBean");
        itemPosition = bundle.getInt("itemPosition");

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        setContentView(R.layout.activity_lesdetail);
        ButterKnife.bind(this);
        settingRecyleView();
    }


    private void settingRecyleView() {
        LinearLayoutManager linearnLayoutManager = new LinearLayoutManager(this);
        listview_lesson.setLayoutManager(linearnLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        listview_lesson.setHasFixedSize(true);
//        添加分割线
        listview_lesson.addItemDecoration(new RecyleViewItemDecoration(this, R.drawable.shape_recycle_item_decoration));


    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        if (NetUtil.isNetworkConnected(this)) {
            getLessonDetail();
            getCommentCounts();

        } else {
            ToastUtils.showTextToast(LessonDetailActivity.this, "网络未连接");
        }

    }

    //获取课程详情
    private void getLessonDetail() {
        String url = Constants.PATH + Constants.PATH_LESSONCOURSE_DETAILS;
        OkHttpUtils.post()
                .url(url)
                .addParams("leID", childItemBean.getLeid())
                .addParams("userid", AcountManager.getAcountId())
                .build()
                .execute(new Callback<LessonHomeCourseDatails>() {
                    @Override
                    public void onBefore(Request request) {
                        super.onBefore(request);
                    }

                    @Override
                    public LessonHomeCourseDatails parseNetworkResponse(Response response) throws Exception {

                        String string = response.body().string();
                        Log.e("sen*******************", string);
                        LessonHomeCourseDatails homeBean = JSON.parseObject(string, LessonHomeCourseDatails.class);

                        return homeBean;
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        mHandler.sendEmptyMessage(GET_LESSDETAIL_DATA_ERROR);
                    }

                    @Override
                    public void onResponse(LessonHomeCourseDatails homeBean) {
                        LessonCourseDetails detail = (LessonCourseDetails) homeBean.getLessonCourseDetails().get(0);
                        Message message = Message.obtain();
                        message.obj = detail;
                        message.what = SHOW_LESSDETAIL_DATA;
                        mHandler.sendMessage(message);
                        if (!detail.getWhether().equals("0")) {

                            // 然后去请求课程的列表
                            getSectionListData();
                        }
                    }
                });

    }

    //获取课程章节数据
    private void getSectionListData() {
        String url = Constants.PATH + Constants.PATH_GETSECTION;
        OkHttpUtils.post()
                .url(url)
                .addParams("leid", childItemBean.getLeid())
                .build()
                .execute(new Callback<SectionBean>() {
                    @Override
                    public void onBefore(Request request) {
                        super.onBefore(request);
                    }

                    @Override
                    public SectionBean parseNetworkResponse(Response response) throws Exception {

                        String string = response.body().string();
                        Log.e("sen*******************", string);
                        SectionBean lesssonBean = JSON.parseObject(string, SectionBean.class);
                        return lesssonBean;

                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        mHandler.sendEmptyMessage(GET_SECTION_DATA_ERROR);
                    }

                    @Override
                    public void onResponse(SectionBean lesssonBean) {
                        Message message = Message.obtain();
                        message.obj = lesssonBean;
                        message.what = SHOW_SECTION_DATA;
                        mHandler.sendMessage(message);

                    }
                });
    }

    //获取用户评论
    private void getCommentCounts() {
        String url = Constants.PATH + Constants.PATH_LESSONCOMMENTS;
        OkHttpUtils.post()
                .url(url)
                .addParams("leid", childItemBean.getLeid())
                .build()
                .execute(new Callback<String>() {
                    @Override
                    public void onBefore(Request request) {
                        super.onBefore(request);
                    }

                    @Override
                    public String parseNetworkResponse(Response response) throws Exception {

                        String string = response.body().string();
                        Log.e("sen*******************", string);
                        LessonCommentCounts commentCounts = JSON.parseObject(string, LessonCommentCounts.class);
                        if (commentCounts.getSuccess().equals("true")) {
                            String counts = commentCounts.getComment();
                            return counts;
                        } else {
                            mHandler.sendEmptyMessage(GET_COMMENT_DATA_ERROR);
                        }
                        return "0";
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        mHandler.sendEmptyMessage(GET_COMMENT_DATA_ERROR);
                    }

                    @Override
                    public void onResponse(String strCount) {
                        Message message = Message.obtain();
                        message.obj = strCount;
                        message.what = SHOW_COMMENT_DATA;
                        mHandler.sendMessage(message);

                    }
                });
    }
}