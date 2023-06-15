/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.co.radis.model;

import java.util.Map;

/**
 *
 * @author wilso
 */
public class User {

    private String estado;
    private String mensaje;
    private Map<String, InformationUser> beneficiarios;
    private String resultados;
    private String tipo_toma;
    private String clave;
    private String consulta;

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

    public Map<String, InformationUser> getBeneficiarios() {
        return beneficiarios;
    }

    public void setBeneficiarios(Map<String, InformationUser> beneficiarios) {
        this.beneficiarios = beneficiarios;
    }

    public String getResultados() {
        return resultados;
    }

    public void setResultados(String resultados) {
        this.resultados = resultados;
    }

    public String getTipo_toma() {
        return tipo_toma;
    }

    public void setTipo_toma(String tipo_toma) {
        this.tipo_toma = tipo_toma;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getConsulta() {
        return consulta;
    }

    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }

}
