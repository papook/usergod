package com.papook.usergod.config;

public class Constants {

    public static final String PERSISTENCE_UNIT_NAME = "user_pu";

    public static final int PAGE_SIZE = 30;

    public static final String USERS_ENDPOINT = "users";
    public static final String USER_BY_ID_ENDPOINT = USERS_ENDPOINT + "/{id: \\d+}";
    public static final String CHANGE_PASSWORD_ENDPOINT = USER_BY_ID_ENDPOINT + "/password";

    public static final String REGISTER_ENDPOINT = "register";

    // Relation names
    public static final String GET_USER_COLLECTION_REL = "getUsersCollection";
    public static final String GET_SINGLE_USER_REL = "getUser";

    public static final String UPDATE_USER_REL = "putUpdateUser";
    public static final String CHANGE_PASSWORD_REL = "putChangePassword";

    public static final String REGISTER_REL = "postRegisterUser";
}
