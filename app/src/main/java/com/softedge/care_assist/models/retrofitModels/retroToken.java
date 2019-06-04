package com.softedge.care_assist.models.retrofitModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class retroToken {

        @SerializedName("access_token")
        @Expose
        private String accessToken;

        @SerializedName("expires_in")
        @Expose
        private int expiresIn;

        @SerializedName("refresh_expires_in")
        @Expose
        private int refreshExpiresIn;

        @SerializedName("refresh_token")
        @Expose
        private String refreshToken;

        @SerializedName("token_type")
        @Expose
        private String tokenType;

        @SerializedName("id_token")
        @Expose
        private String idToken;

        @SerializedName("not-before-policy")
        @Expose
        private int notBeforePolicy;

        @SerializedName("session-state")
        @Expose
        private String sessionState;

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public int getExpiresIn() {
            return expiresIn;
        }

        public void setExpiresIn(int expiresIn) {
            this.expiresIn = expiresIn;
        }

        public int getRefreshExpiresIn() {
            return refreshExpiresIn;
        }

        public void setRefreshExpiresIn(int refreshExpiresIn) {
            this.refreshExpiresIn = refreshExpiresIn;
        }

        public String getRefreshToken() {
            return refreshToken;
        }

        public void setRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
        }

        public String getTokenType() {
            return tokenType;
        }

        public void setTokenType(String tokenType) {
            this.tokenType = tokenType;
        }

        public String getIdToken() {
            return idToken;
        }

        public void setIdToken(String idToken) {
            this.idToken = idToken;
        }

        public int getNotBeforePolicy() {
            return notBeforePolicy;
        }

        public void setNotBeforePolicy(int notBeforePolicy) {
            this.notBeforePolicy = notBeforePolicy;
        }

        public String getSessionState() {
            return sessionState;
        }

        public void setSessionState(String sessionState) {
            this.sessionState = sessionState;
        }


}
