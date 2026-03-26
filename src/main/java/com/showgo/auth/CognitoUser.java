package com.showgo.auth;

/**
 * Represents the user data returned from cognito JWT,
 * used to match against db
 */
public class CognitoUser {
    private String cognitoId;
    private String email;

    /**
     * Instantiates a new Cognito user.
     */
    public CognitoUser() {
    }

    /**
     * Instantiates a new Cognito user.
     *
     * @param cognitoId the cognito id
     * @param email     the email
     */
    public CognitoUser(String cognitoId, String email) {
        this.cognitoId = cognitoId;
        this.email = email;
    }

    /**
     * Gets cognito id.
     *
     * @return the cognito id
     */
    public String getCognitoId() {
        return cognitoId;
    }

    /**
     * Sets cognito id.
     *
     * @param cognitoId the cognito id
     */
    public void setCognitoId(String cognitoId) {
        this.cognitoId = cognitoId;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "CognitoUser{" +
                "cognitoId='" + cognitoId + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
