package kagoyume;

import base.DBmanager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *データベース処理系全般をつかさどるクラス
 * データの受け渡しはUserDataDTO経由で行う
 * @author shohei
 */
public class UserDataDAO {
    //インスタンスオブジェクトを返却させてコードの簡略化
    public static UserDataDAO getInstance(){
        return new UserDataDAO();
    }
    
    /**
     * 会員情報データベースへの挿入処理を行う。現在時刻は挿入直前に生成
     * @param ud 対応したデータを保持しているJavaBeans
     * @throws SQLException 呼び出し元にcatchさせるためにスロー 
     */
    public void insertUserData(UserDataDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBmanager.getConnection();
            st =  con.prepareStatement("INSERT INTO user_t(name,password,mail,address,total,newDate) VALUES(?,?,?,?,?,?)");
            st.setString(1, ud.getName());
            st.setString(2, ud.getPassword());
            st.setString(3, ud.getMail());
            st.setString(4, ud.getAddress());
            st.setInt(5, ud.getTotal());
            st.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            st.executeUpdate();
            System.out.println("insert completed");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if(con != null){
                con.close();
            }
        }
    }
    
    /**
     * ユーザー名とパスワードによる会員情報の照合処理を行う
     * deleteFlgの値が1のレコードについては無視する
     * 一致する会員情報がある場合はUserDataDTOにレコードを格納して返却する
     * @param ud 対応したデータを保持しているJavaBeans
     * @throws SQLException 呼び出し元にcatchさせるためにスロー 
     * @return 条件に一致したレコード
     */
    public UserDataDTO search(UserDataDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBmanager.getConnection();
            //SQL文
            String sql = "SELECT * FROM user_t WHERE name = ? AND password = ? AND deleteFlg = ?";
            
            st =  con.prepareStatement(sql);
            st.setString(1, ud.getName());
            st.setString(2, ud.getPassword());
            st.setInt(3, 0);
            
            
            //executeQueryメソッドでSQL文を実行　ResultSetとして返却される
            ResultSet rs = st.executeQuery();
            //検索に一致したデータを格納するUserDataDTO
            UserDataDTO resultUd = new UserDataDTO();
            //データの有無をチェックし、resultUdにセットする
            while(rs.next()) {
                //ResultSetから特定のカラム情報を取得する
                resultUd.setUserID(rs.getInt(1));
                resultUd.setName(rs.getString(2));
                resultUd.setPassword(rs.getString(3));
                resultUd.setMail(rs.getString(4));
                resultUd.setAddress(rs.getString(5));
                resultUd.setTotal(rs.getInt(6));
                resultUd.setNewDate(rs.getTimestamp(7));
                resultUd.setDeleteFlg(rs.getInt(8));
            }
            System.out.println("search completed");
            return resultUd;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if(con != null){
                con.close();
            }
        }
    }
    
    /**
     * データの更新処理を行う。
     * @param ud 対応したデータを保持しているJavaBeans
     * @throws SQLException 呼び出し元にcatchさせるためにスロー 
     */
    public void update(UserDataDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBmanager.getConnection();
            st =  con.prepareStatement("UPDATE user_t SET name=?,password=?,mail=?,address=?,total=? WHERE userID=?");
            st.setString(1, ud.getName());
            st.setString(2, ud.getPassword());//指定のタイムスタンプ値からSQL格納用のDATE型に変更
            st.setString(3, ud.getMail());
            st.setString(4, ud.getAddress());
            st.setInt(5, ud.getTotal());
            st.setInt(6, ud.getUserID());
            //executeUpdateメソッドでSQL文を実行
            st.executeUpdate();
            System.out.println("update completed");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if(con != null){
                con.close();
            }
        }
    }
    
     /**
     * データの削除処理を行う。指定されたuserIDのレコードのdeleteFlgを1に書き換える。
     * @param ud 対応したデータを保持しているJavaBeans
     * @throws SQLException 呼び出し元にcatchさせるためにスロー 
     */
    public void delete(UserDataDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBmanager.getConnection();
            st =  con.prepareStatement("UPDATE user_t SET deleteFlg=? WHERE userID=?");
            st.setInt(1, 1);
            st.setInt(2, ud.getUserID());
            st.executeUpdate();
            System.out.println("delete completed");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if(con != null){
                con.close();
            }
        }
    }
    
    /**
     * 購入情報データベースへの挿入処理を行う。現在時刻は挿入直前に生成
     * @param ud 対応したデータを保持しているJavaBeans
     * @throws SQLException 呼び出し元にcatchさせるためにスロー 
     */
    public void insertBuyData(UserDataDTO ud, ArrayList<ItemData> cartData, int type) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        
        try {
            con = DBmanager.getConnection();
            
            for (int i=0; i < cartData.size(); i++) {
                st =  con.prepareStatement("INSERT INTO buy_t(userID,itemCode,type,buyDate) VALUES(?,?,?,?)");
                st.setInt(1, ud.getUserID());
                st.setString(2, cartData.get(i).getItemCode());
                st.setInt(3, type);
                st.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
                st.executeUpdate();
            }
            System.out.println("insert completed");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if(con != null){
                con.close();
            }
        }
    }
    
    /**
     * ユーザーIDを基に購入情報データベース記録の検索処理を行う
     * 購入情報がある場合は商品コード及び購入日をArrayListに格納して返却する
     * @param ud 対応したデータを保持しているJavaBeans
     * @throws SQLException 呼び出し元にcatchさせるためにスロー 
     * @return 条件に一致したレコード
     */
    public ArrayList<ItemData> searchBuyData(UserDataDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        //検索に一致したデータを格納するItemDataとArrayList
        ArrayList<ItemData> codeList = new ArrayList<ItemData>();
        
        
        try {
            con = DBmanager.getConnection();
            //SQL文
            String sql = "SELECT * FROM buy_t WHERE userID = ?";
            
            st =  con.prepareStatement(sql);
            st.setInt(1, ud.getUserID());
            
            //executeQueryメソッドでSQL文を実行　ResultSetとして返却される
            ResultSet rs = st.executeQuery();
            
            //データの有無をチェックし、codeListにセットする
            while(rs.next()) {
                ItemData id = new ItemData();
                //ResultSetから特定のカラム情報を取得する
                id.setItemCode(rs.getString(3));
                codeList.add(id);
            }
            
            System.out.println("search completed");
            return codeList;
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if(con != null){
                con.close();
            }
        }
    }
    
    /**
     * カート情報データベースへの挿入処理を行う。
     * @param ud 対応したデータを保持しているJavaBeans
     * @throws SQLException 呼び出し元にcatchさせるためにスロー 
     */
    public void insertCartData(ItemData id, UserDataDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        
        try {
            con = DBmanager.getConnection();
            
                st =  con.prepareStatement("INSERT INTO cart_t(userID,itemCode,itemName,itemImage,itemPrice) VALUES(?,?,?,?,?)");
                st.setInt(1, ud.getUserID());
                st.setString(2, id.getItemCode());
                st.setString(3, id.getName());
                st.setString(4, id.getImage());
                st.setInt(5, id.getPrice());
                st.executeUpdate();
                
            System.out.println("insert cart_t completed");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if(con != null){
                con.close();
            }
        }
    }
    
    /**
     * ユーザーIDを基にカート情報データベースの検索処理を行う
     * deleteFlgの値が1のレコードについては無視する
     * 情報がある場合は商品コード・商品名・画像・価格といった情報を持つBeansをArrayListに格納して返却する
     * @param ud 対応したデータを保持しているJavaBeans
     * @throws SQLException 呼び出し元にcatchさせるためにスロー 
     * @return 条件に一致したレコード
     */
    public ArrayList<ItemData> searchCartData(UserDataDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        //検索に一致したデータを格納するItemDataとArrayList
        ArrayList<ItemData> cartData = new ArrayList<ItemData>();
        
        try {
            con = DBmanager.getConnection();
            //SQL文
            String sql = "SELECT * FROM cart_t WHERE userID = ? AND deleteFlg = ?";
            
            st =  con.prepareStatement(sql);
            st.setInt(1, ud.getUserID());
            st.setInt(2, 0);
            
            //executeQueryメソッドでSQL文を実行　ResultSetとして返却される
            ResultSet rs = st.executeQuery();
            
            //データの有無をチェックし、codeListにセットする
            while(rs.next()) {
                //ResultSetから特定のカラム情報を取得し、Beansへ格納する
                ItemData id = new ItemData();
                id.setCartID(rs.getInt(1));
                id.setItemCode(rs.getString(3));
                id.setName(rs.getString(4));
                id.setImage(rs.getString(5));
                id.setPrice(rs.getInt(6));
                cartData.add(id);
            }
            
            System.out.println("search cart_t completed");
            return cartData;
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if(con != null){
                con.close();
            }
        }
    }
    
     /**
     * カート情報データベースの削除処理を行う。指定されたcartIDのレコードのdeleteFlgを1に書き換える。
     * @param deleteID 対応したデータを保持しているJavaBeans
     * @throws SQLException 呼び出し元にcatchさせるためにスロー 
     */
    public void deleteCartData(int deleteID) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBmanager.getConnection();
            st =  con.prepareStatement("UPDATE cart_t SET deleteFlg=? WHERE cartID=?");
            st.setInt(1, 1);
            st.setInt(2, deleteID);
            st.executeUpdate();
            System.out.println("delete cart_t completed");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if(con != null){
                con.close();
            }
        }
    }
}
