package gpr.com.gprapplication.fe.activity.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Date;

import gpr.com.gprapplication.R;
import gpr.com.gprapplication.fe.activity.adapter.ReferralListViewAdapter;
import gpr.com.gprapplication.service.asynctask.OutgoingReferralListTask;
import gpr.com.gprapplication.service.datamodel.Referral;
import gpr.com.gprapplication.utility.CredentialManager;
import gpr.com.gprapplication.utility.GPRConstants;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnListFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AnalyticsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AnalyticsFragment extends BaseReferralListFragment {




    private OnListFragmentInteractionListener mListener;


    public AnalyticsFragment() {
        // Required empty public constructor
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static IncomingReferralFragment newInstance(int columnCount) {
        IncomingReferralFragment fragment = new IncomingReferralFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }


//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_ougoing_referral, container, false);
//
//        // Set the adapter
//        if (view instanceof RecyclerView) {
//            Context context = view.getContext();
//            RecyclerView recyclerView = (RecyclerView) view;
//            if (mColumnCount <= 1) {
//                recyclerView.setLayoutManager(new LinearLayoutManager(context));
//            } else {
//                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
//            }
//            recyclerView.setAdapter(listAdapter = new ReferralListViewAdapter(new ArrayList<Referral>(), mListener,false));
//
//            fillIncomingReferralList();
//        }
//        return view;
//    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void fillIncomingReferralList(){
//        ListMetaData md = DataHolder.getIncomingListMetaData();
        OutgoingReferralListTask task = new OutgoingReferralListTask();
        task.setCallback(this);
        Date fetchTime = new Date();
        fetchTime.setYear(1970);
        task.execute(CredentialManager.getInstance().get(getActivity()),
                Long.toString(0), "0",
                Integer.toString(GPRConstants.REFERRAL_LIST_SIZE + 1));
    }




}
