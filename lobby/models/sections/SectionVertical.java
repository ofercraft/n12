package com.channel2.mobile.ui.lobby.models.sections;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.Group;
import androidx.core.content.ContextCompat;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder;
import com.channel2.mobile.ui.helpers.ChromeCustomTabsApi;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler;
import com.channel2.mobile.ui.lobby.models.items.Item;
import com.channel2.mobile.ui.lobby.models.items.SectionType;
import com.squareup.picasso.Picasso;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public class SectionVertical extends CustomRecyclerViewHolder {
    private View backgroundImageView;
    private ImageView componentIcon;
    private Group container;
    private View itemView;
    private TextView outbrainViewability;
    private ImageView rightIcon;
    private TextView title;

    @Override // com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void onScrollStateIdle() {
    }

    public SectionVertical(View view) {
        super(view);
        this.itemView = view;
        this.title = (TextView) view.findViewById(R.id.title);
        this.container = (Group) view.findViewById(R.id.container);
        this.componentIcon = (ImageView) view.findViewById(R.id.componentIcon);
        this.outbrainViewability = (TextView) view.findViewById(R.id.outbrainViewability);
        this.rightIcon = (ImageView) view.findViewById(R.id.rightIcon);
        this.backgroundImageView = view.findViewById(R.id.backgroundImageView);
    }

    @Override // com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void setViewResources(Item item, LobbyFragmentHandler lobbyFragmentHandler) {
        super.setViewResources(item, lobbyFragmentHandler);
        final LobbySection lobbySection = (LobbySection) item;
        if (lobbySection.getName().length() > 0) {
            this.title.setText(lobbySection.getName());
            setFontSize(this.title, 25.0f);
            this.container.setVisibility(0);
            this.rightIcon.setVisibility(0);
        } else {
            this.container.setVisibility(8);
            this.rightIcon.setVisibility(8);
        }
        if (lobbySection.getComponentIcon().length() > 0) {
            Picasso.get().load(lobbySection.getComponentIcon()).placeholder((Drawable) Objects.requireNonNull(ContextCompat.getDrawable(this.componentIcon.getContext(), R.drawable.placeholder_dound))).into(this.componentIcon);
            this.componentIcon.setAlpha(1.0f);
            this.componentIcon.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.lobby.models.sections.SectionVertical.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    new ChromeCustomTabsApi().openLinkInChromeCustomTabs(SectionVertical.this.componentIcon.getContext(), lobbySection.getIconClickUrl());
                }
            });
        } else {
            this.componentIcon.setAlpha(0.0f);
        }
        switch (AnonymousClass2.$SwitchMap$com$channel2$mobile$ui$lobby$models$items$SectionType[lobbySection.getSectionType().ordinal()]) {
            case 1:
                this.itemView.setVisibility(8);
                break;
            case 2:
                this.itemView.setVisibility(0);
                if (!lobbySection.getHeaderBackground().startsWith("http")) {
                    setGradientBackground(lobbySection);
                }
                this.title.setTextColor(Color.parseColor(lobbySection.getTextColor()));
                this.title.setIncludeFontPadding(true);
                this.title.setGravity(16);
                if (!lobbySection.getComponentLeftIcon().isEmpty()) {
                    Picasso.get().load(lobbySection.getComponentLeftIcon()).placeholder((Drawable) Objects.requireNonNull(ContextCompat.getDrawable(this.componentIcon.getContext(), R.drawable.placeholder_dound))).into(this.componentIcon);
                    this.componentIcon.setAlpha(1.0f);
                }
                if (!lobbySection.getComponentRightIcon().isEmpty()) {
                    Picasso.get().load(lobbySection.getComponentRightIcon()).placeholder((Drawable) Objects.requireNonNull(ContextCompat.getDrawable(this.rightIcon.getContext(), R.drawable.placeholder_dound))).into(this.rightIcon);
                    this.rightIcon.setAlpha(1.0f);
                    this.rightIcon.setVisibility(0);
                    break;
                } else {
                    this.rightIcon.setAlpha(1.0f);
                    this.rightIcon.setVisibility(0);
                    this.rightIcon.setImageResource(R.drawable.section_new_logo);
                    break;
                }
            case 3:
                this.itemView.setVisibility(0);
                this.rightIcon.setVisibility(0);
                this.rightIcon.setAlpha(1.0f);
                this.rightIcon.setImageResource(R.drawable.section_new_logo);
                this.title.setTextColor(-1);
                this.title.setIncludeFontPadding(true);
                this.title.setGravity(16);
                this.backgroundImageView.setBackgroundResource(R.drawable.n12_bg_gradient_red);
                this.componentIcon.setAlpha(0.0f);
                break;
            case 4:
                this.itemView.setVisibility(0);
                this.rightIcon.setVisibility(0);
                this.rightIcon.setAlpha(1.0f);
                this.rightIcon.setImageResource(R.drawable.section_new_logo);
                Picasso.get().load(lobbySection.getComponentIcon()).placeholder((Drawable) Objects.requireNonNull(ContextCompat.getDrawable(this.componentIcon.getContext(), R.drawable.placeholder_dound))).into(this.componentIcon);
                this.componentIcon.setAlpha(1.0f);
                this.title.setTextColor(-1);
                this.title.setIncludeFontPadding(true);
                this.title.setGravity(16);
                this.backgroundImageView.setBackgroundResource(R.drawable.n12_bg_gradient_red);
                break;
            case 5:
                this.itemView.setVisibility(0);
                this.componentIcon.setAlpha(0.0f);
                this.rightIcon.setVisibility(0);
                Picasso.get().load(lobbySection.getComponentIcon()).placeholder((Drawable) Objects.requireNonNull(ContextCompat.getDrawable(this.rightIcon.getContext(), R.drawable.placeholder_dound))).into(this.rightIcon);
                this.title.setTextColor(-1);
                this.title.setIncludeFontPadding(true);
                this.title.setGravity(16);
                this.backgroundImageView.setBackgroundResource(R.drawable.n12_bg_gradient_red);
                break;
            case 6:
                this.itemView.setVisibility(0);
                this.rightIcon.setVisibility(8);
                this.backgroundImageView.setBackgroundColor(Color.parseColor("#FFFFFF"));
                this.title.setTextColor(Color.parseColor("#AE0000"));
                this.title.setIncludeFontPadding(false);
                this.title.setGravity(80);
                this.componentIcon.setAlpha(0.0f);
                break;
            case 7:
                this.itemView.setVisibility(0);
                this.backgroundImageView.setBackgroundColor(Color.parseColor("#FFFFFF"));
                this.title.setTextColor(Color.parseColor("#AE0000"));
                this.title.setIncludeFontPadding(false);
                this.title.setGravity(80);
                this.rightIcon.setVisibility(8);
                Picasso.get().load(lobbySection.getComponentIcon()).placeholder((Drawable) Objects.requireNonNull(ContextCompat.getDrawable(this.componentIcon.getContext(), R.drawable.placeholder_dound))).into(this.componentIcon);
                this.componentIcon.setAlpha(1.0f);
                break;
            case 8:
                this.itemView.setVisibility(0);
                this.componentIcon.setAlpha(0.0f);
                this.rightIcon.setVisibility(0);
                this.rightIcon.setAlpha(1.0f);
                this.backgroundImageView.setBackgroundColor(Color.parseColor("#FFFFFF"));
                this.title.setTextColor(Color.parseColor("#AE0000"));
                this.title.setIncludeFontPadding(false);
                this.title.setGravity(80);
                Picasso.get().load(lobbySection.getComponentIcon()).placeholder((Drawable) Objects.requireNonNull(ContextCompat.getDrawable(this.rightIcon.getContext(), R.drawable.placeholder_dound))).into(this.rightIcon);
                break;
        }
    }

    /* renamed from: com.channel2.mobile.ui.lobby.models.sections.SectionVertical$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$channel2$mobile$ui$lobby$models$items$SectionType;

        static {
            int[] iArr = new int[SectionType.values().length];
            $SwitchMap$com$channel2$mobile$ui$lobby$models$items$SectionType = iArr;
            try {
                iArr[SectionType.none.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$channel2$mobile$ui$lobby$models$items$SectionType[SectionType.dynamic.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$channel2$mobile$ui$lobby$models$items$SectionType[SectionType.red.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$channel2$mobile$ui$lobby$models$items$SectionType[SectionType.redwithlefticon.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$channel2$mobile$ui$lobby$models$items$SectionType[SectionType.redwithrighticon.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$channel2$mobile$ui$lobby$models$items$SectionType[SectionType.white.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$channel2$mobile$ui$lobby$models$items$SectionType[SectionType.whitewithleftticon.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$channel2$mobile$ui$lobby$models$items$SectionType[SectionType.whitewithrighticon.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    private void setGradientBackground(LobbySection lobbySection) {
        String[] strArrSplit = lobbySection.getHeaderBackground().split(",");
        if (strArrSplit.length == 1) {
            this.backgroundImageView.setBackgroundColor(Color.parseColor(lobbySection.getHeaderBackground()));
            return;
        }
        int[] iArr = new int[strArrSplit.length];
        for (int i = 0; i < strArrSplit.length; i++) {
            iArr[i] = Color.parseColor(strArrSplit[i].replace(StringUtils.SPACE, ""));
        }
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.BL_TR, iArr);
        gradientDrawable.setCornerRadius(0.0f);
        this.backgroundImageView.setBackground(gradientDrawable);
    }
}
