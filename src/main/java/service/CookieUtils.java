package service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {
    public static void addCookies(HttpServletResponse resp, Integer id){
        Cookie userCookie = new Cookie("user_id", String.valueOf(id));
        userCookie.setMaxAge(60*60);
        resp.addCookie(userCookie);
    }
    public static void deleteCookies(HttpServletRequest req, HttpServletResponse resp){
        for (Cookie cookie: req.getCookies()) {
            if (cookie != null) {
                cookie.setValue("");
                cookie.setPath("/");
                cookie.setMaxAge(0);
                resp.addCookie(cookie);
            }
        }
    }
    public static String getCookies(HttpServletRequest req){
        String userId = "";
        Cookie[] cookies = req.getCookies();
        for(Cookie cookie : cookies){
            if( cookie.getName().equals("user_id")){
                userId = cookie.getValue();
            }
        }
        return userId;
    }
}
