package kagoyume;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import kagoyume.UserDataDAO;

/**
 *
 * @author shohei
 */
public class Myhistory extends HttpServlet {

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
            
            UserDataDAO dao = new UserDataDAO();
            ArrayList<ItemData> codeList = new ArrayList<ItemData>();
            ArrayList<ItemData> itemData = new ArrayList<ItemData>();
            JsonProcessing jp = new JsonProcessing();
            //ユーザーIDで購入履歴の検索を行い、結果をArrayList<ItemData>に全て格納する
            try {
                codeList = dao.searchBuyData(loginUser);
                
                for (int i=0; i < codeList.size(); i++) {
                    String itemCode = codeList.get(i).getItemCode();
                    String json = jp.getLookupJson(itemCode);
                    itemData.add(jp.jsonLookupParser(json));
                }
                
                hs.setAttribute("itemData", itemData);
                request.getRequestDispatcher("/myhistory.jsp").forward(request, response);
                
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
