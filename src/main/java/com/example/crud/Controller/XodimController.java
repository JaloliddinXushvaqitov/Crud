package com.example.crud.Controller;

import com.example.crud.Entaite.Xodim;
import com.example.crud.XodimRepazitary.XodimRepozitary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class XodimController {
    @Autowired
    XodimRepozitary xodimRepozitary;
    @RequestMapping(value = "/malumotjoylash",method = RequestMethod.POST)
    public String joylash(@RequestBody Xodim xodim){
        Optional<Xodim> byEmail = xodimRepozitary.findByEmail(xodim.getEmail());
        if(byEmail.isPresent()) return "Bunday xodim mavjud!!!";
         xodimRepozitary.save(xodim);
         return "Xodim malumoti joylandi";
    }
    @RequestMapping(value = "/malumotoqish",method = RequestMethod.GET)
    public List<Xodim> oqish(){
        List<Xodim>all=xodimRepozitary.findAll();
        return all;
    }
    @RequestMapping(value = "/oqish/{id}",method = RequestMethod.GET)
    public Xodim oqish(@PathVariable Integer id){
        Optional<Xodim>oqish=xodimRepozitary.findById(id);
        if(oqish.isPresent()) return oqish.get();
        return new Xodim();
    }
    @RequestMapping(value = "/Ochirish/{id}",method = RequestMethod.DELETE)
    public String Ochirish(@PathVariable Integer id){
        Optional<Xodim> byId = xodimRepozitary.findById(id);
        if(byId.isPresent()){
            xodimRepozitary.deleteById(id);
            return "Malumot ochirildi";
        }
        return "Malumot mavjudmas";
    }
    @RequestMapping(value = "/Taxrirlash/{id}",method = RequestMethod.PUT)
    public String Taxrirlash(@PathVariable Integer id,@RequestBody Xodim xodim){
        Optional<Xodim> byId = xodimRepozitary.findById(id);
        if(byId.isPresent()) {
            Xodim xodim1 = byId.get();
            xodim1.setIsm(xodim.getIsm());
            xodim1.setFam(xodim.getFam());
            xodim1.setEmail(xodim.getEmail());
            xodim1.setLavozim(xodim.getLavozim());
            xodim1.setMaosh(xodim.getMaosh());
            xodimRepozitary.save(xodim1);
            return "Taxrirlandi";
        }
       return "Bunday malumot  mavjudmas!!!";
    }
}
