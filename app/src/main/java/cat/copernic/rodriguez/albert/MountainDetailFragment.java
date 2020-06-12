package cat.copernic.rodriguez.albert;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cat.copernic.rodriguez.albert.content.MountainUtils;

public class MountainDetailFragment extends Fragment {
    public MountainUtils.Mountain mMountain;
    public MountainDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(MountainUtils.MOUNTAIN_ID_KEY)) {

            mMountain = MountainUtils.MOUNTAIN_ITEMS.get(getArguments()
                    .getInt(MountainUtils.MOUNTAIN_ID_KEY));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.mountain_detail,
                container, false);
        if (mMountain != null) {
            ((TextView) rootView.findViewById(R.id.mountain_detail))
                    .setText(mMountain.details);
        }
        return rootView;
    }
    public static MountainDetailFragment newInstance (int selectedMountain) {
        MountainDetailFragment fragment = new MountainDetailFragment();
        // Set the bundle arguments for the fragment.
        Bundle arguments = new Bundle();
        arguments.putInt(MountainUtils.MOUNTAIN_ID_KEY, selectedMountain);
        fragment.setArguments(arguments);
        return fragment;
    }
}