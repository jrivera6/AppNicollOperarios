package pe.jrivera6.nicollperuoperarios.activities;

import android.app.Dialog;
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
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

    private Dialog errorDialog, avisoDialog;
    private LinearLayout linear6a, linear3, linear4, linear5, linear6, linear7, linear8, linear9, linear10, linear11, linear12, linear13, linear14, linear15, linear16, linear17, linear18;

    //Cabecera
    private TextView txtNumMaquina, txtDescripcionTubo, txtTurno;
    private EditText txtIdTubo, txtNomMaquinista, txtNomSupervisor;
    private ImageView btn_error;


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
        final Button btnGuardar = findViewById(R.id.btn_guardar);


        //Inicializa las variables por findById
        iniciarVariables();




        /*
         ** CODIGO FUNCIONAL
         */

        //Listener EditText
        editTextsListener();

        //Cambiar Turno
        cambiarTurno();


        btn_error.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showErrorDialog();
            }
        });



    }

    //GUARDA DATOS DEL FORMULARIO
    public void guardarFormulario(View view){

        //VariablesCilindro
        int oil = 0, cilindroZona1 = 0, cilindroZona2 = 0, cilindroZona3 = 0, cilindroZona4 = 0, cilindroZona5 = 0, cilindroZona6 = 0;

        //VariablesCabezal
        int interna = 0, cabezalZona1 = 0, cabezalZona2 = 0, cabezalZona3 = 0, cabezalZona4 = 0, cabezalZona5 = 0, cabezalZona6 = 0, cabezalZona7 = 0,
                cabezalZona8 = 0, cabezalZona9 = 0, cabezalZona10 = 0, cabezalZona11 = 0, cabezalZona12 = 0, cabezalZona13 = 0, cabezalZona14 = 0,
                cabezalZona15 = 0, cabezalZona16 = 0, cabezalZona17 = 0, cabezalZona18 = 0;

        int diametroRestrictorFiltro = 0, rpmMotorExtrusora = 0, amperajeMotorExtrusora = 0, presionMasa = 0, temperaturaMasa = 0,
                contrapresion = 0, tempPrimeraTinaEnfria = 0, diametroExterno = 0, longitudTubo = 0, kilogramosHoras = 0;

        try {

            if (txtIdTubo.getText().toString().isEmpty() || txtNomMaquinista.getText().toString().isEmpty() || txtNomSupervisor.getText().toString().isEmpty()) {
                makeLongToast("Registro incompleto");
                return;
            }

            /* CILINDRO */

            if (!txtOil.getText().toString().isEmpty()) {
                oil = Integer.parseInt(txtOil.getText().toString());
            }

            if (!txtCilindroZona1.getText().toString().isEmpty()) {
                cilindroZona1 = Integer.parseInt(txtCilindroZona1.getText().toString());
            }

            if (!txtCilindroZona2.getText().toString().isEmpty()) {
                cilindroZona2 = Integer.parseInt(txtCilindroZona2.getText().toString());
            }

            if (!txtCilindroZona3.getText().toString().isEmpty()) {
                cilindroZona3 = Integer.parseInt(txtCilindroZona3.getText().toString());
            }

            if (!txtCilindroZona4.getText().toString().isEmpty()) {
                cilindroZona4 = Integer.parseInt(txtCilindroZona4.getText().toString());

            }

            if (!txtCilindroZona5.getText().toString().isEmpty()) {
                cilindroZona5 = Integer.parseInt(txtCilindroZona5.getText().toString());
            }

            if (!txtCilindroZona6.getText().toString().isEmpty()) {
                cilindroZona6 = Integer.parseInt(txtCilindroZona6.getText().toString());
            }


            /* CABEZAL */

            if (!txtInterna.getText().toString().isEmpty()) {
                interna = Integer.parseInt(txtInterna.getText().toString());
            }

            if (!txtCabezalZona1.getText().toString().isEmpty()) {
                cabezalZona1 = Integer.parseInt(txtCabezalZona1.getText().toString());
            }

            if (!txtCabezalZona2.getText().toString().isEmpty()) {
                cabezalZona2 = Integer.parseInt(txtCabezalZona2.getText().toString());
            }

            if (!txtCabezalZona3.getText().toString().isEmpty()) {
                cabezalZona3 = Integer.parseInt(txtCabezalZona3.getText().toString());
            }

            if (!txtCabezalZona4.getText().toString().isEmpty()) {
                cabezalZona4 = Integer.parseInt(txtCabezalZona4.getText().toString());
            }

            if (!txtCabezalZona5.getText().toString().isEmpty()) {
                cabezalZona5 = Integer.parseInt(txtCabezalZona5.getText().toString());
            }

            if (!txtCabezalZona6.getText().toString().isEmpty()) {
                cabezalZona6 = Integer.parseInt(txtCabezalZona6.getText().toString());
            }

            if (!txtCabezalZona7.getText().toString().isEmpty()) {
                cabezalZona7 = Integer.parseInt(txtCabezalZona7.getText().toString());
            }

            if (!txtCabezalZona8.getText().toString().isEmpty()) {
                cabezalZona8 = Integer.parseInt(txtCabezalZona8.getText().toString());
            }

            if (!txtCabezalZona9.getText().toString().isEmpty()) {
                cabezalZona9 = Integer.parseInt(txtCabezalZona9.getText().toString());
            }

            if (!txtCabezalZona10.getText().toString().isEmpty()) {
                cabezalZona10 = Integer.parseInt(txtCabezalZona10.getText().toString());
            }

            if (!txtCabezalZona11.getText().toString().isEmpty()) {
                cabezalZona11 = Integer.parseInt(txtCabezalZona11.getText().toString());
            }

            if (!txtCabezalZona12.getText().toString().isEmpty()) {
                cabezalZona12 = Integer.parseInt(txtCabezalZona12.getText().toString());
            }

            if (!txtCabezalZona13.getText().toString().isEmpty()) {
                cabezalZona13 = Integer.parseInt(txtCabezalZona13.getText().toString());
            }

            if (!txtCabezalZona14.getText().toString().isEmpty()) {
                cabezalZona14 = Integer.parseInt(txtCabezalZona14.getText().toString());
            }

            if (!txtCabezalZona15.getText().toString().isEmpty()) {
                cabezalZona15 = Integer.parseInt(txtCabezalZona15.getText().toString());
            }

            if (!txtCabezalZona16.getText().toString().isEmpty()) {
                cabezalZona16 = Integer.parseInt(txtCabezalZona16.getText().toString());
            }

            if (!txtCabezalZona17.getText().toString().isEmpty()) {
                cabezalZona17 = Integer.parseInt(txtCabezalZona17.getText().toString());
            }

            if (!txtCabezalZona18.getText().toString().isEmpty()) {
                cabezalZona18 = Integer.parseInt(txtCabezalZona18.getText().toString());
            }

            if (!txtDiametroRestrictorFiltro.getText().toString().isEmpty()) {
                diametroRestrictorFiltro = Integer.parseInt(txtDiametroRestrictorFiltro.getText().toString());
            }

            if (!txtRpmMotorExtrusora.getText().toString().isEmpty()) {
                rpmMotorExtrusora = Integer.parseInt(txtRpmMotorExtrusora.getText().toString());
            }

            if (!txtAmpMotorExtrusora.getText().toString().isEmpty()) {
                amperajeMotorExtrusora = Integer.parseInt(txtAmpMotorExtrusora.getText().toString());
            }

            if (!txtPresionMasa.getText().toString().isEmpty()) {
                presionMasa = Integer.parseInt(txtPresionMasa.getText().toString());
            }

            if (!txtTempMasa.getText().toString().isEmpty()) {
                temperaturaMasa = Integer.parseInt(txtTempMasa.getText().toString());
            }

            if (!txtContrapresion.getText().toString().isEmpty()) {
                contrapresion = Integer.parseInt(txtContrapresion.getText().toString());
            }

            if (!txtTemperaturaPrimTinaEnfriamiento.getText().toString().isEmpty()) {
                tempPrimeraTinaEnfria = Integer.parseInt(txtTemperaturaPrimTinaEnfriamiento.getText().toString());
            }

            if (!txtDiametroExterno.getText().toString().isEmpty()) {
                diametroExterno = Integer.parseInt(txtDiametroExterno.getText().toString());
            }

            if (!txtLongitudTubo.getText().toString().isEmpty()) {
                longitudTubo = Integer.parseInt(txtLongitudTubo.getText().toString());
            }

            if (!txtKilogramoxHora.getText().toString().isEmpty()) {
                kilogramosHoras = Integer.parseInt(txtKilogramoxHora.getText().toString());
            }


            Long idTubo = Long.parseLong(txtIdTubo.getText().toString());
            Integer numMaquina = Integer.parseInt(txtNumMaquina.getText().toString());
            String nomMaquinista = txtNomMaquinista.getText().toString();
            String nomSupervisor = txtNomSupervisor.getText().toString();
            String turno = txtTurno.getText().toString();
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


            Formulario form;
            form = new Formulario(idTubo, numMaquina, nomMaquinista, nomSupervisor, getDate(), turno, oil, cilindroZona1, cilindroZona2, cilindroZona3, cilindroZona4, cilindroZona5, cilindroZona6, interna, cabezalZona1, cabezalZona2, cabezalZona3, cabezalZona4, cabezalZona5, cabezalZona6, cabezalZona7, cabezalZona8, cabezalZona9, cabezalZona10, cabezalZona11, cabezalZona12,
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

                    if (response.isSuccessful()) {
                        Log.d(TAG, "GUARDADO: " + response.code());


                    }


                }

                @Override
                public void onFailure(Call<Formulario> call, Throwable t) {

                }
            });

        } catch (NumberFormatException n) {
            Log.e(TAG, "Error : ", n);
        }

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
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                errorDialog.dismiss();
            }
        });
        errorDialog.show();
    }

    private void iniciarVariables() {
        TextClock textClock = findViewById(R.id.txt_clock);
        textClock.setFormat12Hour("hh:mm:ss a");

        btn_error = findViewById(R.id.btn_error);

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


                                Log.d(TAG, "run: " + h);
                                if (h.equals("6590")|| h.equals("8590") || h.equals("10590") || h.equals("12590") || h.equals("14590") || h.equals("16590") || h.equals("18590")||
                                        h.equals("20590")|| h.equals("22590") || h.equals("0590") || h.equals("2590") || h.equals("4590")) {
                                    showAvisoDialog();
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

                if (c.length() == 7) {
                    obtenerDescripcionTubo();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

                Log.d(TAG, "afterTextChanged: " + editable.toString());

            }
        });


        txtDesgasificadorVacio.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                Log.d(TAG, "onTextChanged: " + charSequence);
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
