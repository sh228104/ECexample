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
            
            
            HttpSession hs = request.getSession();
            //カート画面で商品情報を持ち回るBeans
            ArrayList<ItemData> cartData = (ArrayList<ItemData>) hs.getAttribute("cartData");
            //ログインしている場合、ユーザーのデータを読み込む
            UserDataDTO loginUser = (UserDataDTO) hs.getAttribute("loginUser");
            //カート画面からIDをGETで受け取り、カートから商品を削除する処理
            int deleteID = 0;
            
            if (!Objects.equals(request.getParameter("deleteID"), null) && !Objects.equals(cartData, null)) {
                deleteID = Integer.parseInt(request.getParameter("deleteID"));
                cartData.remove(deleteID);
                hs.setAttribute("cartData", cartData);
                
                if (!Objects.equals(loginUser, null)) {
                    loginUser.setUserCartData(cartData);
                    hs.setAttribute("loginUser", loginUser);
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

