package pe.jrivera6.nicollperuoperarios.activities;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import es.dmoral.toasty.Toasty;
import pe.jrivera6.nicollperuoperarios.R;
import pe.jrivera6.nicollperuoperarios.models.Formulario;
import pe.jrivera6.nicollperuoperarios.models.Tubo;
import pe.jrivera6.nicollperuoperarios.services.ApiService;
import pe.jrivera6.nicollperuoperarios.services.ApiServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    int NOT_FOUND = 404;

    String nombreOperario, nombreSupervisor, error_id, error_descripcion;

    private Dialog errorDialog, avisoDialog, dataDialog, confirmSaveDialog;
    private LinearLayout linear6a, linear3, linear4, linear5, linear6, linear7, linear8, linear9, linear10, linear11, linear12, linear13, linear14, linear15, linear16, linear17, linear18;

    //Cabecera
    private TextView txtNumMaquina, txtDescripcionTubo, txtTurno, txtNomMaquinista, txtNomSupervisor;
    private EditText txtIdTubo;
    private ImageView btn_error;
    private Button btnGuardar;
    private FrameLayout progressBar;


    //Cilindro
    private EditText txtOil, txtCilindroZona1, txtCilindroZona2, txtCilindroZona3, txtCilindroZona4, txtCilindroZona5, txtCilindroZona6;

    //Cabezal
    private EditText txtInterna, txtCabezalZona1, txtCabezalZona2, txtCabezalZona3, txtCabezalZona4, txtCabezalZona5, txtCabezalZona6, txtCabezalZona7,
            txtCabezalZona8, txtCabezalZona9, txtCabezalZona10, txtCabezalZona11, txtCabezalZona12, txtCabezalZona13, txtCabezalZona14,
            txtCabezalZona15, txtCabezalZona16, txtCabezalZona17, txtCabezalZona18;

    private EditText txtNombreCabezal, txtDiametroRestrictorFiltro, txtRpmMotorExtrusora,
            txtAmpMotorExtrusora, txtRpmRevminTornillos, txtPorcentajeVelAlimentador,
            txtAmperajeMotorAlimentador, txtDesgasificadorVacio, txtPresionMasa,
            txtTempMasa, txtContrapresion, txtVacioPrimTina, txtTemperaturaPrimTinaEnfriamiento,
            txtPresionAguaPrimTinaEnfriamiento, txtVacioSecTinaEnfriamiento, txtVelocidadHalador,
            txtLimpiazaFiltroTina, txtAlturaRotulo, txtEspesor, txtDiametroExterno, txtLongitudTubo,
            txtEmbone, txtKilogramoxHora, txtPesoTuboMetro;

    // Estandar

    //Cilindro
    private TextView estandarOil, estandarCilindroZona1, estandarCilindroZona2, estandarCilindroZona3, estandarCilindroZona4, estandarCilindroZona5, estandarCilindroZona6;

    //Cabezal
    private TextView estandarInterna, estandarCabezalZona1, estandarCabezalZona2, estandarCabezalZona3, estandarCabezalZona4, estandarCabezalZona5,
            estandarCabezalZona6, estandarCabezalZona7, estandarCabezalZona8, estandarCabezalZona9, estandarCabezalZona10, estandarCabezalZona11, estandarCabezalZona12,
            estandarCabezalZona13, estandarCabezalZona14, estandarCabezalZona15, estandarCabezalZona16, estandarCabezalZona17, estandarCabezalZona18;

    private TextView estandarNombreCabezal, estandarDiametroRestrictorFiltro, estandarRpmMotorExtrusora,
            estandarAmpMotorExtrusora, estandarRpmRevminTornillos, estandarPorcentajeVelAlimentador,
            estandarAmperajeMotorAlimentador, estandarDesgasificadorVacio, estandarPresionMasa,
            estandarTempMasa, estandarContrapresion, estandarVacioPrimTina, estandarTemperaturaPrimTinaEnfriamiento,
            estandarPresionAguaPrimTinaEnfriamiento, estandarVacioSecTinaEnfriamiento, estandarVelocidadHalador,
            estandarLimpiazaFiltroTina, estandarAlturaRotulo, estandarEspesor, estandarDiametroExterno, estandarLongitudTubo,
            estandarEmbone, estandarKilogramoxHora, estandarPesoTuboMetro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        errorDialog = new Dialog(this);
        avisoDialog = new Dialog(this);
        dataDialog = new Dialog(this);
        confirmSaveDialog = new Dialog(this);


        //Inicializa las variables por findById
        iniciarVariables();




        /*
         ** CODIGO FUNCIONAL
         */

        //Listener EditText
        editTextsListener();

        //Cambiar Turno
        cambiarTurno();

        //prueba
        showDatosDialog();


        btn_error.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showErrorDialog();
            }
        });


    }

    //GUARDA DATOS DEL FORMULARIO
    public void guardarFormulario(View view) {

        try {

            if (txtIdTubo.getText().toString().isEmpty()) {
                makeLongToast("Registro incompleto");
                return;
            }

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("¿Estás seguro de guardar los datos?");

            alertDialogBuilder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    progressBar.setVisibility(View.VISIBLE);

                    /* CILINDRO */

                    int oil = (!txtOil.getText().toString().isEmpty()) ? Integer.parseInt(txtOil.getText().toString()) : 0;
                    int cilindroZona1 = (!txtCilindroZona1.getText().toString().isEmpty()) ? Integer.parseInt(txtCilindroZona1.getText().toString()) : 0;
                    int cilindroZona2 = (!txtCilindroZona2.getText().toString().isEmpty()) ? Integer.parseInt(txtCilindroZona2.getText().toString()) : 0;
                    int cilindroZona3 = (!txtCilindroZona3.getText().toString().isEmpty()) ? Integer.parseInt(txtCilindroZona3.getText().toString()) : 0;
                    int cilindroZona4 = (!txtCilindroZona4.getText().toString().isEmpty()) ? Integer.parseInt(txtCilindroZona4.getText().toString()) : 0;
                    int cilindroZona5 = (!txtCilindroZona5.getText().toString().isEmpty()) ? Integer.parseInt(txtCilindroZona5.getText().toString()) : 0;
                    int cilindroZona6 = (!txtCilindroZona6.getText().toString().isEmpty()) ? Integer.parseInt(txtCilindroZona6.getText().toString()) : 0;

                    /* CABEZAL */
                    int interna = (!txtInterna.getText().toString().isEmpty()) ? Integer.parseInt(txtInterna.getText().toString()) : 0;
                    int cabezalZona1 = (!txtCabezalZona1.getText().toString().isEmpty()) ? Integer.parseInt(txtCabezalZona1.getText().toString()) : 0;
                    int cabezalZona2 = (!txtCabezalZona2.getText().toString().isEmpty()) ? Integer.parseInt(txtCabezalZona2.getText().toString()) : 0;
                    int cabezalZona3 = (!txtCabezalZona3.getText().toString().isEmpty()) ? Integer.parseInt(txtCabezalZona3.getText().toString()) : 0;
                    int cabezalZona4 = (!txtCabezalZona4.getText().toString().isEmpty()) ? Integer.parseInt(txtCabezalZona4.getText().toString()) : 0;
                    int cabezalZona5 = (!txtCabezalZona5.getText().toString().isEmpty()) ? Integer.parseInt(txtCabezalZona5.getText().toString()) : 0;
                    int cabezalZona6 = (!txtCabezalZona6.getText().toString().isEmpty()) ? Integer.parseInt(txtCabezalZona6.getText().toString()) : 0;
                    int cabezalZona7 = (!txtCabezalZona7.getText().toString().isEmpty()) ? Integer.parseInt(txtCabezalZona7.getText().toString()) : 0;
                    int cabezalZona8 = (!txtCabezalZona8.getText().toString().isEmpty()) ? Integer.parseInt(txtCabezalZona8.getText().toString()) : 0;
                    int cabezalZona9 = (!txtCabezalZona9.getText().toString().isEmpty()) ? Integer.parseInt(txtCabezalZona9.getText().toString()) : 0;
                    int cabezalZona10 = (!txtCabezalZona10.getText().toString().isEmpty()) ? Integer.parseInt(txtCabezalZona10.getText().toString()) : 0;
                    int cabezalZona11 = (!txtCabezalZona11.getText().toString().isEmpty()) ? Integer.parseInt(txtCabezalZona11.getText().toString()) : 0;
                    int cabezalZona12 = (!txtCabezalZona12.getText().toString().isEmpty()) ? Integer.parseInt(txtCabezalZona12.getText().toString()) : 0;
                    int cabezalZona13 = (!txtCabezalZona13.getText().toString().isEmpty()) ? Integer.parseInt(txtCabezalZona13.getText().toString()) : 0;
                    int cabezalZona14 = (!txtCabezalZona14.getText().toString().isEmpty()) ? Integer.parseInt(txtCabezalZona14.getText().toString()) : 0;
                    int cabezalZona15 = (!txtCabezalZona15.getText().toString().isEmpty()) ? Integer.parseInt(txtCabezalZona15.getText().toString()) : 0;
                    int cabezalZona16 = (!txtCabezalZona16.getText().toString().isEmpty()) ? Integer.parseInt(txtCabezalZona16.getText().toString()) : 0;
                    int cabezalZona17 = (!txtCabezalZona17.getText().toString().isEmpty()) ? Integer.parseInt(txtCabezalZona17.getText().toString()) : 0;
                    int cabezalZona18 = (!txtCabezalZona18.getText().toString().isEmpty()) ? Integer.parseInt(txtCabezalZona18.getText().toString()) : 0;

                    int diametroRestrictorFiltro = (!txtDiametroRestrictorFiltro.getText().toString().isEmpty()) ? Integer.parseInt(txtDiametroRestrictorFiltro.getText().toString()) : 0;
                    int rpmMotorExtrusora = (!txtRpmMotorExtrusora.getText().toString().isEmpty()) ? Integer.parseInt(txtRpmMotorExtrusora.getText().toString()) : 0;
                    int amperajeMotorExtrusora = (!txtAmpMotorExtrusora.getText().toString().isEmpty()) ? Integer.parseInt(txtAmpMotorExtrusora.getText().toString()) : 0;
                    int presionMasa = (!txtPresionMasa.getText().toString().isEmpty()) ? Integer.parseInt(txtPresionMasa.getText().toString()) : 0;
                    int temperaturaMasa = (!txtTempMasa.getText().toString().isEmpty()) ? Integer.parseInt(txtTempMasa.getText().toString()) : 0;
                    int contrapresion = (!txtContrapresion.getText().toString().isEmpty()) ? Integer.parseInt(txtContrapresion.getText().toString()) : 0;
                    int tempPrimeraTinaEnfria = (!txtTemperaturaPrimTinaEnfriamiento.getText().toString().isEmpty()) ? Integer.parseInt(txtTemperaturaPrimTinaEnfriamiento.getText().toString()) : 0;
                    int diametroExterno = (!txtDiametroExterno.getText().toString().isEmpty()) ? Integer.parseInt(txtDiametroExterno.getText().toString()) : 0;
                    int longitudTubo = (!txtLongitudTubo.getText().toString().isEmpty()) ? Integer.parseInt(txtLongitudTubo.getText().toString()) : 0;
                    int kilogramosHoras = (!txtKilogramoxHora.getText().toString().isEmpty()) ? Integer.parseInt(txtKilogramoxHora.getText().toString()) : 0;

//            if (!txtOil.getText().toString().isEmpty()) {
//                oil = Integer.parseInt(txtOil.getText().toString());
//            }

//            if (!txtCilindroZona1.getText().toString().isEmpty()) {
//                cilindroZona1 = Integer.parseInt(txtCilindroZona1.getText().toString());
//            }
//
//            if (!txtCilindroZona2.getText().toString().isEmpty()) {
//                cilindroZona2 = Integer.parseInt(txtCilindroZona2.getText().toString());
//            }
//
//            if (!txtCilindroZona3.getText().toString().isEmpty()) {
//                cilindroZona3 = Integer.parseInt(txtCilindroZona3.getText().toString());
//            }
//
//            if (!txtCilindroZona4.getText().toString().isEmpty()) {
//                cilindroZona4 = Integer.parseInt(txtCilindroZona4.getText().toString());
//
//            }
//
//            if (!txtCilindroZona5.getText().toString().isEmpty()) {
//                cilindroZona5 = Integer.parseInt(txtCilindroZona5.getText().toString());
//            }
//
//            if (!txtCilindroZona6.getText().toString().isEmpty()) {
//                cilindroZona6 = Integer.parseInt(txtCilindroZona6.getText().toString());
//            }


//            if (!txtInterna.getText().toString().isEmpty()) {
//                interna = Integer.parseInt(txtInterna.getText().toString());
//            }
//
//            if (!txtCabezalZona1.getText().toString().isEmpty()) {
//                cabezalZona1 = Integer.parseInt(txtCabezalZona1.getText().toString());
//            }
//
//            if (!txtCabezalZona2.getText().toString().isEmpty()) {
//                cabezalZona2 = Integer.parseInt(txtCabezalZona2.getText().toString());
//            }
//
//            if (!txtCabezalZona3.getText().toString().isEmpty()) {
//                cabezalZona3 = Integer.parseInt(txtCabezalZona3.getText().toString());
//            }
//
//            if (!txtCabezalZona4.getText().toString().isEmpty()) {
//                cabezalZona4 = Integer.parseInt(txtCabezalZona4.getText().toString());
//            }
//
//            if (!txtCabezalZona5.getText().toString().isEmpty()) {
//                cabezalZona5 = Integer.parseInt(txtCabezalZona5.getText().toString());
//            }
//
//            if (!txtCabezalZona6.getText().toString().isEmpty()) {
//                cabezalZona6 = Integer.parseInt(txtCabezalZona6.getText().toString());
//            }
//
//            if (!txtCabezalZona7.getText().toString().isEmpty()) {
//                cabezalZona7 = Integer.parseInt(txtCabezalZona7.getText().toString());
//            }
//
//            if (!txtCabezalZona8.getText().toString().isEmpty()) {
//                cabezalZona8 = Integer.parseInt(txtCabezalZona8.getText().toString());
//            }
//
//            if (!txtCabezalZona9.getText().toString().isEmpty()) {
//                cabezalZona9 = Integer.parseInt(txtCabezalZona9.getText().toString());
//            }
//
//            if (!txtCabezalZona10.getText().toString().isEmpty()) {
//                cabezalZona10 = Integer.parseInt(txtCabezalZona10.getText().toString());
//            }
//
//            if (!txtCabezalZona11.getText().toString().isEmpty()) {
//                cabezalZona11 = Integer.parseInt(txtCabezalZona11.getText().toString());
//            }
//
//            if (!txtCabezalZona12.getText().toString().isEmpty()) {
//                cabezalZona12 = Integer.parseInt(txtCabezalZona12.getText().toString());
//            }
//
//            if (!txtCabezalZona13.getText().toString().isEmpty()) {
//                cabezalZona13 = Integer.parseInt(txtCabezalZona13.getText().toString());
//            }
//
//            if (!txtCabezalZona14.getText().toString().isEmpty()) {
//                cabezalZona14 = Integer.parseInt(txtCabezalZona14.getText().toString());
//            }
//
//            if (!txtCabezalZona15.getText().toString().isEmpty()) {
//                cabezalZona15 = Integer.parseInt(txtCabezalZona15.getText().toString());
//            }
//
//            if (!txtCabezalZona16.getText().toString().isEmpty()) {
//                cabezalZona16 = Integer.parseInt(txtCabezalZona16.getText().toString());
//            }
//
//            if (!txtCabezalZona17.getText().toString().isEmpty()) {
//                cabezalZona17 = Integer.parseInt(txtCabezalZona17.getText().toString());
//            }
//
//            if (!txtCabezalZona18.getText().toString().isEmpty()) {
//                cabezalZona18 = Integer.parseInt(txtCabezalZona18.getText().toString());
//            }
//


//            if (!txtDiametroRestrictorFiltro.getText().toString().isEmpty()) {
//                diametroRestrictorFiltro = Integer.parseInt(txtDiametroRestrictorFiltro.getText().toString());
//            }
//
//            if (!txtRpmMotorExtrusora.getText().toString().isEmpty()) {
//                rpmMotorExtrusora = Integer.parseInt(txtRpmMotorExtrusora.getText().toString());
//            }
//
//            if (!txtAmpMotorExtrusora.getText().toString().isEmpty()) {
//                amperajeMotorExtrusora = Integer.parseInt(txtAmpMotorExtrusora.getText().toString());
//            }
//
//            if (!txtPresionMasa.getText().toString().isEmpty()) {
//                presionMasa = Integer.parseInt(txtPresionMasa.getText().toString());
//            }
//
//            if (!txtTempMasa.getText().toString().isEmpty()) {
//                temperaturaMasa = Integer.parseInt(txtTempMasa.getText().toString());
//            }
//
//            if (!txtContrapresion.getText().toString().isEmpty()) {
//                contrapresion = Integer.parseInt(txtContrapresion.getText().toString());
//            }
//
//            if (!txtTemperaturaPrimTinaEnfriamiento.getText().toString().isEmpty()) {
//                tempPrimeraTinaEnfria = Integer.parseInt(txtTemperaturaPrimTinaEnfriamiento.getText().toString());
//            }
//
//            if (!txtDiametroExterno.getText().toString().isEmpty()) {
//                diametroExterno = Integer.parseInt(txtDiametroExterno.getText().toString());
//            }
//
//            if (!txtLongitudTubo.getText().toString().isEmpty()) {
//                longitudTubo = Integer.parseInt(txtLongitudTubo.getText().toString());
//            }
//
//            if (!txtKilogramoxHora.getText().toString().isEmpty()) {
//                kilogramosHoras = Integer.parseInt(txtKilogramoxHora.getText().toString());
//            }


                    Long idTubo = Long.parseLong(txtIdTubo.getText().toString());
                    Integer numMaquina = Integer.parseInt(txtNumMaquina.getText().toString());
//                    String nomMaquinista = txtNomMaquinista.getText().toString();
//                    String nomSupervisor = txtNomSupervisor.getText().toString();
                    String turno = txtTurno.getText().toString();
                    String error_id = "";
                    String error_descripcion = "";
                    String nombreCabezal = txtNombreCabezal.getText().toString();
                    String rpmRevMinTornillos = txtRpmRevminTornillos.getText().toString();
                    String porcentajeVelocidadAlimentador = txtPorcentajeVelAlimentador.getText().toString();
                    String amperajeMotorAlimentador = txtAmperajeMotorAlimentador.getText().toString();
                    String desgasificadorVacio = txtDesgasificadorVacio.getText().toString();
                    String vacioPrimeraTina = txtVacioPrimTina.getText().toString();
                    String presionAguaPrimTinaEnfria = txtPresionAguaPrimTinaEnfriamiento.getText().toString();
                    String vacioSegundaTinaEnfria = txtVacioSecTinaEnfriamiento.getText().toString();
                    String velocidadHalador = txtVelocidadHalador.getText().toString();
                    String limpiezaFiltroTina = txtLimpiazaFiltroTina.getText().toString();
                    String alturaRotulo = txtAlturaRotulo.getText().toString();
                    String espesor = txtEspesor.getText().toString();
                    String embone = txtEmbone.getText().toString();
                    String pesoTuboMetro = txtPesoTuboMetro.getText().toString();

                    if(txtKilogramoxHora.toString().isEmpty()){
                        makeShortToast("Falta llenar Kilogramos/Horas");
                        return;
                    }

                    Formulario form;
                    form = new Formulario(idTubo, numMaquina, nombreOperario, nombreSupervisor, getDate(), turno, error_id, error_descripcion, oil, cilindroZona1, cilindroZona2, cilindroZona3, cilindroZona4, cilindroZona5, cilindroZona6, interna, cabezalZona1, cabezalZona2, cabezalZona3, cabezalZona4, cabezalZona5, cabezalZona6, cabezalZona7, cabezalZona8, cabezalZona9, cabezalZona10, cabezalZona11, cabezalZona12,
                            cabezalZona13, cabezalZona14, cabezalZona15, cabezalZona16, cabezalZona17, cabezalZona18, nombreCabezal, diametroRestrictorFiltro, rpmMotorExtrusora, amperajeMotorExtrusora, rpmRevMinTornillos, porcentajeVelocidadAlimentador,
                            amperajeMotorAlimentador, desgasificadorVacio, presionMasa, temperaturaMasa, contrapresion, vacioPrimeraTina, tempPrimeraTinaEnfria, presionAguaPrimTinaEnfria, vacioSegundaTinaEnfria,
                            velocidadHalador, limpiezaFiltroTina, alturaRotulo, espesor, diametroExterno, longitudTubo, embone, kilogramosHoras, pesoTuboMetro);
//                    form = new Formulario(2005696L,8,"prueba","prueba",getDate(),"A",111,111,2,3,4,5,6,0,1,2,3,4,5,6,7,8,9,10,11,12,
//                            13,14,15,16,17,18,"prueba",155,111,111,"111","11",
//                            "11","22",111,111,111,"11",111,"11","11",
//                            "1.1","11","11", "11",15,1,"11",111,"11");


                    ApiService apiService = ApiServiceGenerator.createService(ApiService.class);
                    Call<Formulario> call;
                    call = apiService.createForm(form);
                    call.enqueue(new Callback<Formulario>() {
                        @Override
                        public void onResponse(Call<Formulario> call, Response<Formulario> response) {

                            try {
                                if (response.isSuccessful()) {
                                    Log.d(TAG, "GUARDADO: " + response.code());

                                    Formulario f = response.body();
                                    if (f != null) {
                                        clearParametrosEditText();
                                        showConfimSaveDialog(f.getFecha());
                                        btnGuardar.setEnabled(false);
                                        progressBar.setVisibility(View.GONE);
                                    }

                                } else {
                                    progressBar.setVisibility(View.GONE);
                                    throw new Exception("Error al enviar datos. Intentar nuevamente");
                                }

                            } catch (Throwable t) {
                                try {
                                    Log.e(TAG, "onResponse-Catch: " + t.toString(), t);
                                    makeLongToast(t.getMessage());
                                } catch (Throwable x) {
                                    Log.e(TAG, "onResponse: ", x);
                                }
                            }

                        }

                        @Override
                        public void onFailure(Call<Formulario> call, Throwable t) {

                        }
                    });
                }
            });

            alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();

                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();


        } catch (NumberFormatException n) {
            Log.e(TAG, "Error : ", n);
        }

    }

    //LIMPIA LOS EDITTEXT DESPUES DE UNA ACCION
    private void clearParametrosEditText() {
        txtOil.setText("");
        txtCilindroZona1.setText("");
        txtCilindroZona2.setText("");
        txtCilindroZona3.setText("");
        txtCilindroZona4.setText("");
        txtCilindroZona5.setText("");
        txtCilindroZona6.setText("");


        /*
         **  Cabezal
         */
        txtInterna.setText("");
        txtCabezalZona1.setText("");
        txtCabezalZona2.setText("");
        txtCabezalZona3.setText("");
        txtCabezalZona4.setText("");
        txtCabezalZona5.setText("");
        txtCabezalZona6.setText("");
        txtCabezalZona7.setText("");
        txtCabezalZona8.setText("");
        txtCabezalZona9.setText("");
        txtCabezalZona10.setText("");
        txtCabezalZona11.setText("");
        txtCabezalZona12.setText("");
        txtCabezalZona13.setText("");
        txtCabezalZona14.setText("");
        txtCabezalZona15.setText("");
        txtCabezalZona16.setText("");
        txtCabezalZona17.setText("");
        txtCabezalZona18.setText("");
        txtNombreCabezal.setText("");
        txtDiametroRestrictorFiltro.setText("");
        txtRpmMotorExtrusora.setText("");
        txtAmpMotorExtrusora.setText("");
        txtRpmRevminTornillos.setText("");
        txtPorcentajeVelAlimentador.setText("");
        txtAmperajeMotorAlimentador.setText("");
        txtDesgasificadorVacio.setText("");
        txtPresionMasa.setText("");
        txtTempMasa.setText("");
        txtContrapresion.setText("");
        txtVacioPrimTina.setText("");
        txtTemperaturaPrimTinaEnfriamiento.setText("");
        txtPresionAguaPrimTinaEnfriamiento.setText("");
        txtVacioSecTinaEnfriamiento.setText("");
        txtVelocidadHalador.setText("");
        txtLimpiazaFiltroTina.setText("");
        txtAlturaRotulo.setText("");
        txtEspesor.setText("");
        txtDiametroExterno.setText("");
        txtLongitudTubo.setText("");
        txtEmbone.setText("");
        txtKilogramoxHora.setText("");
        txtPesoTuboMetro.setText("");
    }

    private void clearEstandarTextView() {
        estandarOil.setText("");
        estandarCilindroZona1.setText("");
        estandarCilindroZona2.setText("");
        estandarCilindroZona3.setText("");
        estandarCilindroZona4.setText("");
        estandarCilindroZona5.setText("");
        estandarCilindroZona6.setText("");


        /*
         **  Cabezal
         */
        estandarInterna.setText("");
        estandarCabezalZona1.setText("");
        estandarCabezalZona2.setText("");
        estandarCabezalZona3.setText("");
        estandarCabezalZona4.setText("");
        estandarCabezalZona5.setText("");
        estandarCabezalZona6.setText("");
        estandarCabezalZona7.setText("");
        estandarCabezalZona8.setText("");
        estandarCabezalZona9.setText("");
        estandarCabezalZona10.setText("");
        estandarCabezalZona11.setText("");
        estandarCabezalZona12.setText("");
        estandarCabezalZona13.setText("");
        estandarCabezalZona14.setText("");
        estandarCabezalZona15.setText("");
        estandarCabezalZona16.setText("");
        estandarCabezalZona17.setText("");
        estandarCabezalZona18.setText("");
        estandarNombreCabezal.setText("");
        estandarDiametroRestrictorFiltro.setText("");
        estandarRpmMotorExtrusora.setText("");
        estandarAmpMotorExtrusora.setText("");
        estandarRpmRevminTornillos.setText("");
        estandarPorcentajeVelAlimentador.setText("");
        estandarAmperajeMotorAlimentador.setText("");
        estandarDesgasificadorVacio.setText("");
        estandarPresionMasa.setText("");
        estandarTempMasa.setText("");
        estandarContrapresion.setText("");
        estandarVacioPrimTina.setText("");
        estandarTemperaturaPrimTinaEnfriamiento.setText("");
        estandarPresionAguaPrimTinaEnfriamiento.setText("");
        estandarVacioSecTinaEnfriamiento.setText("");
        estandarVelocidadHalador.setText("");
        estandarLimpiazaFiltroTina.setText("");
        estandarAlturaRotulo.setText("");
        estandarEspesor.setText("");
        estandarDiametroExterno.setText("");
        estandarLongitudTubo.setText("");
        estandarEmbone.setText("");
        estandarKilogramoxHora.setText("");
        estandarPesoTuboMetro.setText("");
    }

    LinearLayout linear1, linear2;
    Button btnAtras, btnSiguiente, btnAceptar;
    EditText txtDNomSupervisor, txtDNomOperario;

    private void showDatosDialog() {

        dataDialog.setContentView(R.layout.data_dialog);
        dataDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dataDialog.setCancelable(false);

        //Linears
        linear1 = dataDialog.findViewById(R.id.data_body);
        linear2 = dataDialog.findViewById(R.id.data_body2);

        linear2.setVisibility(View.GONE);

        // Variables
        btnAtras = dataDialog.findViewById(R.id.btn_atras);
        btnSiguiente = dataDialog.findViewById(R.id.btn_siguiente);
        btnAceptar = dataDialog.findViewById(R.id.btn_aceptar);
        txtDNomSupervisor = dataDialog.findViewById(R.id.txt_dSupervisor);
        txtDNomOperario = dataDialog.findViewById(R.id.txt_dOperario);


        //Button

        btnSiguiente.setEnabled(false);
        btnAceptar.setEnabled(false);


        txtDNomSupervisor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 8) {
                    btnSiguiente.setEnabled(true);
                } else {
                    btnSiguiente.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        txtDNomOperario.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 8) {
                    btnAceptar.setEnabled(true);
                } else {
                    btnAceptar.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear1.setVisibility(View.VISIBLE);
                linear2.setVisibility(View.GONE);
                btnAtras.setVisibility(View.GONE);
                btnSiguiente.setVisibility(View.VISIBLE);
                btnAceptar.setVisibility(View.GONE);
                txtDNomSupervisor.requestFocus();
            }
        });

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                linear1.setVisibility(View.GONE);
                linear2.setVisibility(View.VISIBLE);
                btnAtras.setVisibility(View.VISIBLE);
                btnSiguiente.setVisibility(View.GONE);
                btnAceptar.setVisibility(View.VISIBLE);
                txtDNomOperario.requestFocus();
            }
        });

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Obtiene los nombres y los guarda en las variables
                String nomSupervisor = txtDNomSupervisor.getText().toString();
                String nomOperario = txtDNomOperario.getText().toString();

                nombreOperario = nomOperario;
                nombreSupervisor = nomSupervisor;

                String[] supervisorPart, operarioPart;
                String supPart1, supOper1;

                try {

                    if (nomSupervisor.contains(" ") || nomOperario.contains(" ")) {
                        supervisorPart = nomSupervisor.split(" ");
                        operarioPart = nomOperario.split(" ");
                        supPart1 = supervisorPart[1];
                        supOper1 = operarioPart[1];
                    } else {
                        makeLongToast("Ingresar los nombres correctamente");
                        return;
                    }

                    txtNomSupervisor.setText(supPart1);
                    txtNomMaquinista.setText(supOper1);
                    dataDialog.dismiss();

                } catch (Exception e) {
                    Log.e(TAG, "onClick: ", e);
                    makeLongToast("Caracteres inválidos");
                }

            }
        });
        dataDialog.show();

    }

    private void showConfimSaveDialog(String fecha) {
        confirmSaveDialog.setContentView(R.layout.confirmsave_dialog);
        confirmSaveDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        confirmSaveDialog.setCancelable(false);
        Button btnAceptar = confirmSaveDialog.findViewById(R.id.btn_aceptar);
        TextView msgFechaHoraGuardao = confirmSaveDialog.findViewById(R.id.msg_cHoraGuardado);
        TextView msgHoraProximoGuardado = confirmSaveDialog.findViewById(R.id.msg_cProximaHora);

        Calendar calendar = Calendar.getInstance();
        String hora = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));

        String horaProximoGuardado = "";

        if (hora.equals("9") || hora.equals("10")) {
            horaProximoGuardado = "11:00 AM";
        } else if (hora.equals("11") || hora.equals("12")) {
            horaProximoGuardado = "1:00 PM";
        } else if (hora.equals("13") || hora.equals("14")) {
            horaProximoGuardado = "3:00 PM";
        } else if (hora.equals("15") || hora.equals("16")) {
            horaProximoGuardado = "5:00 PM";
        } else if (hora.equals("17") || hora.equals("18")) {
            horaProximoGuardado = "7:00 PM";
        } else if (hora.equals("19") || hora.equals("20")) {
            horaProximoGuardado = "9:00 PM";
        } else if (hora.equals("21") || hora.equals("22")) {
            horaProximoGuardado = "11:00 PM";
        } else if (hora.equals("23") || hora.equals("0")) {
            horaProximoGuardado = "01:00 AM ";
        } else if (hora.equals("1") || hora.equals("2")) {
            horaProximoGuardado = "03:00 AM";
        } else if (hora.equals("3") || hora.equals("4")) {
            horaProximoGuardado = "05:00 AM";
        } else if (hora.equals("5") || hora.equals("6")) {
            horaProximoGuardado = "07:00 AM";
        } else if (hora.equals("7") || hora.equals("8")) {
            horaProximoGuardado = "09:00 AM";
        }


        msgFechaHoraGuardao.setText(fecha);
        msgHoraProximoGuardado.setText(horaProximoGuardado);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmSaveDialog.dismiss();
                txtOil.requestFocus();
            }
        });
        confirmSaveDialog.show();

    }

    //Muestra un aviso para llenar el formulario
    private void showAvisoDialog() {

        avisoDialog.setContentView(R.layout.aviso_dialog);
        avisoDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button btnAceptar = avisoDialog.findViewById(R.id.btn_aceptar);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                avisoDialog.dismiss();
            }
        });
        avisoDialog.show();
    }

    //Muestra el dialogo de error al accionar el boton
    private void showErrorDialog() {

        errorDialog.setContentView(R.layout.error_dialog);
        errorDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        errorDialog.setCancelable(false);
        TextView msgError = errorDialog.findViewById(R.id.msg_error);
        msgError.setText("Parada en la maquina " + txtNumMaquina.getText().toString());
        Button btnCancelar = errorDialog.findViewById(R.id.btn_cancelar);
        Button btnEnviar = errorDialog.findViewById(R.id.btn_enviar);


        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                errorDialog.dismiss();
            }
        });

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (txtIdTubo.getText().toString().isEmpty()) {
                    makeLongToast("Código de tubo no ingresado");
                    return;
                }

                EditText txtErrorId = errorDialog.findViewById(R.id.txt_error_id);
                EditText txtErrorDescripcion = errorDialog.findViewById(R.id.txt_descripcion);

                long idTubo = Long.parseLong(txtIdTubo.getText().toString());
                int numMaquina = Integer.parseInt(txtNumMaquina.getText().toString());
                String turno = txtTurno.getText().toString();
                error_id = txtErrorId.getText().toString();
                error_descripcion = txtErrorDescripcion.getText().toString();


                Formulario form;
                form = new Formulario(idTubo, numMaquina, nombreOperario, nombreSupervisor, getDate(), turno, error_id, error_descripcion, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, "", 0, 0, 0, "", "",
                        "", "", 0, 0, 0, "", 0, "", "",
                        "", "", "", "", 0, 0, "", 0, "");


                ApiService apiService = ApiServiceGenerator.createService(ApiService.class);
                Call<Formulario> call;
                call = apiService.createForm(form);
                call.enqueue(new Callback<Formulario>() {
                    @Override
                    public void onResponse(Call<Formulario> call, Response<Formulario> response) {

                        if (response.isSuccessful()) {
                            Log.d(TAG, "GUARDADO: " + response.code());

                            Formulario f = response.body();
                            if (f != null) {
                                errorDialog.dismiss();
                                btnGuardar.setEnabled(false);
                                btn_error.setEnabled(false);
                                clearParametrosEditText();
                                showConfimSaveDialog(f.getFecha());
                            }

                        }

                    }

                    @Override
                    public void onFailure(Call<Formulario> call, Throwable t) {

                    }
                });


            }
        });

        errorDialog.show();
    }

    private void iniciarVariables() {
//        TextClock textClock = findViewById(R.id.txt_clock);
//        textClock.setFormat12Hour("hh:mm:ss a");
        btnGuardar = findViewById(R.id.btn_guardar);
        btn_error = findViewById(R.id.btn_error);
        progressBar = findViewById(R.id.progressBar);
        /*
         ** Linear's
         */
        linear6a = findViewById(R.id.linear_6a);
        linear3 = findViewById(R.id.linear_3);
        linear4 = findViewById(R.id.linear_4);
        linear5 = findViewById(R.id.linear_5);
        linear6 = findViewById(R.id.linear_6);
        linear7 = findViewById(R.id.linear_7);
        linear8 = findViewById(R.id.linear_8);
        linear9 = findViewById(R.id.linear_9);
        linear10 = findViewById(R.id.linear_10);
        linear11 = findViewById(R.id.linear_11);
        linear12 = findViewById(R.id.linear_12);
        linear13 = findViewById(R.id.linear_13);
        linear14 = findViewById(R.id.linear_14);
        linear15 = findViewById(R.id.linear_15);
        linear16 = findViewById(R.id.linear_16);
        linear17 = findViewById(R.id.linear_17);
        linear18 = findViewById(R.id.linear_18);


        /*
         ** Cabecera
         */
        txtNumMaquina = findViewById(R.id.txt_num_maquina);
        txtNomMaquinista = findViewById(R.id.txt_maquinista_nom);
        txtNomSupervisor = findViewById(R.id.txt_supervisor_nom);
        txtIdTubo = findViewById(R.id.txt_codigo_tubo);
        txtDescripcionTubo = findViewById(R.id.txt_descripcion);
        txtTurno = findViewById(R.id.txt_turno);


        /*
         **  Cilindro
         */
        txtOil = findViewById(R.id.txt_oil);
        txtCilindroZona1 = findViewById(R.id.txt_cil_zona1);
        txtCilindroZona2 = findViewById(R.id.txt_cil_zona2);
        txtCilindroZona3 = findViewById(R.id.txt_cil_zona3);
        txtCilindroZona4 = findViewById(R.id.txt_cil_zona4);
        txtCilindroZona5 = findViewById(R.id.txt_cil_zona5);
        txtCilindroZona6 = findViewById(R.id.txt_cil_zona6);


        /*
         **  Cabezal
         */
        txtInterna = findViewById(R.id.txt_interna);
        txtCabezalZona1 = findViewById(R.id.txt_cab_zona1);
        txtCabezalZona2 = findViewById(R.id.txt_cab_zona2);
        txtCabezalZona3 = findViewById(R.id.txt_cab_zona3);
        txtCabezalZona4 = findViewById(R.id.txt_cab_zona4);
        txtCabezalZona5 = findViewById(R.id.txt_cab_zona5);
        txtCabezalZona6 = findViewById(R.id.txt_cab_zona6);
        txtCabezalZona7 = findViewById(R.id.txt_cab_zona7);
        txtCabezalZona8 = findViewById(R.id.txt_cab_zona8);
        txtCabezalZona9 = findViewById(R.id.txt_cab_zona9);
        txtCabezalZona10 = findViewById(R.id.txt_cab_zona10);
        txtCabezalZona11 = findViewById(R.id.txt_cab_zona11);
        txtCabezalZona12 = findViewById(R.id.txt_cab_zona12);
        txtCabezalZona13 = findViewById(R.id.txt_cab_zona13);
        txtCabezalZona14 = findViewById(R.id.txt_cab_zona14);
        txtCabezalZona15 = findViewById(R.id.txt_cab_zona15);
        txtCabezalZona16 = findViewById(R.id.txt_cab_zona16);
        txtCabezalZona17 = findViewById(R.id.txt_cab_zona17);
        txtCabezalZona18 = findViewById(R.id.txt_cab_zona18);
        txtNombreCabezal = findViewById(R.id.txt_nombre_cabezal);
        txtDiametroRestrictorFiltro = findViewById(R.id.txt_diametro_restrictor);
        txtRpmMotorExtrusora = findViewById(R.id.txt_rpm_motor_extr);
        txtAmpMotorExtrusora = findViewById(R.id.txt_amperaje_motor_ext);
        txtRpmRevminTornillos = findViewById(R.id.txt_rpm_revmin_tornillos);
        txtPorcentajeVelAlimentador = findViewById(R.id.txt_porcentaje_vel_alimentador);
        txtAmperajeMotorAlimentador = findViewById(R.id.txt_amperaje_motor_alimen);
        txtDesgasificadorVacio = findViewById(R.id.txt_desgasificador_vacio);
        txtPresionMasa = findViewById(R.id.txt_presion_masa);
        txtTempMasa = findViewById(R.id.txt_temp_masa);
        txtContrapresion = findViewById(R.id.txt_contrapresion);
        txtVacioPrimTina = findViewById(R.id.txt_vacio_prim_tina);
        txtTemperaturaPrimTinaEnfriamiento = findViewById(R.id.txt_temp_primtina_enfriamiento);
        txtPresionAguaPrimTinaEnfriamiento = findViewById(R.id.txt_presion_agua_primtina);
        txtVacioSecTinaEnfriamiento = findViewById(R.id.txt_vacio_seguntina);
        txtVelocidadHalador = findViewById(R.id.txt_velocidar_halador);
        txtLimpiazaFiltroTina = findViewById(R.id.txt_limpieza_filtro_tina);
        txtAlturaRotulo = findViewById(R.id.txt_altura_rotulo);
        txtEspesor = findViewById(R.id.txt_espesor);
        txtDiametroExterno = findViewById(R.id.txt_diametro_externo);
        txtLongitudTubo = findViewById(R.id.txt_longitud_tubo);
        txtEmbone = findViewById(R.id.txt_embone);
        txtKilogramoxHora = findViewById(R.id.txt_kilogramos_horas);
        txtPesoTuboMetro = findViewById(R.id.txt_peso_tubo_metro);

        txtDesgasificadorVacio.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    txtDesgasificadorVacio.setSelection(1);
                }
            }
        });


        txtVacioPrimTina.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {

                    txtVacioPrimTina.setSelection(1);
                }

            }
        });

        txtVacioSecTinaEnfriamiento.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    txtVacioSecTinaEnfriamiento.setSelection(1);
                }
            }
        });




        /*
         **  ESTANDAR Cilindro
         */
        estandarOil = findViewById(R.id.estandar_oil);
        estandarCilindroZona1 = findViewById(R.id.estandar_cil_zona1);
        estandarCilindroZona2 = findViewById(R.id.estandar_cil_zona2);
        estandarCilindroZona3 = findViewById(R.id.estandar_cil_zona3);
        estandarCilindroZona4 = findViewById(R.id.estandar_cil_zona4);
        estandarCilindroZona5 = findViewById(R.id.estandar_cil_zona5);
        estandarCilindroZona6 = findViewById(R.id.estandar_cil_zona6);


        /*
         **  ESTANDAR CABEZAL
         */
        estandarInterna = findViewById(R.id.estandar_interna);
        estandarCabezalZona1 = findViewById(R.id.estandar_cab_zona1);
        estandarCabezalZona2 = findViewById(R.id.estandar_cab_zona2);
        estandarCabezalZona3 = findViewById(R.id.estandar_cab_zona3);
        estandarCabezalZona4 = findViewById(R.id.estandar_cab_zona4);
        estandarCabezalZona5 = findViewById(R.id.estandar_cab_zona5);
        estandarCabezalZona6 = findViewById(R.id.estandar_cab_zona6);
        estandarCabezalZona7 = findViewById(R.id.estandar_cab_zona7);
        estandarCabezalZona8 = findViewById(R.id.estandar_cab_zona8);
        estandarCabezalZona9 = findViewById(R.id.estandar_cab_zona9);
        estandarCabezalZona10 = findViewById(R.id.estandar_cab_zona10);
        estandarCabezalZona11 = findViewById(R.id.estandar_cab_zona11);
        estandarCabezalZona12 = findViewById(R.id.estandar_cab_zona12);
        estandarCabezalZona13 = findViewById(R.id.estandar_cab_zona13);
        estandarCabezalZona14 = findViewById(R.id.estandar_cab_zona14);
        estandarCabezalZona15 = findViewById(R.id.estandar_cab_zona15);
        estandarCabezalZona16 = findViewById(R.id.estandar_cab_zona16);
        estandarCabezalZona17 = findViewById(R.id.estandar_cab_zona17);
        estandarCabezalZona18 = findViewById(R.id.estandar_cab_zona18);

        estandarNombreCabezal = findViewById(R.id.estandar_nombre_cabezal);
        estandarDiametroRestrictorFiltro = findViewById(R.id.estandar_diametro_restrictor);
        estandarRpmMotorExtrusora = findViewById(R.id.estandar_rpm_motor_extr);
        estandarAmpMotorExtrusora = findViewById(R.id.estandar_amperaje_motor_ext);
        estandarRpmRevminTornillos = findViewById(R.id.estandar_rpm_revmin_tornillos);
        estandarPorcentajeVelAlimentador = findViewById(R.id.estandar_porcentaje_vel_alimentador);
        estandarAmperajeMotorAlimentador = findViewById(R.id.estandar_amperaje_motor_alimen);
        estandarDesgasificadorVacio = findViewById(R.id.estandar_desgasificador_vacio);
        estandarPresionMasa = findViewById(R.id.estandar_presion_masa);
        estandarTempMasa = findViewById(R.id.estandar_temp_masa);
        estandarContrapresion = findViewById(R.id.estandar_contrapresion);
        estandarVacioPrimTina = findViewById(R.id.estandar_vacio_prim_tina);
        estandarTemperaturaPrimTinaEnfriamiento = findViewById(R.id.estandar_temp_primtina_enfriamiento);
        estandarPresionAguaPrimTinaEnfriamiento = findViewById(R.id.estandar_presion_agua_primtina);
        estandarVacioSecTinaEnfriamiento = findViewById(R.id.estandar_vacio_seguntina);
        estandarVelocidadHalador = findViewById(R.id.estandar_velocidar_halador);
        estandarLimpiazaFiltroTina = findViewById(R.id.estandar_limpieza_filtro_tina);
        estandarAlturaRotulo = findViewById(R.id.estandar_altura_rotulo);
        estandarEspesor = findViewById(R.id.estandar_espesor);
        estandarDiametroExterno = findViewById(R.id.estandar_diametro_externo);
        estandarLongitudTubo = findViewById(R.id.estandar_longitud_tubo);
        estandarEmbone = findViewById(R.id.estandar_embone);
        estandarKilogramoxHora = findViewById(R.id.estandar_kilogramos_horas);
        estandarPesoTuboMetro = findViewById(R.id.estandar_peso_tubo_metro);
    }


    //OBTIENE LA DESCRIPCION DEL TUBO INGRESADO POR ID
    private void obtenerDescripcionTubo() {

        try{
            long idTubo = Long.parseLong(txtIdTubo.getText().toString());
            ApiService apiService = ApiServiceGenerator.createService(ApiService.class);
            Call<Tubo> call;
            call = apiService.getTubo(idTubo);
            call.enqueue(new Callback<Tubo>() {
                @Override
                public void onResponse(Call<Tubo> call, @NonNull Response<Tubo> response) {

                    try {

                        Log.d(TAG, "HTTP status code: " + response.code());

                        if (response.isSuccessful()) {

                            Tubo tubo = response.body();

                            if (tubo != null) {
                                String descripcion = tubo.getDescripcion_tubo();
                                txtDescripcionTubo.setText(descripcion);
                                txtDescripcionTubo.setTextColor(getResources().getColor(R.color.colorBlue));
                                obtenerIndicadores();

                            }


                        } else {

                            Log.e(TAG, "onResponseError: " + response.errorBody().toString());
                            throw new Exception("Ingresar código de tubo valido");
                        }

                    } catch (Throwable t) {
                        try {
                            Log.e(TAG, "onResponse-Catch: " + t.toString(), t);
                            makeLongToast(t.getMessage());
                            txtDescripcionTubo.setText(t.getMessage());
                            txtDescripcionTubo.setTextColor(getResources().getColor(R.color.colorAccent));


                        } catch (Throwable x) {
                            Log.e(TAG, "onResponse: ", x);
                        }

                    }

                }

                @Override
                public void onFailure(Call<Tubo> call, Throwable t) {

                    Log.e(TAG, "onFailure: " + t.toString());
                    makeLongToast("ERROR EN EL SERVICIO: PORFAVOR ACTIVARLO");


                }
            });

        }catch (NumberFormatException e){
            Log.e(TAG, "obtenerDescripcionTubo: ",e );
        }


    }


    //OBTIENE LOS MAXIMOS INDICADORES DEL TUBO INGRESADO POR ID
    private void obtenerIndicadores() {

        long idForm = Long.parseLong(txtIdTubo.getText().toString());
        ApiService apiService = ApiServiceGenerator.createService(ApiService.class);
        Call<Formulario> call;
        call = apiService.getForm(idForm);
        call.enqueue(new Callback<Formulario>() {
            @Override
            public void onResponse(Call<Formulario> call, @NonNull Response<Formulario> response) {


                try {
                    Log.d(TAG, "formulario: " + response.code());



                    if(response.code() == NOT_FOUND){
                        makeLongToast("Ingresar datos nuevos");
                        return;
                    }

                    if (response.isSuccessful()) {

                        Formulario form = response.body();

                        if (form != null) {

                            if (form.getCilindro_oil() == null) {
                                estandarOil.setText("");
                            } else {
                                estandarOil.setText(String.valueOf(form.getCilindro_oil()));
                            }

                            estandarCilindroZona1.setText(String.valueOf(form.getCilindro_zona_1()));
                            estandarCilindroZona2.setText(String.valueOf(form.getCilindro_zona_2()));
                            estandarCilindroZona3.setText(String.valueOf(form.getCilindro_zona_3()));
                            estandarCilindroZona4.setText(String.valueOf(form.getCilindro_zona_4()));
                            estandarCilindroZona5.setText(String.valueOf(form.getCilindro_zona_5()));

                            if (form.getCilindro_zona_6() == null) {
                                linear6a.setVisibility(View.GONE);
                            } else {
                                linear6a.setVisibility(View.VISIBLE);
                                estandarCilindroZona6.setText(String.valueOf(form.getCilindro_zona_6()));
                            }


                            estandarInterna.setText(String.valueOf(form.getCabezal_interna()));
                            estandarCabezalZona1.setText(String.valueOf(form.getCabezal_zona_1()));
                            estandarCabezalZona2.setText(String.valueOf(form.getCabezal_zona_2()));

                            if (form.getCabezal_zona_3() == null) {
                                linear3.setVisibility(View.GONE);
                            } else {
                                linear3.setVisibility(View.VISIBLE);
                                estandarCabezalZona3.setText(String.valueOf(form.getCabezal_zona_3()));
                            }

                            if (form.getCabezal_zona_4() == null) {
                                linear4.setVisibility(View.GONE);
                            } else {
                                linear4.setVisibility(View.VISIBLE);
                                estandarCabezalZona4.setText(String.valueOf(form.getCabezal_zona_4()));
                            }

                            if (form.getCabezal_zona_5() == null) {
                                linear5.setVisibility(View.GONE);
                            } else {
                                linear5.setVisibility(View.VISIBLE);
                                estandarCabezalZona5.setText(String.valueOf(form.getCabezal_zona_5()));
                            }

                            if (form.getCabezal_zona_6() == null) {
                                linear6.setVisibility(View.GONE);
                            } else {
                                linear6.setVisibility(View.VISIBLE);
                                estandarCabezalZona6.setText(String.valueOf(form.getCabezal_zona_6()));
                            }

                            if (form.getCabezal_zona_7() == null) {
                                linear7.setVisibility(View.GONE);
                            } else {
                                linear7.setVisibility(View.VISIBLE);
                                estandarCabezalZona7.setText(String.valueOf(form.getCabezal_zona_7()));
                            }
                            if (form.getCabezal_zona_8() == null) {
                                linear8.setVisibility(View.GONE);
                            } else {
                                linear8.setVisibility(View.VISIBLE);
                                estandarCabezalZona8.setText(String.valueOf(form.getCabezal_zona_8()));
                            }

                            if (form.getCabezal_zona_9() == null) {
                                linear9.setVisibility(View.GONE);
                            } else {
                                linear9.setVisibility(View.VISIBLE);
                                estandarCabezalZona9.setText(String.valueOf(form.getCabezal_zona_9()));
                            }

                            if (form.getCabezal_zona_10() == null) {
                                linear10.setVisibility(View.GONE);
                            } else {
                                linear10.setVisibility(View.VISIBLE);
                                estandarCabezalZona10.setText(String.valueOf(form.getCabezal_zona_10()));
                            }

                            if (form.getCabezal_zona_11() == null) {
                                linear11.setVisibility(View.GONE);
                            } else {
                                linear11.setVisibility(View.VISIBLE);
                                estandarCabezalZona11.setText(String.valueOf(form.getCabezal_zona_11()));
                            }

                            if (form.getCabezal_zona_12() == null) {
                                linear12.setVisibility(View.GONE);
                            } else {
                                linear12.setVisibility(View.VISIBLE);
                                estandarCabezalZona12.setText(String.valueOf(form.getCabezal_zona_12()));
                            }

                            if (form.getCabezal_zona_13() == null) {
                                linear13.setVisibility(View.GONE);
                            } else {
                                linear13.setVisibility(View.VISIBLE);
                                estandarCabezalZona13.setText(String.valueOf(form.getCabezal_zona_13()));
                            }

                            if (form.getCabezal_zona_14() == null) {
                                linear14.setVisibility(View.GONE);
                            } else {
                                linear14.setVisibility(View.VISIBLE);
                                estandarCabezalZona14.setText(String.valueOf(form.getCabezal_zona_14()));
                            }

                            if (form.getCabezal_zona_15() == null) {
                                linear15.setVisibility(View.GONE);
                            } else {
                                linear15.setVisibility(View.VISIBLE);
                                estandarCabezalZona15.setText(String.valueOf(form.getCabezal_zona_15()));
                            }

                            if (form.getCabezal_zona_16() == null) {
                                linear16.setVisibility(View.GONE);
                            } else {
                                linear16.setVisibility(View.VISIBLE);
                                estandarCabezalZona16.setText(String.valueOf(form.getCabezal_zona_16()));
                            }

                            if (form.getCabezal_zona_17() == null) {
                                linear17.setVisibility(View.GONE);
                            } else {
                                linear17.setVisibility(View.VISIBLE);
                                estandarCabezalZona17.setText(String.valueOf(form.getCabezal_zona_17()));
                            }

                            if (form.getCabezal_zona_18() == null) {
                                linear18.setVisibility(View.GONE);
                            } else {
                                linear18.setVisibility(View.VISIBLE);
                                estandarCabezalZona18.setText(String.valueOf(form.getCabezal_zona_18()));
                            }

                            estandarNombreCabezal.setText(form.getNombre_cabezal());
                            estandarDiametroRestrictorFiltro.setText(String.valueOf(form.getDiametro_restrictor_filtro()));
                            estandarRpmMotorExtrusora.setText(String.valueOf(form.getRpm_motorExtrusora()));
                            estandarAmpMotorExtrusora.setText(String.valueOf(form.getAmperaje_motorExtrusora()));
                            estandarRpmRevminTornillos.setText(String.valueOf(form.getRpm_revMin_tornillos()));
                            estandarPorcentajeVelAlimentador.setText(String.valueOf(form.getPorcentaje_velocidad_alimentador()));
                            estandarAmperajeMotorAlimentador.setText(form.getAmperaje_motor_alimentador());
                            estandarDesgasificadorVacio.setText(form.getDesgasificador_vacio());
                            estandarPresionMasa.setText(String.valueOf(form.getPresion_masa()));
                            estandarTempMasa.setText(String.valueOf(form.getTemperatura_masa()));
                            if (form.getContrapresion() == null) {
                                estandarContrapresion.setText("");
                            } else {
                                estandarContrapresion.setText(String.valueOf(form.getContrapresion()));
                            }

                            estandarVacioPrimTina.setText(form.getVacio_primera_tina());
                            estandarTemperaturaPrimTinaEnfriamiento.setText(String.valueOf(form.getTemperatura_primera_tina_enfria()));
                            estandarPresionAguaPrimTinaEnfriamiento.setText(form.getPresion_agua_primera_tina_enfria());
                            estandarVacioSecTinaEnfriamiento.setText(form.getVacio_segunda_tina_enfria());
                            estandarVelocidadHalador.setText(String.valueOf(form.getVelocidad_halador()));
                            estandarLimpiazaFiltroTina.setText(form.getLimpieza_filtro_tina());
                            estandarAlturaRotulo.setText(form.getAltura_rotulo());
                            estandarEspesor.setText(String.valueOf(form.getEspesor()));
                            estandarDiametroExterno.setText(String.valueOf(form.getDiametro_externo()));
                            estandarLongitudTubo.setText(String.valueOf(form.getLongitud_tubo()));
                            estandarEmbone.setText(form.getEmbone());
                            estandarKilogramoxHora.setText(String.valueOf(form.getKilogramos_horas()));
                            estandarPesoTuboMetro.setText(form.getPeso_tubo_metro());
                        }else{
                            makeLongToast("Ingresar nuevos datos");
                        }


                    } else {

                        Log.e(TAG, "onResponseError: " + response.errorBody().toString());
                        throw new Exception("Ingresar código de tubo valido");
                    }

                } catch (Throwable t) {
                    try {
                        Log.e(TAG, "onResponse-Catch: " + t.toString(), t);
                        makeLongToast(t.getMessage());
                    } catch (Throwable x) {
                        Log.e(TAG, "onResponse: ", x);
                    }
                }


            }

            @Override
            public void onFailure(Call<Formulario> call, Throwable t) {
                Log.d(TAG, "onFailure: ERRRRRRORR");
            }
        });
    }


    //CODIGO PARA INICIAR AUTOMATICAMENTE
//        Runnable runnable = new Runnable() {
//            public void run() {
//            }
//        };
//
//        Handler handler = new android.os.Handler();
//        handler.postDelayed(runnable, 1000);

    private void cambiarTurno() {

        new Thread() {
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        txtTurno.post(new Runnable() {
                            public void run() {

                                Calendar hoy = Calendar.getInstance();
                                int hour = hoy.get(Calendar.HOUR_OF_DAY);
                                int min = hoy.get(Calendar.MINUTE);
                                int sec = hoy.get(Calendar.SECOND);
                                String h = String.valueOf(hour) + "" + String.valueOf(min) + "" + String.valueOf(sec);


//                                Log.d(TAG, "run: " + h);
                                if (h.equals("8590") || h.equals("10590") || h.equals("12590") || h.equals("16590") || h.equals("18590") ||
                                        h.equals("20590") || h.equals("0590") || h.equals("2590") || h.equals("4590")) {
                                    showAvisoDialog();
                                    btnGuardar.setEnabled(true);
                                    btn_error.setEnabled(true);
                                }

                                if (h.equals("6590") || h.equals("14590") || h.equals("22590")) {
                                    txtIdTubo.getText().clear();
                                    btnGuardar.setEnabled(true);
                                    clearEstandarTextView();
                                    clearParametrosEditText();
                                    showDatosDialog();
                                }


                                if (hour >= 7 && hour < 15) {
                                    txtTurno.setText("A");
                                } else if (hour >= 15 && hour < 23) {
                                    txtTurno.setText("B");
                                } else if (hour >= 23) {
                                    txtTurno.setText("C");
                                }
                            }
                        });
                    }
                } catch (InterruptedException e) {
                    Log.e(TAG, "run: ", e);
                }
            }
        }.start();


//        Runnable runnable = new Runnable() {
//            public void run() {
//                Log.d(TAG, "EJECUTANDOSE: CAMBIO DE TURNO ");
//                String hora = cambiarTurno();
//
//
//                if(hora.equals("04:00:00 p.mm")){
//                    txtTurno.setText("B");
//                }
//
//            }
//        };
//
//        Handler handler = new android.os.Handler();
//        handler.postDelayed(runnable, 1000);

    }


    //AUTOCLICK CODIGO
//        int noOfSecond = 1;
//        new Handler().postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                //TODO Set your button auto perform click.
//                btnGuardar.performClick();
//                txtDescripcionTubo.setText("Prueba funciono");
//            }
//        }, noOfSecond * 1000);


    //ESCUCHA LOS CAMBIOS DE LOS EDIT TEXT
    private void editTextsListener() {

        txtDesgasificadorVacio.setText("-");
        txtVacioPrimTina.setText("-");
        txtVacioSecTinaEnfriamiento.setText("-");

        txtIdTubo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence c, int i, int i1, int i2) {

                txtDescripcionTubo.setText("");

                if (c.length() == 7 || c.length() == 8) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            obtenerDescripcionTubo();
                        }
                    }, 2000);

                } else {
                    clearEstandarTextView();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        txtDesgasificadorVacio.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (charSequence.toString().isEmpty()) {
                    txtDesgasificadorVacio.setText("-");
                    txtDesgasificadorVacio.setSelection(1);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        txtVacioPrimTina.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String vacio1 = txtVacioPrimTina.getText().toString();
                if (vacio1.isEmpty()) {
                    txtVacioPrimTina.setText("-");
                    txtVacioPrimTina.setSelection(1);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        txtVacioSecTinaEnfriamiento.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String vacio2 = txtVacioSecTinaEnfriamiento.getText().toString();

                if (vacio2.isEmpty()) {
                    txtVacioSecTinaEnfriamiento.setText("-");
                    txtVacioSecTinaEnfriamiento.setSelection(1);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }


    private void makeLongToast(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
    }

    private void makeShortToast(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    private String getDate() {

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        return dateFormat.format(date);
    }

}
