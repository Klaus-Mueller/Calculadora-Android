package br.feevale.calculadora;

import android.content.Intent;
import android.service.wallpaper.WallpaperService;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Element;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Activity inicial do programa
 */
public class CalculatorActivity extends AppCompatActivity {

    // Lista de elementos para efetuar calculos
    List<CalculatorElement> elements =  new ArrayList<CalculatorElement>();
    // Valor de entrada e saída da calculadora
    TextView txtValues;
    // Objeto responsável por efetuar a parte de operações
    MathCalculator mathCalculator = new MathCalculator();
    // Botão de virgula
    Button comma;

    /**
     * Método chamado na criação da Activity
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtValues = (TextView) findViewById(R.id.txtValues);
        comma = (Button) findViewById(R.id.comma);
        txtValues.setEnabled(false);
        elements.clear();
    }


    /**
     * Adiciona o Elemento de número um
     *
     * @param v
     */
    public void addOne(View v) {
        CalculatorElement e = new CalculatorElement("1");
        elements.add(e);
        this.elementsToString();
    }

    /**
     * Adiciona o Elemento de número dois
     *
     * @param v
     */
    public void addTwo(View v) {
        CalculatorElement e = new CalculatorElement("2");
        elements.add(e);
        this.elementsToString();
    }

    /**
     * Adiciona o Elemento de número três
     *
     * @param v
     */
    public void addThree(View v) {
        CalculatorElement e = new CalculatorElement("3");
        elements.add(e);
        this.elementsToString();
    }

    /**
     * Adiciona o Elemento de número quatro
     *
     * @param v
     */
    public void addFour(View v) {
        CalculatorElement e = new CalculatorElement("4");
        elements.add(e);
        this.elementsToString();
    }

    /**
     * Adiciona o Elemento de número cinco
     *
     * @param v
     */
    public void addFive(View v) {
        CalculatorElement e = new CalculatorElement("5");
        elements.add(e);
        this.elementsToString();
    }

    /**
     * Adiciona o Elemento de número seis
     *
     * @param v
     */
    public void addSix(View v) {
        CalculatorElement e = new CalculatorElement("6");
        elements.add(e);
        this.elementsToString();
    }

    /**
     * Adiciona o Elemento de número sete
     *
     * @param v
     */
    public void addSeven(View v) {
        CalculatorElement e = new CalculatorElement("7");
        elements.add(e);
        this.elementsToString();
    }

    /**
     * Adiciona o Elemento de número oito
     *
     * @param v
     */
    public void addEight(View v) {
        CalculatorElement e = new CalculatorElement("8");
        elements.add(e);
        this.elementsToString();
    }

    /**
     * Adiciona o Elemento de número nove
     *
     * @param v
     */
    public void addNine(View v) {
        CalculatorElement e = new CalculatorElement("9");
        elements.add(e);
        this.elementsToString();
    }

    /**
     * Adiciona o Elemento de número zero
     *
     * @param v
     */
    public void addZero(View v) {
        CalculatorElement e = new CalculatorElement("0");
        elements.add(e);
        this.elementsToString();
    }

    /**
     * Adiciona o operador de soma
     *
     * @param v
     */
    public void addPlus(View v) {
        // Verifica se o operador não é o primeiro elemento a ser adicionado
        if(elements.isEmpty()) {
            return;
        }
        // Cria novo elemento
        CalculatorElement e = new CalculatorElement("+");
        // Verifica se está sendo inserido um operador em sequencia
        if(isOperacaoSequencial(e)){
            elements.remove(elements.size() -1);
        }
        elements.add(e);
        this.elementsToString();
        comma.setEnabled(true);
    }

    /**
     * Adiciona o operador de subtração
     *
     * @param v
     */
    public void addSubtract(View v) {
        CalculatorElement e = new CalculatorElement("-");
        // Verifica se o operador não é o primeiro elemento a ser adicionado e se não está sendo inserido em sequência de outro operador
        if(!elements.isEmpty() && isOperacaoSequencial(e)){
            elements.remove(elements.size() -1);
        }
        elements.add(e);
        this.elementsToString();
        comma.setEnabled(true);
    }

    /**
     * Adiciona o operador de divisão
     *
     * @param v
     */
    public void addDivision(View v) {
        // Verifica se o operador não é o primeiro elemento a ser adicionado
        if(elements.isEmpty()) {
            return;
        }
        CalculatorElement e = new CalculatorElement("/");
        // Verifica se está sendo inserido um operador em sequencia
        if(isOperacaoSequencial(e)){
            elements.remove(elements.size() -1);
        }
        elements.add(e);
        this.elementsToString();
        comma.setEnabled(true);
    }

    /**
     * Adiciona o operador de multiplicação
     *
     * @param v
     */
    public void addMultiply(View v) {
        // Verifica se o operador não é o primeiro elemento a ser adicionado
        if(elements.isEmpty()) {
            return;
        }
        CalculatorElement e = new CalculatorElement("*");
        // Verifica se está sendo inserido um operador em sequencia
        if(isOperacaoSequencial(e)){
            elements.remove(elements.size() -1);
        }
        elements.add(e);
        this.elementsToString();
        comma.setEnabled(true);
    }

    /**
     * Adiciona o separador virgula
     *
     * @param v
     */
    public void addComma(View v) {
        // Verifica se o operador não é o primeiro elemento a ser adicionado
        if(elements.isEmpty()) {
            return;
        }
        CalculatorElement e = new CalculatorElement(",");
        // Verifica se está sendo inserido um operador em sequencia
        if(isOperacaoSequencial(e)){
            elements.remove(elements.size() -1);
        }
        elements.add(e);
        this.elementsToString();
        comma.setEnabled(false);
    }

    /**
     * Método chamado para execução do calculo
     *
     * @param v
     */
    public void resolve(View v) {
        // Caso não exista dados inseridos ou o último elemento da lista dor um operador
        if(elements.isEmpty() || isUltimoElementOperador()) {
            return;
        }
        // Converte os dados em String
        String values = txtValues.getText().toString();
        // Executa cálculo matematico
        String result = mathCalculator.resolve(values);
        // Executa o replace do separador de "." para ","
        result = result.replace(".", ",");
        txtValues.setText(result);
        elements.clear();
        elements.add(new CalculatorElement(result));
    }

    /**
     * Ação responsável por limpar os dados já inseridos
     *
     * @param v
     */
    public void clear(View v) {
        txtValues.setText("");
        elements.clear();
    }

    /**
     * Transforma os elementos inseridos em valores do tipo String
     */
    private void elementsToString() {
        String values = "";
        String result = "";
        for(CalculatorElement e : elements) {
            values = values.concat(e.getElement());
        }
        txtValues.setText(values);
    }

    /**
     * Retorna verdadeiro se o elemento que está sendo inserido é diferente de um número e se o ultimo elemento da lista também é diferente de um número
     *
     * @param element Objeto com dados para o calculo
     * @return boolean
     */
    public boolean isOperacaoSequencial(CalculatorElement element) {
        CalculatorElement ultimoElemento = elements.get(elements.size()-1);
        return (ultimoElemento.isOperador() || ultimoElemento.isComma()) && (element.isOperador() || element.isComma());
    }

    /**
     * Retorna verdadeiro se o último elemento da lista é diferente de um número
     *
     * @return boolean
     */
    public boolean isUltimoElementOperador() {
        return elements.get(elements.size()-1).isOperador() || elements.get(elements.size()-1).isComma();
    }

    /**
     * Executa a ação de chamada da janela de créditos do programa
     *
     * @param v
     */
    public void creditos(View v) {
        Intent intent = new Intent(this, CreditosActivity.class);
        startActivity(intent);
    }

}
