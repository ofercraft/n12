package com.channel2.mobile.ui.customViews;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.configs.MainConfig;
import com.channel2.mobile.ui.configs.models.Footer;
import com.channel2.mobile.ui.configs.models.Tab;
import com.channel2.mobile.ui.helpers.Utils;
import com.channel2.mobile.ui.liveStreaming.Live;

/* loaded from: classes2.dex */
public class CustomBottomNavigationView extends LinearLayout implements View.OnClickListener {
    private int chatTabId;
    private Footer footer;
    private String imageColor;
    private boolean isAnimationStoped;
    private boolean isRedDotIcon;
    private String selectedColor;
    private OnNavigationItemSelectedListener selectedListener;
    private View selectedView;
    private String textColor;

    public interface OnNavigationItemSelectedListener {
        void onNavigationItemSelected(int i);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int iIntValue = ((Integer) view.getTag()).intValue();
        this.selectedListener.onNavigationItemSelected(iIntValue);
        selectItem(iIntValue);
    }

    public CustomBottomNavigationView(Context context) {
        super(context);
        this.chatTabId = -1;
    }

    public CustomBottomNavigationView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.chatTabId = -1;
        if (MainConfig.main == null || MainConfig.main.getFooter() == null) {
            return;
        }
        Footer footer = MainConfig.main.getFooter();
        this.footer = footer;
        setBackgroundColor(Color.parseColor(footer.backgroundColor));
        this.selectedColor = this.footer.selectedColor;
        this.imageColor = this.footer.itemColor;
        this.textColor = this.footer.textColor;
        addItems();
    }

    public CustomBottomNavigationView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.chatTabId = -1;
    }

    public void setOnNavigationItemSelectedListener(OnNavigationItemSelectedListener onNavigationItemSelectedListener) {
        this.selectedListener = onNavigationItemSelectedListener;
    }

    public void addItems() {
        for (int i = 0; i < this.footer.tabs.size(); i++) {
            Tab tab = this.footer.tabs.get(i);
            if (tab.view.equals("Chat")) {
                this.chatTabId = i;
            }
            View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.navigation_item_new, (ViewGroup) this, false);
            ImageView imageView = (ImageView) viewInflate.findViewById(R.id.image);
            if (Live.isLive() && tab.view.equals("Programs")) {
                Glide.with(getContext()).asGif().load(Integer.valueOf(R.drawable.live_icon_anim)).into(imageView);
            } else {
                int identifier = getContext().getResources().getIdentifier(tab.view.toLowerCase(), "drawable", getContext().getPackageName());
                if (identifier > 0) {
                    Glide.with(getContext()).load(Integer.valueOf(identifier)).into(imageView);
                } else {
                    Glide.with(getContext()).load(tab.icon).into(imageView);
                }
                imageView.setColorFilter(Color.parseColor(this.imageColor));
            }
            TextView textView = (TextView) viewInflate.findViewById(R.id.title);
            textView.setTextColor(Color.parseColor(this.textColor));
            textView.setText(tab.title);
            viewInflate.setOnClickListener(this);
            viewInflate.setTag(Integer.valueOf(getChildCount()));
            addView(viewInflate);
        }
    }

    public void selectItem(int i) {
        View childAt = getChildAt(i);
        ImageView imageView = (ImageView) childAt.findViewById(R.id.image);
        Tab tab = this.footer.tabs.get(i);
        if (tab != null && tab.view.equals("Programs") && !this.isAnimationStoped && Live.isLive()) {
            this.isAnimationStoped = true;
            imageView.setImageResource(R.drawable.live_icon_not_anim);
        }
        if (tab != null && tab.view.equals("Chat") && this.isRedDotIcon) {
            ((ImageView) childAt.findViewById(R.id.red_dot)).setVisibility(8);
            this.isRedDotIcon = false;
        }
        imageView.setColorFilter(Color.parseColor(this.selectedColor));
        ((TextView) childAt.findViewById(R.id.title)).setTextColor(Color.parseColor(this.selectedColor));
        View view = this.selectedView;
        if (view != null && view != childAt) {
            ((ImageView) view.findViewById(R.id.image)).setColorFilter(Color.parseColor(this.imageColor));
            ((TextView) this.selectedView.findViewById(R.id.title)).setTextColor(Color.parseColor(this.textColor));
        }
        this.selectedView = childAt;
    }

    public String getTabNameById(int i) {
        Tab tab = this.footer.tabs.get(i);
        return tab != null ? tab.view : "";
    }

    public void addRedDot() {
        if (this.chatTabId < 0 || this.isRedDotIcon || !Utils.getBoolFromPreferences(getContext(), getResources().getString(R.string.redDot))) {
            return;
        }
        Tab tab = this.footer.tabs.get(this.chatTabId);
        View childAt = getChildAt(this.chatTabId);
        if (tab == null || childAt == null) {
            return;
        }
        ((ImageView) childAt.findViewById(R.id.red_dot)).setVisibility(0);
        this.isRedDotIcon = true;
    }
}
