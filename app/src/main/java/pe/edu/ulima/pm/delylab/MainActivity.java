package pe.edu.ulima.pm.delylab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Hashtable;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    String URL_SERVIDOR = "http://192.168.0.3/LoginPruebas/login.php";
    EditText etUsuario, etContrasena, etDireccion, etReferencia, etCelular;
    Button btnLogin, btnRegistrar, btnConfirmarPedido,btnAdicionarPedido, btnbtnRealizarCompra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carritopedidos);
        btnAdicionarPedido = (Button) findViewById(R.id.btnAdicionarPedido);
        btnAdicionarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btnAdicionarPedido = new Intent(MainActivity.this,carritopedidos.class);
                startActivity(btnAdicionarPedido);
            }
        });

        btnConfirmarPedido = (Button) findViewById(R.id.btnConfirmarPedido);
        btnConfirmarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btnConfirmarPedido = new Intent(MainActivity.this,metodoPago1.class);
                startActivity(btnConfirmarPedido);
            }
        });
        StrictMode.enableDefaults();

        etUsuario = findViewById(R.id.etUsuario);
        etContrasena = findViewById(R.id.etContrasena);
        etDireccion = findViewById(R.id.etDireccion);
        etReferencia = findViewById(R.id.etReferencia);
        etCelular = findViewById(R.id.etCelular);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnConfirmarPedido = findViewById(R.id.btnConfirmarPedido);
        btnAdicionarPedido = findViewById(R.id.btnAdicionarPedido);
        btnbtnRealizarCompra = findViewById(R.id.btnRealizarCompra);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Registro.class);
                startActivity(intent);
            }
        });

        btnbtnRealizarCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    public void login() {
        StringRequest stringRequest;
        stringRequest = new StringRequest(Request.Method.POST, URL_SERVIDOR,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // En este apartado se programa lo que deseamos hacer en caso de no haber errores

                        if(response.equals("ERROR 1")) {
                            Toast.makeText(MainActivity.this, "Se deben de llenar todos los campos.", Toast.LENGTH_SHORT).show();
                        } else if(response.equals("ERROR 2")) {
                            Toast.makeText(MainActivity.this, "No existe ese registro.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Inicio de Sesion exitoso.", Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // En caso de tener algun error en la obtencion de los datos
                Toast.makeText(MainActivity.this, "ERROR AL INICIAR SESION", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                // En este metodo se hace el envio de valores de la aplicacion al servidor
                Map<String, String> parametros = new Hashtable<String, String>();
                parametros.put("usuario", etUsuario.getText().toString().trim());
                parametros.put("contrasena", etContrasena.getText().toString().trim());

                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);
    }
}