package br.feevale.calculadora;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 0153474 on 10/10/2017.
 */

/**
 * Classe reponsável pelos elementos utilizados para as operações matemáticas
 */
public class CalculatorElement {

    // valor do elemento
    private String element;

    /**
     * Construtor padrão da classe de operações matemáticas
     *
     * @param element
     */
    public CalculatorElement(String element) {
        this.element = element;
    }

    /**
     * Retorna valor em formato String do elemento
     *
     * @return String
     */
    public String getElement() {
        return element;
    }

    /**
     * Define valor para elemento
     *
      * @param element String contendo valor do elemento
     */
    public void setElement(String element) {
        this.element = element;
    }

    /**
     * Retorna se o elemento for um operador
     *
     * @return boolean
     */
    public boolean isOperador() {
        return getElement().equals("+") || getElement().equals("-") || getElement().equals("/") || getElement().equals("*");
    }

    /**
     * Retorna verdadeiro se o elemento for o separador de virgula
     *
     * @return boolean
     */
    public boolean isComma() {
        return getElement().equals(",");
    }


}
