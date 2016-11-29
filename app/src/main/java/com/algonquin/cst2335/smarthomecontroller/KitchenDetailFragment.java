package com.algonquin.cst2335.smarthomecontroller;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.algonquin.cst2335.smarthomecontroller.dummy.DummyContent;

/**
 * A fragment representing a single Kitchen detail screen.
 * This fragment is either contained in a {@link KitchenListActivity}
 * in two-pane mode (on tablets) or a {@link KitchenDetailActivity}
 * on handsets.
 */
public class KitchenDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private String mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public KitchenDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = getArguments().getString(ARG_ITEM_ID);

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle("Kitchen Appliance");
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.kitchen_detail, container, false);

        // Start an intent on click
        if (mItem != null) {
            if (mItem.equals("Lights")) {
                Intent intent = new Intent(getActivity(), KLightsActivity.class);
                startActivity(intent);
            } else if (mItem.equals("Microwave")) {
                Intent intent = new Intent(getActivity(), KMicrowaveActivity.class);
                startActivity(intent);
            } else {
                ((TextView) rootView.findViewById(R.id.kitchen_detail)).setText(mItem);
            }
        }
        return rootView;
    }
}
