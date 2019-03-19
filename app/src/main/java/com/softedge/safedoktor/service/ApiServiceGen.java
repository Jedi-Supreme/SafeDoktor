package com.softedge.safedoktor.service;

public class ApiServiceGen {

    private static final String authrl = "http://192.168.8.10:8180/auth/realms/carewex/protocol/openid-connect/token";

    private static final String restURL = "{ip_address:port}/carewex/rest";

    public final static String employeeApi = restURL + "/employees";
    final static String patientApi = restURL + "/patient";
    public final static String appointmentApi = restURL + "/appointment";
    public final static String encounterApi = restURL + "/encounter";



}
