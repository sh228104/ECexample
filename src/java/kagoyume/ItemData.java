package kagoyume;

import java.io.Serializable;
import java.util.Date;
/**
 *商品情報を格納するBeansクラス
 * @author shohei
 */
public class ItemData implements Serializable {
    private String name;
    private String image;
    private int price;
    private String description;
    private double rate;
    private String itemCode;
    private Date buyDate;
    private int cartID;
    
    public ItemData() {
        name = "";
        image = "";
        price = 0;
        description = "";
        rate = 0;
        itemCode = "";
        buyDate = null;
        cartID = 0;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getRate() {
        return rate;
    }
    public void setRate(double rate) {
        this.rate = rate;
    }
    public String getItemCode() {
        return itemCode;
    }
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }
    public Date getBuyDate() {
        return buyDate;
    }
    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }
    public int getCartID() {
        return cartID;
    }
    public void setCartID(int cartID) {
        this.cartID = cartID;
    }
}
