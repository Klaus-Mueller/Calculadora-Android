package br.feevale.calculadora;

import android.app.Activity;
import android.os.Bundle;

/**
 * Janela responsável pela exibição dos créditos do programa
 */
public class CreditosActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creditos);
    }
}
