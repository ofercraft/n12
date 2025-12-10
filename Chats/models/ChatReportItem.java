package com.channel2.mobile.ui.Chats.models;

import android.os.Build;
import android.text.Html;
import com.channel2.mobile.ui.lobby.models.items.Item;
import com.google.firebase.database.DataSnapshot;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class ChatReportItem extends Item implements Serializable {
    public static final int MEDIA_TYPE_AUDIO = 3;
    public static final int MEDIA_TYPE_COLLAGE = 6;
    public static final int MEDIA_TYPE_DOCUMENT = 4;
    public static final int MEDIA_TYPE_GALLERY = 7;
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_LINK = 5;
    public static final int MEDIA_TYPE_VIDEO = 2;
    private static final int TEST = 3;
    private ChatItemType chatItemTempType;
    private ChatItemType chatItemType;
    private ArrayList<ChatMediaItem> chatMedias;
    private String chatName;
    private long clickedMediaID;
    private String firebaseID;
    private boolean isCurrent;
    private boolean isFirst;
    private boolean isFromFirebase;
    private boolean isImportant;
    private boolean isReadMoreClick;
    private boolean isSplitted;
    private boolean isTyping;
    private String messageContent;
    private long messageID;
    public MessageState messageState;
    private String name;
    private ChatReportItem reply;
    private String reporterImage;
    private long sharedDate;
    private String sharedName;
    private long statusID;
    private int topicID;
    private long updatedDate;

    public enum MessageState {
        regular,
        shared,
        special,
        blue
    }

    public ChatReportItem() {
        this.name = "";
        this.sharedName = "";
        this.isFirst = true;
        this.isTyping = false;
        this.isReadMoreClick = false;
        this.messageState = MessageState.regular;
    }

    public ChatReportItem(ChatReportItem chatReportItem) {
        this.name = "";
        this.sharedName = "";
        this.isFirst = true;
        this.isTyping = false;
        this.isReadMoreClick = false;
        this.messageState = MessageState.regular;
        this.topicID = chatReportItem.topicID;
        this.chatName = chatReportItem.chatName;
        this.chatItemType = chatReportItem.chatItemType;
        this.messageID = chatReportItem.messageID;
        this.firebaseID = chatReportItem.firebaseID;
        this.name = chatReportItem.name;
        this.sharedName = chatReportItem.sharedName;
        this.statusID = chatReportItem.statusID;
        this.messageContent = chatReportItem.messageContent;
        this.updatedDate = chatReportItem.updatedDate;
        this.sharedDate = chatReportItem.sharedDate;
        this.reporterImage = chatReportItem.reporterImage;
        this.reply = chatReportItem.reply;
        this.isFirst = chatReportItem.isFirst;
        this.isFromFirebase = chatReportItem.isFromFirebase;
        this.isSplitted = chatReportItem.isSplitted;
        this.isCurrent = chatReportItem.isCurrent;
        this.isImportant = chatReportItem.isImportant;
    }

    public void copy(ChatReportItem chatReportItem) {
        this.topicID = chatReportItem.topicID;
        this.chatName = chatReportItem.chatName;
        this.chatItemType = chatReportItem.chatItemType;
        this.messageID = chatReportItem.messageID;
        this.firebaseID = chatReportItem.firebaseID;
        this.name = chatReportItem.name;
        this.sharedName = chatReportItem.sharedName;
        this.statusID = chatReportItem.statusID;
        this.messageContent = chatReportItem.messageContent;
        this.updatedDate = chatReportItem.updatedDate;
        this.sharedDate = chatReportItem.sharedDate;
        this.reporterImage = chatReportItem.reporterImage;
        this.reply = chatReportItem.reply;
        this.isFirst = chatReportItem.isFirst;
        this.chatMedias = chatReportItem.chatMedias;
        this.isTyping = chatReportItem.isTyping;
        this.isFromFirebase = chatReportItem.isFromFirebase;
        this.isSplitted = chatReportItem.isSplitted;
        this.isCurrent = chatReportItem.isCurrent;
        this.isImportant = chatReportItem.isImportant;
        this.messageState = chatReportItem.messageState;
    }

    public ChatReportItem(DataSnapshot dataSnapshot) {
        this.name = "";
        this.sharedName = "";
        this.isFirst = true;
        this.isTyping = false;
        this.isReadMoreClick = false;
        this.messageState = MessageState.regular;
        try {
            setTopicID(dataSnapshot.child("reporter").child("reporter").hasChild("topicID") ? ((Long) dataSnapshot.child("reporter").child("reporter").child("topicID").getValue()).intValue() : 1);
            setMessageID(((Long) dataSnapshot.child("messageID").getValue()).longValue());
            setName((String) dataSnapshot.child("reporter").child("reporter").child("name").getValue());
            setSharedName((String) dataSnapshot.child("reporter").child("reporter").child("name").getValue());
            setReporterImage((String) dataSnapshot.child("reporter").child("reporter").child("image").getValue());
            setStatusID(((Long) dataSnapshot.child("statusID").getValue()).longValue());
            setMessageContent((String) dataSnapshot.child("messageContent").getValue());
            if (dataSnapshot.child("current").getValue() != null) {
                setCurrent(((Boolean) dataSnapshot.child("current").getValue()).booleanValue());
            }
            if (dataSnapshot.child("important").getValue() != null) {
                setImportant(((Boolean) dataSnapshot.child("important").getValue()).booleanValue());
            }
            if (dataSnapshot.hasChild("updatedDate/time")) {
                setUpdatedDate(((Long) dataSnapshot.child("updatedDate/time").getValue()).longValue());
                setSharedDate(((Long) dataSnapshot.child("updatedDate/time").getValue()).longValue());
            }
            setMedias((ArrayList) dataSnapshot.child("medias").getValue());
            if (dataSnapshot.child("replyMessage").getValue() != null) {
                this.reply = new ChatReportItem(dataSnapshot.child("replyMessage"));
            }
            setChatItemType();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ChatReportItem(JSONObject jSONObject) {
        super(jSONObject);
        this.name = "";
        this.sharedName = "";
        this.isFirst = true;
        this.isTyping = false;
        this.isReadMoreClick = false;
        this.messageState = MessageState.regular;
        try {
            setTopicID(jSONObject.optJSONObject("reporter").optJSONObject("reporter").optInt("topicID", 1));
            setChatName(jSONObject.optJSONObject("chat").optString("chatName"));
            setMessageID(jSONObject.optInt("messageID"));
            setFirebaseID(jSONObject.optString("firebaseID"));
            setName(jSONObject.optJSONObject("reporter").optJSONObject("reporter").optString("name"));
            setSharedName(jSONObject.optJSONObject("reporter").optJSONObject("reporter").optString("name"));
            setReporterImage(jSONObject.optJSONObject("reporter").optJSONObject("reporter").optString("image"));
            setStatusID(jSONObject.optInt("statusID"));
            setMessageContent(jSONObject.optString("messageContent"));
            setUpdatedDate(jSONObject.optLong("updatedDate"));
            setSharedDate(jSONObject.optLong("updatedDate"));
            setMedias(jSONObject.optJSONArray("medias"));
            if (jSONObject.optJSONObject("replyMessage") != null && jSONObject.optJSONObject("replyMessage").length() > 0) {
                try {
                    this.reply = new ChatReportItem(jSONObject.optJSONObject("replyMessage"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            setChatItemType();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public int getTopicID() {
        return this.topicID;
    }

    public void setTopicID(int i) {
        this.topicID = i;
    }

    public String getChatName() {
        return this.chatName;
    }

    public void setChatName(String str) {
        this.chatName = str;
    }

    public long getMessageID() {
        return this.messageID;
    }

    public void setMessageID(long j) {
        this.messageID = j;
    }

    public String getFirebaseID() {
        return this.firebaseID;
    }

    public void setFirebaseID(String str) {
        this.firebaseID = str;
    }

    public boolean isCurrent() {
        return this.isCurrent;
    }

    public void setCurrent(boolean z) {
        this.isCurrent = z;
    }

    public boolean isImportant() {
        return this.isImportant;
    }

    public void setImportant(boolean z) {
        this.isImportant = z;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        if (str == null || str.equals("")) {
            str = "";
        }
        this.name = str;
    }

    public String getSharedName() {
        return this.sharedName;
    }

    public void setSharedName(String str) {
        this.sharedName = str;
    }

    public long getStatusID() {
        return this.statusID;
    }

    public void setStatusID(long j) {
        this.statusID = j;
    }

    public String getMessageContent() {
        return this.messageContent;
    }

    public void setMessageContent(String str) {
        if (str != null) {
            this.messageContent = stripHtml(str);
        } else {
            this.messageContent = "";
        }
    }

    public String getTimeString() {
        return new SimpleDateFormat("HH:mm", Locale.ENGLISH).format(new Date(this.updatedDate));
    }

    public String getSharedTimeAsString() {
        return new SimpleDateFormat("HH:mm", Locale.ENGLISH).format(new Date(this.sharedDate));
    }

    public String getSharedDateAndTimeAsString() {
        return new SimpleDateFormat("dd/MM/yy HH:mm", Locale.ENGLISH).format(new Date(this.sharedDate));
    }

    public long getUpdatedDate() {
        return this.updatedDate;
    }

    public void setUpdatedDate(long j) {
        this.updatedDate = j;
    }

    public long getSharedDate() {
        return this.sharedDate;
    }

    public void setSharedDate(long j) {
        this.sharedDate = j;
    }

    public int getNumberOfMedias() {
        ArrayList<ChatMediaItem> arrayList = this.chatMedias;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    public ChatMediaItem getChatMedia(int i) {
        return this.chatMedias.get(i);
    }

    public ArrayList<ChatMediaItem> getChatMedias() {
        return this.chatMedias;
    }

    public void setMedias(JSONArray jSONArray) {
        this.chatMedias = new ArrayList<>();
        if (jSONArray == null || jSONArray.length() <= 0) {
            return;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                this.chatMedias.add(new ChatMediaItem((JSONObject) jSONArray.get(i)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setMedias(ArrayList arrayList) {
        this.chatMedias = new ArrayList<>();
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        for (int i = 0; i < arrayList.size(); i++) {
            this.chatMedias.add(new ChatMediaItem((HashMap) arrayList.get(i)));
        }
    }

    public void setMedias(ChatMediaItem chatMediaItem) {
        ArrayList<ChatMediaItem> arrayList = new ArrayList<>();
        this.chatMedias = arrayList;
        arrayList.add(chatMediaItem);
    }

    public String getReporterImage() {
        return this.reporterImage;
    }

    public void setReporterImage(String str) {
        this.reporterImage = str;
    }

    public ChatItemType getChatItemType() {
        return this.chatItemType;
    }

    public void setChatItemType(ChatItemType chatItemType) {
        this.chatItemType = chatItemType;
    }

    public ChatItemType getChatItemTempType() {
        return this.chatItemTempType;
    }

    public void setChatItemTempType(ChatItemType chatItemType) {
        this.chatItemTempType = chatItemType;
    }

    public void setChatItemType() {
        ChatReportItem chatReportItem = this.reply;
        if (chatReportItem == null) {
            ArrayList<ChatMediaItem> arrayList = this.chatMedias;
            if (arrayList != null && arrayList.size() > 0) {
                ChatMediaItem chatMediaItem = this.chatMedias.get(0);
                if (chatMediaItem.getMediaTypeId() == 5) {
                    this.chatItemType = ChatItemType.link;
                } else if (chatMediaItem.getMediaContent() != null && chatMediaItem.getMediaContent().length() > 0) {
                    this.chatItemType = ChatItemType.mediaAndText;
                    if (chatMediaItem.getMediaTypeId() == 2) {
                        this.chatItemType = ChatItemType.mediaVideoAndText;
                    }
                } else if (this.chatMedias.size() == 2) {
                    this.chatItemType = ChatItemType.mediaX2;
                } else if (this.chatMedias.size() == 3) {
                    this.chatItemType = ChatItemType.collage;
                } else if (this.chatMedias.size() >= 4) {
                    this.chatItemType = ChatItemType.gallery;
                } else {
                    this.chatItemType = ChatItemType.mediaX1;
                    if (chatMediaItem.getMediaTypeId() == 2) {
                        this.chatItemType = ChatItemType.mediaVideo;
                    }
                }
            } else {
                this.chatItemType = ChatItemType.text;
            }
        } else if (chatReportItem.getChatMedias() != null && this.reply.getNumberOfMedias() > 0) {
            for (int i = 0; i < this.reply.getChatMedias().size(); i++) {
                if (this.reply.getChatMedias().get(i).getMediaTypeId() == 5) {
                    this.chatItemType = ChatItemType.linkReply;
                } else if (this.reply.getChatMedias().get(i).getMediaContent() != null && this.reply.getChatMedias().get(i).getMediaContent().length() > 0) {
                    this.chatItemType = ChatItemType.mediaAndTextReply;
                } else {
                    this.chatItemType = ChatItemType.mediaReply;
                }
            }
        } else {
            this.chatItemType = ChatItemType.textReply;
        }
        if (this.chatItemType == ChatItemType.link) {
            StringBuilder sb = new StringBuilder();
            if (!this.messageContent.equals("")) {
                sb.append(this.messageContent);
                sb.append(StringUtils.LF);
            }
            for (int i2 = 0; i2 < this.chatMedias.size(); i2++) {
                ChatMediaItem chatMediaItem2 = this.chatMedias.get(i2);
                if (chatMediaItem2.getLink1().length() > 0) {
                    sb.append(chatMediaItem2.getLink1());
                }
                if (chatMediaItem2.getMediaContent().length() > 0) {
                    sb.append(StringUtils.LF);
                    sb.append(chatMediaItem2.getMediaContent());
                }
                if (i2 != this.chatMedias.size() - 1) {
                    sb.append(StringUtils.LF);
                }
            }
            this.messageContent = stripHtml(sb.toString());
            if (this.chatMedias.get(0).getLinkTitle().equals("") && this.chatMedias.get(0).getLinkDescription().equals("")) {
                this.chatItemType = ChatItemType.text;
            }
        }
    }

    public ChatReportItem getReply() {
        return this.reply;
    }

    public boolean isFirst() {
        return this.isFirst;
    }

    public void setFirst(boolean z) {
        this.isFirst = z;
    }

    public boolean isTyping() {
        return this.isTyping;
    }

    public void setTyping(boolean z) {
        this.isTyping = z;
    }

    public boolean isFromFirebase() {
        return this.isFromFirebase;
    }

    public void setFromFirebase(boolean z) {
        this.isFromFirebase = z;
    }

    public boolean isSplitted() {
        return this.isSplitted;
    }

    public void setSplitted(boolean z) {
        this.isSplitted = z;
    }

    public long getClickedMediaID() {
        return this.clickedMediaID;
    }

    public void setClickedMediaID(long j) {
        this.clickedMediaID = j;
    }

    public String stripHtml(String str) {
        String string;
        if (str == null || str.length() <= 0) {
            return "";
        }
        String strReplace = str.replace(StringUtils.LF, "|*|");
        if (Build.VERSION.SDK_INT >= 24) {
            string = Html.fromHtml(strReplace, 0).toString();
        } else {
            string = Html.fromHtml(strReplace).toString();
        }
        return string.replace("|*|", StringUtils.LF);
    }

    public boolean isReadMoreClick() {
        return this.isReadMoreClick;
    }

    public void setReadMoreClick(boolean z) {
        this.isReadMoreClick = z;
    }
}
