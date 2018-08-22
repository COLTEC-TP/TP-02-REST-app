package coltectp.github.io.tp_02_rest_app.charts.coin;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import coltectp.github.io.tp_02_rest_app.R;

public class StatsCoinFragmentPagerAdapter extends FragmentPagerAdapter {
    final private static int PAGE_COUNT = 3;
    private Context mContext;

    public StatsCoinFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) return MarketPriceFragment.newInstance(position + 1);
        else if (position == 1) return MarketCapFragment.newInstance(position + 1);
        else return TradeVolumeFragment.newInstance(position + 1);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.market_price_fragment);
        } else if (position == 1) {
            return mContext.getString(R.string.market_cap_fragment);
        } else {
            return mContext.getString(R.string.trade_volume_fragment);
        }
    }


    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
