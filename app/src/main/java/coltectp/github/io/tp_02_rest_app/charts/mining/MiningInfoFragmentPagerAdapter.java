package coltectp.github.io.tp_02_rest_app.charts.mining;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MiningInfoFragmentPagerAdapter extends FragmentPagerAdapter {
    final private static int PAGE_COUNT = 4;
    private String tabTitles[] = new String[] { "Hash Rate",
                                                "Miners Revenue",
                                                "Transaction Fees",
                                                "Cost/Transaction"
                                                };
    private Context mContext;

    public MiningInfoFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) return HashRateFragment.newInstance(position + 1);
        else if (position == 1) return TransactionFeesFragment.newInstance(position + 1);
        else if (position == 2) return CostTransactionFragment.newInstance(position + 1);
        else return MinersRevenueFragment.newInstance(position + 1);
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
