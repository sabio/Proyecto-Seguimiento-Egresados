package com.sse.beans.generales;

public class Menu {
    Integer idMenu;
    Integer idMenuPadre;
    String menu;
    Integer nivel;
    Integer orden;
    String url;

    public Menu(){
        
    }
    
    public Menu(Integer idMenu, Integer idMenuPadre, String menu, Integer nivel, Integer orden, String url) {
        this.idMenu = idMenu;
        this.idMenuPadre = idMenuPadre;
        this.menu=menu;
        this.nivel = nivel;
        this.orden = orden;
        this.url = url;
    }

    public Integer getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Integer idMenu) {
        this.idMenu = idMenu;
    }

    public Integer getIdMenuPadre() {
        return idMenuPadre;
    }

    public void setIdMenuPadre(Integer idMenuPadre) {
        this.idMenuPadre = idMenuPadre;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }
    
    

    @Override
    public String toString() {
        return "nivel="+this.getNivel()+" orden="+this.getOrden()+" idmenu="+this.getIdMenu()+" menu="+this.getMenu();
    }
    
}
