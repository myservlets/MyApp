package seven.team.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import seven.team.entity.ReceiveInfo;
import seven.team.activity.NewAddressActivity;
import seven.team.activity.R;

/**
 * Created by Administrator on 2019/3/18 0018.
 */
public class ReceiveAddressAdapter extends RecyclerView.Adapter<ReceiveAddressAdapter.ViewHolder>{

    private List<ReceiveInfo> addressList;

    public ReceiveAddressAdapter(List<ReceiveInfo>addressList){
        this.addressList = addressList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txtNmae;
        private TextView txtPhone;
        private TextView txtAddress;
        private TextView txtEdit;

        public ViewHolder(View itemView){
            super(itemView);
            txtNmae = itemView.findViewById(R.id.receive_name);
            txtPhone = itemView.findViewById(R.id.receive_phone);
            txtAddress = itemView.findViewById(R.id.receive_address);
            txtEdit = itemView.findViewById(R.id.receive_edit);
            txtEdit.setOnClickListener(this);
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
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_receive_address,viewGroup,false);
        return new ViewHolder(view);
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
