/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Assignment3;

import org.json.simple.JSONObject;

/**
 *
 * @author c0652674
 */
public class Product {
    int id;
    String name;
    String description;
    int quantity;
    
    public Product(){
        
    }
    
    public Product(int pId,String pName,String pDescription,int pQuantity){
        this.id=pId;
        this.name=pName;
        this.description=pDescription;
        this.quantity=pQuantity;
    }
    
    public void setId(int id){
        this.id=id;
    }
    
    public int getId(){
        return this.id;
    }
    
    public void setName(String name){
        this.name=name;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setDescription(String description){
        this.description=description;
    }
    
    public String getDescription(){
        return this.description;
    }
    
    public void setQuantity(int quantity){
        this.quantity=quantity;
    }
    
    public int getQuantity(){
        return this.quantity;
    }
    
    public String toJSON(){
        JSONObject obj=new JSONObject();
        
        obj.put("productId", this.id);
        obj.put("productname", this.name);
        obj.put("description", this.description);
        obj.put("quantity", this.quantity);
        
        return obj.toJSONString();
    }
}
