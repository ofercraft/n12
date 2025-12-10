package com.channel2.mobile.ui.helpers;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.FrameLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.configs.MainConfig;
import com.channel2.mobile.ui.customViews.CustomFragment;
import com.channel2.mobile.ui.header.Header;
import com.channel2.mobile.ui.header.HeaderVisibility;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes2.dex */
public class NavigationManager {
    private Handler handler;
    public HashMap<Integer, ArrayList<CustomFragment>> tabs = new HashMap<>();
    private HashMap<Integer, FrameLayout> headers = new HashMap<>();

    public interface Handler {
        void onHeaderBackPressed();
    }

    public interface RemoveLastHandler {
        void onBackPressed();

        void setVisibleHeader();
    }

    public NavigationManager(Handler handler) {
        this.handler = handler;
    }

    public void addTab(int i, Context context) {
        this.tabs.put(Integer.valueOf(i), new ArrayList<>());
        this.headers.put(Integer.valueOf(i), new FrameLayout(context));
    }

    public ArrayList<CustomFragment> getTabFragments(int i) {
        return this.tabs.get(Integer.valueOf(i));
    }

    public FrameLayout getTabHeaders(int i) {
        return this.headers.get(Integer.valueOf(i));
    }

    public CustomFragment getLastFragment(int i) {
        ArrayList<CustomFragment> tabFragments = getTabFragments(i);
        if (tabFragments == null || tabFragments.size() <= 0) {
            return null;
        }
        return tabFragments.get(tabFragments.size() - 1);
    }

    public void removeAll(int i, FragmentManager fragmentManager) {
        ArrayList<CustomFragment> tabFragments = getTabFragments(i);
        if (tabFragments != null && tabFragments.size() > 0) {
            for (int i2 = 0; i2 < tabFragments.size(); i2++) {
                FragmentTransaction fragmentTransactionBeginTransaction = fragmentManager.beginTransaction();
                CustomFragment customFragment = tabFragments.get(i2);
                if (customFragment != null) {
                    customFragment.onDestroy();
                    fragmentTransactionBeginTransaction.remove(customFragment);
                    fragmentTransactionBeginTransaction.commitAllowingStateLoss();
                }
            }
            tabFragments.clear();
        }
        FrameLayout tabHeaders = getTabHeaders(i);
        FrameLayout frameLayout = (FrameLayout) tabHeaders.getChildAt(0);
        ((FrameLayout) frameLayout.getParent()).removeView(frameLayout);
        tabHeaders.removeAllViews();
        tabHeaders.addView(frameLayout);
    }

    public void removeLast(int i, FragmentManager fragmentManager, RemoveLastHandler removeLastHandler) {
        try {
            ArrayList<CustomFragment> tabFragments = getTabFragments(i);
            if (tabFragments != null && tabFragments.size() > 0) {
                CustomFragment customFragment = tabFragments.get(tabFragments.size() - 1);
                if (customFragment != null) {
                    FragmentTransaction fragmentTransactionBeginTransaction = fragmentManager.beginTransaction();
                    customFragment.onDestroy();
                    fragmentTransactionBeginTransaction.remove(customFragment);
                    fragmentTransactionBeginTransaction.commitAllowingStateLoss();
                    tabFragments.remove(tabFragments.size() - 1);
                    FrameLayout tabHeaders = getTabHeaders(i);
                    tabHeaders.removeView((FrameLayout) tabHeaders.getChildAt(tabHeaders.getChildCount() - 1));
                    if (i == 0 && tabFragments.size() == 0 && MainConfig.main.getHeader().getHeaderPosition() == HeaderVisibility.INVISIBLE) {
                        removeLastHandler.setVisibleHeader();
                    }
                }
            } else {
                removeLastHandler.onBackPressed();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addView(int i, CustomFragment customFragment) {
        getTabFragments(i).add(customFragment);
    }

    public void addHeader(int i, FrameLayout frameLayout) {
        try {
            FrameLayout tabHeaders = getTabHeaders(i);
            int childCount = tabHeaders.getChildCount();
            FrameLayout frameLayout2 = (FrameLayout) frameLayout.findViewById(R.id.back);
            Header header = MainConfig.main.getHeader();
            if (header.backgroundColorFromService != null && header.backgroundColorFromService.length() > 0) {
                frameLayout.setBackgroundColor(Color.parseColor(header.backgroundColorFromService));
            } else {
                frameLayout.setBackgroundColor(Color.parseColor(header.backgroundColorFromConfig));
            }
            if (childCount == 0) {
                frameLayout2.setVisibility(8);
            } else {
                frameLayout2.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.helpers.NavigationManager.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        NavigationManager.this.handler.onHeaderBackPressed();
                    }
                });
            }
            tabHeaders.addView(frameLayout);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
