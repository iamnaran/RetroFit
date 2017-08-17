package com.retrofit.Remote;

import com.retrofit.Service.APIService;

/**
 * Created by NaRan on 8/15/17.
 */


public class ApiUtils {


    public static String BASE_URL ="https://api.stackexchange.com/2.2/";
    public static SOService getSOService(){

        return RetrofitClient.getClient(BASE_URL).create(SOService.class);
    }



    public static final String URL = "http://jsonplaceholder.typicode.com/";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(URL).create(APIService.class);
    }



}
