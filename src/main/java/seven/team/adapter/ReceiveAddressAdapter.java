package seven.team.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import seven.team.activity.PayActivity;
import seven.team.activity.ReceiveAddressActivity;
import seven.team.entity.ReceiveInfo;
import seven.team.activity.NewAddressActivity;
import seven.team.activity.R;
import seven.team.util.ActivityCollector;

/**
 * Created by Administrator on 2019/3/18 0018.
 */
public class ReceiveAddressAdapter extends RecyclerView.Adapter<ReceiveAddressAdapter.ViewHolder> implements View.OnClickListener {

    private List<ReceiveInfo> addressList;
    private int clickPosition;

    public ReceiveAddressAdapter(List<ReceiveInfo>addressList){
        this.addressList = addressList;
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.receive_edit:
                intent = new Intent(v.getContext(), NewAddressActivity.class);
                v.getContext().startActivity(intent);
                break;
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNmae;
        private TextView txtPhone;
        private TextView txtAddress;
        private TextView txtEdit;
        private RelativeLayout layout;
        public ViewHolder(View itemView){ super(itemView);}
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_receive_address,viewGroup,false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.txtNmae = view.findViewById(R.id.receive_name);
        viewHolder.txtPhone = view.findViewById(R.id.receive_phone);
        viewHolder.txtAddress = view.findViewById(R.id.receive_address);
        viewHolder.txtEdit = view.findViewById(R.id.receive_edit);
        viewHolder.layout = view.findViewById(R.id.receive_info_layout);
        if (ActivityCollector.isActivityAlive((Activity)view.getContext())){
            viewHolder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = viewHolder.getAdapterPosition();
                    ReceiveInfo receiveInfo = addressList.get(pos);
                    Intent intent = new Intent();
                    intent.putExtra("address_data",receiveInfo);
                    System.out.println(receiveInfo);
                    ReceiveAddressActivity activity = (ReceiveAddressActivity) v.getContext();
                    activity.setResult(ReceiveAddressActivity.CHOOSED_A_ADDRESS,intent);
                    activity.finish();
                }
            });
        }
        viewHolder.txtEdit.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHodler, int i) {
        ReceiveInfo address = addressList.get(i);
        viewHodler.txtNmae.setText(address.getName());
        viewHodler.txtPhone.setText(address.getPhone());
        //viewHodler.txtAddress.setText(address.getAddress());
    }
    @Override
    public int getItemCount() {
        return addressList.size();
    }


}
