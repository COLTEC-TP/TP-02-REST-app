package coltectp.github.io.tp_02_rest_app.charts.block;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class BlockChartsFragmentPagerAdapter extends FragmentPagerAdapter {
    final private static int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] { "Transaction", "Median Confirmation", "Block Size" };
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
        return tabTitles[position];
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
