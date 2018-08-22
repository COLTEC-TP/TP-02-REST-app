package coltectp.github.io.tp_02_rest_app.charts.block;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import coltectp.github.io.tp_02_rest_app.R;

public class BlockChartsFragmentPagerAdapter extends FragmentPagerAdapter {
    final private static int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] { String.valueOf(R.string.transaction_fragment),
                                                String.valueOf(R.string.median_confirmation_time_fragment),
                                                String.valueOf(R.string.block_size_fragment) };
    private Context mContext;

    public BlockChartsFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) return TransactionPerBlockFragment.newInstance(position + 1);
        else if (position == 1) return MedianConfirmationFragment.newInstance(position + 1);
        else return BlockSizeFragment.newInstance(position + 1);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.transaction_fragment);
        } else if (position == 1) {
            return mContext.getString(R.string.median_confirmation_time_fragment);
        } else {
            return mContext.getString(R.string.block_size_fragment);
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
