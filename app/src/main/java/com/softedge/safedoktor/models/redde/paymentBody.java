package com.softedge.safedoktor.models.redde;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static com.softedge.safedoktor.utilities.AppConstants.*;

public class paymentBody {


    private int amount;

    @SerializedName("appid")
    @Expose
    private int appId = REDDE_APP_ID;

    @SerializedName("clientreference")
    @Expose
    private String clientReference;

    @SerializedName("clienttransid")
    @Expose
    private String clientTransId;

    private String description;

    private String nickname = "SAFEDOKTOR";

    @SerializedName("paymentoption")
    @Expose
    private String paymentOption;

    @SerializedName("walletnumber")
    @Expose
    private String walletNumber;

    public paymentBody(
            int amount, String clientReference, String clientTransId,
            String description, String paymentOption, String walletNumber) {
        this.amount = amount;
        this.clientReference = clientReference;
        this.clientTransId = clientTransId;
        this.description = description;
        this.paymentOption = paymentOption;
        this.walletNumber = walletNumber;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public String getClientReference() {
        return clientReference;
    }

    public void setClientReference(String clientReference) {
        this.clientReference = clientReference;
    }

    public String getClientTransId() {
        return clientTransId;
    }

    public void setClientTransId(String clientTransId) {
        this.clientTransId = clientTransId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPaymentOption() {
        return paymentOption;
    }

    public void setPaymentOption(String paymentOption) {
        this.paymentOption = paymentOption;
    }

    public String getWalletNumber() {
        return walletNumber;
    }

    public void setWalletNumber(String walletNumber) {
        this.walletNumber = walletNumber;
    }
}
