package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.customViews.CustomTextView;

/* loaded from: classes2.dex */
public final class FragmentGalleryBinding implements ViewBinding {
    public final CustomTextView caption;
    public final ImageView closeBtn;
    public final LinearLayout footerContainer;
    public final RelativeLayout headerContainer;
    public final CustomTextView pageNum;
    private final FrameLayout rootView;
    public final ViewPager2 viewPager;

    private FragmentGalleryBinding(FrameLayout frameLayout, CustomTextView customTextView, ImageView imageView, LinearLayout linearLayout, RelativeLayout relativeLayout, CustomTextView customTextView2, ViewPager2 viewPager2) {
        this.rootView = frameLayout;
        this.caption = customTextView;
        this.closeBtn = imageView;
        this.footerContainer = linearLayout;
        this.headerContainer = relativeLayout;
        this.pageNum = customTextView2;
        this.viewPager = viewPager2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static FragmentGalleryBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentGalleryBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_gallery, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentGalleryBinding bind(View view) {
        int i = R.id.caption;
        CustomTextView customTextView = (CustomTextView) ViewBindings.findChildViewById(view, R.id.caption);
        if (customTextView != null) {
            i = R.id.closeBtn;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.closeBtn);
            if (imageView != null) {
                i = R.id.footerContainer;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.footerContainer);
                if (linearLayout != null) {
                    i = R.id.headerContainer;
                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.headerContainer);
                    if (relativeLayout != null) {
                        i = R.id.pageNum;
                        CustomTextView customTextView2 = (CustomTextView) ViewBindings.findChildViewById(view, R.id.pageNum);
                        if (customTextView2 != null) {
                            i = R.id.viewPager;
                            ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(view, R.id.viewPager);
                            if (viewPager2 != null) {
                                return new FragmentGalleryBinding((FrameLayout) view, customTextView, imageView, linearLayout, relativeLayout, customTextView2, viewPager2);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
