package kagoyume;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author shohei
 */
public class Item extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            //セッション
            HttpSession hs = request.getSession();
            
            int itemID = Integer.parseInt(request.getParameter("id"));
            //フラグ管理
            int cartFlg = 0;
            int loginFlg = 0;
            if (!Objects.equals(request.getParameter("cartFlg"), null)) {
                cartFlg = 1;
            }
            UserDataDTO loginUser = (UserDataDTO) hs.getAttribute("loginUser");
            if (!Objects.equals(loginUser, null)) {
                loginFlg = 1;
            }
            
            //商品情報を保管するためのBeans
            ItemData id = new ItemData();
            
            //カート画面から遷移してきた場合の処理
            if (cartFlg == 1) {
                //ログインしている場合
                if (loginFlg == 1) {
                    
                    try {
                        
                            ArrayList<ItemData> cartData = UserDataDAO.getInstance().searchCartData(loginUser);
                            id = cartData.get(itemID);
                            hs.setAttribute("itemData", id);
                        
                        } catch (Exception e) {
                            //何らかの理由で失敗したらエラーページにエラー文を渡して表示。想定はDBエラー
                            request.setAttribute("error", e.getMessage());
                            request.getRequestDispatcher("/error.jsp").forward(request, response); 
                        }
                    
                } else {
                    ArrayList<ItemData> cartData = new ArrayList<ItemData>();
                    if (!Objects.equals(hs.getAttribute("cartData"), null)) {
                        cartData = (ArrayList<ItemData>) hs.getAttribute("cartData");
                    }
                    
                    id = cartData.get(itemID);
                    hs.setAttribute("itemData", id);
                }
                
            }
            //検索結果画面から遷移してきた場合の処理
            if (cartFlg == 0) {
                
                ArrayList<String> nameList = (ArrayList<String>) hs.getAttribute("nameList");
                ArrayList<String> imageList = (ArrayList<String>) hs.getAttribute("imageList");
                ArrayList<String> priceList = (ArrayList<String>) hs.getAttribute("priceList");
                ArrayList<String> descriptionList = (ArrayList<String>) hs.getAttribute("descriptionList");
                ArrayList<String> rateList = (ArrayList<String>) hs.getAttribute("rateList");
                ArrayList<String> itemCodeList = (ArrayList<String>) hs.getAttribute("itemCodeList");
                
                id.setName(nameList.get(itemID));
                id.setImage(imageList.get(itemID));
                id.setPrice(Integer.parseInt(priceList.get(itemID)));
                id.setDescription(descriptionList.get(itemID));
                id.setRate(Double.parseDouble(rateList.get(itemID)));
                id.setItemCode(itemCodeList.get(itemID));
                
                hs.setAttribute("itemData", id);
            }
            //RequestDispatcher
            request.getRequestDispatcher("/item.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
