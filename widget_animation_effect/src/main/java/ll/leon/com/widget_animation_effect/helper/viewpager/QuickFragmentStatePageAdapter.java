package ll.leon.com.widget_animation_effect.helper.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class QuickFragmentStatePageAdapter<T extends Fragment> extends FragmentStatePagerAdapter {
  private List<T> mList;
  private String[] mStrings;

  /**
   * @param fm
   * @param list
   * @param titles PageTitles
   */
  public QuickFragmentStatePageAdapter(FragmentManager fm, List<T> list, String[] titles) {
      super(fm);
      mList = list;
      mStrings = titles;
  }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mStrings == null ? super.getPageTitle(position) : mStrings[position];
    }

}
