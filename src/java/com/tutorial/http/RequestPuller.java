/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tutorial.http;

import com.tutorial.EventHandler.Aevent;
import com.tutorial.app.Main;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.tutorial.enums.Command;
import com.tutorial.enums.ResponseCode;
import com.tutorial.processors.LogoutProcess;
import com.tutorial.processors.RequestProcessorLoginLogout;
import com.tutorial.processors.RequestQueryConvert;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Manoj
 */
public class RequestPuller extends HttpServlet {

    static org.slf4j.Logger logger = LoggerFactory.getLogger(Main.class);

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
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
        try (PrintWriter out = response.getWriter()) {
            Aevent responseEvent = null;
            //command coming from ui that will be hardcoded "cmd" from ui side and this value will be checked accordingly..
            String cmd = request.getParameter("cmd");
            try {
                //get the session object for further use.
                HttpSession hSess = request.getSession();
                //the string value which are appended with the request url that comes from user side need to
                //decode the request string manually...
                String requestqueryStr = request.getQueryString();

                Command usrCommand = Command.ToUserCommand(cmd);
                //for our verification..
                System.out.println("" + RequestQueryConvert.convert(requestqueryStr));
                String reqParams = URLDecoder.decode(request.getQueryString(), "UTF-8");
                HashMap<String, String> requestparams = QueryStringToHashMap(reqParams);
//                JSONObject jobj = new JSONObject(convert(queryStr));
                System.out.println("hSession.." + requestparams.toString());

                if (!Main.IsSessionExist(hSess.getId()) && usrCommand.equals(Command.Login)) {
                    System.out.println("login request validate here..");
                    responseEvent = RequestProcessorLoginLogout.LoginValidateProcess(hSess, requestparams);                    //here we check if the request for login is firsttime then go for login ..
                } else if (!Main.IsSessionExist(hSess.getId()) && !usrCommand.equals(Command.Login)) {
                    switch (usrCommand) {

                        case Logout:
                            responseEvent = LogoutProcess.LogoutProcess(hSess, requestparams);

                            break;
                        case Admin:
                            //we need to send this to admin login page by redirecting method

                            break;
//                    
                        default:
                            //here failed message will be thrown to ui....
                            break;
                    }
                }
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
            }
            String jsonResp = responseEvent.ToJson();
            logger.info(jsonResp);
            out.write(jsonResp);

        }
    }

    public static HashMap<String, String> QueryStringToHashMap(String qrystr) {
        HashMap<String, String> htArgs = new HashMap<>();
        String[] arrArgs = qrystr.split("&");
        for (String paramvalpair : arrArgs) {
            if (paramvalpair.trim().isEmpty()) {
                continue;
            }
            if (paramvalpair.split("=").length > 1) {
                htArgs.put(paramvalpair.split("=")[0].trim(), paramvalpair.split("=")[1]);
            } else {
                htArgs.put(paramvalpair.split("=")[0].trim(), "");
            }
        }
        return htArgs;

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
