package br.com.vempracaruaru.util;

import java.io.Serializable;

public class Solicitacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @SuppressWarnings("unused")
    private static final String URL = "http://192.168.1.104:8080/VemPraCaruaru/Android";

    /*  Opção de solicitação de login padrão
     *  1º Parametro é o email
     *  2º Parametro é a senha
     */
    public static int iLoginPadrao = 1;

    /*  Opção de solicitação de cadastro de usuário
     *  4º Parametro é ojeto a ser cadastrado
     */
    public static int iUsuarioCadastro = 2;

    /*  Opção de solicitação de artistas
     *  Se quiser listar todos passar um objeto Integer = 0
     *  Se quiser listar um especifico passar um objeto Integer = id
     *  4º Parametro é ojeto a ser recuperado
     */
    public static int iArtistaListar = 3;

    /*  Opção de solicitação de obras
     *  Se quiser listar todos passar um objeto Integer = 0
     *  Se quiser listar um especifico passar um objeto Integer = id
     *  4º Parametro é ojeto a ser recuperado
     */
    public static int iObraListar = 4;

    /*  Opção de solicitação de Pontos Turistícos
     *  Se quiser listar todos passar um objeto Integer = 0
     *  Se quiser listar um especifico passar um objeto Integer = id
     *  4º Parametro é ojeto a ser recuperado
     */
    public static int iPontoTuristicoListar = 5;

    /*  Opção de solicitação de Pontos Turistícos
     *  Se quiser listar todos passar um objeto Integer = 0
     *  Se quiser listar um especifico passar um objeto Integer = id
     *  4º Parametro é ojeto a ser recuperado
     */
    public static int iFotoListar = 6;

    /*  Opção de solicitação de obras
     *  Se quiser listar todos passar um objeto Integer = 0
     *  Se quiser listar um especifico passar um objeto Integer = id
     *  4º Parametro é ojeto a ser recuperado
     */
    public static int iObraPontoListar = 7;

    // Tipo de solicitação que será feira a servlet
    private int iSolicitacao;

    // 1º Parametro da solicitação
    private String sParam1;

    // 2º Parametro da solicitação
    private String sParam2;

    // 3º Parametro da solicitação
    private String sParam3;

    // 4º Parametro da solicitação
    private Object sParam4;

    public Solicitacao(int iSolicitacao, String sParam1, String sParam2,
                       String sParam3, Object sParam4) {
        super();
        this.iSolicitacao = iSolicitacao;
        this.sParam1 = sParam1;
        this.sParam2 = sParam2;
        this.sParam3 = sParam3;
        this.sParam4 = sParam4;
    }

    public int getiSolicitacao() {
        return iSolicitacao;
    }

    public void setiSolicitacao(int iSolicitacao) {
        this.iSolicitacao = iSolicitacao;
    }

    public String getsParam1() {
        return sParam1;
    }

    public void setsParam1(String sParam1) {
        this.sParam1 = sParam1;
    }

    public String getsParam2() {
        return sParam2;
    }

    public void setsParam2(String sParam2) {
        this.sParam2 = sParam2;
    }

    public String getsParam3() {
        return sParam3;
    }

    public void setsParam3(String sParam3) {
        this.sParam3 = sParam3;
    }

    public Object getsParam4() {
        return sParam4;
    }

    public void setsParam4(Object sParam4) {
        this.sParam4 = sParam4;
    }

}
