package org.nt.filter;

import org.nt.action.SaveProductAction;
import org.nt.form.ProductForm;
import org.nt.model.Product;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * 使用Filter来充当控制器
 */
// 这个注解相当于在web.xml文件中写了配置信息
@WebFilter(filterName = "DispatcherFilter", urlPatterns = "/*")
public class DispatcherFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // ServletRequest对象的功能不够使用
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String uri = request.getRequestURI();
        // 获取资源文件名
        String action = uri.substring(uri.lastIndexOf("/") + 1);
        String dispatchUrl = null;
        if ("input-product".equals(action)) {
            dispatchUrl = "/jsp/ProductForm.jsp";
        } else if ("save-product".equals(action)) {
            ProductForm productForm = new ProductForm();
            productForm.setName(request.getParameter("name"));
            productForm.setPrice(request.getParameter("price"));
            productForm.setDescription(request.getParameter("description"));
            Product product = new Product();
            product.setName(productForm.getName());
            product.setPrice(new BigDecimal(request.getParameter("price")));
            product.setDescription(request.getParameter("description"));
            SaveProductAction saveProductAction = new SaveProductAction();
            saveProductAction.save(product);
            dispatchUrl = "/jsp/ProductDetail.jsp";
            // 便于在JSP中使用EL表达式访问product对象
            request.setAttribute("product", product);
        }

        if (dispatchUrl != null) {
            request.getRequestDispatcher(dispatchUrl).forward(request, servletResponse);
        } else {
            filterChain.doFilter(request, servletResponse);
        }

    }

    public void destroy() {
        System.out.println("destroy");
    }
}
