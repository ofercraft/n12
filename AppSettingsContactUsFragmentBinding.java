package com.channel2.mobile.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.channel2.mobile.ui.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

/* loaded from: classes2.dex */
public final class AppSettingsContactUsFragmentBinding implements ViewBinding {
    public final TextView bodyText;
    public final TextView boldTopTitle;
    public final TextInputEditText emailTextField;
    public final TextInputLayout emailTextFieldLayout;
    public final TextView emailTitle;
    public final TextView errorText;
    public final TextView leaveCredentialsTitle;
    public final TextInputEditText messageTextField;
    public final TextInputLayout messageTextFieldLayout;
    public final TextInputEditText nameTextField;
    public final TextInputLayout nameTextFieldLayout;
    public final TextView nameTitle;
    private final ConstraintLayout rootView;
    public final MaterialButton sendButton;
    public final TextView tellUsTitle;

    private AppSettingsContactUsFragmentBinding(ConstraintLayout constraintLayout, TextView textView, TextView textView2, TextInputEditText textInputEditText, TextInputLayout textInputLayout, TextView textView3, TextView textView4, TextView textView5, TextInputEditText textInputEditText2, TextInputLayout textInputLayout2, TextInputEditText textInputEditText3, TextInputLayout textInputLayout3, TextView textView6, MaterialButton materialButton, TextView textView7) {
        this.rootView = constraintLayout;
        this.bodyText = textView;
        this.boldTopTitle = textView2;
        this.emailTextField = textInputEditText;
        this.emailTextFieldLayout = textInputLayout;
        this.emailTitle = textView3;
        this.errorText = textView4;
        this.leaveCredentialsTitle = textView5;
        this.messageTextField = textInputEditText2;
        this.messageTextFieldLayout = textInputLayout2;
        this.nameTextField = textInputEditText3;
        this.nameTextFieldLayout = textInputLayout3;
        this.nameTitle = textView6;
        this.sendButton = materialButton;
        this.tellUsTitle = textView7;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static AppSettingsContactUsFragmentBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AppSettingsContactUsFragmentBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.app_settings_contact_us_fragment, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static AppSettingsContactUsFragmentBinding bind(View view) {
        int i = R.id.body_text;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.body_text);
        if (textView != null) {
            i = R.id.bold_top_title;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.bold_top_title);
            if (textView2 != null) {
                i = R.id.email_textField;
                TextInputEditText textInputEditText = (TextInputEditText) ViewBindings.findChildViewById(view, R.id.email_textField);
                if (textInputEditText != null) {
                    i = R.id.email_textField_layout;
                    TextInputLayout textInputLayout = (TextInputLayout) ViewBindings.findChildViewById(view, R.id.email_textField_layout);
                    if (textInputLayout != null) {
                        i = R.id.email_title;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.email_title);
                        if (textView3 != null) {
                            i = R.id.errorText;
                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.errorText);
                            if (textView4 != null) {
                                i = R.id.leave_credentials_title;
                                TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.leave_credentials_title);
                                if (textView5 != null) {
                                    i = R.id.message_textField;
                                    TextInputEditText textInputEditText2 = (TextInputEditText) ViewBindings.findChildViewById(view, R.id.message_textField);
                                    if (textInputEditText2 != null) {
                                        i = R.id.message_textField_layout;
                                        TextInputLayout textInputLayout2 = (TextInputLayout) ViewBindings.findChildViewById(view, R.id.message_textField_layout);
                                        if (textInputLayout2 != null) {
                                            i = R.id.name_textField;
                                            TextInputEditText textInputEditText3 = (TextInputEditText) ViewBindings.findChildViewById(view, R.id.name_textField);
                                            if (textInputEditText3 != null) {
                                                i = R.id.name_textField_layout;
                                                TextInputLayout textInputLayout3 = (TextInputLayout) ViewBindings.findChildViewById(view, R.id.name_textField_layout);
                                                if (textInputLayout3 != null) {
                                                    i = R.id.name_title;
                                                    TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.name_title);
                                                    if (textView6 != null) {
                                                        i = R.id.send_button;
                                                        MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, R.id.send_button);
                                                        if (materialButton != null) {
                                                            i = R.id.tell_us_title;
                                                            TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tell_us_title);
                                                            if (textView7 != null) {
                                                                return new AppSettingsContactUsFragmentBinding((ConstraintLayout) view, textView, textView2, textInputEditText, textInputLayout, textView3, textView4, textView5, textInputEditText2, textInputLayout2, textInputEditText3, textInputLayout3, textView6, materialButton, textView7);
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
