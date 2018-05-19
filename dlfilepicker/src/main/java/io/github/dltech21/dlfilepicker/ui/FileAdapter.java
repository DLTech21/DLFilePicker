package io.github.dltech21.dlfilepicker.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import io.github.dltech21.dlfilepicker.R;
import io.github.dltech21.dlfilepicker.model.FileItem;

/**
 * Created by donal on 2018/4/25.
 */

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.ViewHolder> {

    private Context context;
    private List<FileItem> mData;

    public FileAdapter(Context context, List<FileItem> mData) {
        this.context = context;
        this.mData = mData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = null;
        if (holder == null) {
            holder = new ViewHolder(LayoutInflater.from(
                    context).inflate(R.layout.item_file, parent,
                    false));
        } else {
            holder = (ViewHolder) parent.getTag();
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvName.setText(mData.get(position).getFileName());
        holder.tvPath.setText(mData.get(position).getFilePath());
        holder.tvSize.setText(mData.get(position).getFileSize());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName, tvPath, tvSize;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvPath = (TextView) itemView.findViewById(R.id.tv_path);
            tvSize = (TextView) itemView.findViewById(R.id.tv_size);
        }

    }
}
