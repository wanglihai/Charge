package com.znxk.charge.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by w on 2017/11/16.
 */

public abstract class BaseFragment extends Fragment {

    protected BaseActivity mActivitySelf;
    public LayoutInflater mLayoutInflater;
    public View mContentView = null;
    public FragmentManager mFragmentManager;
    public BaseFragment mFragmentSelf;

//    //获取宿主Activity
//    protected BaseActivity getHoldingActivity() {
//        return mActivity;
//    }

    @Override
    public void onAttach(Context context) {//Modified 2016-06-01</span>
        super.onAttach(context);
    }

    //添加fragment
    protected void addFragment(BaseFragment fragment) {
        if (null != fragment) {
            mActivitySelf.addFragment(fragment);
        }
    }

    //移除fragment
    protected void removeFragment() {
        mActivitySelf.removeFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        initView(view, savedInstanceState);
        mFragmentManager = this.getFragmentManager();
        mFragmentSelf = this;
        mActivitySelf = (BaseActivity) this.getActivity();
        mLayoutInflater = inflater;
        mContentView = mLayoutInflater.inflate(setRootView(), container, false);
        initViews();
        initDatas();
        initListeners();
        return mContentView;
    }

    public View findViewById(int resID) {
        return mContentView.findViewById(resID);
    }


    //------------标题栏操作
    public abstract int setRootView();

    public abstract void initViews();

    public abstract void initDatas();

    public abstract void initListeners();

    //获取布局文件ID
    protected abstract int getLayoutId();

    /**打开新的Activity，向左滑入效果
     * @param intent
     */
    public void toActivity(Intent intent) {
        toActivity(intent, true);
    }

    /**打开新的Activity
     * @param intent
     * @param showAnimation
     */
    public void toActivity(Intent intent, boolean showAnimation) {
        toActivity(intent, -1);
    }

    /**打开新的Activity，向左滑入效果
     * @param intent
     * @param requestCode
     */
    public void toActivityResult(Intent intent, int requestCode) {
        toActivity(intent, requestCode);
    }

    /**打开新的Activity
     * @param intent
     * @param requestCode
     */
    public void toActivity(final Intent intent, final int requestCode) {
                if (intent == null) {
                    return;
                }
                //fragment中使用mActivitySelf.startActivity会导致在fragment中不能正常接收onActivityResult
                if (requestCode < 0) {
                    startActivity(intent);
                } else {
                    startActivityForResult(intent, requestCode);
                }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    
}
