package kagoyume;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 *JSON全般の処理（文字列の取得やパース処理、変数への格納など）を行うクラス
 * 
 * @author shohei
 */
public class JsonProcessing {
    //Yahooの検索APIから情報を取得する為に必要なフィールド
    private final String itemSearchUrl = "https://shopping.yahooapis.jp/ShoppingWebService/V1/json/itemSearch";
    private final String itemLookupUrl = "https://shopping.yahooapis.jp/ShoppingWebService/V1/json/itemLookup";
    private final String appId = "dj00aiZpPXZSOWNqYXpIa0p1diZzPWNvbnN1bWVyc2VjcmV0Jng9Mzc-";
    private String jsonData = "";
    private ArrayList<String> nameList;
    private ArrayList<String> urlList;
    private ArrayList<String> imageList;
    
    //getterとsetter
    public ArrayList<String> getNameList () {
        return nameList;
    }
    public void setNameList (ArrayList<String> nameList) {
        this.nameList = nameList;
    }
    public ArrayList<String> getUrlList () {
        return urlList;
    }
    public void setUrlList (ArrayList<String> urlList) {
        this.urlList = urlList;
    }
    public ArrayList<String> getImageList () {
        return imageList;
    }
    public void setImageList (ArrayList<String> imageList) {
        this.imageList = imageList;
    }
    public String getJsonText () {
        return jsonData;
    }
    public void setJsonText (String json) {
        this.jsonData = json;
    }
    //YahooのitemSearchAPIからJSON文字列をStringとして取得するメソッド
    public String getSearchJson (String keyword) {
        String json = "";
        String line = "";
        BufferedReader reader = null;
        HttpURLConnection con = null;
        
        try {
            //keywordのエンコード
            keyword = URLEncoder.encode(keyword, "UTF-8");
            //接続するURLを指定する
            URL url = new URL(itemSearchUrl + "?appid=" + appId + "&query=" + keyword );
            //コネクションを取得する
            con = (HttpURLConnection) url.openConnection();
            //リクエストメソッドの設定
            con.setRequestMethod("GET");
            //接続
            con.connect();
            
            /*webページから文字列を取得する
            BufferedReader・・・ファイルを１行ずつ読み込む
            InputStreamReader・・・指定したストリームを指定した文字コードで構成されるテキストファイルとして読み込む*/
            reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            while ((line = reader.readLine()) != null) {
                json += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
    //Json文字列と取得したい商品情報項目を文字列で受け取り、10件の商品情報を取り出してArrayListとして返却するメソッド
    //指定可能・・・Name,Url,Description,Price,Image,その他
    public ArrayList<String> jsonSearchParser (String json, String item) {
        ArrayList<String> x = new ArrayList<String>();
        try {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode node = mapper.readTree(json);
                
                if (item.equals("Image")) {
                    for (int i=0; i < 10; i++) {
                        String a = node.get("ResultSet").get("0").get("Result").get(String.valueOf(i)).get("Image").get("Medium").toString();
                        //取得した文字列の前後に「"」が含まれているので、substringメソッドで除く
                        a = a.substring(1, a.length()-1);
                        x.add(a);
                    }
                } else if (item.equals("Price")) {
                    for (int i=0; i < 10; i++) {
                        String a = node.get("ResultSet").get("0").get("Result").get(String.valueOf(i)).get("Price").get("_value").toString();
                        a = a.substring(1, a.length()-1);
                        x.add(a);
                    }
                } else if (item.equals("Rate")) {
                    for (int i=0; i < 10; i++) {
                        String a = node.get("ResultSet").get("0").get("Result").get(String.valueOf(i)).get("Review").get("Rate").toString();
                        a = a.substring(1, a.length()-1);
                        x.add(a);
                    }
                } else if (item.equals("Count")) {
                    for (int i=0; i < 10; i++) {
                        String a = node.get("ResultSet").get("0").get("Result").get(String.valueOf(i)).get("Review").get("Count").toString();
                        a = a.substring(1, a.length()-1);
                        x.add(a);
                    }
                } else {
                    for (int i=0; i < 10; i++) {
                        String a = node.get("ResultSet").get("0").get("Result").get(String.valueOf(i)).get(item).toString();
                        a = a.substring(1, a.length()-1);
                        x.add(a);
                    } 
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        return x;
    }
    
    //YahooのitemLookupAPIからJSON文字列をStringとして取得するメソッド
    public String getLookupJson (String itemCode) {
        String json = "";
        String line = "";
        BufferedReader reader = null;
        HttpURLConnection con = null;
        
        try {
            //keywordのエンコード
            itemCode = URLEncoder.encode(itemCode, "UTF-8");
            //接続するURLを指定する
            URL url = new URL(itemLookupUrl + "?appid=" + appId + "&itemcode=" + itemCode + "&responsegroup=large");
            //コネクションを取得する
            con = (HttpURLConnection) url.openConnection();
            //リクエストメソッドの設定
            con.setRequestMethod("GET");
            //接続
            con.connect();
            
            /*webページから文字列を取得する
            BufferedReader・・・ファイルを１行ずつ読み込む
            InputStreamReader・・・指定したストリームを指定した文字コードで構成されるテキストファイルとして読み込む*/
            reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            while ((line = reader.readLine()) != null) {
                json += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
    
    //商品コードでの検索結果から情報を取り出してItemDataとして返却するメソッド
    public ItemData jsonLookupParser (String json) {
        
        ItemData id = new ItemData();
        
        try {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode node = mapper.readTree(json);
                    
                    //商品名の取得
                    String name = node.get("ResultSet").get("0").get("Result").get("0").get("Name").toString();
                    name = name.substring(1, name.length()-1);
                    id.setName(name);
                    //画像URLの取得
                    String image = node.get("ResultSet").get("0").get("Result").get("0").get("Image").get("Medium").toString();
                    image = image.substring(1, image.length()-1);
                    id.setImage(image);
                    //説明文の取得
                    String description = node.get("ResultSet").get("0").get("Result").get("0").get("Description").toString();
                    description = description.substring(1, description.length()-1);
                    id.setDescription(image);
                    //値段の取得
                    String price = node.get("ResultSet").get("0").get("Result").get("0").get("Price").get("_value").toString();
                    price = price.substring(1, price.length()-1);
                    id.setPrice(Integer.parseInt(price));
                    //評価の取得
                    String rate = node.get("ResultSet").get("0").get("Result").get("0").get("Review").get("Rate").toString();
                    rate = rate.substring(1, rate.length()-1);
                    id.setRate(Double.parseDouble(rate));
                    
            } catch (IOException e) {
                e.printStackTrace();
            }
        return id;
    }
}
