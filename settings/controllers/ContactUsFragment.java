package com.channel2.mobile.ui.settings.controllers;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.channel2.mobile.ui.MainActivity;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.configs.MainConfig;
import com.channel2.mobile.ui.customViews.CustomFragment;
import com.channel2.mobile.ui.helpers.Utils;
import com.channel2.mobile.ui.network.ApiService;
import com.channel2.mobile.ui.settings.models.ContactMessage;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.util.HashMap;

/* loaded from: classes2.dex */
public class ContactUsFragment extends CustomFragment implements View.OnFocusChangeListener, View.OnClickListener {
    protected TextInputEditText email;
    protected TextInputLayout emailLayout;
    private String emailText;
    private TextView errorText;
    private TextWatcher generalTextWatcher = new TextWatcher() { // from class: com.channel2.mobile.ui.settings.controllers.ContactUsFragment.2
        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (ContactUsFragment.this.message.getText().hashCode() == charSequence.hashCode()) {
                if (charSequence.length() > 0) {
                    ContactUsFragment.this.messageLayout.setErrorEnabled(false);
                }
            } else if (ContactUsFragment.this.name.getText().hashCode() == charSequence.hashCode()) {
                if (charSequence.length() > 0) {
                    ContactUsFragment.this.nameLayout.setErrorEnabled(false);
                }
            } else if (ContactUsFragment.this.email.getText().hashCode() == charSequence.hashCode() && ContactUsFragment.this.isEmailValid(charSequence)) {
                ContactUsFragment.this.emailLayout.setErrorEnabled(false);
            }
        }
    };
    private int id;
    private MainActivity mainActivity;
    protected TextInputEditText message;
    protected TextInputLayout messageLayout;
    private String messageText;
    protected TextInputEditText name;
    protected TextInputLayout nameLayout;
    private String nameText;
    protected Button sendBtn;
    private String url;
    private View view;

    public static ContactUsFragment newInstance(int i, String str) {
        ContactUsFragment contactUsFragment = new ContactUsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", i);
        bundle.putString("url", str);
        contactUsFragment.setArguments(bundle);
        return contactUsFragment;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.id = getArguments().getInt("id");
            this.url = getArguments().getString("url");
            setTabId(this.id);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        init();
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) context;
            this.mainActivity = mainActivity;
            if (mainActivity.adContainer != null) {
                this.mainActivity.adContainer.setVisibility(8);
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.view == null) {
            this.view = layoutInflater.inflate(R.layout.app_settings_contact_us_fragment, viewGroup, false);
        }
        this.mainActivity.getWindow().setSoftInputMode(3);
        initViews();
        return this.view;
    }

    private void initViews() {
        this.errorText = (TextView) this.view.findViewById(R.id.errorText);
        this.messageLayout = (TextInputLayout) this.view.findViewById(R.id.message_textField_layout);
        this.nameLayout = (TextInputLayout) this.view.findViewById(R.id.name_textField_layout);
        this.emailLayout = (TextInputLayout) this.view.findViewById(R.id.email_textField_layout);
        TextInputEditText textInputEditText = (TextInputEditText) this.view.findViewById(R.id.message_textField);
        this.message = textInputEditText;
        textInputEditText.setOnFocusChangeListener(this);
        this.message.addTextChangedListener(this.generalTextWatcher);
        TextInputEditText textInputEditText2 = (TextInputEditText) this.view.findViewById(R.id.name_textField);
        this.name = textInputEditText2;
        textInputEditText2.setOnFocusChangeListener(this);
        this.name.addTextChangedListener(this.generalTextWatcher);
        TextInputEditText textInputEditText3 = (TextInputEditText) this.view.findViewById(R.id.email_textField);
        this.email = textInputEditText3;
        textInputEditText3.setOnFocusChangeListener(this);
        this.email.addTextChangedListener(this.generalTextWatcher);
        Button button = (Button) this.view.findViewById(R.id.send_button);
        this.sendBtn = button;
        button.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.view == null || !isValidForm()) {
            return;
        }
        onPostForm();
    }

    private boolean isValidForm() {
        boolean z;
        String string = this.message.getText().toString();
        this.messageText = string;
        if (string.length() > 0) {
            this.messageLayout.setErrorEnabled(false);
            z = true;
        } else {
            this.messageLayout.setErrorEnabled(true);
            this.messageLayout.setError(getResources().getString(R.string.describe_the_problem));
            z = false;
        }
        String string2 = this.name.getText().toString();
        this.nameText = string2;
        if (string2.length() > 0) {
            this.nameLayout.setErrorEnabled(false);
        } else {
            this.nameLayout.setErrorEnabled(true);
            this.nameLayout.setError(getResources().getString(R.string.enter_your_name));
            z = false;
        }
        String string3 = this.email.getText().toString();
        this.emailText = string3;
        if (isEmailValid(string3)) {
            this.emailLayout.setErrorEnabled(false);
            return z;
        }
        this.emailLayout.setErrorEnabled(true);
        this.emailLayout.setError(getResources().getString(R.string.enter_correct_email_address));
        return false;
    }

    private void onPostForm() {
        String currentSource = MainConfig.main.getCurrentSource("contactUsApi");
        ContactMessage contactMessage = new ContactMessage(this.messageText, this.nameText, this.emailText);
        HashMap map = new HashMap();
        map.put("senderNameCoded", contactMessage.getName());
        map.put("informationCoded", contactMessage.getMessage());
        map.put("senderEmail", contactMessage.getEmail());
        map.put(NotificationCompat.CATEGORY_SERVICE, "news12");
        map.put("type", AppEventsConstants.EVENT_NAME_CONTACT);
        map.put("deviceInfo", Utils.buildMessageFromDeviceData(this.mainActivity).toString());
        map.put("attachment", "");
        ApiService.post(currentSource, this.mainActivity, map, new ApiService.AsyncHTTPStringResponseHandler() { // from class: com.channel2.mobile.ui.settings.controllers.ContactUsFragment.1
            @Override // com.channel2.mobile.ui.network.ApiService.AsyncHTTPStringResponseHandler
            public void onSuccess(String str) {
                ContactUsFragment.this.sendBtn.setText(ContactUsFragment.this.getResources().getString(R.string.send));
                Log.i("VOLLEY", str);
                SummaryFragment summaryFragmentNewInstance = SummaryFragment.newInstance(ContactUsFragment.this.id, ContactUsFragment.this.url);
                ContactUsFragment.this.mainActivity.navigationManager.addView(ContactUsFragment.this.getTabId(), summaryFragmentNewInstance);
                ContactUsFragment.this.errorText.setVisibility(4);
                if (summaryFragmentNewInstance != null) {
                    ContactUsFragment.this.mainActivity.replaceFragment(R.id.settings_fragments_container, summaryFragmentNewInstance, String.valueOf(ContactUsFragment.this.getTabId()));
                }
            }

            @Override // com.channel2.mobile.ui.network.ApiService.AsyncHTTPStringResponseHandler
            public void onFailure(String str, int i) {
                Log.e("VOLLEY", "error: " + str + " errorCode: " + i);
                ContactUsFragment.this.errorText.setVisibility(0);
                ContactUsFragment.this.sendBtn.setText(ContactUsFragment.this.getResources().getString(R.string.try_again));
            }
        });
    }

    private void setHeader() {
        FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(this.view.getContext()).inflate(R.layout.header_lobby, (ViewGroup) null, false);
        ConstraintLayout constraintLayout = (ConstraintLayout) frameLayout.findViewById(R.id.logoContainer);
        TextView textView = (TextView) frameLayout.findViewById(R.id.channelTitle);
        ImageView imageView = (ImageView) frameLayout.findViewById(R.id.appLogo);
        imageView.setImageResource(R.drawable.app_logo_lines);
        imageView.setVisibility(0);
        textView.setVisibility(0);
        textView.setText(" צור קשר");
        constraintLayout.setVisibility(0);
        this.mainActivity.navigationManager.addHeader(getTabId(), frameLayout);
    }

    @Override // com.channel2.mobile.ui.customViews.CustomFragment
    public void fragmentOnResume(Activity activity) {
        super.fragmentOnResume(activity);
        try {
            if (this.mainActivity == null) {
                this.mainActivity = (MainActivity) getActivity();
            }
            MainActivity mainActivity = this.mainActivity;
            if (mainActivity != null) {
                mainActivity.exitFullScreen(this);
                if (this.mainActivity.adContainer != null) {
                    this.mainActivity.adContainer.setVisibility(8);
                }
                if (this.mainActivity.bottomNavigationView != null) {
                    this.mainActivity.bottomNavigationView.selectItem(getTabId());
                }
                this.mainActivity.setRequestedOrientation(1);
            }
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {
        setHeader();
    }

    boolean isEmailValid(CharSequence charSequence) {
        return Patterns.EMAIL_ADDRESS.matcher(charSequence).matches();
    }

    public void hideKeyboard(View view) {
        ((InputMethodManager) this.mainActivity.getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override // android.view.View.OnFocusChangeListener
    public void onFocusChange(View view, boolean z) {
        if (z) {
            return;
        }
        hideKeyboard(view);
    }
}
