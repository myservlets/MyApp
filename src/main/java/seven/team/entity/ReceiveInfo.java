package seven.team.entity;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

/**
 * Created by Administrator on 2019/3/18 0018.
 */
public class ReceiveInfo extends LitePalSupport implements Serializable{

    private String userId;

    private String name;

    private String phone;

    private String address;

    public void setUserId(String userId) { this.userId = userId; }

    public String getUserId() { return userId; }

    public void setAddress(String address) { this.address = address; }

    public String getAddress() { return address; }

    public void setName(String name) {this.name = name;}

    public String getName() {return name;}

    public void setPhone(String phone) {this.phone = phone;}

    public String getPhone() {return phone;}
}
