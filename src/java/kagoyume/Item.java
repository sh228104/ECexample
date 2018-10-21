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
            try {
                request.setCharacterEncoding("UTF-8");//リクエストパラメータの文字コードをUTF-8に変更
                //GETで取得したIDを格納する
                int userID = Integer.parseInt(request.getParameter("id"));
                //セッション
                HttpSession hs = request.getSession();
                //商品情報を格納するBeans
                ItemData id = new ItemData();
                
                ArrayList<ItemData> cartData = (ArrayList<ItemData>) hs.getAttribute("cartData");
                //カートに商品がある場合と、無い場合で処理を分ける
                if (!Objects.equals(cartData, null)) {
                    id.setName(cartData.get(userID).getName());
                    id.setImage(cartData.get(userID).getImage());
                    id.setPrice(cartData.get(userID).getPrice());
                    id.setDescription(cartData.get(userID).getDescription());
                    id.setRate(cartData.get(userID).getRate());
                    id.setItemCode(cartData.get(userID).getItemCode());
                    hs.setAttribute("itemData", id);
                } else {
                    ArrayList<String> nameList = (ArrayList<String>) hs.getAttribute("nameList");
                    ArrayList<String> imageList = (ArrayList<String>) hs.getAttribute("imageList");
                    ArrayList<String> priceList = (ArrayList<String>) hs.getAttribute("priceList");
                    ArrayList<String> descriptionList = (ArrayList<String>) hs.getAttribute("descriptionList");
                    ArrayList<String> rateList = (ArrayList<String>) hs.getAttribute("rateList");
                    ArrayList<String> itemCodeList = (ArrayList<String>) hs.getAttribute("itemCodeList");

                    id.setName(nameList.get(userID));
                    id.setImage(imageList.get(userID));
                    id.setPrice(Integer.parseInt(priceList.get(userID)));
                    id.setDescription(descriptionList.get(userID));
                    id.setRate(Double.parseDouble(rateList.get(userID)));
                    id.setItemCode(itemCodeList.get(userID));
                    hs.setAttribute("itemData", id);
                }
                
                request.getRequestDispatcher("/item.jsp").forward(request, response);  
            } catch (Exception e) {
                //何らかの理由で失敗したらエラーページにエラー文を渡して表示。想定はDBエラー
                request.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }    
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
