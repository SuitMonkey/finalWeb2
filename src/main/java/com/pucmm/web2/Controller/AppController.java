package com.pucmm.web2.Controller;

import com.pucmm.web2.Entity.Receipt;
import com.pucmm.web2.Service.CRUD.ReadDataService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AppController {
    ReadDataService RDS;

    @RequestMapping(value ="/greeting", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Receipt> greeting(@RequestParam(value="name", defaultValue="World") String name) {

        List<Receipt> test= RDS.findAllRegisteredTransactions();
        if (RDS.findAllRegisteredTransactions() == null)
        {
            test= new ArrayList<>();
            return test;
        }



        return test;

    }
}
