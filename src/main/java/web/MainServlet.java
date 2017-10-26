package web;

import dao.AdminDao;
import dao.AdminDaoImpl;
import dao.CostDao;
import dao.CostImpl;
import entity.Admin;
import entity.Cost;
import util.ImageUtil;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class MainServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取请求路径
        String path = req.getServletPath();
        //根据规范处理路径
        if ("/findCost.do".equals(path)) {
            findCost(req, resp);
        } else if ("/toAddCost.do".equals(path)) {
            toAddCost(req, resp);
        } else if ("/addCost.do".equals(path)) {
            addCost(req, resp);
        } else if ("/toUpdateCost.do".equals(path)) {
            toUpdateCost(req, resp);
        } else if ("/updateCost.do".equals(path)) {
            updateCost(req, resp);
        } else if ("/toDelCost.do".equals(path)) {
            toDelCost(req, resp);
        } else if ("/toLogin.do".equals(path)) {
            toLogin(req, resp);
        } else if ("/toIndex.do".equals(path)) {
            toIndex(req, resp);
        } else if ("/login.do".equals(path)) {
            login(req, resp);
        } else if ("/logout.do".equals(path)) {
            logout(req, resp);
        } else if ("/createImg.do".equals(path)) {
            createImg(req, resp);
        } else {
            //抛异常，由tomcat统一处理
            throw new RuntimeException("查无此页");
        }
    }

    /**
     * 退出登录
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //
        HttpSession session = req.getSession();
        session.invalidate();
        resp.sendRedirect("toLogin.do");
    }

    /**
     * 生成验证码
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void createImg(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //生成验证码和图片
        Object[] objs = ImageUtil.createImage();
        //将验证码存入session
        HttpSession session = req.getSession();
        session.setAttribute("imgCode", objs[0]);
        //将图片发送给浏览器
        //在server下的web.xml里已经规定了输出格式
        resp.setContentType("image/png");
        //获取输出流，该输出流由服务器自动创建，输出目标就是当前访问的浏览器
        OutputStream os = resp.getOutputStream();
        BufferedImage image = (BufferedImage) objs[1];
        ImageIO.write(image, "png", os);
        os.close();
    }

    /**
     * 登陆检查（验证帐号密码验证码）
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1.接收参数
        String adminCode = req.getParameter("adminCode");
        String password = req.getParameter("password");
        String code = req.getParameter("code");
        HttpSession session = req.getSession();
        String imaCode = (String) session.getAttribute("imgCode");
        if (code == null || !code.equalsIgnoreCase(imaCode)) {
            //验证码错误
            req.setAttribute("error", "验证码错误");
            req.getRequestDispatcher("WEB-INF/main/login.jsp").forward(req, resp);
            return;
        }
        //2.业务处理
        AdminDao dao = new AdminDaoImpl();
        Admin a = dao.findByCode(adminCode);
        //3.响应
        if (a == null) {
            //3.1帐号错误
            req.setAttribute("error", "帐号错误");
            req.getRequestDispatcher("WEB-INF/main/login.jsp").forward(req, resp);
        } else if (!a.getPassword().equals(password)) {
            //3.2密码错误
            req.setAttribute("error", "密码错误");
            req.getRequestDispatcher("WEB-INF/main/login.jsp").forward(req, resp);
        } else {
            //3.3检查通过
            /*Cookie c = new Cookie("adminCode", adminCode);
            resp.addCookie(c);*/
            session.setAttribute("adminCode", adminCode);
            //3.4重定向到首页
            resp.sendRedirect("toIndex.do");
        }
    }

    /**
     * 跳转首页
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void toIndex(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1.接收参数
        //2.业务处理
        //3.转发
        req.getRequestDispatcher("WEB-INF/main/index.jsp").forward(req, resp);
    }

    /**
     * 跳转登陆页面
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void toLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1.接收参数
        //2.业务处理
        //3.转发
        req.getRequestDispatcher("WEB-INF/main/login.jsp").forward(req, resp);
    }

    /**
     * 删除资费信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void toDelCost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1.接收参数
        //2.业务处理
        //3.重定向
        resp.sendRedirect("findCost.do");
    }

    /**
     * 保存修改资费信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void updateCost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1.接收参数
        req.setCharacterEncoding("UTF-8");
        String costId = req.getParameter("costId");
        String name = req.getParameter("name");
        String costType = req.getParameter("costType");
        String baseDuration = req.getParameter("baseDuration");
        String baseCost = req.getParameter("baseCost");
        String unitCost = req.getParameter("unitCost");
        String descr = req.getParameter("descr");
        //2.业务处理
        Cost c = new Cost();
        c.setCostId(new Integer(costId));
        c.setName(name);
        c.setCostType(costType);
        //当包月时不需要传值，为避免空指针异常，需加判断
        if (baseDuration != null && !baseDuration.equals("")) {
            c.setBaseDuration(new Integer(baseDuration));
        }
        if (baseCost != null && !baseCost.equals("")) {
            c.setBaseCost(new Double(baseCost));
        }
        if (unitCost != null && !unitCost.equals("")) {
            c.setUnitCost(new Double(unitCost));
        }
        c.setDescr(descr);
        //实例化Dao并保存数据
        CostDao dao = new CostImpl();
        dao.save(c);
        //3.重定向
        resp.sendRedirect("findCost.do");
    }

    /**
     * 跳转修改资费信息页面
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void toUpdateCost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.接收参数
        String id = req.getParameter("id");
        //2.业务处理
        CostDao dao = new CostImpl();
        Cost cost = dao.findById(new Integer(id));
        //3.转发
        req.setAttribute("cost", cost);
        req.getRequestDispatcher("WEB-INF/cost/update.jsp").forward(req, resp);
    }

    /**
     * 保存增加资费信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void addCost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1.接收参数
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String costType = req.getParameter("costType");
        String baseDuration = req.getParameter("baseDuration");
        String baseCost = req.getParameter("baseCost");
        String unitCost = req.getParameter("unitCost");
        String descr = req.getParameter("descr");
        //2.业务处理
        Cost c = new Cost();
        c.setName(name);
        c.setCostType(costType);
        //当包月时不需要传值，为避免空指针异常，需加判断
        if (baseDuration != null && !baseDuration.equals("")) {
            c.setBaseDuration(new Integer(baseDuration));
        }
        if (baseCost != null && !baseCost.equals("")) {
            c.setBaseCost(new Double(baseCost));
        }
        if (unitCost != null && !unitCost.equals("")) {
            c.setUnitCost(new Double(unitCost));
        }
        c.setDescr(descr);
        //实例化Dao并保存数据
        CostDao dao = new CostImpl();
        dao.save(c);
        //3.重定向
        resp.sendRedirect("findCost.do");

    }

    /**
     * 跳转增加资费信息页面
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void toAddCost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1.接收参数
        //2.业务处理
        //3.转发
        req.getRequestDispatcher("WEB-INF/cost/add.jsp").forward(req, resp);

    }

    /**
     * 查询资费信息
     * @param req 请求参数
     * @param resp 响应参数
     * @throws ServletException 异常处理
     * @throws IOException 异常处理
     */
    private void findCost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1.接收参数
        //2.业务处理（查询）
        CostDao dao = new CostImpl();
        List<Cost> list = dao.findAll();
        //3.转发
        //3.1)将数据存到request里
        req.setAttribute("costs", list);
        //3.2)转发
        //当前：netcomss/findCost.do
        //目标：netcomss/WEB-INF/cost/find.jsp
        req.getRequestDispatcher("WEB-INF/cost/find.jsp").forward(req, resp);

    }
}
