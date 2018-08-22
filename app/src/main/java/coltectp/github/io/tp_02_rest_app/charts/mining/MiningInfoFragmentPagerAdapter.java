package coltectp.github.io.tp_02_rest_app.charts.mining;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import coltectp.github.io.tp_02_rest_app.R;

public class MiningInfoFragmentPagerAdapter extends FragmentPagerAdapter {
    final private static int PAGE_COUNT = 4;
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
        if (position == 0) {
            return mContext.getString(R.string.hash_rate_fragment);
        } else if (position == 1) {
            return mContext.getString(R.string.transaction_fees_fragment);
        } else if (position == 2) {
            return mContext.getString(R.string.cost_per_transaction_fragment);
        } else {
            return mContext.getString(R.string.miners_revenue_fragment);
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
