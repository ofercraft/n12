package com.channel2.mobile.ui.programs.controllers;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.customViews.CustomTextView;
import com.channel2.mobile.ui.helpers.CircleTransform;
import com.channel2.mobile.ui.helpers.Utils;
import com.channel2.mobile.ui.programs.models.ProgramsComponent;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Objects;

/* loaded from: classes2.dex */
public class HeaderPagerAdapter extends ViewPagerAdapter {
    private ArrayList<ProgramsComponent> items;

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return 1000;
    }

    public HeaderPagerAdapter(ArrayList<ProgramsComponent> arrayList) {
        this.items = arrayList;
    }

    @Override // com.channel2.mobile.ui.programs.controllers.ViewPagerAdapter
    public View getView(int i, ViewPager viewPager) {
        FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(viewPager.getContext()).inflate(R.layout.programs_header_view, (ViewGroup) null);
        int size = i % this.items.size();
        this.items.get(size).setHeaderView(frameLayout);
        ImageView imageView = (ImageView) frameLayout.findViewById(R.id.image);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imageView.getLayoutParams();
        layoutParams.height = Utils.convertDipToPixels(imageView.getContext(), 90.0f);
        layoutParams.width = Utils.convertDipToPixels(imageView.getContext(), 90.0f);
        imageView.requestLayout();
        if (this.items.get(size).getImage().length() > 0) {
            Picasso.get().load(this.items.get(size).getImage()).placeholder((Drawable) Objects.requireNonNull(ContextCompat.getDrawable(imageView.getContext(), R.drawable.placeholder_dound))).transform(new CircleTransform()).into(imageView);
        }
        CustomTextView customTextView = (CustomTextView) frameLayout.findViewById(R.id.title);
        String name = this.items.get(size).getName();
        Objects.requireNonNull(customTextView);
        customTextView.setHebText(name, "fonts/YonitBold_v2.ttf");
        customTextView.setTextSize(2, Utils.getFloatFromPreferences(customTextView.getContext(), "zoom").floatValue() * 16.0f);
        return frameLayout;
    }
}
