package com.channel2.mobile.ui.lobby.models.firstReport;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder;
import com.channel2.mobile.ui.customViews.CustomTextView;
import com.channel2.mobile.ui.lobby.controllers.LobbyFragmentHandler;
import com.channel2.mobile.ui.lobby.models.items.Item;
import java.util.Objects;

/* loaded from: classes2.dex */
public class TeaserFirstReport extends CustomRecyclerViewHolder {
    private CustomTextView body;
    private ImageView close;
    private View itemView;
    private int itemViewHeight;
    private CustomTextView more;
    private CustomTextView title;

    @Override // com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void onScrollStateIdle() {
    }

    public TeaserFirstReport(View view) {
        super(view);
        this.itemViewHeight = 0;
        this.itemView = view;
        this.close = (ImageView) view.findViewById(R.id.close);
        this.title = (CustomTextView) view.findViewById(R.id.title);
        this.body = (CustomTextView) view.findViewById(R.id.body);
        this.more = (CustomTextView) view.findViewById(R.id.more);
    }

    @Override // com.channel2.mobile.ui.customViews.CustomRecyclerViewHolder, com.channel2.mobile.ui.lobby.controllers.CustomRecyclerViewInterface
    public void setViewResources(Item item, LobbyFragmentHandler lobbyFragmentHandler) {
        super.setViewResources(item, lobbyFragmentHandler);
        LobbyFirstReport lobbyFirstReport = (LobbyFirstReport) item;
        CustomTextView customTextView = this.title;
        String title = lobbyFirstReport.getTitle();
        Objects.requireNonNull(this.title);
        customTextView.setHebText(title, "fonts/YonitMedium_v2.ttf");
        setFontSize(this.title, 18.0f);
        CustomTextView customTextView2 = this.body;
        String text = lobbyFirstReport.getText();
        Objects.requireNonNull(this.body);
        customTextView2.setHebText(text, "fonts/YonitMedium_v2.ttf");
        setFontSize(this.body, 16.0f);
        CustomTextView customTextView3 = this.more;
        String more_details_text = lobbyFirstReport.getMore_details_text();
        Objects.requireNonNull(this.more);
        customTextView3.setHebText(more_details_text, "fonts/YonitMedium_v2.ttf");
        setFontSize(this.more, 16.0f);
        this.itemView.post(new Runnable() { // from class: com.channel2.mobile.ui.lobby.models.firstReport.TeaserFirstReport.1
            @Override // java.lang.Runnable
            public void run() {
                TeaserFirstReport teaserFirstReport = TeaserFirstReport.this;
                teaserFirstReport.itemViewHeight = teaserFirstReport.itemView.getHeight();
            }
        });
        this.close.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.lobby.models.firstReport.TeaserFirstReport.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                final ViewGroup.LayoutParams layoutParams = TeaserFirstReport.this.itemView.getLayoutParams();
                ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(TeaserFirstReport.this.itemViewHeight, 0);
                valueAnimatorOfInt.setDuration(300);
                valueAnimatorOfInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.channel2.mobile.ui.lobby.models.firstReport.TeaserFirstReport.2.1
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        layoutParams.height = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                        TeaserFirstReport.this.itemView.requestLayout();
                    }
                });
                valueAnimatorOfInt.start();
            }
        });
    }
}
