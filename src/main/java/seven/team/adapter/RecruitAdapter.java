package seven.team.adapter;


import seven.team.entity.Recruitment;
import seven.team.activity.R;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class RecruitAdapter extends RecyclerView.Adapter<RecruitAdapter.ViewHolder>{
    private List<Recruitment> rList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView rImage;
        private TextView rName;
        private TextView rInfo;
        private TextView tel;
        private TextView hotNum;
        private TextView tranNum;
        private LinearLayout rItem;

        public ViewHolder(View itemView) {
            super(itemView);

            rImage = itemView.findViewById(R.id.recruitment_image);
            rName = itemView.findViewById(R.id.recruitment_name);
            rInfo = itemView.findViewById(R.id.recruitment_info);
            tel = itemView.findViewById(R.id.recruitment_chat);
            hotNum = itemView.findViewById(R.id.hot_num);
            tranNum = itemView.findViewById(R.id.tran_num);
            rItem = itemView.findViewById(R.id.recruitment_item);

        }
    }

    public RecruitAdapter(List<Recruitment> rList){
        this.rList = rList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_recruitment,viewGroup,false);
        RecruitAdapter.ViewHolder holder = new RecruitAdapter.ViewHolder(view);
//        holder.rItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int position = holder.getAdapterPosition();
//                Recruitment opposeRecruitment = rList.get(position);
//                Intent intent = new Intent(view.getContext(), RecruitmentActivity.class);
//                intent.putExtra("opposeRecruitment", opposeRecruitment);
//                view.getContext().startActivity(intent);
//            }
//        });
        return holder;
    }



    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Recruitment recruitment = rList.get(i);

        //TODO
        // 动态加载 private ImageView rImage;


        viewHolder.rName.setText(recruitment.getName());
        viewHolder.rInfo.setText(recruitment.getDescribe());
        // viewHolder.hotNum.setText(product.getHot());
        // viewHolder.tranNum.setText(product.getTransmit());

    }

    @Override
    public int getItemCount() {
            return rList.size();
    }
}
