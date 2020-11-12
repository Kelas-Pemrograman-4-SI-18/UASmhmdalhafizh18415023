package com.mhmdalhafizh.aplikasipenjualanmakanan.server;

public class BaseURL {

    public static String baseUrl ="http://192.168.100.70:8000/";

    public static String login    = baseUrl +"user/login";
    public static String registrasi = baseUrl + "user/registrasi";

    //Makanan
    public static String dataMakanan = baseUrl + "makanan/datamakanan";
    public static String editDataMakanan = baseUrl + "editdatamakanan/ubah/";
    public static String hapusData = baseUrl + "hapusdata/hapus/";
    public static String inputData = baseUrl + "inputdata/input/";
}
