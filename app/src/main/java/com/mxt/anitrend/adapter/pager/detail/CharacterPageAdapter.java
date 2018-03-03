package com.mxt.anitrend.adapter.pager.detail;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.mxt.anitrend.R;
import com.mxt.anitrend.base.custom.pager.BaseStatePageAdapter;
import com.mxt.anitrend.util.KeyUtils;
import com.mxt.anitrend.view.fragment.detail.CharacterOverviewFragment;
import com.mxt.anitrend.view.fragment.group.SeriesRolesFragment;
import com.mxt.anitrend.view.fragment.group.SeriesStaffRoleFragment;

/**
 * Created by max on 2017/12/01.
 */

public class CharacterPageAdapter  extends BaseStatePageAdapter {

    public CharacterPageAdapter(FragmentManager fragmentManager, Context context) {
        super(fragmentManager, context);
        setPagerTitles(R.array.character_page_titles);
    }

    @Override
    public int getCount() {
        return super.getCount() - 1;
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {
        Bundle bundle;
        switch (position) {
            case 0:
                return CharacterOverviewFragment.newInstance(getParams());
            case 1:
                bundle = new Bundle();
                bundle.putInt(KeyUtils.arg_series_type, KeyUtils.ANIME);
                return SeriesRolesFragment.newInstance(bundle);
            case 2:
                bundle = new Bundle();
                bundle.putInt(KeyUtils.arg_series_type, KeyUtils.MANGA);
                return SeriesRolesFragment.newInstance(bundle);
            case 3:
                return new Fragment();
        }
        return null;
    }
}