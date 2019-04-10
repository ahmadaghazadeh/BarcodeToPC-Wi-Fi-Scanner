package com.github.ahmadaghazadeh.barcodescannerclient.utils.exception;

import android.content.Context;

import com.github.ahmadaghazadeh.barcodescannerclient.R;
import com.github.ahmadaghazadeh.barcodescannerclient.utils.exception.model.ApiMessage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Response;


public class ApiException extends BaseException {

    private Context context;

    @Inject
    public ApiException(Context context) {
        super();
        this.context = context;
    }

    public ApiException(Context context,Response response) {
        super();
        this.context = context;
        newException(response);
    }

    public ApiException newException(Response response)   {
        errorModel.systemMessage = response.message();
        errorModel.userTitle = context.getString(R.string.error_title);
        generateMessage(response);
        return this;
    }

    private void generateMessage(Response response)  {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        ApiMessage apiMessage= null;
        try {
            apiMessage = gson.fromJson(response.errorBody().string(),
                    new TypeToken<ApiMessage>() {
                    }.getType());
        } catch (IOException e) {
            errorModel.userMessage = context.getString(R.string.error_not_recognized);
        }

        switch (response.code()) {
            case 400:
                if (apiMessage.Message.equals(ErrorConsts.ErrorLogin)) {
                    errorModel.userMessage = context.getString(R.string.error_login);
                } else if (apiMessage.Message.equals(ErrorConsts.EmailDuplicate) ||
                        errorModel.systemMessage.equals(ErrorConsts.CanNotRegister)) {
                    errorModel.userMessage = context.getString(R.string.error_email_duplicate);
                } else {
                    errorModel.userMessage = context.getString(R.string.error_message_api_not_found);
                }
                break;
            case 404:
                errorModel.userMessage = context.getString(R.string.error_message_api_not_found);
                break;
            case 500:
                errorModel.userMessage = context.getString(R.string.error_message_api_server);
                break;
            default:
                errorModel.userMessage = String.valueOf(response.code());
                break;
        }
    }
}

