package seven.team.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import seven.team.activity.R;
import seven.team.entity.Recruitment;

import java.util.List;

public class RecruitAdapter extends RecyclerView.Adapter<RecruitAdapter.ViewHolder>{
    private List<Recruitment> rList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView jobName;
        private TextView jobPay;
        private TextView compName;
        private TextView compAddress;
        private TextView expRequirement;
        private TextView eduRequirement;
        private LinearLayout rItem;

        public ViewHolder(View itemView) {
            super(itemView);

            jobName = itemView.findViewById(R.id.job_name);
            jobPay = itemView.findViewById(R.id.job_pay);
            compName = itemView.findViewById(R.id.company_name);
            compAddress = itemView.findViewById(R.id.company_address);
            expRequirement = itemView.findViewById(R.id.experience_requirement);
            eduRequirement = itemView.findViewById(R.id.education_requirement);
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
        ViewHolder holder = new ViewHolder(view);
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


        viewHolder.jobName.setText(recruitment.getJobName());
        viewHolder.jobPay.setText(recruitment.getJobPay());
        viewHolder.compName.setText(recruitment.getCompanyName());
        viewHolder.expRequirement.setText(recruitment.getExperienceRequirement());
        // viewHolder.hotNum.setText(product.getHot());
        // viewHolder.tranNum.setText(product.getTransmit());

    }

    @Override
    public int getItemCount() {
            return rList.size();
    }
}
