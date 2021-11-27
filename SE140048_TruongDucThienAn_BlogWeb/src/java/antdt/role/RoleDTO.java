/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.role;

import java.io.Serializable;

/**
 *
 * @author antru
 */
public class RoleDTO implements Serializable{
    private int Id;
    private String name;

    public RoleDTO() {
    }

    public RoleDTO(int Id, String name) {
        this.Id = Id;
        this.name = name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RoleDTO{" + "Id=" + Id + ", name=" + name + '}';
    }
    
}
