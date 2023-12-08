package Model;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "localizacao_chamado")
public class LocalizacaoChamado implements LocationListener {

    public static double latitude;
    public static double longitude;

    private long idChamado;

    @PrimaryKey(autoGenerate = true)
    private long id;

    public long getId() {
        return id;
    }

    public LocalizacaoChamado() {
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    public static double getLatitude() {
        return latitude;
    }

    public static void setLatitude(double latitude) {
        LocalizacaoChamado.latitude = latitude;
    }

    public static double getLongitude() {
        return longitude;
    }

    public static void setLongitude(double longitude) {
        LocalizacaoChamado.longitude = longitude;
    }

    public long getIdChamado() {
        return idChamado;
    }

    public void setIdChamado(long idChamado) {
        this.idChamado = idChamado;
    }
}
