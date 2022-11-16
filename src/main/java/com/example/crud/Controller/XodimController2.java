package com.example.crud.Controller;

import com.example.crud.Entaite.Manzil;
import com.example.crud.Entaite.Xodim;
import com.example.crud.Payload.XodimDTO;
import com.example.crud.XodimRepazitary.ManzilRepozitary;
import com.example.crud.XodimRepazitary.XodimRepozitary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class XodimController2 {
    @Autowired
    XodimRepozitary xodimRepozitary;
    @Autowired
    ManzilRepozitary manzilRepozitary;
    @RequestMapping(value = "/Joylash2",method = RequestMethod.POST)
    public String Joylash2(@RequestBody XodimDTO xodimDTO){
        Optional<Xodim> byEmail = xodimRepozitary.findByEmail(xodimDTO.getEmail());
        if(byEmail.isPresent()) return "Bunday xodim mavjud";
        Xodim xodim=new Xodim();
        xodim.setIsm(xodimDTO.getIsm());
        xodim.setFam(xodimDTO.getFam());
        xodim.setLavozim(xodimDTO.getLavozim());
        xodim.setMaosh(xodimDTO.getMaosh());
        xodim.setEmail(xodimDTO.getEmail());
        List<Manzil> manzilList12=new ArrayList<>();
        for (Manzil manzil : xodimDTO.getManzilList()) {
            //Bunda har safar yangi malumot kelganda yangi obekt oladi va listga qoshadi

            Manzil manzil1=new Manzil();
            manzil1.setViloyat(manzil.getViloyat());
            manzil1.setTuman(manzil.getTuman());
            manzil1.setKocha(manzil.getKocha());
            manzil1.setXodim(xodim);
            manzilList12.add(manzil1);

        }
        xodim.setManzilList(manzilList12);
        xodimRepozitary.save(xodim);
        return "Malumot saqlandi!!!";
    }
    @RequestMapping(value = "/Taxrirlashmanzil/{id}",method = RequestMethod.PUT)
    public String Taxrirlashmanzil(@PathVariable Integer id, @RequestBody XodimDTO xodimDTO) {
        Optional<Xodim> byId = xodimRepozitary.findById(id);
        if(byId.isPresent()){
            Xodim xodim = byId.get();
            xodim.setIsm(xodimDTO.getIsm());
            xodim.setFam(xodimDTO.getFam());
            xodim.setLavozim(xodimDTO.getLavozim());
            xodim.setMaosh(xodimDTO.getMaosh());
            xodim.setEmail(xodimDTO.getEmail());
            List<Manzil> byXodimId = manzilRepozitary.findByXodimId(id);
            List<Manzil> manzilList123=new ArrayList<>();
            int i=0;
            // bu yerda xodimDTO beriladi yangi malumot olib kelish uchun xodim
            // berilsa oziga ozini berib taxrirlamaydi
            for (Manzil manzil : xodimDTO.getManzilList()) {
                Manzil manzil1 = byXodimId.get(i);
                manzil1.setViloyat(manzil.getViloyat());
                manzil1.setTuman(manzil.getTuman());
                manzil1.setKocha(manzil.getKocha());
                manzil1.setXodim(xodim);
                // bunda ichiagi bir necha manzillarga bir vaqtda malumot berib manzillist123 ga add orqli
                // qoshdik
                // va id ni yani i ni i++ oshirib qoydik
                manzilList123.add(manzil1);
                i++;
            }
            // bunda manzillist123 ga oziga for ichida manzil malumotlarini olib kelayabdi
            //xodimga set qilib barcha malumotlarni birib yubordik va saqladik;
            xodim.setManzilList(manzilList123);
            xodimRepozitary.save(xodim);
            return "Xodim malumotlari taxrirlandi!!!";
        }
        return "Bunday xodim mavjud emas!!";
    }
    @RequestMapping(value = "/Delete/{id}",method = RequestMethod.DELETE)
    public String Delete(@PathVariable Integer id){
        Optional<Xodim> byId = xodimRepozitary.findById(id);
        if(byId.isPresent()){
            xodimRepozitary.deleteById(id);
           manzilRepozitary.deleteById(id);
            return "Malumot ochirildi";
        }
        return "Malumot ochirilmadi";

    }
    @RequestMapping(value = "/malumotolish/{id}",method = RequestMethod.GET)
    public Xodim malumot(@PathVariable Integer id){
        Optional<Xodim> byId = xodimRepozitary.findById(id);
        if(byId.isPresent()){
            return byId.get();
        }
        return  new Xodim();
    }
    @RequestMapping(value = "/malumotolish",method = RequestMethod.GET)
    public List<Xodim> Oqish(){
        List<Xodim>all=xodimRepozitary.findAll();
        return all;
    }
}
