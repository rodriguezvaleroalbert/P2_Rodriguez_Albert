package cat.copernic.rodriguez.albert;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import cat.copernic.rodriguez.albert.content.MountainUtils;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private boolean mTwoPane = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mountain_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.mountain_list);
        recyclerView.setAdapter
                (new SimpleItemRecyclerViewAdapter(MountainUtils.MOUNTAIN_ITEMS));
        if (findViewById(R.id.mountain_detail_container) != null) {
            mTwoPane = true;
        }
    }
    class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter
            <SimpleItemRecyclerViewAdapter.ViewHolder> {
        private final List<MountainUtils.Mountain> mValues;
        SimpleItemRecyclerViewAdapter(List<MountainUtils.Mountain> items) {
            mValues = items;
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.mountain_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            holder.mIdView.setText(String.valueOf(position + 1));
            holder.mContentView.setText(mValues.get(position).mountain_title);
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        int selectedMountain = holder.getAdapterPosition();
                        MountainDetailFragment fragment =
                                MountainDetailFragment.newInstance(selectedMountain);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.mountain_detail_container, fragment)
                                .addToBackStack(null)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context,
                                MountainDetailActivity.class);
                        intent.putExtra(MountainUtils.MOUNTAIN_ID_KEY,
                                holder.getAdapterPosition());
                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final View mView;
            final TextView mIdView;
            final TextView mContentView;
            MountainUtils.Mountain mItem;

            ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.id);
                mContentView = (TextView) view.findViewById(R.id.content);
            }
        }
    }
}