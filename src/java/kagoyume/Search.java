package kagoyume;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author shohei
 */
public class Search extends HttpServlet {

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
            JsonProcessing jp = new JsonProcessing();
            JumsHelper jh = new JumsHelper();
            //リクエストパラメータの文字コードをUTF-8に変更
            request.setCharacterEncoding("UTF-8");
            //検索ワードをリクエストから取得して格納する
            String keyword = request.getParameter("keyword");
            
            //セッション
            HttpSession hs = request.getSession();
            
            jp.setJsonText(jp.getSearchJson(keyword));
            
            jh.setSearchKeyword(keyword);
            
            ArrayList<String> nameList = jp.jsonSearchParser(jp.getJsonText(), "Name");
            ArrayList<String> imageList = jp.jsonSearchParser(jp.getJsonText(), "Image");
            ArrayList<String> priceList = jp.jsonSearchParser(jp.getJsonText(), "Price");
            ArrayList<String> descriptionList = jp.jsonSearchParser(jp.getJsonText(), "Description");
            ArrayList<String> rateList = jp.jsonSearchParser(jp.getJsonText(), "Rate");
            ArrayList<String> itemCodeList = jp.jsonSearchParser(jp.getJsonText(), "Code");
            
            //セッションスコープ経由で検索結果を送信する
            hs.setAttribute("nameList", nameList);
            hs.setAttribute("imageList", imageList);
            hs.setAttribute("priceList", priceList);
            hs.setAttribute("descriptionList", descriptionList);
            hs.setAttribute("rateList", rateList);
            hs.setAttribute("itemCodeList", itemCodeList);
            hs.setAttribute("jums", jh);
            //RequestDispatcher
            request.getRequestDispatcher("/search.jsp").forward(request, response);
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
