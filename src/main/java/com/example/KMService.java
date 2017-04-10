package com.example;

/**
 * Created by mizan on 4/9/17.
 */
public enum KMService {
    MAP(10),
    DCP(11),
    CIS(12),
    TSP(13),
    ITA(14),
    RPG(15),
    PSM(16),
    CMS(17),
    IAS(18),
    KOD9(19);

    int componentNo;

    KMService(int componentNo) {
        this.componentNo = componentNo;
    }
}
