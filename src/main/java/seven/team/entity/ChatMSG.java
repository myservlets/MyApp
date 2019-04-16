package seven.team.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatMSG {

    public final static int TYPE_SENT = 0;
    
    public final static int TYPE_RECEIVE = 1;

    private String fromid;

    private String targetid;

    private String content;

    private int type;

    private String date;

    public ChatMSG(String content, int type){
        this.content = content;
        this.type = type;
    }

    public ChatMSG(){}

    public String getContent(){ return content; }

    public void setContent(String content){
        this.content = content;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType(){ return type; }

    public Date getDate() {
        Date date1 = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date1 = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date1;
    }

    public void setDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.date = sdf.format(date);
    }

    public String getFromid() {
        return fromid;
    }

    public void setFromid(String fromid) {
        this.fromid = fromid;
    }

    public String getTargetid() {
        return targetid;
    }

    public void setTargetid(String targetid) {
        this.targetid = targetid;
    }
}
