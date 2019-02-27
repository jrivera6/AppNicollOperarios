package pe.jrivera6.nicollperuoperarios.models;

public class Formulario {

    private Long id;
    private Long tubo_id;
    private Integer numero_maquina;
    private String nombre_maquinista;
    private String nombre_supervisor;
    private String fecha;
    private String turno;
    private String error_id;
    private String error_descripcion;
    private Integer cilindro_oil;
    private Integer cilindro_zona_1;
    private Integer cilindro_zona_2;
    private Integer cilindro_zona_3;
    private Integer cilindro_zona_4;
    private Integer cilindro_zona_5;
    private Integer cilindro_zona_6;
    private Integer cabezal_interna;
    private Integer cabezal_zona_1;
    private Integer cabezal_zona_2;
    private Integer cabezal_zona_3;
    private Integer cabezal_zona_4;
    private Integer cabezal_zona_5;
    private Integer cabezal_zona_6;
    private Integer cabezal_zona_7;
    private Integer cabezal_zona_8;
    private Integer cabezal_zona_9;
    private Integer cabezal_zona_10;
    private Integer cabezal_zona_11;
    private Integer cabezal_zona_12;
    private Integer cabezal_zona_13;
    private Integer cabezal_zona_14;
    private Integer cabezal_zona_15;
    private Integer cabezal_zona_16;
    private Integer cabezal_zona_17;
    private Integer cabezal_zona_18;
    private String nombre_cabezal;
    private Integer diametro_restrictor_filtro;
    private Integer rpm_motorExtrusora;
    private Integer amperaje_motorExtrusora;
    private String rpm_revMin_tornillos;
    private String porcentaje_velocidad_alimentador;
    private String amperaje_motor_alimentador;
    private String desgasificador_vacio;
    private Integer presion_masa;
    private Integer temperatura_masa;
    private Integer contrapresion;
    private String vacio_primera_tina;
    private Integer temperatura_primera_tina_enfria;
    private String presion_agua_primera_tina_enfria;
    private String vacio_segunda_tina_enfria;
    private String velocidad_halador;
    private String limpieza_filtro_tina;
    private String altura_rotulo;
    private String espesor;
    private Integer diametro_externo;
    private Integer longitud_tubo;
    private String embone;
    private Integer kilogramos_horas;
    private String peso_tubo_metro;

    public Formulario() {
    }

    public Formulario(Long tubo_id, Integer numero_maquina, String nombre_maquinista, String nombre_supervisor, String fecha, String turno, String error_id, String error_descripcion, Integer cilindro_oil, Integer cilindro_zona_1, Integer cilindro_zona_2, Integer cilindro_zona_3, Integer cilindro_zona_4, Integer cilindro_zona_5, Integer cilindro_zona_6, Integer cabezal_interna, Integer cabezal_zona_1, Integer cabezal_zona_2, Integer cabezal_zona_3, Integer cabezal_zona_4, Integer cabezal_zona_5, Integer cabezal_zona_6, Integer cabezal_zona_7, Integer cabezal_zona_8, Integer cabezal_zona_9, Integer cabezal_zona_10, Integer cabezal_zona_11, Integer cabezal_zona_12, Integer cabezal_zona_13, Integer cabezal_zona_14, Integer cabezal_zona_15, Integer cabezal_zona_16, Integer cabezal_zona_17, Integer cabezal_zona_18, String nombre_cabezal, Integer diametro_restrictor_filtro, Integer rpm_motorExtrusora, Integer amperaje_motorExtrusora, String rpm_revMin_tornillos, String porcentaje_velocidad_alimentador, String amperaje_motor_alimentador, String desgasificador_vacio, Integer presion_masa, Integer temperatura_masa, Integer contrapresion, String vacio_primera_tina, Integer temperatura_primera_tina_enfria, String presion_agua_primera_tina_enfria, String vacio_segunda_tina_enfria, String velocidad_halador, String limpieza_filtro_tina, String altura_rotulo, String espesor, Integer diametro_externo, Integer longitud_tubo, String embone, Integer kilogramos_horas, String peso_tubo_metro) {
        this.tubo_id = tubo_id;
        this.numero_maquina = numero_maquina;
        this.nombre_maquinista = nombre_maquinista;
        this.nombre_supervisor = nombre_supervisor;
        this.fecha = fecha;
        this.turno = turno;
        this.error_id = error_id;
        this.error_descripcion = error_descripcion;
        this.cilindro_oil = cilindro_oil;
        this.cilindro_zona_1 = cilindro_zona_1;
        this.cilindro_zona_2 = cilindro_zona_2;
        this.cilindro_zona_3 = cilindro_zona_3;
        this.cilindro_zona_4 = cilindro_zona_4;
        this.cilindro_zona_5 = cilindro_zona_5;
        this.cilindro_zona_6 = cilindro_zona_6;
        this.cabezal_interna = cabezal_interna;
        this.cabezal_zona_1 = cabezal_zona_1;
        this.cabezal_zona_2 = cabezal_zona_2;
        this.cabezal_zona_3 = cabezal_zona_3;
        this.cabezal_zona_4 = cabezal_zona_4;
        this.cabezal_zona_5 = cabezal_zona_5;
        this.cabezal_zona_6 = cabezal_zona_6;
        this.cabezal_zona_7 = cabezal_zona_7;
        this.cabezal_zona_8 = cabezal_zona_8;
        this.cabezal_zona_9 = cabezal_zona_9;
        this.cabezal_zona_10 = cabezal_zona_10;
        this.cabezal_zona_11 = cabezal_zona_11;
        this.cabezal_zona_12 = cabezal_zona_12;
        this.cabezal_zona_13 = cabezal_zona_13;
        this.cabezal_zona_14 = cabezal_zona_14;
        this.cabezal_zona_15 = cabezal_zona_15;
        this.cabezal_zona_16 = cabezal_zona_16;
        this.cabezal_zona_17 = cabezal_zona_17;
        this.cabezal_zona_18 = cabezal_zona_18;
        this.nombre_cabezal = nombre_cabezal;
        this.diametro_restrictor_filtro = diametro_restrictor_filtro;
        this.rpm_motorExtrusora = rpm_motorExtrusora;
        this.amperaje_motorExtrusora = amperaje_motorExtrusora;
        this.rpm_revMin_tornillos = rpm_revMin_tornillos;
        this.porcentaje_velocidad_alimentador = porcentaje_velocidad_alimentador;
        this.amperaje_motor_alimentador = amperaje_motor_alimentador;
        this.desgasificador_vacio = desgasificador_vacio;
        this.presion_masa = presion_masa;
        this.temperatura_masa = temperatura_masa;
        this.contrapresion = contrapresion;
        this.vacio_primera_tina = vacio_primera_tina;
        this.temperatura_primera_tina_enfria = temperatura_primera_tina_enfria;
        this.presion_agua_primera_tina_enfria = presion_agua_primera_tina_enfria;
        this.vacio_segunda_tina_enfria = vacio_segunda_tina_enfria;
        this.velocidad_halador = velocidad_halador;
        this.limpieza_filtro_tina = limpieza_filtro_tina;
        this.altura_rotulo = altura_rotulo;
        this.espesor = espesor;
        this.diametro_externo = diametro_externo;
        this.longitud_tubo = longitud_tubo;
        this.embone = embone;
        this.kilogramos_horas = kilogramos_horas;
        this.peso_tubo_metro = peso_tubo_metro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTubo_id() {
        return tubo_id;
    }

    public void setTubo_id(Long tubo_id) {
        this.tubo_id = tubo_id;
    }

    public Integer getNumero_maquina() {
        return numero_maquina;
    }

    public void setNumero_maquina(Integer numero_maquina) {
        this.numero_maquina = numero_maquina;
    }

    public String getNombre_maquinista() {
        return nombre_maquinista;
    }

    public void setNombre_maquinista(String nombre_maquinista) {
        this.nombre_maquinista = nombre_maquinista;
    }

    public String getNombre_supervisor() {
        return nombre_supervisor;
    }

    public void setNombre_supervisor(String nombre_supervisor) {
        this.nombre_supervisor = nombre_supervisor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getError_id() {
        return error_id;
    }

    public void setError_id(String error_id) {
        this.error_id = error_id;
    }

    public String getError_descripcion() {
        return error_descripcion;
    }

    public void setError_descripcion(String error_descripcion) {
        this.error_descripcion = error_descripcion;
    }

    public Integer getCilindro_oil() {
        return cilindro_oil;
    }

    public void setCilindro_oil(Integer cilindro_oil) {
        this.cilindro_oil = cilindro_oil;
    }

    public Integer getCilindro_zona_1() {
        return cilindro_zona_1;
    }

    public void setCilindro_zona_1(Integer cilindro_zona_1) {
        this.cilindro_zona_1 = cilindro_zona_1;
    }

    public Integer getCilindro_zona_2() {
        return cilindro_zona_2;
    }

    public void setCilindro_zona_2(Integer cilindro_zona_2) {
        this.cilindro_zona_2 = cilindro_zona_2;
    }

    public Integer getCilindro_zona_3() {
        return cilindro_zona_3;
    }

    public void setCilindro_zona_3(Integer cilindro_zona_3) {
        this.cilindro_zona_3 = cilindro_zona_3;
    }

    public Integer getCilindro_zona_4() {
        return cilindro_zona_4;
    }

    public void setCilindro_zona_4(Integer cilindro_zona_4) {
        this.cilindro_zona_4 = cilindro_zona_4;
    }

    public Integer getCilindro_zona_5() {
        return cilindro_zona_5;
    }

    public void setCilindro_zona_5(Integer cilindro_zona_5) {
        this.cilindro_zona_5 = cilindro_zona_5;
    }

    public Integer getCilindro_zona_6() {
        return cilindro_zona_6;
    }

    public void setCilindro_zona_6(Integer cilindro_zona_6) {
        this.cilindro_zona_6 = cilindro_zona_6;
    }

    public Integer getCabezal_interna() {
        return cabezal_interna;
    }

    public void setCabezal_interna(Integer cabezal_interna) {
        this.cabezal_interna = cabezal_interna;
    }

    public Integer getCabezal_zona_1() {
        return cabezal_zona_1;
    }

    public void setCabezal_zona_1(Integer cabezal_zona_1) {
        this.cabezal_zona_1 = cabezal_zona_1;
    }

    public Integer getCabezal_zona_2() {
        return cabezal_zona_2;
    }

    public void setCabezal_zona_2(Integer cabezal_zona_2) {
        this.cabezal_zona_2 = cabezal_zona_2;
    }

    public Integer getCabezal_zona_3() {
        return cabezal_zona_3;
    }

    public void setCabezal_zona_3(Integer cabezal_zona_3) {
        this.cabezal_zona_3 = cabezal_zona_3;
    }

    public Integer getCabezal_zona_4() {
        return cabezal_zona_4;
    }

    public void setCabezal_zona_4(Integer cabezal_zona_4) {
        this.cabezal_zona_4 = cabezal_zona_4;
    }

    public Integer getCabezal_zona_5() {
        return cabezal_zona_5;
    }

    public void setCabezal_zona_5(Integer cabezal_zona_5) {
        this.cabezal_zona_5 = cabezal_zona_5;
    }

    public Integer getCabezal_zona_6() {
        return cabezal_zona_6;
    }

    public void setCabezal_zona_6(Integer cabezal_zona_6) {
        this.cabezal_zona_6 = cabezal_zona_6;
    }

    public Integer getCabezal_zona_7() {
        return cabezal_zona_7;
    }

    public void setCabezal_zona_7(Integer cabezal_zona_7) {
        this.cabezal_zona_7 = cabezal_zona_7;
    }

    public Integer getCabezal_zona_8() {
        return cabezal_zona_8;
    }

    public void setCabezal_zona_8(Integer cabezal_zona_8) {
        this.cabezal_zona_8 = cabezal_zona_8;
    }

    public Integer getCabezal_zona_9() {
        return cabezal_zona_9;
    }

    public void setCabezal_zona_9(Integer cabezal_zona_9) {
        this.cabezal_zona_9 = cabezal_zona_9;
    }

    public Integer getCabezal_zona_10() {
        return cabezal_zona_10;
    }

    public void setCabezal_zona_10(Integer cabezal_zona_10) {
        this.cabezal_zona_10 = cabezal_zona_10;
    }

    public Integer getCabezal_zona_11() {
        return cabezal_zona_11;
    }

    public void setCabezal_zona_11(Integer cabezal_zona_11) {
        this.cabezal_zona_11 = cabezal_zona_11;
    }

    public Integer getCabezal_zona_12() {
        return cabezal_zona_12;
    }

    public void setCabezal_zona_12(Integer cabezal_zona_12) {
        this.cabezal_zona_12 = cabezal_zona_12;
    }

    public Integer getCabezal_zona_13() {
        return cabezal_zona_13;
    }

    public void setCabezal_zona_13(Integer cabezal_zona_13) {
        this.cabezal_zona_13 = cabezal_zona_13;
    }

    public Integer getCabezal_zona_14() {
        return cabezal_zona_14;
    }

    public void setCabezal_zona_14(Integer cabezal_zona_14) {
        this.cabezal_zona_14 = cabezal_zona_14;
    }

    public Integer getCabezal_zona_15() {
        return cabezal_zona_15;
    }

    public void setCabezal_zona_15(Integer cabezal_zona_15) {
        this.cabezal_zona_15 = cabezal_zona_15;
    }

    public Integer getCabezal_zona_16() {
        return cabezal_zona_16;
    }

    public void setCabezal_zona_16(Integer cabezal_zona_16) {
        this.cabezal_zona_16 = cabezal_zona_16;
    }

    public Integer getCabezal_zona_17() {
        return cabezal_zona_17;
    }

    public void setCabezal_zona_17(Integer cabezal_zona_17) {
        this.cabezal_zona_17 = cabezal_zona_17;
    }

    public Integer getCabezal_zona_18() {
        return cabezal_zona_18;
    }

    public void setCabezal_zona_18(Integer cabezal_zona_18) {
        this.cabezal_zona_18 = cabezal_zona_18;
    }

    public String getNombre_cabezal() {
        return nombre_cabezal;
    }

    public void setNombre_cabezal(String nombre_cabezal) {
        this.nombre_cabezal = nombre_cabezal;
    }

    public Integer getDiametro_restrictor_filtro() {
        return diametro_restrictor_filtro;
    }

    public void setDiametro_restrictor_filtro(Integer diametro_restrictor_filtro) {
        this.diametro_restrictor_filtro = diametro_restrictor_filtro;
    }

    public Integer getRpm_motorExtrusora() {
        return rpm_motorExtrusora;
    }

    public void setRpm_motorExtrusora(Integer rpm_motorExtrusora) {
        this.rpm_motorExtrusora = rpm_motorExtrusora;
    }

    public Integer getAmperaje_motorExtrusora() {
        return amperaje_motorExtrusora;
    }

    public void setAmperaje_motorExtrusora(Integer amperaje_motorExtrusora) {
        this.amperaje_motorExtrusora = amperaje_motorExtrusora;
    }

    public String getRpm_revMin_tornillos() {
        return rpm_revMin_tornillos;
    }

    public void setRpm_revMin_tornillos(String rpm_revMin_tornillos) {
        this.rpm_revMin_tornillos = rpm_revMin_tornillos;
    }

    public String getPorcentaje_velocidad_alimentador() {
        return porcentaje_velocidad_alimentador;
    }

    public void setPorcentaje_velocidad_alimentador(String porcentaje_velocidad_alimentador) {
        this.porcentaje_velocidad_alimentador = porcentaje_velocidad_alimentador;
    }

    public String getAmperaje_motor_alimentador() {
        return amperaje_motor_alimentador;
    }

    public void setAmperaje_motor_alimentador(String amperaje_motor_alimentador) {
        this.amperaje_motor_alimentador = amperaje_motor_alimentador;
    }

    public String getDesgasificador_vacio() {
        return desgasificador_vacio;
    }

    public void setDesgasificador_vacio(String desgasificador_vacio) {
        this.desgasificador_vacio = desgasificador_vacio;
    }

    public Integer getPresion_masa() {
        return presion_masa;
    }

    public void setPresion_masa(Integer presion_masa) {
        this.presion_masa = presion_masa;
    }

    public Integer getTemperatura_masa() {
        return temperatura_masa;
    }

    public void setTemperatura_masa(Integer temperatura_masa) {
        this.temperatura_masa = temperatura_masa;
    }

    public Integer getContrapresion() {
        return contrapresion;
    }

    public void setContrapresion(Integer contrapresion) {
        this.contrapresion = contrapresion;
    }

    public String getVacio_primera_tina() {
        return vacio_primera_tina;
    }

    public void setVacio_primera_tina(String vacio_primera_tina) {
        this.vacio_primera_tina = vacio_primera_tina;
    }

    public Integer getTemperatura_primera_tina_enfria() {
        return temperatura_primera_tina_enfria;
    }

    public void setTemperatura_primera_tina_enfria(Integer temperatura_primera_tina_enfria) {
        this.temperatura_primera_tina_enfria = temperatura_primera_tina_enfria;
    }

    public String getPresion_agua_primera_tina_enfria() {
        return presion_agua_primera_tina_enfria;
    }

    public void setPresion_agua_primera_tina_enfria(String presion_agua_primera_tina_enfria) {
        this.presion_agua_primera_tina_enfria = presion_agua_primera_tina_enfria;
    }

    public String getVacio_segunda_tina_enfria() {
        return vacio_segunda_tina_enfria;
    }

    public void setVacio_segunda_tina_enfria(String vacio_segunda_tina_enfria) {
        this.vacio_segunda_tina_enfria = vacio_segunda_tina_enfria;
    }

    public String getVelocidad_halador() {
        return velocidad_halador;
    }

    public void setVelocidad_halador(String velocidad_halador) {
        this.velocidad_halador = velocidad_halador;
    }

    public String getLimpieza_filtro_tina() {
        return limpieza_filtro_tina;
    }

    public void setLimpieza_filtro_tina(String limpieza_filtro_tina) {
        this.limpieza_filtro_tina = limpieza_filtro_tina;
    }

    public String getAltura_rotulo() {
        return altura_rotulo;
    }

    public void setAltura_rotulo(String altura_rotulo) {
        this.altura_rotulo = altura_rotulo;
    }

    public String getEspesor() {
        return espesor;
    }

    public void setEspesor(String espesor) {
        this.espesor = espesor;
    }

    public Integer getDiametro_externo() {
        return diametro_externo;
    }

    public void setDiametro_externo(Integer diametro_externo) {
        this.diametro_externo = diametro_externo;
    }

    public Integer getLongitud_tubo() {
        return longitud_tubo;
    }

    public void setLongitud_tubo(Integer longitud_tubo) {
        this.longitud_tubo = longitud_tubo;
    }

    public String getEmbone() {
        return embone;
    }

    public void setEmbone(String embone) {
        this.embone = embone;
    }

    public Integer getKilogramos_horas() {
        return kilogramos_horas;
    }

    public void setKilogramos_horas(Integer kilogramos_horas) {
        this.kilogramos_horas = kilogramos_horas;
    }

    public String getPeso_tubo_metro() {
        return peso_tubo_metro;
    }

    public void setPeso_tubo_metro(String peso_tubo_metro) {
        this.peso_tubo_metro = peso_tubo_metro;
    }

    @Override
    public String toString() {
        return "Formulario{" +
                "id=" + id +
                ", tubo_id=" + tubo_id +
                ", numero_maquina=" + numero_maquina +
                ", nombre_maquinista='" + nombre_maquinista + '\'' +
                ", nombre_supervisor='" + nombre_supervisor + '\'' +
                ", fecha='" + fecha + '\'' +
                ", turno='" + turno + '\'' +
                ", error_id='" + error_id + '\'' +
                ", error_descripcion='" + error_descripcion + '\'' +
                ", cilindro_oil=" + cilindro_oil +
                ", cilindro_zona_1=" + cilindro_zona_1 +
                ", cilindro_zona_2=" + cilindro_zona_2 +
                ", cilindro_zona_3=" + cilindro_zona_3 +
                ", cilindro_zona_4=" + cilindro_zona_4 +
                ", cilindro_zona_5=" + cilindro_zona_5 +
                ", cilindro_zona_6=" + cilindro_zona_6 +
                ", cabezal_interna=" + cabezal_interna +
                ", cabezal_zona_1=" + cabezal_zona_1 +
                ", cabezal_zona_2=" + cabezal_zona_2 +
                ", cabezal_zona_3=" + cabezal_zona_3 +
                ", cabezal_zona_4=" + cabezal_zona_4 +
                ", cabezal_zona_5=" + cabezal_zona_5 +
                ", cabezal_zona_6=" + cabezal_zona_6 +
                ", cabezal_zona_7=" + cabezal_zona_7 +
                ", cabezal_zona_8=" + cabezal_zona_8 +
                ", cabezal_zona_9=" + cabezal_zona_9 +
                ", cabezal_zona_10=" + cabezal_zona_10 +
                ", cabezal_zona_11=" + cabezal_zona_11 +
                ", cabezal_zona_12=" + cabezal_zona_12 +
                ", cabezal_zona_13=" + cabezal_zona_13 +
                ", cabezal_zona_14=" + cabezal_zona_14 +
                ", cabezal_zona_15=" + cabezal_zona_15 +
                ", cabezal_zona_16=" + cabezal_zona_16 +
                ", cabezal_zona_17=" + cabezal_zona_17 +
                ", cabezal_zona_18=" + cabezal_zona_18 +
                ", nombre_cabezal='" + nombre_cabezal + '\'' +
                ", diametro_restrictor_filtro=" + diametro_restrictor_filtro +
                ", rpm_motorExtrusora=" + rpm_motorExtrusora +
                ", amperaje_motorExtrusora=" + amperaje_motorExtrusora +
                ", rpm_revMin_tornillos='" + rpm_revMin_tornillos + '\'' +
                ", porcentaje_velocidad_alimentador='" + porcentaje_velocidad_alimentador + '\'' +
                ", amperaje_motor_alimentador='" + amperaje_motor_alimentador + '\'' +
                ", desgasificador_vacio='" + desgasificador_vacio + '\'' +
                ", presion_masa=" + presion_masa +
                ", temperatura_masa=" + temperatura_masa +
                ", contrapresion=" + contrapresion +
                ", vacio_primera_tina='" + vacio_primera_tina + '\'' +
                ", temperatura_primera_tina_enfria=" + temperatura_primera_tina_enfria +
                ", presion_agua_primera_tina_enfria='" + presion_agua_primera_tina_enfria + '\'' +
                ", vacio_segunda_tina_enfria='" + vacio_segunda_tina_enfria + '\'' +
                ", velocidad_halador='" + velocidad_halador + '\'' +
                ", limpieza_filtro_tina='" + limpieza_filtro_tina + '\'' +
                ", altura_rotulo='" + altura_rotulo + '\'' +
                ", espesor='" + espesor + '\'' +
                ", diametro_externo=" + diametro_externo +
                ", longitud_tubo=" + longitud_tubo +
                ", embone='" + embone + '\'' +
                ", kilogramos_horas=" + kilogramos_horas +
                ", peso_tubo_metro='" + peso_tubo_metro + '\'' +
                '}';
    }
}

