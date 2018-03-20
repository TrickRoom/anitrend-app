package com.mxt.anitrend.adapter.recycler.index;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;

import com.annimon.stream.Stream;
import com.bumptech.glide.Glide;
import com.mxt.anitrend.R;
import com.mxt.anitrend.base.custom.recycler.RecyclerViewAdapter;
import com.mxt.anitrend.base.custom.recycler.RecyclerViewHolder;
import com.mxt.anitrend.databinding.AdapterMangaBinding;
import com.mxt.anitrend.model.entity.anilist.Media;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.OnClick;
import butterknife.OnLongClick;

/**
 * Created by max on 2017/10/30.
 */

public class SeriesMangaAdapter extends RecyclerViewAdapter<Media> {

    public SeriesMangaAdapter(List<Media> data, Context context) {
        super(data, context);
    }

    @Override
    public RecyclerViewHolder<Media> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SeriesViewHolder(AdapterMangaBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String filter = constraint.toString();
                if(filter.isEmpty())
                    data = clone;
                else
                    data = new ArrayList<>(Stream.of(clone).filter((model) -> model.getTitle_english().toLowerCase(Locale.getDefault()).contains(filter) ||
                            model.getTitle_japanese().toLowerCase(Locale.getDefault()).contains(filter) ||
                            model.getTitle_romaji().toLowerCase(Locale.getDefault()).contains(filter)).toList());
                FilterResults results = new FilterResults();
                results.values = data;
                return results;
            }

            @Override @SuppressWarnings("unchecked")
            protected void publishResults(CharSequence constraint, FilterResults results) {
                data = new ArrayList<>((List<Media>) results.values);
                notifyDataSetChanged();
            }
        };
    }

    protected class SeriesViewHolder extends RecyclerViewHolder<Media> {

        private AdapterMangaBinding binding;

        /**
         * Default constructor which includes binding with butter knife
         *
         * @param view
         */
        SeriesViewHolder(AdapterMangaBinding view) {
            super(view.getRoot());
            this.binding = view;
        }

        /**
         * Load image, text, buttons, etc. in this method from the given parameter
         * <br/>
         *
         * @param model Is the model at the current adapter position
         * @see Media
         */
        @Override
        public void onBindViewHolder(Media model) {
            binding.setModel(model);
            binding.executePendingBindings();
        }

        /**
         * If any image views are used within the view holder, clear any pending async img requests
         * by using Glide.clear(ImageView) or Glide.with(context).clear(view) if using Glide v4.0
         * <br/>
         *
         * @see Glide
         */
        @Override
        public void onViewRecycled() {
            Glide.with(getContext()).clear(binding.seriesImage);
            binding.unbind();
        }

        /**
         * Handle any onclick events from our views
         * <br/>
         *
         * @param v the view that has been clicked
         * @see View.OnClickListener
         */
        @Override @OnClick(R.id.container)
        public void onClick(View v) {
            int index;
            if((index = getAdapterPosition()) > -1)
                clickListener.onItemClick(v, data.get(index));
        }

        @Override @OnLongClick(R.id.container)
        public boolean onLongClick(View view) {
            int index;
            if((index = getAdapterPosition()) > -1)
                clickListener.onItemLongClick(view, data.get(index));
            return true;
        }
    }
}
