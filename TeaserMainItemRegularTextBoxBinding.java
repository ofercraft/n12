package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.channel2.mobile.ui.R;

/* loaded from: classes2.dex */
public final class TeaserMainItemRegularTextBoxBinding implements ViewBinding {
    public final TextView author;
    public final TextView date;
    public final DividerBottomRegular10dpBinding dividerBottomRegular;
    private final ConstraintLayout rootView;
    public final TextView subtitle;
    public final TextView textDevider;
    public final TextView title;

    private TeaserMainItemRegularTextBoxBinding(ConstraintLayout constraintLayout, TextView textView, TextView textView2, DividerBottomRegular10dpBinding dividerBottomRegular10dpBinding, TextView textView3, TextView textView4, TextView textView5) {
        this.rootView = constraintLayout;
        this.author = textView;
        this.date = textView2;
        this.dividerBottomRegular = dividerBottomRegular10dpBinding;
        this.subtitle = textView3;
        this.textDevider = textView4;
        this.title = textView5;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static TeaserMainItemRegularTextBoxBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static TeaserMainItemRegularTextBoxBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.teaser_main_item_regular_text_box, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static TeaserMainItemRegularTextBoxBinding bind(View view) {
        int i = R.id.author;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.author);
        if (textView != null) {
            i = R.id.date;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.date);
            if (textView2 != null) {
                i = R.id.divider_bottom_regular;
                View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.divider_bottom_regular);
                if (viewFindChildViewById != null) {
                    DividerBottomRegular10dpBinding dividerBottomRegular10dpBindingBind = DividerBottomRegular10dpBinding.bind(viewFindChildViewById);
                    i = R.id.subtitle;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.subtitle);
                    if (textView3 != null) {
                        i = R.id.text_devider;
                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.text_devider);
                        if (textView4 != null) {
                            i = R.id.title;
                            TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.title);
                            if (textView5 != null) {
                                return new TeaserMainItemRegularTextBoxBinding((ConstraintLayout) view, textView, textView2, dividerBottomRegular10dpBindingBind, textView3, textView4, textView5);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
