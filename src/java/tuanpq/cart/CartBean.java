/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuanpq.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author LENOVO
 */
public class CartBean implements Serializable{
    private Map<String, Integer> items;

    public Map<String, Integer> getItems() {
        return items;
    }
    public void addItemToCart(String item) {
        if (item == null) {
            return;
        }
        if (item.trim().isEmpty()) {
            return;
        }
        //1.check existd items(gio)
        if (this.items == null) {
            this.items = new HashMap<>();
        }
        //2.check existed item(do)
        int quantity = 1;
        if (this.items.containsKey(item)) {
            quantity = this.items.get(item) + 1;
        }
        //3. drop item to items
        this.items.put(item, quantity);
    }
    public void removeItemToCart(String item) {
        if (item == null) {
            return;
        }
        if (item.trim().isEmpty()) {
            return;
        }
        if (this.items == null) {
            return;
        }
        if (this.items.containsKey(item)) {
            this.items.remove(item);
            if (this.items.isEmpty()) {
                this.items = null;
            }
        }
    }
}
