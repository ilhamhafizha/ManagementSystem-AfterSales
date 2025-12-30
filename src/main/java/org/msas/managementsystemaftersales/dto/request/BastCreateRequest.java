package org.msas.managementsystemaftersales.dto.request;


import lombok.Data;

@Data
public class BastCreateRequest {

    private String nomorPolisi;
    private String nomorRangka;
    private String nomorMesin;

    private String jenisMobil;
    private String warna;
    private String lokasiPengiriman;
}