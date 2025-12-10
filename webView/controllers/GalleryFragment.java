package com.channel2.mobile.ui.webView.controllers;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;
import com.channel2.mobile.ui.Chats.controllers.Gallery.ChatGalleryHandler;
import com.channel2.mobile.ui.MainActivity;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.customViews.CustomFragment;
import com.channel2.mobile.ui.customViews.CustomTextView;
import com.channel2.mobile.ui.webView.models.GalleryItem;
import com.google.firebase.sessions.settings.RemoteSettings;
import java.util.ArrayList;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class GalleryFragment extends CustomFragment implements ViewPager.OnPageChangeListener {
    private CustomTextView caption;
    private GalleryAdapter galleryAdapter;
    private int id;
    private ArrayList<GalleryItem> imageList;
    private String imageListString;
    private int mSelectedImage = -1;
    private MainActivity mainActivity;
    private CustomTextView pageNum;
    private View view;
    private ViewPager2 viewPager;

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
    }

    public static GalleryFragment newInstance(int i, String str) {
        GalleryFragment galleryFragment = new GalleryFragment();
        Bundle bundle = new Bundle();
        bundle.putString("imageListString", str);
        bundle.putInt("id", i);
        galleryFragment.setArguments(bundle);
        return galleryFragment;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.imageListString = getArguments().getString("imageListString");
            int i = getArguments().getInt("id");
            this.id = i;
            setTabId(i);
        }
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
            this.view = layoutInflater.inflate(R.layout.fragment_gallery, viewGroup, false);
            init();
        }
        return this.view;
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        GalleryItem galleryItem = this.imageList.get(i);
        CustomTextView customTextView = this.caption;
        String str = galleryItem.getCaption() + galleryItem.getPhotoCredit();
        Objects.requireNonNull(this.caption);
        customTextView.setHebText(str, "fonts/YonitMedium_v2.ttf");
        CustomTextView customTextView2 = this.pageNum;
        String str2 = String.valueOf(galleryItem.getIndex() + 1) + RemoteSettings.FORWARD_SLASH_STRING + String.valueOf(this.imageList.size());
        Objects.requireNonNull(this.caption);
        customTextView2.setHebText(str2, "fonts/YonitMedium_v2.ttf");
        this.mSelectedImage = i;
    }

    private void init() {
        setHeader();
        try {
            parseGalleryItems(new JSONObject(this.imageListString));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        fragmentOnResume(this.mainActivity);
    }

    private void parseGalleryItems(JSONObject jSONObject) throws JSONException {
        try {
            this.imageList = new ArrayList<>();
            if (!jSONObject.isNull("images")) {
                JSONArray jSONArray = jSONObject.getJSONArray("images");
                for (int i = 0; i < jSONArray.length(); i++) {
                    this.imageList.add(new GalleryItem(jSONArray.optJSONObject(i), getContext(), i));
                }
                if (!jSONObject.isNull("selected_index") && jSONObject.getString("selected_index").length() > 0 && this.mSelectedImage == -1) {
                    this.mSelectedImage = Integer.parseInt(jSONObject.getString("selected_index"));
                }
            }
            if (this.imageList.size() > 0) {
                initPageView(this.mSelectedImage >= this.imageList.size() ? 1 : this.mSelectedImage);
            }
        } catch (JSONException unused) {
        }
    }

    private void initPageView(int i) {
        try {
            ImageView imageView = (ImageView) this.view.findViewById(R.id.closeBtn);
            this.caption = (CustomTextView) this.view.findViewById(R.id.caption);
            this.pageNum = (CustomTextView) this.view.findViewById(R.id.pageNum);
            GalleryItem galleryItem = this.imageList.get(i);
            CustomTextView customTextView = this.caption;
            String str = galleryItem.getCaption() + galleryItem.getPhotoCredit();
            Objects.requireNonNull(this.caption);
            customTextView.setHebText(str, "fonts/YonitMedium_v2.ttf");
            CustomTextView customTextView2 = this.pageNum;
            String str2 = String.valueOf(galleryItem.getIndex() + 1) + RemoteSettings.FORWARD_SLASH_STRING + String.valueOf(this.imageList.size());
            Objects.requireNonNull(this.caption);
            customTextView2.setHebText(str2, "fonts/YonitMedium_v2.ttf");
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.webView.controllers.GalleryFragment.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    GalleryFragment.this.mainActivity.backPressed();
                }
            });
            ViewPager2 viewPager2 = (ViewPager2) this.view.findViewById(R.id.viewPager);
            this.viewPager = viewPager2;
            viewPager2.setOrientation(0);
            GalleryAdapter galleryAdapter = new GalleryAdapter(this.imageList, getActivity(), new ChatGalleryHandler() { // from class: com.channel2.mobile.ui.webView.controllers.GalleryFragment.2
                @Override // com.channel2.mobile.ui.Chats.controllers.Gallery.ChatGalleryHandler
                public void onDetach(View view, int i2) {
                }

                @Override // com.channel2.mobile.ui.Chats.controllers.Gallery.ChatGalleryHandler
                public void onItemClick(int i2) {
                }

                @Override // com.channel2.mobile.ui.Chats.controllers.Gallery.ChatGalleryHandler
                public void onItemClick(View view) {
                }

                @Override // com.channel2.mobile.ui.Chats.controllers.Gallery.ChatGalleryHandler
                public void onPauseVideo(FrameLayout frameLayout) {
                }

                @Override // com.channel2.mobile.ui.Chats.controllers.Gallery.ChatGalleryHandler
                public void onPlayVideo(FrameLayout frameLayout, int i2) {
                }

                @Override // com.channel2.mobile.ui.Chats.controllers.Gallery.ChatGalleryHandler
                public void onAttach(View view, int i2) {
                    GalleryItem galleryItem2 = (GalleryItem) GalleryFragment.this.imageList.get(i2);
                    CustomTextView customTextView3 = GalleryFragment.this.caption;
                    String str3 = galleryItem2.getCaption() + galleryItem2.getPhotoCredit();
                    Objects.requireNonNull(GalleryFragment.this.caption);
                    customTextView3.setHebText(str3, "fonts/YonitMedium_v2.ttf");
                    CustomTextView customTextView4 = GalleryFragment.this.pageNum;
                    String str4 = String.valueOf(galleryItem2.getIndex() + 1) + RemoteSettings.FORWARD_SLASH_STRING + String.valueOf(GalleryFragment.this.imageList.size());
                    Objects.requireNonNull(GalleryFragment.this.caption);
                    customTextView4.setHebText(str4, "fonts/YonitMedium_v2.ttf");
                    GalleryFragment.this.mSelectedImage = i2;
                }
            });
            this.galleryAdapter = galleryAdapter;
            this.viewPager.setAdapter(galleryAdapter);
            this.viewPager.setCurrentItem(i, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setHeader() {
        this.mainActivity.navigationManager.addHeader(getTabId(), (FrameLayout) LayoutInflater.from(getContext()).inflate(R.layout.header_lobby, (ViewGroup) null, false));
    }

    @Override // com.channel2.mobile.ui.customViews.CustomFragment
    public void fragmentOnResume(Activity activity) {
        super.fragmentOnResume(activity);
        try {
            if (this.mainActivity == null) {
                this.mainActivity = (MainActivity) activity;
            }
            if (this.mainActivity == null) {
                this.mainActivity = (MainActivity) getActivity();
            }
            MainActivity mainActivity = this.mainActivity;
            if (mainActivity != null) {
                mainActivity.toolbarParams.setScrollFlags(0);
                this.mainActivity.enterFullScreen(this);
                if (this.mainActivity.adContainer != null) {
                    this.mainActivity.adContainer.setVisibility(8);
                }
                this.mainActivity.setRequestedOrientation(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
