package com.channel2.mobile.ui.outbrain;

import android.util.Log;
import com.channel2.mobile.ui.lobby.models.items.Item;
import com.channel2.mobile.ui.lobby.models.items.ItemType;
import com.channel2.mobile.ui.lobby.models.teasers.LobbyTeaser;
import com.outbrain.OBSDK.Entities.OBRecommendation;
import com.outbrain.OBSDK.Entities.OBRecommendationsResponse;
import com.outbrain.OBSDK.FetchRecommendations.OBRecommendationImpl;
import com.outbrain.OBSDK.FetchRecommendations.OBRequest;
import com.outbrain.OBSDK.FetchRecommendations.RecommendationsListener;
import com.outbrain.OBSDK.Outbrain;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class OutBrainData {
    public static final OutBrainData instance = new OutBrainData();

    public void fetchOutBrainData(OBRequest oBRequest, final ArrayList<LobbyTeaser> arrayList, final OBArrivedListener oBArrivedListener) {
        Outbrain.fetchRecommendations(oBRequest, new RecommendationsListener() { // from class: com.channel2.mobile.ui.outbrain.OutBrainData.1
            @Override // com.outbrain.OBSDK.FetchRecommendations.RecommendationsListener
            public void onOutbrainRecommendationsSuccess(OBRecommendationsResponse oBRecommendationsResponse) {
                ArrayList<Item> arrayList2;
                ArrayList<OBRecommendation> all = oBRecommendationsResponse.getAll();
                if (all != null && all.size() > 0) {
                    ArrayList arrayList3 = arrayList;
                    int i = 0;
                    if (arrayList3 == null) {
                        arrayList2 = new ArrayList<>();
                        while (i < all.size()) {
                            LobbyTeaser lobbyTeaser = new LobbyTeaser();
                            OBRecommendation oBRecommendation = all.get(i);
                            if (i == 0) {
                                lobbyTeaser.setLobbyItemType(ItemType.regularitembigovertext);
                            } else {
                                lobbyTeaser.setLobbyItemType(ItemType.regularItemSmall);
                            }
                            OutBrainData.this.setItem(lobbyTeaser, oBRecommendation);
                            arrayList2.add(lobbyTeaser);
                            i++;
                        }
                    } else {
                        int iMin = Math.min(arrayList3.size(), all.size());
                        while (i < iMin) {
                            OutBrainData.this.setItem((LobbyTeaser) arrayList.get(i), all.get(i));
                            i++;
                        }
                        arrayList2 = null;
                    }
                    oBArrivedListener.onOBReady(oBRecommendationsResponse.getRequest().getWidgetId(), arrayList2);
                    return;
                }
                oBArrivedListener.onFailed();
            }

            @Override // com.outbrain.OBSDK.FetchRecommendations.RecommendationsListener
            public void onOutbrainRecommendationsFailure(Exception exc) {
                oBArrivedListener.onFailed();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setItem(LobbyTeaser lobbyTeaser, OBRecommendation oBRecommendation) {
        lobbyTeaser.setTeaserVcmId(String.valueOf(System.currentTimeMillis()));
        lobbyTeaser.setTitle(oBRecommendation.getContent());
        Log.d("checkOutBrainTitle", oBRecommendation.getContent());
        lobbyTeaser.setSubTitle("");
        lobbyTeaser.setImage(oBRecommendation.getThumbnail().getUrl());
        if (oBRecommendation.isPaid()) {
            lobbyTeaser.setAuthor(oBRecommendation.getSourceName());
            lobbyTeaser.setDate("ממומן");
        } else {
            lobbyTeaser.setAuthor("");
            String audienceCampaignsLabel = ((OBRecommendationImpl) oBRecommendation).getAudienceCampaignsLabel();
            lobbyTeaser.setDate(audienceCampaignsLabel != null ? audienceCampaignsLabel : "");
            Log.d("checkOutBrain", "label: " + audienceCampaignsLabel);
        }
        lobbyTeaser.setClickUrl(((OBRecommendationImpl) oBRecommendation).getUrl());
        lobbyTeaser.setOutBrain(true);
        lobbyTeaser.setOutbrainRec(oBRecommendation);
    }
}
