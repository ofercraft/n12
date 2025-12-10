package com.channel2.mobile.ui.programs.controllers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.channel2.mobile.ui.R;
import com.channel2.mobile.ui.programs.models.ProgramsItem;
import com.channel2.mobile.ui.programs.views.ProgramsViewHolder;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class ListAdapter extends RecyclerView.Adapter<ProgramsViewHolder> {
    private ClickHandler handler;
    private ArrayList<ProgramsItem> items;

    public interface ClickHandler {
        void onClicked(ProgramsItem programsItem);
    }

    public ListAdapter(ArrayList<ProgramsItem> arrayList, ClickHandler clickHandler) {
        this.items = arrayList;
        this.handler = clickHandler;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ProgramsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ProgramsViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.programs_item_view, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ProgramsViewHolder programsViewHolder, final int i) {
        programsViewHolder.initial(this.items.get(i));
        programsViewHolder.overlay.setOnClickListener(new View.OnClickListener() { // from class: com.channel2.mobile.ui.programs.controllers.ListAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ListAdapter.this.handler.onClicked((ProgramsItem) ListAdapter.this.items.get(i));
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.items.size();
    }
}
