package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.constraintlayout.widget.Group;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.channel2.mobile.ui.R;
import com.google.android.material.checkbox.MaterialCheckBox;

/* loaded from: classes2.dex */
public final class FragmentNotificationsBinding implements ViewBinding {
    public final AppCompatRadioButton allRadio;
    public final DividerSettingsThikBinding divider1;
    public final DividerSettingsThinBinding divider2;
    public final DividerSettingsThikBinding divider3;
    public final DividerSettingsThinBinding divider4;
    public final DividerSettingsThikBinding divider5;
    public final DividerSettingsThinBinding divider6;
    public final DividerSettingsThikBinding divider7;
    public final DividerSettingsThikBinding divider9;
    public final AppCompatRadioButton importatndRadio;
    public final ImageView inside;
    public final CheckBox insideBox;
    public final TextView insideTV;
    public final Switch mainPushSwitchButton;
    public final TextView mainPushTV;
    public final TextView mikumPushTV;
    public final Group noficiationGroup;
    public final ImageView outside;
    public final MaterialCheckBox outsideBox;
    public final View outsideDisabled;
    public final TextView outsideTV;
    public final ImageView redDotImage;
    public final TextView redDotImageTV;
    public final Switch redDotSwitchButton;
    private final ScrollView rootView;
    public final ProgressBar spinner;
    public final View spinnerBG;
    public final Switch switchHFC;
    public final Switch switchSound;
    public final RadioGroup tadirutPushRadioGroup;
    public final TextView tadirutPushTV;
    public final TextView txtSound;
    public final TextView txtSubTitleHFC;
    public final TextView txtTitleHFC;

    private FragmentNotificationsBinding(ScrollView scrollView, AppCompatRadioButton appCompatRadioButton, DividerSettingsThikBinding dividerSettingsThikBinding, DividerSettingsThinBinding dividerSettingsThinBinding, DividerSettingsThikBinding dividerSettingsThikBinding2, DividerSettingsThinBinding dividerSettingsThinBinding2, DividerSettingsThikBinding dividerSettingsThikBinding3, DividerSettingsThinBinding dividerSettingsThinBinding3, DividerSettingsThikBinding dividerSettingsThikBinding4, DividerSettingsThikBinding dividerSettingsThikBinding5, AppCompatRadioButton appCompatRadioButton2, ImageView imageView, CheckBox checkBox, TextView textView, Switch r17, TextView textView2, TextView textView3, Group group, ImageView imageView2, MaterialCheckBox materialCheckBox, View view, TextView textView4, ImageView imageView3, TextView textView5, Switch r27, ProgressBar progressBar, View view2, Switch r30, Switch r31, RadioGroup radioGroup, TextView textView6, TextView textView7, TextView textView8, TextView textView9) {
        this.rootView = scrollView;
        this.allRadio = appCompatRadioButton;
        this.divider1 = dividerSettingsThikBinding;
        this.divider2 = dividerSettingsThinBinding;
        this.divider3 = dividerSettingsThikBinding2;
        this.divider4 = dividerSettingsThinBinding2;
        this.divider5 = dividerSettingsThikBinding3;
        this.divider6 = dividerSettingsThinBinding3;
        this.divider7 = dividerSettingsThikBinding4;
        this.divider9 = dividerSettingsThikBinding5;
        this.importatndRadio = appCompatRadioButton2;
        this.inside = imageView;
        this.insideBox = checkBox;
        this.insideTV = textView;
        this.mainPushSwitchButton = r17;
        this.mainPushTV = textView2;
        this.mikumPushTV = textView3;
        this.noficiationGroup = group;
        this.outside = imageView2;
        this.outsideBox = materialCheckBox;
        this.outsideDisabled = view;
        this.outsideTV = textView4;
        this.redDotImage = imageView3;
        this.redDotImageTV = textView5;
        this.redDotSwitchButton = r27;
        this.spinner = progressBar;
        this.spinnerBG = view2;
        this.switchHFC = r30;
        this.switchSound = r31;
        this.tadirutPushRadioGroup = radioGroup;
        this.tadirutPushTV = textView6;
        this.txtSound = textView7;
        this.txtSubTitleHFC = textView8;
        this.txtTitleHFC = textView9;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ScrollView getRoot() {
        return this.rootView;
    }

    public static FragmentNotificationsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentNotificationsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_notifications, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentNotificationsBinding bind(View view) {
        int i = R.id.allRadio;
        AppCompatRadioButton appCompatRadioButton = (AppCompatRadioButton) ViewBindings.findChildViewById(view, R.id.allRadio);
        if (appCompatRadioButton != null) {
            i = R.id.divider1;
            View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.divider1);
            if (viewFindChildViewById != null) {
                DividerSettingsThikBinding dividerSettingsThikBindingBind = DividerSettingsThikBinding.bind(viewFindChildViewById);
                i = R.id.divider2;
                View viewFindChildViewById2 = ViewBindings.findChildViewById(view, R.id.divider2);
                if (viewFindChildViewById2 != null) {
                    DividerSettingsThinBinding dividerSettingsThinBindingBind = DividerSettingsThinBinding.bind(viewFindChildViewById2);
                    i = R.id.divider3;
                    View viewFindChildViewById3 = ViewBindings.findChildViewById(view, R.id.divider3);
                    if (viewFindChildViewById3 != null) {
                        DividerSettingsThikBinding dividerSettingsThikBindingBind2 = DividerSettingsThikBinding.bind(viewFindChildViewById3);
                        i = R.id.divider4;
                        View viewFindChildViewById4 = ViewBindings.findChildViewById(view, R.id.divider4);
                        if (viewFindChildViewById4 != null) {
                            DividerSettingsThinBinding dividerSettingsThinBindingBind2 = DividerSettingsThinBinding.bind(viewFindChildViewById4);
                            i = R.id.divider5;
                            View viewFindChildViewById5 = ViewBindings.findChildViewById(view, R.id.divider5);
                            if (viewFindChildViewById5 != null) {
                                DividerSettingsThikBinding dividerSettingsThikBindingBind3 = DividerSettingsThikBinding.bind(viewFindChildViewById5);
                                i = R.id.divider6;
                                View viewFindChildViewById6 = ViewBindings.findChildViewById(view, R.id.divider6);
                                if (viewFindChildViewById6 != null) {
                                    DividerSettingsThinBinding dividerSettingsThinBindingBind3 = DividerSettingsThinBinding.bind(viewFindChildViewById6);
                                    i = R.id.divider7;
                                    View viewFindChildViewById7 = ViewBindings.findChildViewById(view, R.id.divider7);
                                    if (viewFindChildViewById7 != null) {
                                        DividerSettingsThikBinding dividerSettingsThikBindingBind4 = DividerSettingsThikBinding.bind(viewFindChildViewById7);
                                        i = R.id.divider9;
                                        View viewFindChildViewById8 = ViewBindings.findChildViewById(view, R.id.divider9);
                                        if (viewFindChildViewById8 != null) {
                                            DividerSettingsThikBinding dividerSettingsThikBindingBind5 = DividerSettingsThikBinding.bind(viewFindChildViewById8);
                                            i = R.id.importatndRadio;
                                            AppCompatRadioButton appCompatRadioButton2 = (AppCompatRadioButton) ViewBindings.findChildViewById(view, R.id.importatndRadio);
                                            if (appCompatRadioButton2 != null) {
                                                i = R.id.inside;
                                                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.inside);
                                                if (imageView != null) {
                                                    i = R.id.insideBox;
                                                    CheckBox checkBox = (CheckBox) ViewBindings.findChildViewById(view, R.id.insideBox);
                                                    if (checkBox != null) {
                                                        i = R.id.insideTV;
                                                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.insideTV);
                                                        if (textView != null) {
                                                            i = R.id.mainPushSwitchButton;
                                                            Switch r18 = (Switch) ViewBindings.findChildViewById(view, R.id.mainPushSwitchButton);
                                                            if (r18 != null) {
                                                                i = R.id.mainPushTV;
                                                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.mainPushTV);
                                                                if (textView2 != null) {
                                                                    i = R.id.mikumPushTV;
                                                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.mikumPushTV);
                                                                    if (textView3 != null) {
                                                                        i = R.id.noficiationGroup;
                                                                        Group group = (Group) ViewBindings.findChildViewById(view, R.id.noficiationGroup);
                                                                        if (group != null) {
                                                                            i = R.id.outside;
                                                                            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.outside);
                                                                            if (imageView2 != null) {
                                                                                i = R.id.outsideBox;
                                                                                MaterialCheckBox materialCheckBox = (MaterialCheckBox) ViewBindings.findChildViewById(view, R.id.outsideBox);
                                                                                if (materialCheckBox != null) {
                                                                                    i = R.id.outsideDisabled;
                                                                                    View viewFindChildViewById9 = ViewBindings.findChildViewById(view, R.id.outsideDisabled);
                                                                                    if (viewFindChildViewById9 != null) {
                                                                                        i = R.id.outsideTV;
                                                                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.outsideTV);
                                                                                        if (textView4 != null) {
                                                                                            i = R.id.redDotImage;
                                                                                            ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.redDotImage);
                                                                                            if (imageView3 != null) {
                                                                                                i = R.id.redDotImageTV;
                                                                                                TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.redDotImageTV);
                                                                                                if (textView5 != null) {
                                                                                                    i = R.id.redDotSwitchButton;
                                                                                                    Switch r28 = (Switch) ViewBindings.findChildViewById(view, R.id.redDotSwitchButton);
                                                                                                    if (r28 != null) {
                                                                                                        i = R.id.spinner;
                                                                                                        ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(view, R.id.spinner);
                                                                                                        if (progressBar != null) {
                                                                                                            i = R.id.spinnerBG;
                                                                                                            View viewFindChildViewById10 = ViewBindings.findChildViewById(view, R.id.spinnerBG);
                                                                                                            if (viewFindChildViewById10 != null) {
                                                                                                                i = R.id.switchHFC;
                                                                                                                Switch r31 = (Switch) ViewBindings.findChildViewById(view, R.id.switchHFC);
                                                                                                                if (r31 != null) {
                                                                                                                    i = R.id.switchSound;
                                                                                                                    Switch r32 = (Switch) ViewBindings.findChildViewById(view, R.id.switchSound);
                                                                                                                    if (r32 != null) {
                                                                                                                        i = R.id.tadirutPushRadioGroup;
                                                                                                                        RadioGroup radioGroup = (RadioGroup) ViewBindings.findChildViewById(view, R.id.tadirutPushRadioGroup);
                                                                                                                        if (radioGroup != null) {
                                                                                                                            i = R.id.tadirutPushTV;
                                                                                                                            TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tadirutPushTV);
                                                                                                                            if (textView6 != null) {
                                                                                                                                i = R.id.txtSound;
                                                                                                                                TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.txtSound);
                                                                                                                                if (textView7 != null) {
                                                                                                                                    i = R.id.txtSubTitleHFC;
                                                                                                                                    TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.txtSubTitleHFC);
                                                                                                                                    if (textView8 != null) {
                                                                                                                                        i = R.id.txtTitleHFC;
                                                                                                                                        TextView textView9 = (TextView) ViewBindings.findChildViewById(view, R.id.txtTitleHFC);
                                                                                                                                        if (textView9 != null) {
                                                                                                                                            return new FragmentNotificationsBinding((ScrollView) view, appCompatRadioButton, dividerSettingsThikBindingBind, dividerSettingsThinBindingBind, dividerSettingsThikBindingBind2, dividerSettingsThinBindingBind2, dividerSettingsThikBindingBind3, dividerSettingsThinBindingBind3, dividerSettingsThikBindingBind4, dividerSettingsThikBindingBind5, appCompatRadioButton2, imageView, checkBox, textView, r18, textView2, textView3, group, imageView2, materialCheckBox, viewFindChildViewById9, textView4, imageView3, textView5, r28, progressBar, viewFindChildViewById10, r31, r32, radioGroup, textView6, textView7, textView8, textView9);
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
