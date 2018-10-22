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
public class Cart extends HttpServlet {

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
            //フラグ管理
            int loginFlg = 0;
            
            HttpSession hs = request.getSession();
            //カートの商品情報を格納する配列
            ArrayList<ItemData> cartData = new ArrayList<ItemData>();
            
            //ログインしている場合の処理
            UserDataDTO loginUser = (UserDataDTO) hs.getAttribute("loginUser");
            if (!Objects.equals(loginUser, null)) {
                loginFlg = 1;
                
                try {
                    
                    cartData = UserDataDAO.getInstance().searchCartData(loginUser);
                    
                } catch (Exception e) {
                    //何らかの理由で失敗したらエラーページにエラー文を渡して表示。想定は不正なアクセスとDBエラー
                    request.setAttribute("error", e.getMessage());
                    request.getRequestDispatcher("/error.jsp").forward(request, response);
                }
            }
            //ログインしていない場合の処理
            //セッションにcartDataが保存されている時
            if (loginFlg == 0 && !Objects.equals(hs.getAttribute("cartData"), null)) {
                
                cartData = (ArrayList<ItemData>) hs.getAttribute("cartData");
            
            }
            
            //カート画面からIDをGETで受け取り、カートから商品を削除する処理
            int deleteID = 0;
            
            if (!Objects.equals(request.getParameter("deleteID"), null)) {
                
                deleteID = Integer.parseInt(request.getParameter("deleteID"));
                
                if (loginFlg == 0) {
                    
                    cartData.remove(deleteID);
                
                } else if (loginFlg == 1) {
                    
                    try {
                        
                        UserDataDAO.getInstance().deleteCartData(deleteID);
                        cartData = UserDataDAO.getInstance().searchCartData(loginUser);
                        
                    } catch (Exception e) {
                        //何らかの理由で失敗したらエラーページにエラー文を渡して表示。想定は不正なアクセスとDBエラー
                        request.setAttribute("error", e.getMessage());
                        request.getRequestDispatcher("/error.jsp").forward(request, response);
                    }
                    
                }
            } 
            //cartDataのセッションを上書きする
            hs.setAttribute("cartData", cartData);
            
            request.getRequestDispatcher("/cart.jsp").forward(request, response);
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

