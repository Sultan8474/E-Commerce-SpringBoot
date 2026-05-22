package com.eco.main.Controller;

import java.io.File;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.eco.main.entity.Product;
import com.eco.main.service.IProductService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class ProductController {

    @Autowired
    private IProductService service;

    private static final int PAGE_SIZE = 6;

	
    @GetMapping("/adminDashbord")
    public String adminDashbord(Model model,
                               @RequestParam(defaultValue = "0") int page,
                               HttpSession session,
                               HttpServletResponse response)
    {
        // Prevent browser cache
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        // Session check
        if (session.getAttribute("loggedInAdmin") == null)
        {
            return "redirect:/adminLogin";
        }

        Page<Product> productPage = service.getProducts(page, PAGE_SIZE);

        model.addAttribute("products", productPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());

        return "adminDashbord";
    }
    
    @GetMapping("/userDashbord")
    public String userDashbord(Model model,
                               @RequestParam(defaultValue = "0") int page,
                               HttpSession session,
                               HttpServletResponse response)
    {
        // Prevent browser cache
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        // Session check
        if (session.getAttribute("loggedInUser") == null)
        {
            return "redirect:/userLogin";
        }

        Page<Product> productPage = service.getProducts(page, PAGE_SIZE);

        model.addAttribute("products", productPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());

        return "userDashbord";
    }
    

    @GetMapping("/add")
    public String addProduct(Model model,
                             HttpSession session,
                             HttpServletResponse response) {

        // Prevent browser cache
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        // Only admin can access
        if (session.getAttribute("loggedInAdmin") == null) {
            return "redirect:/adminLogin";
        }

        model.addAttribute("product", new Product());

        return "add-product";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute Product product,
                              @RequestParam(value = "imgFile", required = false) MultipartFile file) {

        try {

            if(file != null && !file.isEmpty()) {

                String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/images/";

                File dir = new File(uploadDir);
                if(!dir.exists()) {
                    dir.mkdirs();
                }

                // create unique filename
                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

                File saveFile = new File(uploadDir + fileName);
                file.transferTo(saveFile);

                product.setImage(fileName);
            }
            else {
                // keep old image when updating product
                if(product.getId() != null) {
                    Product oldProduct = service.getById(product.getId());
                    product.setImage(oldProduct.getImage());
                }
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        service.save(product);

        return "redirect:/adminDashbord";
    }
    
    

    @GetMapping("/addStock/{id}")
    public String addStock(@PathVariable Long id,Model model,
            HttpSession session,
            HttpServletResponse response) 
    {
    	 response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
         response.setHeader("Pragma", "no-cache");
         response.setDateHeader("Expires", 0);

         if (session.getAttribute("loggedInAdmin") == null) {
             return "redirect:/adminLogin";
         }

        Product product = service.getById(id);

        product.setQuantity(product.getQuantity() + 1);

        service.save(product);

        return "redirect:/adminDashbord";
    }
    
    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id,
                              Model model,
                              HttpSession session,
                              HttpServletResponse response) {

        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        if (session.getAttribute("loggedInAdmin") == null) {
            return "redirect:/adminLogin";
        }

        Product product = service.getById(id);
        model.addAttribute("product", product);

        return "add-product";
    }

	
    
    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id,Model model,
            HttpSession session,
            HttpServletResponse response)
    {
    	
    	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        if (session.getAttribute("loggedInAdmin") == null) {
            return "redirect:/adminLogin";
        }
        service.delete(id);
        return "redirect:/adminDashbord";
    }
    
    
    
}