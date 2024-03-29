package com.pucmm.web2.Controller;

import com.pucmm.web2.Entity.History;
import com.pucmm.web2.Entity.Product;
import com.pucmm.web2.Entity.Receipt;
import com.pucmm.web2.Service.CRUD.CreateDataService;
import com.pucmm.web2.Service.CRUD.ReadDataService;
import com.pucmm.web2.Service.CRUD.UpdateDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Controller
public class StoreFrontController {
    // Services
    @Autowired
    private CreateDataService CDS;
    @Autowired
    private ReadDataService RDS;
    @Autowired
    private UpdateDataService UDS;

    // Gets
    @GetMapping("/")
    public ModelAndView storeFront(Model model) {

        model.addAttribute("user", RDS.getCurrentLoggedUser());

        if(RDS.getCurrentLoggedUser() != null) {
            model.addAttribute("shoppingCart", RDS.findRegisteredUserHistory(RDS.getCurrentLoggedUser().getEmail()).getShoppingCart());
        }else
                 model.addAttribute("shoppingCart", new HashSet<Product>()); // empty cart

        model.addAttribute("selection", RDS.findAllRegisteredProducts());


        return new ModelAndView("StoreFront/homepage/index");
    }

    @GetMapping("/account")
    public ModelAndView account(Model model){

        if(!RDS.isUserLoggedIn())
            return new ModelAndView("redirect:/login");

        if(RDS.getCurrentLoggedUser() != null)
            model.addAttribute("shoppingCart", RDS.findRegisteredUserHistory(RDS.getCurrentLoggedUser().getEmail()).getShoppingCart());
        else
            model.addAttribute("shoppingCart", new HashSet<Product>()); // empty cart

        model.addAttribute("selection", RDS.findAllRegisteredProducts());

        return new ModelAndView("StoreFront/account");
    }

    @GetMapping("/cart")
    public ModelAndView cart(Model model){
        if(RDS.getCurrentLoggedUser() != null)
            model.addAttribute("shoppingCart", RDS.findRegisteredUserHistory(RDS.getCurrentLoggedUser().getEmail()).getShoppingCart());
        else
            model.addAttribute("shoppingCart", new HashSet<Product>()); // empty cart


        return new ModelAndView("StoreFront/cart");
    }

    @GetMapping("/checkout")
    public ModelAndView checkout(Model model){

        if(RDS.getCurrentLoggedUser() != null)
            model.addAttribute("shoppingCart", RDS.findRegisteredUserHistory(RDS.getCurrentLoggedUser().getEmail()).getShoppingCart());
        else
            model.addAttribute("shoppingCart", new HashSet<Product>()); // empty cart
        double total=0.0;
        for(Product i : RDS.findRegisteredUserHistory(RDS.getCurrentLoggedUser().getEmail()).getShoppingCart())
        {
            total+=i.getProductPrice();
        }
        model.addAttribute("total",total);


        return new ModelAndView("StoreFront/checkout");
    }

    @GetMapping("/products")
    public ModelAndView productList(Model model){

        if(RDS.getCurrentLoggedUser() != null)
            model.addAttribute("shoppingCart", RDS.findRegisteredUserHistory(RDS.getCurrentLoggedUser().getEmail()).getShoppingCart());
        else
            model.addAttribute("shoppingCart", new HashSet<Product>()); // empty cart


        model.addAttribute("selection", RDS.findAllRegisteredProducts());

        return new ModelAndView("StoreFront/product_list/product");
    }

    @GetMapping("/product-detail/{id}")
    public ModelAndView product(Model model, @PathVariable("id") String productId) {
        if(RDS.getCurrentLoggedUser() != null)
            model.addAttribute("shoppingCart", RDS.findRegisteredUserHistory(RDS.getCurrentLoggedUser().getEmail()).getShoppingCart());
        else
            model.addAttribute("shoppingCart", new HashSet<Product>()); // empty cart


        Product product = RDS.findRegisteredProduct(Integer.parseInt(productId));

        model.addAttribute("item", product);

        try {
            History history = RDS.findRegisteredUserHistory(RDS.getCurrentLoggedUser().getEmail());
            Set<Product> browsingHistory = history.getBrowsingHistory();

            // Updating the browsing history
            browsingHistory.add(product);
            history.setBrowsingHistory(browsingHistory);

            UDS.updateRegisteredUserHistory(history);
        } catch (Exception exp){
            //
        }

        return new ModelAndView("StoreFront/product_details/product-detail");
    }

    // Posts
    @PostMapping("/add_to_cart")
    public String addToCart(@RequestParam("productId") String productId){

        if(!RDS.isUserLoggedIn())
            return "redirect:/login";

        try {
            Product product = RDS.findRegisteredProduct(Integer.parseInt(productId));

            if (product.getProductInStock() > 0) {
                History history = RDS.findRegisteredUserHistory(RDS.getCurrentLoggedUser().getEmail());
                Set<Product> shoppingCart = history.getShoppingCart();
                ArrayList<Integer> amount = history.getAmount();
                Set<Product> browsingHistory = history.getBrowsingHistory();

                if (amount == null)
                    amount = new ArrayList<>(); // fail safe

                // Adding to cart
                if(!shoppingCart.contains(product)){
                    shoppingCart.add(product);
                    amount.add(1);
                }

                history.setShoppingCart(shoppingCart);
                history.setAmount(amount);

                // Updating the browsing history
                browsingHistory.add(product);
                history.setBrowsingHistory(browsingHistory);

                UDS.updateRegisteredUserHistory(history);

                return "redirect:/products"; // TODO: this should go back to the origin - store page, or product detail
            } else
                return "redirect:/products"; // TODO: this should go back to the origin - store page, or product detail with error message
        } catch (Exception exp){
            //
        }

        return "redirect:/"; // TODO: Add error handling
    }

    @PostMapping("/one_click/quick_buy")
    public String oneClickBuy(@RequestParam("productId") Integer productId, @RequestParam("stripeToken") String stripeToken, @RequestParam("stripeEmail") String stripeEmail){
        if(!RDS.isUserLoggedIn())
            return "redirect:/login";

        try {
            Product product = RDS.findRegisteredProduct(productId);
            ArrayList<Integer> list = new ArrayList<>();
            ArrayList<Integer> amount = new ArrayList<>();

            if (product.getProductInStock() > 0) {

                list.add(product.getProductId());
                amount.add(1);

                // Updating Inventory
                product.setProductInStock(product.getProductInStock() - 1);
            }

            Receipt receipt = CDS.registerTransaction(RDS.getCurrentLoggedUser().getEmail(), list, amount, product.getProductPrice());

            // TODO: send email to admin to confirm transaction
            return "redirect:/download_pdf/transaction?fiscalCode=" + receipt.getFiscalCode();

            //return "redirect:/"; // TODO: this should go back to the origin - store page, or product detail
        } catch (Exception exp){
            //
        }

        return "redirect:/"; // TODO: Add error handling
    }
}
