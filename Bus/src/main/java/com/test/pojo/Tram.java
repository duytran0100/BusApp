/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.pojo;

/**
 *
 * @author Admin
 */
public class Tram {
    private int tramId;
    private String name;
    private String diaChi;

    /**
     * @return the tramId
     */
    public int getTramId() {
        return tramId;
    }

    /**
     * @param tramId the tramId to set
     */
    public void setTramId(int tramId) {
        this.tramId = tramId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the diaChi
     */
    public String getDiaChi() {
        return diaChi;
    }

    /**
     * @param diaChi the diaChi to set
     */
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
    
    @Override
    public String toString() {
        String result = String.format("%s (%s)",this.name,this.diaChi);
        return result;
    }
}
