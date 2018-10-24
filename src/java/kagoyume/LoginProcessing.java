package kagoyume;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *ログイン処理を実装するサーブレット
 * @author shohei
 */
public class LoginProcessing extends HttpServlet {

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
            //フラグ管理
            int cartFlg = 0;
            if (!Objects.equals(hs.getAttribute("cartData"), null)) {
                cartFlg = 1;
            }
            
            try {
                //フォームからの入力を取得してUserDataに格納
                JumsHelper jh = new JumsHelper();
                UserData ud = new UserData();
                
                request.setCharacterEncoding("UTF-8");
                ud.setName(request.getParameter("username"));
                ud.setPassword(request.getParameter("password"));
                
                UserDataDTO loginUser = new UserDataDTO();
                
                if (!ud.getName().equals("")) {
                    
                    UserDataDTO udto = new UserDataDTO();
                    //UserDataからUserDataDTOへマッピング
                    ud.DTOMapping(udto);
                    loginUser = UserDataDAO.getInstance().search(udto);
                    //ログイン成功時の戻り先アドレスを取得する
                    jh = (JumsHelper) hs.getAttribute("jums");
                    //カートの中身の情報を格納するArrayList
                    ArrayList<ItemData> cartData = new ArrayList<ItemData>(); 
                    //ログインに成功した場合の処理
                    if (!Objects.equals(loginUser.getName(), null)) {
                        ArrayList<ItemData> userCartData = UserDataDAO.getInstance().searchCartData(loginUser);
                        //セッション"cartData"が存在しているか、ユーザー毎のカート情報がデータベースに登録されているか否かで処理を分ける
                        //ユーザーのカート情報が無い場合
                        if (Objects.equals(userCartData, null)) {
                            
                            //セッションにカート情報がある場合
                            if (cartFlg == 1) {
                                cartData = (ArrayList<ItemData>) hs.getAttribute("cartData");
                                //カート情報データベースへの挿入処理
                                for (int i=0; i < cartData.size(); i++) {
                                    UserDataDAO.getInstance().insertCartData(cartData.get(i), loginUser);
                                }
                                
                            //セッションにカート情報が無い場合
                            } else {
                                hs.setAttribute("cartData", cartData);
                            }
                        //ユーザーのカート情報がある場合
                        } else {
                            //セッションにカート情報がある場合
                            if (cartFlg == 1) {
                                cartData = (ArrayList<ItemData>) hs.getAttribute("cartData");
                                //セッションで保存していた商品をユーザー固有のカート(データベース)へ追加する
                                for (int i=0; i < cartData.size(); i++) {
                                    UserDataDAO.getInstance().insertCartData(cartData.get(i), loginUser);
                                }
                                hs.removeAttribute("cartData");
                            //セッションにカート情報が無い場合
                            } else {
                                
                            }
                        }
                        //ログイン情報の保存
                        hs.setAttribute("loginUser", loginUser);
                        //以前のページにリダイレクトする
                        response.sendRedirect(jh.getReturnAdd());
                    } else {
                        //ログインに失敗したフラグをセットする
                        jh.setWrongFlg(1);
                        hs.setAttribute("jums", jh);
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    }
                }
                
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
