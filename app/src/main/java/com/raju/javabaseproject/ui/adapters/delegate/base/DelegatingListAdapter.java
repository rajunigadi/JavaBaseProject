package com.raju.javabaseproject.ui.adapters.delegate.base;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Filter;
import android.widget.Filterable;

import com.raju.javabaseproject.ui.adapters.base.ClickableItemTarget;
import com.raju.javabaseproject.ui.adapters.base.ItemClickListener;
import com.raju.javabaseproject.data.model.ListItem;

import java.util.ArrayList;
import java.util.List;

public abstract class DelegatingListAdapter<T extends ListItem>
        extends DelegatingAdapter<List<T>>
        implements ClickableItemTarget<T>, Filterable {

    protected DelegatingListAdapter() {
        super();
        this.data   = new ArrayList<>();
        this.filteredData   = new ArrayList<>();
    }

    public void removeItems(){
        data.clear();
        filteredData.clear();
        notifyDataSetChanged();
    }

    public void addItem(T item) {
        int pos = getItemCount();
        data.add(item);
        filteredData.add(item);
        notifyItemInserted(pos);
    }

    public void addItems(List<T> items) {
        int size;
        if (items != null && (size = items.size()) > 0) {
            int pos = getItemCount();
            data.addAll(items);
            filteredData.addAll(items);
            notifyItemRangeInserted(pos, size);
        }
    }

    public void refactorItems(List<T> items) {
        data.clear();
        data.addAll(items);

        filteredData.clear();
        filteredData.addAll(items);

        notifyDataSetChanged();
    }

    public @Nullable T getLastItem() {
        int size = filteredData.size();
        if (size > 0) {
            return filteredData.get(size - 1);
        }
        return null;
    }

    @Override
    public void onClick(int position, View v) {
        if(filteredData!=null && filteredData.size() > position)
            listener.onClick(filteredData.get(position), position, v);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString().toLowerCase();
                if (charString.isEmpty()) {
                    filteredData = data;
                } else {
                    List<T> filteredList = new ArrayList<>();
                    /*for (T row : data) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if(row instanceof Team) {
                            Team t = (Team) row;
                            String teamName = t.getTeamName();
                            if (teamName.toLowerCase().contains(charString)) {
                                filteredList.add((T) t);
                            }
                        }
                    }*/

                    filteredData = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredData;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredData = (ArrayList<T>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public int getItemCount() {
        return filteredData.size();
    }

    public void setItemClickListener(ItemClickListener<T> listener) {
        this.listener = listener;
    }

    public final List<T> getItemList() {
        return filteredData;
    }

    protected ItemClickListener<T> listener;
}
