package br.com.local.buscacepapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    Button btnBuscarCep;
    EditText txtCep;
    TextView lblResposta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        txtCep = findViewById(R.id.txtCep);
        lblResposta = findViewById(R.id.lblResposta);
        btnBuscarCep = findViewById(R.id.btnBuscaCep);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.txtCep), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnBuscarCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    CEP retorno = new HttpService(txtCep.getText().toString().trim()).execute().get();
                    lblResposta.setText(retorno.toString());
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });


    }
}
