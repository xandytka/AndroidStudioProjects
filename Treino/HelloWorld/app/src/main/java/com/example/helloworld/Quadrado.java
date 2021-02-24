package com.example.helloworld;

public class Quadrado {
    //Declaração das propriedas internas da classe
    private int lado_a;
    private int lado_b;

    //Declaração dos Metodos Setters e Getters
    public void setLado_a(int a){
        lado_a = a;
    }
    public void setLado_b(int b){
        lado_b = b;
    }
    public int getLado_a(){
        return lado_a;
    }
    public int getLado_b(){
        return lado_b;
    }

    //Declaração dos metodos da Classe Quadrado
    public int MostraArea(){
        return getLado_a() * getLado_b();
    }

}
