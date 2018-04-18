package com.znxk.charge.ui.Fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.znxk.charge.R;
import com.znxk.charge.base.BaseFragment;
import com.znxk.charge.ui.activity.TestActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class FunctionFragment extends BaseFragment {
    private ImageView mTest;



    @Override
    public int setRootView() {
        return R.layout.fragment_function;
    }

    @Override
    public void initViews() {
        mTest = (ImageView) findViewById(R.id.test);
        mTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mActivitySelf, "点到了", Toast.LENGTH_LONG).show();
                startActivity(new Intent(mActivitySelf,TestActivity.class));
            }
        });
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void initListeners() {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }
}
