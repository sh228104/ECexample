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
public class Add extends HttpServlet {

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
            UserDataDTO loginUser = (UserDataDTO) hs.getAttribute("loginUser");
            ItemData id = (ItemData) hs.getAttribute("itemData");
            //カートの中身の情報を一時的に入れるための配列
            ArrayList<ItemData> cartData = new ArrayList<ItemData>();
            //セッション"cartData"がnullか否かで処理を分ける
            if (Objects.equals(hs.getAttribute("cartData"), null)) {
                cartData.add(id);
                hs.setAttribute("cartData", cartData);
            } else if (!Objects.equals(hs.getAttribute("cartData"), null)) {
                cartData = (ArrayList<ItemData>) hs.getAttribute("cartData");
                cartData.add(id);
                hs.setAttribute("cartData", cartData);
            }
            //ログインしている場合、ユーザー固有のカートデータを保存する
            if (!Objects.equals(loginUser, null)) {
                loginUser.setUserCartData(cartData);
                hs.setAttribute("loginUser", loginUser);
            }
            request.getRequestDispatcher("add.jsp").forward(request, response);
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
