package priv.hy.web.old;

import com.alibaba.fastjson.JSON;
import priv.hy.pojo.Brand;
import priv.hy.service.BrandService;
import priv.hy.service.impl.BrandServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;

//@WebServlet("/addServlet")
public class AddServlet extends HttpServlet {
    private BrandService brandService = new BrandServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        BufferedReader reader = request.getReader();
        String brand = reader.readLine();

        Brand brand1 = JSON.parseObject(brand, Brand.class);

        brandService.add(brand1);


        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write("ok");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
