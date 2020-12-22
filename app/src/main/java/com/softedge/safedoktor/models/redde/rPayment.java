package com.softedge.safedoktor.models.redde;
import com.google.gson.annotations.SerializedName;

public class rPayment {

    @SerializedName("status")
    private String status;

    @SerializedName("reason")
    private String reason;

    @SerializedName("transactionid")
    private int transactionId;

    @SerializedName("clienttransid")
    private String clientTransId;

    @SerializedName("clientreference")
    private String clientReference;

    @SerializedName("statusdate")
    private String statusDate;

    @SerializedName("brandtransid")
    private String brandTransId;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getClientTransId() {
        return clientTransId;
    }

    public void setClientTransId(String clientTransId) {
        this.clientTransId = clientTransId;
    }

    public String getClientReference() {
        return clientReference;
    }

    public void setClientReference(String clientReference) {
        this.clientReference = clientReference;
    }

    public String getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(String statusDate) {
        this.statusDate = statusDate;
    }

    public String getBrandTransId() {
        return brandTransId;
    }

    public void setBrandTransId(String brandTransId) {
        this.brandTransId = brandTransId;
    }
}
