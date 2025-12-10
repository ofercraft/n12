package com.channel2.mobile.ui.lobby.models.teasers;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler;
import com.channel2.mobile.ui.lobby.models.items.Item;
import com.channel2.mobile.ui.lobby.models.items.ItemType;
import com.channel2.mobile.ui.lobby.models.items.LinkItem;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class TeaserMainItemSpecial extends CustomRecyclerViewHolder {
    protected TextView author;
    protected TextView authorAndDate;
    protected TextView caption;
    protected TextView date;
    public ImageView image;
    protected View lineBeforeLinks;
    protected View line_separator;
    protected TextView linkA;
    protected ImageView linkAIcon;
    protected TextView linkB;
    protected ImageView linkBIcon;
    protected TextView subtitle;
    protected ViewGroup teaser_main_item_special_text_box;
    protected TextView title;

    @Override // com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void onScrollStateIdle() {
    }

    public TeaserMainItemSpecial(View view) {
        super(view);
        this.title = (TextView) view.findViewById(R.id.title);
        this.subtitle = (TextView) view.findViewById(R.id.subtitle);
        this.caption = (TextView) view.findViewById(R.id.caption);
        this.image = (ImageView) view.findViewById(R.id.image);
        this.linkA = (TextView) view.findViewById(R.id.linkA);
        this.linkB = (TextView) view.findViewById(R.id.linkB);
        this.linkAIcon = (ImageView) view.findViewById(R.id.linkAIcon);
        this.linkBIcon = (ImageView) view.findViewById(R.id.linkBIcon);
        this.authorAndDate = (TextView) view.findViewById(R.id.author_and_date);
        this.lineBeforeLinks = view.findViewById(R.id.line_before_links);
        this.teaser_main_item_special_text_box = (ViewGroup) view.findViewById(R.id.teaser_main_item_special_text_box);
        this.line_separator = view.findViewById(R.id.line_separator);
    }

    @Override // com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void setViewResources(Item item, final LobbyFragmentHandler lobbyFragmentHandler) {
        String str;
        super.setViewResources(item, lobbyFragmentHandler);
        LobbyTeaser lobbyTeaser = (LobbyTeaser) item;
        if (lobbyTeaser.getNTheme() != null) {
            Glide.with(this.itemView).load(lobbyTeaser.getNTheme().getGradientBackground()).dontAnimate().into((RequestBuilder) new CustomTarget<Drawable>() { // from class: com.channel2.mobile.ui.lobby.models.teasers.TeaserMainItemSpecial.1
                @Override // com.bumptech.glide.request.target.Target
                public void onLoadCleared(Drawable drawable) {
                }

                @Override // com.bumptech.glide.request.target.Target
                public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                    onResourceReady((Drawable) obj, (Transition<? super Drawable>) transition);
                }

                public void onResourceReady(Drawable drawable, Transition<? super Drawable> transition) {
                    TeaserMainItemSpecial.this.teaser_main_item_special_text_box.setBackground(drawable);
                }
            });
            Glide.with(this.linkAIcon).load(lobbyTeaser.getNTheme().getLinkedImage()).into(this.linkAIcon);
            Glide.with(this.linkBIcon).load(lobbyTeaser.getNTheme().getLinkedImage()).into(this.linkBIcon);
            this.line_separator.setBackgroundColor(Color.parseColor(lobbyTeaser.getNTheme().getLineColor()));
            TextView textView = this.caption;
            if (textView != null) {
                textView.setBackgroundColor(Color.parseColor(lobbyTeaser.getNTheme().getCaptionColor()));
            }
        } else {
            ViewGroup viewGroup = this.teaser_main_item_special_text_box;
            viewGroup.setBackground(ContextCompat.getDrawable(viewGroup.getContext(), R.drawable.n12_bg_gradient_blue_reverse));
        }
        Glide.with(this.image).load(lobbyTeaser.getImage()).into(this.image);
        this.title.setText(lobbyTeaser.getTitle());
        this.subtitle.setText(lobbyTeaser.getSubTitle());
        if (this.caption != null) {
            if (lobbyTeaser.getCaption() != null && lobbyTeaser.getCaption().length() > 1) {
                this.caption.setText(lobbyTeaser.getCaption());
            } else {
                this.caption.setVisibility(8);
            }
        }
        if (lobbyTeaser.getDate().length() == 0 || lobbyTeaser.getAuthor().length() == 0) {
            str = "";
        } else {
            str = " | ";
            if (lobbyTeaser.getAuthor().length() > 28 && lobbyTeaser.getDate().length() > 1) {
                str = " | " + System.getProperty("line.separator");
            }
        }
        if (lobbyTeaser.getAuthor().length() > 0 && lobbyTeaser.getAuthor().substring(0, 1).matches("[a-zA-Z]")) {
            this.authorAndDate.setText(lobbyTeaser.getDate() + str + lobbyTeaser.getAuthor());
            this.authorAndDate.setTextDirection(3);
            this.authorAndDate.setGravity(5);
        } else {
            this.authorAndDate.setText(lobbyTeaser.getAuthor() + str + lobbyTeaser.getDate());
        }
        if (lobbyTeaser.getDate().length() == 0 && lobbyTeaser.getAuthor().length() == 0) {
            this.authorAndDate.setVisibility(8);
        }
        if (lobbyTeaser.getLinks() != null && lobbyTeaser.getLinks().length() > 0) {
            this.lineBeforeLinks.setVisibility(0);
            this.linkA.setVisibility(0);
            this.linkAIcon.setVisibility(0);
            final JSONObject jSONObjectOptJSONObject = lobbyTeaser.getLinks().optJSONObject(0);
            this.linkA.setText(jSONObjectOptJSONObject.optString("title"));
            this.linkA.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.lobby.models.teasers.TeaserMainItemSpecial.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) throws JSONException {
                    String strOptString = jSONObjectOptJSONObject.optString("url");
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("url", strOptString);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    LinkItem linkItem = new LinkItem(jSONObject);
                    linkItem.setLobbyItemType(ItemType.regularItemSmall);
                    linkItem.setMako_ref_comp("slider_Video");
                    lobbyFragmentHandler.onClick(linkItem, null);
                }
            });
            if (lobbyTeaser.getLinks().length() > 1) {
                this.linkB.setVisibility(0);
                this.linkBIcon.setVisibility(0);
                final JSONObject jSONObjectOptJSONObject2 = lobbyTeaser.getLinks().optJSONObject(1);
                this.linkB.setText(jSONObjectOptJSONObject2.optString("title"));
                this.linkB.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.lobby.models.teasers.TeaserMainItemSpecial.3
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) throws JSONException {
                        String strOptString = jSONObjectOptJSONObject2.optString("url");
                        JSONObject jSONObject = new JSONObject();
                        try {
                            jSONObject.put("url", strOptString);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        LinkItem linkItem = new LinkItem(jSONObject);
                        linkItem.setLobbyItemType(ItemType.regularItemSmall);
                        linkItem.setMako_ref_comp("slider_Video");
                        lobbyFragmentHandler.onClick(linkItem, null);
                    }
                });
            }
        }
        if (lobbyTeaser.getNTheme() != null) {
            Glide.with(this.teaser_main_item_special_text_box).load(lobbyTeaser.getNTheme().getGradientBackground()).into((RequestBuilder<Drawable>) new CustomTarget<Drawable>() { // from class: com.channel2.mobile.ui.lobby.models.teasers.TeaserMainItemSpecial.4
                @Override // com.bumptech.glide.request.target.Target
                public void onLoadCleared(Drawable drawable) {
                }

                @Override // com.bumptech.glide.request.target.Target
                public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                    onResourceReady((Drawable) obj, (Transition<? super Drawable>) transition);
                }

                public void onResourceReady(Drawable drawable, Transition<? super Drawable> transition) {
                    TeaserMainItemSpecial.this.teaser_main_item_special_text_box.setBackground(drawable);
                }
            });
        }
    }
}
