package cn.studyjams.s1.sj38.dupengcan;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * this Frament  is used to display Content
 * Created by dupengcan on 16-4-26.
 */
public class ContentFrament extends ListFragment {
    //culture page
    private int[] culture_pictures = {R.drawable.culture01, R.drawable.culture02,
            R.drawable.culture03, R.drawable.culture04};
    private int[] culture_subtitles = {R.string.culture_subtitle_01, R.string.culture_subtitle_02,
            R.string.culture_subtitle_03, R.string.culture_subtitle_04};
    private int[] culture_introduces = {R.string.culture_introduce_01, R.string.culture_introduce_02,
            R.string.culture_introduce_03, R.string.culture_introduce_04};
    //sight page
    private int[] sight_pictures = {R.drawable.sight_01, R.drawable.sight_02,
            R.drawable.sight_03, R.drawable.sight_04, R.drawable.sight_05};
    private int[] sight_subtitles = {R.string.sight_subtitle_01, R.string.sight_subtitle_02,
            R.string.sight_subtitle_03, R.string.sight_subtitle_04, R.string.sight_subtitle_05};
    private int[] sight_introduces = {R.string.sight_introduce_01, R.string.sight__introduce_02,
            R.string.sight_introduce_03, R.string.sight_introduce_04, R.string.sight_introduce_05};
    //food page
    private int[] food_pictures = {R.drawable.food_01, R.drawable.food_02,
            R.drawable.food_03, R.drawable.food_04};
    private int[] food_subtitles = {R.string.food_subtitle_01, R.string.food_subtitle_02,
            R.string.food_subtitle_03, R.string.food_subtitle_04};
    private int[] food_introduces = {R.string.food_introduce_01, R.string.food_introduce_02,
            R.string.food_introduce_03, R.string.food_introduce_04};
    //historical persons page
    private int[] historical_persons_picture = {R.drawable.historical_persons_01, R.drawable.historical_persons_02,
            R.drawable.historical_persons_03};
    private int[] historical_persons_subtitles = {R.string.historical_persons_subtitle_01, R.string.historical_persons_subtitle_02,
            R.string.historical_persons_subtitle_03};
    private int[] historical_persons_introduces = {R.string.historical_persons_introduce_01, R.string.historical_persons_introduce_02,
            R.string.historical_persons_introduce_03};
    //education page
    private int[] education_pictures = {R.drawable.education_01, R.drawable.education_02,
            R.drawable.education_03};
    private int[] education_subtitles = {R.string.education_subtitle_01, R.string.education_subtitle_02,
            R.string.education_subtitle_03};
    private int[] education_introduces = {R.string.education_introduce_01, R.string.education_introduce_02,
            R.string.education_introduce_03};

    private int[][] pictures = {culture_pictures, sight_pictures, food_pictures, historical_persons_picture, education_pictures};
    private int[][] subtitles = {culture_subtitles, sight_subtitles, food_subtitles, historical_persons_subtitles, education_subtitles};
    private int[][] introduces = {culture_introduces, sight_introduces, food_introduces, historical_persons_introduces, education_introduces};
    private int indexFrament;


    private Activity mActivity;

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        introducePicture(indexFrament, position);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mActivity = getActivity();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        indexFrament = getArguments().getInt("IndexFrament");
        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), getData(indexFrament),
                R.layout.content_list, new String[]{"img", "info"},
                new int[]{R.id.img, R.id.info});
        setListAdapter(simpleAdapter);
    }

    /**
     * get the data for each page
     *
     * @param indexFrament
     * @return List<Map<String, Object>>
     */
    private List<Map<String, Object>> getData(int indexFrament) {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map;
        int[] page_pictures = pictures[indexFrament];
        int[] page_describe = subtitles[indexFrament];
        int currentPageSize = page_pictures.length;
        for (int i = 0; i < currentPageSize; i++) {
            map = new HashMap<>();
            map.put("img", page_pictures[i]);
            map.put("info", getString(page_describe[i]));
            list.add(map);
        }
        return list;
    }

    /**
     * A brief introduction to each picture that showing
     *
     * @param indexFrament
     * @param position
     */
    private void introducePicture(int indexFrament, int position) {
        final Dialog dialog = new AlertDialog.Builder(mActivity).create();
        dialog.setCancelable(true);
        dialog.show();
        DisplayMetrics metrics = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        dialog.getWindow().setLayout(metrics.widthPixels, metrics.heightPixels / 2);
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.content_detail, null);
        dialog.setContentView(view);
        TextView introduceText = (TextView) view.findViewById(R.id.introduce_text);
        introduceText.setText(introduces[indexFrament][position]);
    }

}
