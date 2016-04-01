package com.sen.redbull.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sen.redbull.R;
import com.sen.redbull.activity.ActSearchLesson;
import com.sen.redbull.adapter.ResoursCatalogAdapter;
import com.sen.redbull.base.BaseFragment;
import com.sen.redbull.widget.RecyleViewItemDecoration;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Sen on 2016/3/3.
 */
public class FragmentRepository extends BaseFragment {
    private View rootView;
    @Bind(R.id.resourse_et_search)
    AppCompatTextView resourse_et_search;
    @Bind(R.id.resourse_listview)
    RecyclerView resourse_listview;


    private void getLessonCatalog() {
        String[] stringArrays = getActivity().getResources().getStringArray(R.array.resource_arrays);
        List<String> list = Arrays.asList(stringArrays);
        ResoursCatalogAdapter adapter = new ResoursCatalogAdapter(getContext(),list);
        resourse_listview.setAdapter(adapter);

    }


    @Override
    protected void dealAdaptationToPhone() {

    }

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_resourse, container, false);
        ButterKnife.bind(this, rootView);
        settingRecyleView();


        return rootView;
    }

    private void settingRecyleView() {

        LinearLayoutManager linearnLayoutManager = new LinearLayoutManager(getActivity());
        resourse_listview.setLayoutManager(linearnLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        resourse_listview.setHasFixedSize(true);
//            添加分割线
        resourse_listview.addItemDecoration(new RecyleViewItemDecoration(getContext(), R.drawable.shape_recycle_item_decoration));

    }


    @Override
    public void onDestroy() {


        super.onDestroy();
    }


    @Override
    protected void initData() {
        getLessonCatalog();
    }

    @OnClick(R.id.resourse_et_search)
    public void search() {
        Intent intent = new Intent(getActivity(), ActSearchLesson.class);
        startActivity(intent);
    }

}
