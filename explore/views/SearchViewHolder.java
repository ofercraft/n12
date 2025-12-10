package com.channel2.mobile.ui.explore.views;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.explore.models.SearchResultsItem;
import com.squareup.picasso.Picasso;
import java.util.Objects;

/* loaded from: classes2.dex */
public class SearchViewHolder extends SearchRecyclerViewHolder {
    private TextView authorAndDate;
    private TextView caption;
    private ImageView image;
    private TextView subtitle;
    private TextView title;

    public SearchViewHolder(View view) {
        super(view);
        this.title = (TextView) view.findViewById(R.id.title);
        this.subtitle = (TextView) view.findViewById(R.id.subtitle);
        this.caption = (TextView) view.findViewById(R.id.caption);
        this.image = (ImageView) view.findViewById(R.id.image);
        this.authorAndDate = (TextView) view.findViewById(R.id.authorAndDate);
    }

    @Override // com.channel2.mobile.ui.explore.views.SearchRecyclerViewHolder
    public void initial(SearchResultsItem searchResultsItem) {
        this.title.setText(searchResultsItem.getTitle());
        setFontSize(this.title, 20.0f);
        if (searchResultsItem.getPubDate().length() == 0 && searchResultsItem.getAuthor().length() == 0) {
            this.authorAndDate.setVisibility(8);
        } else {
            String str = (searchResultsItem.getPubDate().length() == 0 || searchResultsItem.getAuthor().length() == 0) ? "" : " | ";
            String[] strArrSplit = searchResultsItem.getAuthor().split(", ");
            if (strArrSplit.length == 0) {
                strArrSplit = searchResultsItem.getAuthor().split(",");
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < strArrSplit.length; i++) {
                sb.append("\u200f");
                sb.append(strArrSplit[i]);
                if (i < strArrSplit.length - 1) {
                    sb.append(", ");
                }
            }
            sb.append("\u200f");
            sb.append(str);
            sb.append("\u200f");
            sb.append(searchResultsItem.getPubDate());
            this.authorAndDate.setText(sb);
            this.authorAndDate.setTextDirection(4);
            setFontSize(this.authorAndDate, 12.0f);
            this.authorAndDate.setVisibility(0);
        }
        setFontSize(this.authorAndDate, 12.0f);
        if (searchResultsItem.getPubDate().length() == 0 && searchResultsItem.getAuthor().length() == 0) {
            this.authorAndDate.setVisibility(8);
        }
        if (searchResultsItem.getImage().length() > 0) {
            Picasso.get().load(searchResultsItem.getImage()).placeholder((Drawable) Objects.requireNonNull(ContextCompat.getDrawable(this.image.getContext(), R.drawable.placeholder_ir))).into(this.image);
        }
        this.subtitle.setVisibility(8);
        this.caption.setVisibility(8);
    }
}
