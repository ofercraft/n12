package com.channel2.mobile.ui.lobby.models.items;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.channel2.mobile.ui.Chats.models.ChatItemType;
import com.channel2.mobile.ui.Chats.views.TabChats.ChatCollageTabItem;
import com.channel2.mobile.ui.Chats.views.TabChats.ChatGalleryTabItem;
import com.channel2.mobile.ui.Chats.views.TabChats.ChatLinkTabItem;
import com.channel2.mobile.ui.Chats.views.TabChats.ChatMediaTabItem;
import com.channel2.mobile.ui.Chats.views.TabChats.ChatReplayMediaTabItem;
import com.channel2.mobile.ui.Chats.views.TabChats.ChatReplayTextTabItem;
import com.channel2.mobile.ui.Chats.views.TabChats.ChatSectionHeader;
import com.channel2.mobile.ui.Chats.views.TabChats.ChatTabTypingItem;
import com.channel2.mobile.ui.Chats.views.TabChats.ChatTextTabItem;
import com.channel2.mobile.ui.Chats.views.TabChats.ChatVideoTabItem;
import com.channel2.mobile.ui.Chats.views.TabChats.UnreadChatMessages;
import com.channel2.mobile.ui.Chats.views.lobbyChats.LobbyChatVHLink;
import com.channel2.mobile.ui.Chats.views.lobbyChats.LobbyChatVHLinkReply;
import com.channel2.mobile.ui.Chats.views.lobbyChats.LobbyChatVHMedia;
import com.channel2.mobile.ui.Chats.views.lobbyChats.LobbyChatVHMediaAndText;
import com.channel2.mobile.ui.Chats.views.lobbyChats.LobbyChatVHMediaAndTextReply;
import com.channel2.mobile.ui.Chats.views.lobbyChats.LobbyChatVHMediaReply;
import com.channel2.mobile.ui.Chats.views.lobbyChats.LobbyChatVHText;
import com.channel2.mobile.ui.Chats.views.lobbyChats.LobbyChatVHTextReply;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder;
import com.channel2.mobile.ui.lobby.models.ads.AdBanner;
import com.channel2.mobile.ui.lobby.models.ads.AdSponsored;
import com.channel2.mobile.ui.lobby.models.ads.NativeAdFeedroll;
import com.channel2.mobile.ui.lobby.models.firstReport.TeaserFirstReport;
import com.channel2.mobile.ui.lobby.models.sections.SectionChatReportLobby;
import com.channel2.mobile.ui.lobby.models.sections.SectionFooter;
import com.channel2.mobile.ui.lobby.models.sections.SectionHorizontal;
import com.channel2.mobile.ui.lobby.models.sections.SectionHorizontalPaging;
import com.channel2.mobile.ui.lobby.models.sections.SectionVertical;
import com.channel2.mobile.ui.lobby.models.sections.SectionVerticalParshanim;
import com.channel2.mobile.ui.lobby.models.teasers.ObituariesTeaserItem;
import com.channel2.mobile.ui.lobby.models.teasers.TeaserDevider;
import com.channel2.mobile.ui.lobby.models.teasers.TeaserIframe;
import com.channel2.mobile.ui.lobby.models.teasers.TeaserImage;
import com.channel2.mobile.ui.lobby.models.teasers.TeaserMainItemMedium;
import com.channel2.mobile.ui.lobby.models.teasers.TeaserMainItemMediumVideo;
import com.channel2.mobile.ui.lobby.models.teasers.TeaserMainItemRegular;
import com.channel2.mobile.ui.lobby.models.teasers.TeaserMainItemRegularVideo;
import com.channel2.mobile.ui.lobby.models.teasers.TeaserMainItemSpecial;
import com.channel2.mobile.ui.lobby.models.teasers.TeaserMainItemSpecialVideo;
import com.channel2.mobile.ui.lobby.models.teasers.TeaserNoneItem;
import com.channel2.mobile.ui.lobby.models.teasers.TeaserPlaceholder;
import com.channel2.mobile.ui.lobby.models.teasers.TeaserPodcastSmall;
import com.channel2.mobile.ui.lobby.models.teasers.TeaserQuoteItem;
import com.channel2.mobile.ui.lobby.models.teasers.TeaserRegularItemBig;
import com.channel2.mobile.ui.lobby.models.teasers.TeaserRegularItemBigLobby;
import com.channel2.mobile.ui.lobby.models.teasers.TeaserRegularItemBigOverText;
import com.channel2.mobile.ui.lobby.models.teasers.TeaserRegularItemSmall;
import com.channel2.mobile.ui.lobby.models.teasers.TeaserRegularItemSmallFlach;
import com.channel2.mobile.ui.lobby.models.teasers.TeaserRegularItemSmallKatav;
import com.channel2.mobile.ui.lobby.models.teasers.TeaserRegularItemSmallMador;
import com.channel2.mobile.ui.lobby.models.teasers.TeaserRegularItemSmallRound;
import com.channel2.mobile.ui.lobby.models.teasers.TeaserRegularItemSmallRoundNoCaption;
import com.channel2.mobile.ui.lobby.models.teasers.TeaserRegularItemSmallRoundThickDivider;
import com.channel2.mobile.ui.lobby.models.teasers.TeaserRegularItemSmallTagit;
import com.channel2.mobile.ui.lobby.models.teasers.TeaserRoundItem;
import com.channel2.mobile.ui.lobby.models.teasers.TeaserSpecialItem;
import com.channel2.mobile.ui.lobby.models.teasers.TeaserTextItem;
import com.channel2.mobile.ui.lobby.models.teasers.TeaserTextItemFlach;
import com.channel2.mobile.ui.lobby.models.teasers.TeaserTextItemRegular;
import com.channel2.mobile.ui.lobby.models.teasers.TeaserVideoItem;
import com.rd.animation.type.BaseAnimation;
import org.apache.http.HttpStatus;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Token;

/* loaded from: classes2.dex */
public class ItemManager {
    private static final int MAIN_ITEM_MEDIUM = 1;
    private static final int MAIN_ITEM_MEDIUM_VIDEO = 101;
    private static final int VIDEO_ITEM = 6;
    private final int MAIN_ITEM_REGULAR = 0;
    private final int MAIN_ITEM_REGULAR_VIDEO = 1001;
    private final int TEXT_ITEM = 2;
    private final int QUOTE_ITEM = 201;
    private final int MAIN_ITEM_SPECIAL_EVENT = 3;
    private final int MAIN_ITEM_SPECIAL_EVENT_VIDEO = 301;
    private final int REGULAR_ITEM_SMALL = 4;
    private final int REGULAR_ITEM_PODCAST_SMALL = 401;
    private final int REGULAR_ITEM_BIG = 5;
    private final int REGULAR_ITEM_BIG_OVER_TEXT = 500;
    private final int REGULAR_ITEM_BIG_OVER_TEXT_C = 501;
    private final int SPECIAL_ITEM = 7;
    private final int ROUND_ITEM = 8;
    private final int FLUID = 200;
    private final int BANNER = 9;
    private final int SPONSORED = 10;
    private final int FEEDROLL = 11;
    private final int HORIZONTAL = 12;
    private final int VERTICAL = 13;
    private final int VERTICAL_PARSHANIM = 130;
    private final int OBITUARIES = Token.LOOP;
    private final int REGULAR_ITEM_SMALL_ROUND = 14;
    private final int REGULAR_ITEM_SMALL_ROUND_NO_CAPTION = 140;
    private final int NONE = 15;
    private final int HORIZONTAL_PAGING = 16;
    private final int TEXT_ITEM_REGULAR = 17;
    private final int TEXT_ITEM_REGULAR_AD = Context.VERSION_1_7;
    private final int PLACEHOLDER_BIG = 18;
    private final int PLACEHOLDER_SMALL = 19;
    private final int SPACER_DIVIDER_THIN = 20;
    private final int SPACER_DIVIDER_THICK = 21;
    private final int SPACER_DIVIDER_MEDIUM = 22;
    private final int IMAGE_ITEM = 23;
    private final int IFRAME_ITEM = 24;
    private final int FOOTER = 25;
    private final int FOOTER_PARSHANIM = 250;
    private final int FOOTER_PODCAST = 251;
    private final int REGULAR_ITEM_SMALL_MADOR = 26;
    private final int REGULAR_ITEM_SMALL_KATAV = 27;
    private final int REGULAR_ITEM_SMALL_TAGIT = 28;
    private final int REGULAR_ITEM_SMALL_AD = 49;
    private final int REGULAR_ITEM_BIG_LOBBY = 29;
    private final int REGULAR_ITEM_BIG_LOBBY_C = 290;
    private final int REGULAR_ITEM_SMALL_ROUND_THICK_DIVIDER = 30;
    private final int COLLABORATION_ITEM = 50;
    private final int COLLABORATION_ITEM_TEXT = 51;
    private final int ADVERTISING_ITEM = 52;
    private final int ADVERTISING_ITEM_TEXT = 53;
    private final int OBITUARIES_ITEM = 54;
    private final int FIRST_REPORT = 31;
    private final int HORIZONTAL_PAGING_VIDEO = 32;
    private final int REPORTERS_CHAT = 33;
    private final int CHAT_ITEM_TEXT = 34;
    private final int CHAT_ITEM_TEXT_REPLY = 340;
    private final int CHAT_ITEM_MEDIA_AND_TEXT = 35;
    private final int CHAT_ITEM_MEDIA_AND_TEXT_REPLY = BaseAnimation.DEFAULT_ANIMATION_TIME;
    private final int CHAT_ITEM_MEDIA = 36;
    private final int CHAT_ITEM_MEDIA_REPLY = 360;
    private final int CHAT_ITEM_LINK = 37;
    private final int CHAT_ITEM_LINK_REPLY = 370;
    private final int CHAT_TEXT_TAB_ITEM = 38;
    private final int CHAT_TEXT_TAB_ITEM_REPLY = 380;
    private final int CHAT_MEDIA_TAB_ITEM = 39;
    private final int CHAT_MEDIA_TAB_ITEM_REPLY = 390;
    private final int CHAT_MEDIA_AND_TEXT_TAB_ITEM = 40;
    private final int CHAT_LINK_TAB_ITEM = 41;
    private final int CHAT_LINK_TAB_ITEM_REPLY = HttpStatus.SC_GONE;
    private final int CHAT_COLLAGE_TAB_ITEM = 42;
    private final int CHAT_GALLERY_TAB_ITEM = 43;
    private final int CHAT_UNREAD_MESSAGES_ITEM = 44;
    private final int CHAT_TYPING_ITEM = 45;
    private final int CHAT_VIDEO_AND_TEXT_TAB_ITEM = 46;
    private final int CHAT_VIDEO_TAB_ITEM = 47;
    private final int CHAT_SECTION_HEADER_ITEM = 48;
    private final int TOP_DIVIDER_REGULAR = 999;
    private final int TOP_DIVIDER_REGULAR_WHITE = 888;
    private final int BOTTOM_DIVIDER_REGULAR = 777;
    private final int BOTTOM_DIVIDER_REGULAR_WHITE = 666;

    public int getItemViewType(ItemType itemType) {
        if (itemType == ItemType.topDividerregular) {
            return 999;
        }
        if (itemType == ItemType.topDividerregularwhite) {
            return 888;
        }
        if (itemType == ItemType.bottomDividerregular) {
            return 777;
        }
        if (itemType == ItemType.bottomDividerregularwhite) {
            return 666;
        }
        if (itemType == ItemType.mainItemRegular) {
            return 0;
        }
        if (itemType == ItemType.mainItemRegularVideo) {
            return 1001;
        }
        if (itemType == ItemType.obituaryItem) {
            return 54;
        }
        if (itemType == ItemType.obituaries) {
            return Token.LOOP;
        }
        if (itemType == ItemType.mainItemMedium) {
            return 1;
        }
        if (itemType == ItemType.mainItemMediumVideo) {
            return 101;
        }
        if (itemType == ItemType.mainItemSpecialEvents) {
            return 3;
        }
        if (itemType == ItemType.mainItemSpecialEventsVideo) {
            return 301;
        }
        if (itemType == ItemType.regularItemSmall || itemType == ItemType.regularitemsmallc) {
            return 4;
        }
        if (itemType == ItemType.regularItemSmallAd || itemType == ItemType.regularitemsmallcAd) {
            return 49;
        }
        if (itemType == ItemType.podcastsmall) {
            return 401;
        }
        if (itemType == ItemType.regularItemBig || itemType == ItemType.regularItemBigC) {
            return 5;
        }
        if (itemType == ItemType.regularitembigovertext) {
            return 500;
        }
        if (itemType == ItemType.regularitembigovertextc) {
            return 501;
        }
        if (itemType == ItemType.videoItem) {
            return 6;
        }
        if (itemType == ItemType.textitem) {
            return 2;
        }
        if (itemType == ItemType.textitemregular) {
            return 17;
        }
        if (itemType == ItemType.textitemregularAd) {
            return Context.VERSION_1_7;
        }
        if (itemType == ItemType.quoteItem) {
            return 201;
        }
        if (itemType == ItemType.specialItem) {
            return 7;
        }
        if (itemType == ItemType.roundItem) {
            return 8;
        }
        if (itemType == ItemType.banner) {
            return 9;
        }
        if (itemType == ItemType.fluid) {
            return 200;
        }
        if (itemType == ItemType.sponsored) {
            return 10;
        }
        if (itemType == ItemType.feedroll) {
            return 11;
        }
        if (itemType == ItemType.horizontal) {
            return 12;
        }
        if (itemType == ItemType.horizontalPaging) {
            return 16;
        }
        if (itemType == ItemType.horizontalPagingVideo) {
            return 32;
        }
        if (itemType == ItemType.regularItemSmallRound) {
            return 14;
        }
        if (itemType == ItemType.regularitemsmallroundnocaption) {
            return 140;
        }
        if (itemType == ItemType.vertical || itemType == ItemType.outbrain || itemType == ItemType.outbrainfooter || itemType == ItemType.verticalpodcast) {
            return 13;
        }
        if (itemType == ItemType.verticalparshanim) {
            return 130;
        }
        if (itemType == ItemType.none) {
            return 15;
        }
        if (itemType == ItemType.placeholderBig) {
            return 18;
        }
        if (itemType == ItemType.placeholderSmall) {
            return 19;
        }
        if (itemType == ItemType.spacerDividerThick) {
            return 21;
        }
        if (itemType == ItemType.spacerDividerMedium) {
            return 22;
        }
        if (itemType == ItemType.spacerDividerThin) {
            return 20;
        }
        if (itemType == ItemType.genericItem) {
            return 23;
        }
        if (itemType == ItemType.flashitem) {
            return 24;
        }
        if (itemType == ItemType.footer) {
            return 25;
        }
        if (itemType == ItemType.footerparshanim) {
            return 250;
        }
        if (itemType == ItemType.footerpodcast) {
            return 251;
        }
        if (itemType == ItemType.regularItemSmallMador) {
            return 26;
        }
        if (itemType == ItemType.regularItemSmallKatav) {
            return 27;
        }
        if (itemType == ItemType.regularItemSmallTagit) {
            return 28;
        }
        if (itemType == ItemType.bigItemLobby) {
            return 29;
        }
        if (itemType == ItemType.bigitemlobbyc) {
            return 290;
        }
        if (itemType == ItemType.lobbyRoundItem) {
            return 30;
        }
        if (itemType == ItemType.firstReport) {
            return 31;
        }
        if (itemType == ItemType.reportersChat) {
            return 33;
        }
        if (itemType == ItemType.advertisingitem) {
            return 52;
        }
        if (itemType == ItemType.textadvertisingitem) {
            return 53;
        }
        if (itemType == ItemType.collaborationitem) {
            return 50;
        }
        return itemType == ItemType.textcollaborationitem ? 51 : 4;
    }

    public int getChatItemViewType(ChatItemType chatItemType, Boolean bool) {
        if (chatItemType == ChatItemType.text) {
            return bool.booleanValue() ? 38 : 34;
        }
        if (chatItemType == ChatItemType.textReply) {
            return bool.booleanValue() ? 380 : 340;
        }
        if (chatItemType == ChatItemType.mediaX1) {
            return bool.booleanValue() ? 39 : 36;
        }
        if (chatItemType == ChatItemType.mediaX2) {
            return bool.booleanValue() ? 39 : 36;
        }
        if (chatItemType == ChatItemType.mediaReply) {
            return bool.booleanValue() ? 390 : 360;
        }
        if (chatItemType == ChatItemType.mediaAndText) {
            return bool.booleanValue() ? 40 : 35;
        }
        if (chatItemType == ChatItemType.mediaVideoAndText) {
            return bool.booleanValue() ? 46 : 35;
        }
        if (chatItemType == ChatItemType.mediaVideo) {
            return bool.booleanValue() ? 47 : 35;
        }
        if (chatItemType == ChatItemType.mediaAndTextReply) {
            if (bool.booleanValue()) {
                return 390;
            }
            return BaseAnimation.DEFAULT_ANIMATION_TIME;
        }
        if (chatItemType == ChatItemType.link) {
            return bool.booleanValue() ? 41 : 37;
        }
        if (chatItemType == ChatItemType.linkReply) {
            return bool.booleanValue() ? 390 : 370;
        }
        if (chatItemType == ChatItemType.collage) {
            return bool.booleanValue() ? 42 : 36;
        }
        if (chatItemType == ChatItemType.gallery) {
            return bool.booleanValue() ? 43 : 36;
        }
        if (chatItemType == ChatItemType.unreadMessages) {
            return 44;
        }
        if (chatItemType == ChatItemType.typing && bool.booleanValue()) {
            return 45;
        }
        return (chatItemType == ChatItemType.sectionHeader && bool.booleanValue()) ? 48 : 34;
    }

    public CustomRecyclerViewHolder getLobbyItemView(int i, ViewGroup viewGroup) {
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(viewGroup.getContext());
        if (i != 200) {
            if (i == 201) {
                return new TeaserQuoteItem(layoutInflaterFrom.inflate(R.layout.teaser_quote_item, viewGroup, false));
            }
            if (i == 250) {
                return new SectionFooter(layoutInflaterFrom.inflate(R.layout.teaser_section_footer_parshanim, viewGroup, false));
            }
            if (i == 251) {
                return new SectionFooter(layoutInflaterFrom.inflate(R.layout.teaser_section_footer_podcast, viewGroup, false));
            }
            if (i == 500) {
                return new TeaserRegularItemBigOverText(layoutInflaterFrom.inflate(R.layout.teaser_regular_big_over_text, viewGroup, false));
            }
            if (i != 501) {
                switch (i) {
                    case 0:
                        return new TeaserMainItemRegular(layoutInflaterFrom.inflate(R.layout.teaser_main_item_regular, viewGroup, false));
                    case 1:
                        return new TeaserMainItemMedium(layoutInflaterFrom.inflate(R.layout.teaser_main_item_medium, viewGroup, false));
                    case 2:
                        return new TeaserTextItem(layoutInflaterFrom.inflate(R.layout.teaser_text_item, viewGroup, false));
                    case 3:
                        return new TeaserMainItemSpecial(layoutInflaterFrom.inflate(R.layout.teaser_main_item_special, viewGroup, false));
                    case 4:
                        return new TeaserRegularItemSmall(layoutInflaterFrom.inflate(R.layout.teaser_regular_small, viewGroup, false));
                    case 5:
                        return new TeaserRegularItemBig(layoutInflaterFrom.inflate(R.layout.teaser_regular_big, viewGroup, false));
                    case 6:
                        View viewInflate = layoutInflaterFrom.inflate(R.layout.teaser_video_item, viewGroup, false);
                        viewInflate.setTag("videoitem");
                        return new TeaserVideoItem(viewInflate);
                    case 7:
                        View viewInflate2 = layoutInflaterFrom.inflate(R.layout.teaser_special_item, viewGroup, false);
                        viewInflate2.setTag("specialitem");
                        return new TeaserSpecialItem(viewInflate2);
                    case 8:
                        return new TeaserRoundItem(layoutInflaterFrom.inflate(R.layout.teaser_round_item, viewGroup, false));
                    case 9:
                        break;
                    case 10:
                        return new AdSponsored(layoutInflaterFrom.inflate(R.layout.teaser_ad_sponsored, viewGroup, false));
                    case 11:
                        return new NativeAdFeedroll(layoutInflaterFrom.inflate(R.layout.teaser_ad_feedroll, viewGroup, false));
                    case 12:
                        return new SectionHorizontal(layoutInflaterFrom.inflate(R.layout.teaser_section_horizontal, viewGroup, false));
                    case 13:
                        break;
                    case 14:
                        return new TeaserRegularItemSmallRound(layoutInflaterFrom.inflate(R.layout.teaser_regular_small_round, viewGroup, false));
                    case 15:
                        return new TeaserNoneItem(layoutInflaterFrom.inflate(R.layout.teaser_none, viewGroup, false));
                    case 16:
                        return new SectionHorizontalPaging(layoutInflaterFrom.inflate(R.layout.teaser_section_horizontal_paging, viewGroup, false));
                    case 17:
                        return new TeaserTextItemRegular(layoutInflaterFrom.inflate(R.layout.teaser_text_item_regular, viewGroup, false));
                    case 18:
                        return new TeaserPlaceholder(layoutInflaterFrom.inflate(R.layout.teaser_placeholder_big, viewGroup, false));
                    case 19:
                        return new TeaserPlaceholder(layoutInflaterFrom.inflate(R.layout.teaser_placeholder_small, viewGroup, false));
                    case 20:
                        return new TeaserPlaceholder(layoutInflaterFrom.inflate(R.layout.teaser_spacer_divider_thin, viewGroup, false));
                    case 21:
                        return new TeaserPlaceholder(layoutInflaterFrom.inflate(R.layout.teaser_spacer_divider_thick, viewGroup, false));
                    case 22:
                        return new TeaserPlaceholder(layoutInflaterFrom.inflate(R.layout.teaser_spacer_divider_medium, viewGroup, false));
                    case 23:
                        return new TeaserImage(layoutInflaterFrom.inflate(R.layout.teaser_image, viewGroup, false));
                    case 24:
                        return new TeaserIframe(layoutInflaterFrom.inflate(R.layout.teaser_iframe, viewGroup, false));
                    case 25:
                        return new SectionFooter(layoutInflaterFrom.inflate(R.layout.teaser_section_footer, viewGroup, false));
                    case 26:
                        return new TeaserRegularItemSmallMador(layoutInflaterFrom.inflate(R.layout.teaser_regular_small_mador, viewGroup, false));
                    case 27:
                        return new TeaserRegularItemSmallKatav(layoutInflaterFrom.inflate(R.layout.teaser_regular_small_katav, viewGroup, false));
                    case 28:
                        return new TeaserRegularItemSmallTagit(layoutInflaterFrom.inflate(R.layout.teaser_regular_small_tagit, viewGroup, false));
                    case 29:
                        return new TeaserRegularItemBigLobby(layoutInflaterFrom.inflate(R.layout.teaser_regular_big_lobby, viewGroup, false));
                    case 30:
                        return new TeaserRegularItemSmallRoundThickDivider(layoutInflaterFrom.inflate(R.layout.teaser_regular_small_round_thick_divider, viewGroup, false));
                    case 31:
                        return new TeaserFirstReport(layoutInflaterFrom.inflate(R.layout.teaser_first_report, viewGroup, false));
                    case 32:
                        return new SectionHorizontalPaging(layoutInflaterFrom.inflate(R.layout.teaser_section_horizontal_paging_video, viewGroup, false));
                    case 33:
                        return new SectionChatReportLobby(layoutInflaterFrom.inflate(R.layout.lobby_chat_section, viewGroup, false));
                    case 34:
                        View viewInflate3 = layoutInflaterFrom.inflate(R.layout.lobby_chat_item_text, viewGroup, false);
                        viewInflate3.setTag("LobbyChatVHText");
                        return new LobbyChatVHText(viewInflate3);
                    case 35:
                        View viewInflate4 = layoutInflaterFrom.inflate(R.layout.lobby_chat_item_media_and_text, viewGroup, false);
                        viewInflate4.setTag("LobbyChatVHMediaAndText");
                        return new LobbyChatVHMediaAndText(viewInflate4);
                    case 36:
                        View viewInflate5 = layoutInflaterFrom.inflate(R.layout.lobby_chat_item_media, viewGroup, false);
                        viewInflate5.setTag("LobbyChatVHMedia");
                        return new LobbyChatVHMedia(viewInflate5);
                    case 37:
                        View viewInflate6 = layoutInflaterFrom.inflate(R.layout.lobby_chat_item_link, viewGroup, false);
                        viewInflate6.setTag("LobbyChatVHLink");
                        return new LobbyChatVHLink(viewInflate6);
                    case 38:
                        return new ChatTextTabItem(layoutInflaterFrom.inflate(R.layout.chat_text_tab_item, viewGroup, false));
                    case 39:
                    case 40:
                        return new ChatMediaTabItem(layoutInflaterFrom.inflate(R.layout.chat_media_tab_item, viewGroup, false));
                    case 41:
                        return new ChatLinkTabItem(layoutInflaterFrom.inflate(R.layout.chat_link_tab_item, viewGroup, false));
                    case 42:
                        return new ChatCollageTabItem(layoutInflaterFrom.inflate(R.layout.chat_collage_tab_item, viewGroup, false));
                    case 43:
                        return new ChatGalleryTabItem(layoutInflaterFrom.inflate(R.layout.chat_gallery_tab_item, viewGroup, false));
                    case 44:
                        return new UnreadChatMessages(layoutInflaterFrom.inflate(R.layout.unread_chat_messages, viewGroup, false));
                    case 45:
                        return new ChatTabTypingItem(layoutInflaterFrom.inflate(R.layout.chat_tab_typing_item, viewGroup, false));
                    case 46:
                    case 47:
                        return new ChatVideoTabItem(layoutInflaterFrom.inflate(R.layout.chat_video_tab_item, viewGroup, false));
                    case 48:
                        return new ChatSectionHeader(layoutInflaterFrom.inflate(R.layout.chat_section_header, viewGroup, false));
                    case 49:
                        return new TeaserRegularItemSmallFlach(layoutInflaterFrom.inflate(R.layout.teaser_regular_small_ad, viewGroup, false));
                    case 50:
                        return new TeaserRegularItemSmallFlach(layoutInflaterFrom.inflate(R.layout.teaser_collaboration_item, viewGroup, false));
                    case 51:
                        return new TeaserTextItemFlach(layoutInflaterFrom.inflate(R.layout.teaser_collaboration_item_text, viewGroup, false));
                    case 52:
                        return new TeaserRegularItemSmallFlach(layoutInflaterFrom.inflate(R.layout.teaser_advertising_item, viewGroup, false));
                    case 53:
                        return new TeaserTextItemFlach(layoutInflaterFrom.inflate(R.layout.teaser_advertising_item_text, viewGroup, false));
                    case 54:
                        return new ObituariesTeaserItem(layoutInflaterFrom.inflate(R.layout.obituaries_item, viewGroup, false));
                    default:
                        switch (i) {
                            case 101:
                                return new TeaserMainItemMediumVideo(layoutInflaterFrom.inflate(R.layout.teaser_main_item_medium_video, viewGroup, false));
                            case 130:
                                return new SectionVerticalParshanim(layoutInflaterFrom.inflate(R.layout.teaser_section_vertical_parshanim, viewGroup, false));
                            case Token.LOOP /* 132 */:
                                break;
                            case 140:
                                return new TeaserRegularItemSmallRoundNoCaption(layoutInflaterFrom.inflate(R.layout.teaser_regular_small_round_no_caption, viewGroup, false));
                            case Context.VERSION_1_7 /* 170 */:
                                return new TeaserTextItemFlach(layoutInflaterFrom.inflate(R.layout.teaser_text_item_regular_ad, viewGroup, false));
                            case 290:
                                return new TeaserRegularItemBigLobby(layoutInflaterFrom.inflate(R.layout.teaser_regular_big_lobby_c, viewGroup, false));
                            case 301:
                                return new TeaserMainItemSpecialVideo(layoutInflaterFrom.inflate(R.layout.teaser_main_item_special_video, viewGroup, false));
                            case 340:
                                View viewInflate7 = layoutInflaterFrom.inflate(R.layout.lobby_chat_item_text_reply, viewGroup, false);
                                viewInflate7.setTag("LobbyChatVHTextReply");
                                return new LobbyChatVHTextReply(viewInflate7);
                            case BaseAnimation.DEFAULT_ANIMATION_TIME /* 350 */:
                                View viewInflate8 = layoutInflaterFrom.inflate(R.layout.lobby_chat_item_reply, viewGroup, false);
                                viewInflate8.setTag("LobbyChatVHMediaAndTextReply");
                                return new LobbyChatVHMediaAndTextReply(viewInflate8);
                            case 360:
                                View viewInflate9 = layoutInflaterFrom.inflate(R.layout.lobby_chat_item_reply, viewGroup, false);
                                viewInflate9.setTag("LobbyChatVHMediaReply");
                                return new LobbyChatVHMediaReply(viewInflate9);
                            case 370:
                                View viewInflate10 = layoutInflaterFrom.inflate(R.layout.lobby_chat_item_reply, viewGroup, false);
                                viewInflate10.setTag("LobbyChatVHLinkReply");
                                return new LobbyChatVHLinkReply(viewInflate10);
                            case 380:
                                return new ChatReplayTextTabItem(layoutInflaterFrom.inflate(R.layout.chat_reply_text_tab_item, viewGroup, false));
                            case 390:
                                return new ChatReplayMediaTabItem(layoutInflaterFrom.inflate(R.layout.chat_reply_media_tab_item, viewGroup, false));
                            case 401:
                                return new TeaserPodcastSmall(layoutInflaterFrom.inflate(R.layout.teaser_podcast_small, viewGroup, false));
                            case 666:
                                return new TeaserDevider(layoutInflaterFrom.inflate(R.layout.divider_bottom_regular_white, viewGroup, false));
                            case 777:
                                return new TeaserDevider(layoutInflaterFrom.inflate(R.layout.divider_bottom_regular, viewGroup, false));
                            case 888:
                                return new TeaserDevider(layoutInflaterFrom.inflate(R.layout.divider_top_regular_white, viewGroup, false));
                            case 999:
                                return new TeaserDevider(layoutInflaterFrom.inflate(R.layout.divider_top_regular, viewGroup, false));
                            case 1001:
                                return new TeaserMainItemRegularVideo(layoutInflaterFrom.inflate(R.layout.teaser_main_item_regular_video, viewGroup, false));
                            default:
                                return new TeaserDevider(layoutInflaterFrom.inflate(R.layout.divider_bottom_regular, viewGroup, false));
                        }
                }
                return new SectionVertical(layoutInflaterFrom.inflate(R.layout.teaser_section_vertical, viewGroup, false));
            }
            return new TeaserRegularItemBigOverText(layoutInflaterFrom.inflate(R.layout.teaser_regular_big_over_text_c, viewGroup, false));
        }
        return new AdBanner(layoutInflaterFrom.inflate(R.layout.teaser_ad_banner, viewGroup, false));
    }
}
