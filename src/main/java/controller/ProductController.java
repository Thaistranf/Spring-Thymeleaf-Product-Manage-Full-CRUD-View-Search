package controller;

import Service.ProductService;
import model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    ProductService productService;

    public ProductController(){
        productService = new ProductService();
    }

    @GetMapping("/list")
    //ModelAndView => tra ve du lieu va giao dien, cu show giao dien thi su dung ham nay
    public ModelAndView showList(){
        //Tao 1 doi tuong list de lay ve ds o productService
        List<Product> productList = productService.productList();
        //ModelAndView => tra ve du lieu va giao dien tu list.html
        ModelAndView modelAndView = new ModelAndView("/list");
        //Tao 1 doi tuong products de luu du lieu productList
        modelAndView.addObject("products", productList);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showFormCreate(){
        ModelAndView modelAndView = new ModelAndView("/create");
        //Moi lan them 1 product moi se la 1 lan new Product()
        modelAndView.addObject("newProduct", new Product());
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(Product product){
        //Goi ra ham add o class productService
        productService.add(product);
        //Sau khi them product vao list thi tu dong quay ve ds nen chon KDL String
        return ("redirect:/product/list");
    }

    @GetMapping("/update/{id}")
    public ModelAndView showFormUpdate(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("/update");
        //Tao 1 doi tuong product duoc tim theo id
        Product product = productService.findById(id);
        //Moi lan tao moi 1 product se la 1 lan new Product()
        modelAndView.addObject("productUp", product);
        return modelAndView;
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable int id, Product product){
        //Goi ra ham update o class productService
        //Do id tu dong random nen ko phai nhap id ma se lay luon id cua product
        productService.update(product.getId(), product);
        return ("redirect:/product/list");
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        //Goi ra ham delete o class productService
        productService.delete(id);
        //Sau khi them product vao list thi tu dong quay ve ds nen chon KDL String
        return ("redirect:/product/list");
    }

    @GetMapping("/view/{id}")
    //De xem dc thong tin product thi phai tim product theo id va hien ra giao dien (Model) chua thong tin do
    public String view(@PathVariable int id, Model model){
        //addAttribute => de add toan bo thong tin vao giao dien va lay doi tuong productV de luu lai
        model.addAttribute("productV", productService.findById(id));
        //Sau khi an nut view se chuyen den view.html
        return "/view";
    }

//    @GetMapping("/search/{id}")
//    public String formSearch(@PathVariable int id, Model model){
//        model.addAttribute("productSearch", productService.findById(id));
//        return "/search";
//    }
//
//    @PostMapping("/search/{id}")
//    public String search(@PathVariable int id, Model model){
//        model.addAttribute("productS", productService.findById(id));
//        return "/search";
//    }
}
