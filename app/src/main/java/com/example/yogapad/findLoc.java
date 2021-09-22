/*package com.example.yogapad;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class findLoc<OnTaskCompleted> extends AsyncTask<Location, Void, String[]>{
    private final Context mContext;
    private final OnTaskCompleted mListener;

    <onTaskCompleted> findLoc(Context applicationContext, onTaskCompleted listener){
        mContext = applicationContext;
        mListener = (OnTaskCompleted) listener;
    }

    private final String TAG = findLoc.class.getSimpleName();

    @Override
    protected String[] doInBackground(Location... params) {
        Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());
        Location location = params[0];

        List<Address> adressess = null;
        String[] resultMessage = new String[3];
        resultMessage[0] = "";
        resultMessage[1] = "";
        resultMessage[2] = "";

        try{
            adressess = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            resultMessage[1] = String.valueOf(location.getLatitude());
            resultMessage[2] = String.valueOf(location.getLongitude());
        } catch (IOException ioException){
            resultMessage[0] = mContext.getString(R.string.service_not_avaiable);
            Log.e(TAG, resultMessage[0], ioException);
        } catch(IllegalArgumentException illegalArgumentException){
            resultMessage[0] = mContext.getString(R.string.invalid_lat_long);
            Log.e(TAG, resultMessage[0] + ". " + "Latitude = " + location.getLatitude() + ", Longitude = " + location.getLongitude(), illegalArgumentException);
        }

        if(adressess == null || adressess.size() == 0){
            if(resultMessage[0].isEmpty()){
                resultMessage[0] = mContext.getString(R.string.no_adress_found);
                Log.e(TAG, resultMessage[0]);
            }
        } else{
            Adress adress = adressess.get(0);
            ArrayList<String> adressParts = new ArrayList<>();
            for(int i = 0; i<= adress.getMaxAdressLineIndex(); i++){
                adressParts.add(adress.getAdressLine(i));
            }
            resultMessage[0] = TextUtils.join("\n", adressParts);
        }

        return resultMessage;
    }

    @Override
    protected void onPostExecute(String[] adress){
        mListener.onTaskCompleted(adress);
        super.onPostExecute(adress);
    }

    interface OnTaskCompleted{
        void onTaskCompleted(String[] result);
    }
}
*/