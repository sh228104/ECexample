package kagoyume;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *フォームからの入出力されるデータを格納するBeansオブジェクト
 * DTOからの変換、DTOへの変換を行うメソッドを実装する
 * @author shohei
 */
public class UserData implements Serializable {
    private String name;
    private String password;
    private String mail;
    private String address;
    private int total;
    
    //コンストラクタ
    public UserData(){
        this.name = "";
        this.password = "";
        this.mail = "";
        this.address = "";
        this.total = 0;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        //空文字(未入力)の場合空文字をセット
        if(name.trim().length()==0){
            this.name = "";
        }else{
            this.name = name;
        }
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        if(password.trim().length()==0){
            this.password = "";
        }else{
            this.password = password;
        }
    }

    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        if(mail.trim().length()==0){
            this.mail = "";
        }else{
            this.mail = mail;
        }
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        if(address.trim().length()==0){
            this.address = "";
        }else{
            this.address = address;
        }
    }
    
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        if(total == 0){
            this.total = 0;
        }else{
            this.total = total;
        }
    }
    //不足項目をチェックし、ArrayListで返却するメソッド
    public ArrayList<String> chkproperties(){
        ArrayList<String> chkList = new ArrayList<String>();
        if(this.name.equals("")){
            chkList.add("name");
        }
        if(this.password.equals("")){
            chkList.add("password");
        }
        if(this.mail.equals("")){
            chkList.add("mail");
        }
        if(this.address.equals("")){
            chkList.add("address");
        }
        
        return chkList;
    }
    //UserDataDTOへの変換を行うメソッド
    public void DTOMapping(UserDataDTO udd){
        udd.setName(this.name);
        udd.setPassword(this.password);
        udd.setMail(this.mail);
        udd.setAddress(this.address);
        udd.setTotal(this.total);
    }
    
    //UserDataDTOからの変換を行うメソッド
    public void UDMapping(UserDataDTO udd){
        setName(udd.getName());
        setPassword(udd.getPassword());
        setMail(udd.getMail());
        setAddress(udd.getAddress());
        setTotal(udd.getTotal());
    }
    
}
