package priv.hy.web.servlet;

import com.alibaba.fastjson.JSON;
import priv.hy.pojo.Brand;
import priv.hy.pojo.PageBean;
import priv.hy.service.BrandService;
import priv.hy.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet {
    private BrandService brandService = new BrandServiceImpl();

    /**
     * 查询所有
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        List<Brand> brands = brandService.selectAll();

        String jsonString = JSON.toJSONString(brands);

        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    /**
     * 添加
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("utf-8");

        BufferedReader reader = request.getReader();
        String brand = reader.readLine();

        Brand brand1 = JSON.parseObject(brand, Brand.class);

        brandService.add(brand1);


        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write("ok");
    }

    /**
     * 根据id查询（数据回显）
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        BufferedReader reader = request.getReader();
        String id = reader.readLine();


        Brand brand = brandService.selectById(Integer.parseInt(id));

        String jsonString = JSON.toJSONString(brand);


        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    /**
     * 修改
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("utf-8");

        BufferedReader reader = request.getReader();
        String brand = reader.readLine();

        Brand brand1 = JSON.parseObject(brand, Brand.class);

        System.out.println(brand1);

        brandService.update(brand1);

        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write("ok");
    }

    /**
     * 删除一个
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("utf-8");

        BufferedReader reader = request.getReader();
        String id = reader.readLine();

        brandService.deleteById(Integer.parseInt(id));

        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write("ok");
    }

    /**
     * 批量删除
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");

        BufferedReader reader = request.getReader();
        String params = reader.readLine();

        int[] ids = JSON.parseObject(params, int[].class);

        System.out.println(Arrays.toString(ids));
        brandService.deleteByIds(ids);

        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write("ok");
    }

    /**
     * 分页查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //接收参数 当前页码和每页展示条数
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        PageBean<Brand> pageBean = brandService.selectByPage(currentPage, pageSize);

        String jsonString = JSON.toJSONString(pageBean);

        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    /**
     * 多条件查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //接收参数 当前页码和每页展示条数
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        //获取对应的查询条件对象
        BufferedReader reader = request.getReader();
        String params = reader.readLine();

        System.out.println(currentPage);
        System.out.println(pageSize);
        System.out.println(params);

        Brand brand = JSON.parseObject(params, Brand.class);

        System.out.println(brand);

        PageBean<Brand> pageBean = brandService.selectByPageAndCondition(currentPage, pageSize,brand);

        String jsonString = JSON.toJSONString(pageBean);

        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);

    }
}

