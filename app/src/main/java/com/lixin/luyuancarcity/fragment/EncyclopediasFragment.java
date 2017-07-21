package com.lixin.luyuancarcity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lixin.luyuancarcity.R;

/**
 * Created by 小火
 * Create time on  2017/7/15
 * My mailbox is 1403241630@qq.com
 */


    public class EncyclopediasFragment extends Fragment {
        private View view;
        public static final String ARG_PAGE = "ARG_PAGE";
        private int mPage;
        private PullToRefreshListView forum_list;

        public static PageFragment newInstance(int page) {
            Bundle args = new Bundle();
            args.putInt(ARG_PAGE, page);
            PageFragment pageFragment = new PageFragment();
            pageFragment.setArguments(args);
            return pageFragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mPage = getArguments().getInt(ARG_PAGE);
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            view = inflater.inflate(R.layout.fragment_page, container, false);
            initView();
//        textView.setText("Fragment #" + mPage);
            return view;
        }

        private void initView() {
            forum_list = (PullToRefreshListView) view.findViewById(R.id.forum_list);

        }

    }
