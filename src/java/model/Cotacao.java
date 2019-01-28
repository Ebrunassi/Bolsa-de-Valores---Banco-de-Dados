/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author ebrun
 */
public class Cotacao {
    private String data;            // Chave
    private float cotacao;
    private float minima;
    private float maxima;
    private float variacao;
    private float variacaoPorc;
    private float volume;
    private String empresa;         // Chave

    public String getData() {
        return data;
    }
    public float getCotacaoValor() {
        return cotacao;
    }
    public float getMinima() {
        return minima;
    }
    public float getMaxima() {
        return maxima;
    }
    public float getVariacao() {
        return variacao;
    }
    public float getVariacaoPorc() {
        return variacaoPorc;
    }
    public float getVolume() {
        return volume;
    }
    public void setData(String data) {
        this.data = data;
    }
    public void setCotacaoValor(float cotacao) {
        this.cotacao = cotacao;
    }
    public void setMinima(float minima) {
        this.minima = minima;
    }
    public void setMaxima(float maxima) {
        this.maxima = maxima;
    }
    public void setVariacao(float variacao) {
        this.variacao = variacao;
    }
    public void setVariacaoPorc(float variacaoPorc) {
        this.variacaoPorc = variacaoPorc;
    }
    public void setVolume(float volume) {
        this.volume = volume;
    }
    public String getEmpresa() {
        return empresa;
    }
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

  

  
    
}
