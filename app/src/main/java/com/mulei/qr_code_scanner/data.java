package com.mulei.qr_code_scanner;

public class data {
    String numberplate;
    String sacco;
    String trip;

    public data(String numberplate, String sacco, String trip, String email) {
        this.numberplate = numberplate;
        this.sacco = sacco;
        this.trip = trip;
        this.email = email;
    }

    String email;

    public String getNumberplate() {
        return numberplate;
    }

    public void setNumberplate(String numberplate) {
        this.numberplate = numberplate;
    }

    public String getSacco() {
        return sacco;
    }

    public void setSacco(String sacco) {
        this.sacco = sacco;
    }

    public String getTrip() {
        return trip;
    }

    public void setTrip(String trip) {
        this.trip = trip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
