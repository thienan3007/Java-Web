/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.cart;

import antdt.cakes.CakesDAO;
import antdt.cakes.CakesDTO;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author antru
 */
public class CartObjects implements Serializable {

    private Map<String, CakesDTO> items;

    public Map<String, CakesDTO> getItems() {
        return items;
    }

    public void addToCarts(String id) throws SQLException, ClassNotFoundException {
        //1. checking id existed
        if (id == null) {
            return;
        }

        //2. checking items existed
        if (this.items == null) {
            this.items = new HashMap<>();
        }

        //3. When items existed, check id existed in itmes
        int quantity;
        if (this.items.containsKey(id)) {
            quantity = this.items.get(id).getQuantity();
            this.items.get(id).setQuantity(quantity + 1);
            this.items.put(id, this.items.get(id));
        } else {
            //4. update items
            CakesDAO dao = new CakesDAO();
            CakesDTO cakesDTO = dao.loadCakesDataByid(Integer.parseInt(id));
            this.items.put(id, cakesDTO);
        }
    }

    public void removeItemFromCart(String id) {
        //1. checking items existed
        if (this.items == null) {
            return;
        }
        //2. when items existed, check id existed in items
        if (this.items.containsKey(id)) {
            this.items.remove(id);
            if (this.items.isEmpty()) {
                this.items = null;
            }
        }
    }

}
