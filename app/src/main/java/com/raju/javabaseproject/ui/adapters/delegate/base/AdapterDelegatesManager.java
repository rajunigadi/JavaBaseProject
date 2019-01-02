package com.raju.javabaseproject.ui.adapters.delegate.base;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by Rajashekhar Vanahalli on 12/21/17.
 */

public class AdapterDelegatesManager<T> {

    static final int FALLBACK_DELEGATE_VIEW_TYPE = Integer.MAX_VALUE - 1;

    SparseArrayCompat<AdapterDelegate<T>> delegates = new SparseArrayCompat();
    private AdapterDelegate<T> fallbackDelegate;

    public AdapterDelegatesManager<T> addDelegate(@NonNull AdapterDelegate<T> delegate) {
        int viewType = delegates.size();
        while (delegates.get(viewType) != null) {
            viewType++;
            if (viewType == FALLBACK_DELEGATE_VIEW_TYPE) {
                throw new IllegalArgumentException(
                        "Oops, we are very close to Integer.MAX_VALUE. It seems that there are no more free and unused view type integers left to add another AdapterDelegate.");
            }
        }
        return addDelegate(viewType, false, delegate);
    }

    public AdapterDelegatesManager<T> addDelegate(int viewType,
                                                  @NonNull AdapterDelegate<T> delegate) {
        return addDelegate(viewType, false, delegate);
    }

    public AdapterDelegatesManager<T> addDelegate(int viewType, boolean allowReplacingDelegate,
                                                  @NonNull AdapterDelegate<T> delegate) {

        if (viewType == FALLBACK_DELEGATE_VIEW_TYPE) {
            throw new IllegalArgumentException("The view type = "
                    + FALLBACK_DELEGATE_VIEW_TYPE
                    + " is reserved for fallback adapter delegate (see setFallbackDelegate() ). Please use another view type.");
        }

        if (!allowReplacingDelegate && delegates.get(viewType) != null) {
            throw new IllegalArgumentException(
                    "An AdapterDelegate is already registered for the viewType = "
                            + viewType
                            + ". Already registered AdapterDelegate is "
                            + delegates.get(viewType));
        }

        delegates.put(viewType, delegate);

        return this;
    }

    public AdapterDelegatesManager<T> removeDelegate(@NonNull AdapterDelegate<T> delegate) {

        int indexToRemove = delegates.indexOfValue(delegate);

        if (indexToRemove >= 0) {
            delegates.removeAt(indexToRemove);
        }
        return this;
    }

    public AdapterDelegatesManager<T> removeDelegate(int viewType) {
        delegates.remove(viewType);
        return this;
    }

    public int getItemViewType(@NonNull T items, int position) {

        int delegatesCount = delegates.size();
        for (int i = 0; i < delegatesCount; i++) {
            AdapterDelegate<T> delegate = delegates.valueAt(i);
            if (delegate.isForViewType(items, position)) {
                return delegates.keyAt(i);
            }
        }

        if (fallbackDelegate != null) {
            return FALLBACK_DELEGATE_VIEW_TYPE;
        }

        throw new IllegalArgumentException(
                "No AdapterDelegate added that matches position=" + position + " in data source, for item: " + items);
    }

    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AdapterDelegate<T> delegate = delegates.get(viewType);
        if (delegate == null) {
            if (fallbackDelegate == null) {
                throw new NullPointerException("No AdapterDelegate added for ViewType " + viewType);
            } else {
                delegate = fallbackDelegate;
            }
        }

        return delegate.onCreateViewHolder(parent);
    }

    public void onBindViewHolder(@NonNull T items, int position,
                                 @NonNull RecyclerView.ViewHolder viewHolder) {

        getDelegateForViewType(viewHolder.getItemViewType())
                .onBindViewHolder(items, position, viewHolder);
    }

    public AdapterDelegatesManager<T> setFallbackDelegate(
            @Nullable AdapterDelegate<T> fallbackDelegate) {
        this.fallbackDelegate = fallbackDelegate;
        return this;
    }

    public int getViewType(@NonNull AdapterDelegate<T> delegate) {

        int index = delegates.indexOfValue(delegate);
        if (index == -1) {
            return -1;
        }
        return delegates.keyAt(index);
    }

    @NonNull
    public AdapterDelegate<T> getDelegateForViewType(int viewType) {
        AdapterDelegate<T> delegate = delegates.get(viewType);
        if (delegate == null) {
            if (fallbackDelegate == null) {
                throw new NullPointerException(
                        "No AdapterDelegate added for ViewType " + viewType);
            } else {
                delegate = fallbackDelegate;
            }
        }

        return delegate;
    }
}
