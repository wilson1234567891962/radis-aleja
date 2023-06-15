/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.co.radis.model;

/**
 *
 * @author wilso
 */
public class Data {

    private String estado;
    private String mensaje;
    private RspGrb rsp_grb;
    private String rac_cont;
    private Resultado resultado;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public RspGrb getRsp_grb() {
        return rsp_grb;
    }

    public void setRsp_grb(RspGrb rsp_grb) {
        this.rsp_grb = rsp_grb;
    }

    public String getRac_cont() {
        return rac_cont;
    }

    public void setRac_cont(String rac_cont) {
        this.rac_cont = rac_cont;
    }

    public Resultado getResultado() {
        return resultado;
    }

    public void setResultado(Resultado resultado) {
        this.resultado = resultado;
    }

}
