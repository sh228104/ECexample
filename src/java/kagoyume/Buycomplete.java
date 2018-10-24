package kagoyume;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *
 * @author shohei
 */
public class Buycomplete extends HttpServlet {

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
            int type = Integer.parseInt(request.getParameter("type"));
            int totalPrice = 0;
            //セッション
            HttpSession hs = request.getSession();
            
            ArrayList<ItemData> cartData = (ArrayList<ItemData>) hs.getAttribute("userCartData");
            UserDataDTO loginUser = (UserDataDTO) hs.getAttribute("loginUser");
            UserDataDAO dao = new UserDataDAO();
            
            try {
                //DBへデータの挿入
                dao.insertBuyData(loginUser, cartData, type);
                //ユーザーの総購入金額を計算する
                for (int i=0; i < cartData.size(); i++) {
                    totalPrice += cartData.get(i).getPrice();
                }
                totalPrice += loginUser.getTotal();
                //セッションを更新
                loginUser.setTotal(totalPrice);
                hs.setAttribute("loginUser", loginUser);
                //データベースの総購入金額を更新する
                dao.update(loginUser);
                //成功したので、カート情報データベースのレコードを削除する
                for (int i=0; i < cartData.size(); i++) {
                    dao.deleteCartData(cartData.get(i).getCartID());
                }
                //成功したので、余計なセッションを破棄する
                hs.removeAttribute("cartData");
                
                request.getRequestDispatcher("/buycomplete.jsp").forward(request, response);
            } catch (Exception e) {
                //何らかの理由で失敗したらエラーページにエラー文を渡して表示。想定は不正なアクセスとDBエラー
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
