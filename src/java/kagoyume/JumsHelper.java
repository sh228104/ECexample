package kagoyume;

import java.util.ArrayList;

/**
 *
 * @author shohei
 */
public class JumsHelper {
    
    //トップへのリンクを定数として設定
    private final String homeURL = "top.jsp";
    //検索キーワードを保存する
    private String searchKeyword = "";
    //ログイン完了後の戻り先URLを保存
    private String returnAddress = "";
    //ユーザ名又はパスワードが間違っていた場合、警告文を表示する為のフラグ
    private int wrongFlg = 0;
    //インスタンス
    public static JumsHelper getInstance(){
        return new JumsHelper();
    }
    //getterとsetter
    public String getSearchKeyword() {
        return searchKeyword;
    }
    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }
    public String getReturnAdd() {
        return returnAddress;
    }
    public void setReturnAdd(String returnAdd) {
        this.returnAddress = returnAdd;
    }
    public int getWrongFlg() {
        return wrongFlg;
    }
    public void setWrongFlg(int wrongFlg) {
        this.wrongFlg = wrongFlg;
    }
    //トップへのリンクを返却する
    public String home(){
        return "<a href=\""+homeURL+"\">トップへ戻る</a>";
    }
    
    /**
     * 入力されたデータのうち未入力項目がある場合、チェックリストにしたがいどの項目が
     * 未入力なのかのhtml文を返却する
     * @param chkList　UserDataで生成されるリスト。未入力要素の名前が格納されている
     * @return 未入力の項目に対応する文字列
     */
    public String chkinput(ArrayList<String> chkList){
        String output = "";
        for(String val : chkList){
                if(val.equals("name")){
                    output += "名前";
                }
                if(val.equals("password")){
                    output += "パスワード";
                }
                if(val.equals("mail")){
                    output += "メールアドレス";
                }
                if(val.equals("address")){
                    output += "住所";
                }
                output += "が未記入です<br>";
            }
        return output;
    }
    
    /**
     * 発送方法は数字で取り扱っているので画面に表示するときは日本語に変換
     * @param i
     * @return 
     */
    public String exTypenum(int i){
        switch(i){
            case 1:
                return "ヤマト";
            case 2:
                return "佐川";
            case 3:
                return "日本郵便";
        }
        return "";
    }
}
