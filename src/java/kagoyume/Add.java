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
            //フラグ管理
            int loginFlg = 0;
            int cartFlg = 0;
            
            HttpSession hs = request.getSession();
            UserDataDTO loginUser = (UserDataDTO) hs.getAttribute("loginUser");
            
            if (!Objects.equals(loginUser, null)) {
                loginFlg = 1;
            }
            
            ArrayList<ItemData> cartData = new ArrayList<ItemData>();
            
            if (!Objects.equals(hs.getAttribute("cartData"), null)) {
                cartData = (ArrayList<ItemData>) hs.getAttribute("cartData");
                cartFlg = 1;
            }
            //カートに追加する商品情報を読み込む
            ItemData id = (ItemData) hs.getAttribute("itemData");
            
            //ログインしている場合はデータベースに商品情報を登録する
            if (loginFlg == 1) {
                
                UserDataDAO dao = new UserDataDAO();
                
                try {
                    
                    dao.insertCartData(id, loginUser);
                    
                } catch (Exception e) {
                    //何らかの理由で失敗したらエラーページにエラー文を渡して表示。想定は不正なアクセスとDBエラー
                    request.setAttribute("error", e.getMessage());
                    request.getRequestDispatcher("/error.jsp").forward(request, response);
                }
                
            } else {
                cartData.add(id);
                hs.setAttribute("cartData", cartData);
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
