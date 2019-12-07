package souza.home.com.mypersonalcarassistant.data.model;

import android.graphics.drawable.Drawable;

public class notification {
    private final String nome;
    String km_oleo;
    String km_prox;
    int image;

    public notification(String nome, String km_oleo, String km_prox, int image) {
        this.nome = nome;
        this.km_oleo = km_oleo;
        this.km_prox = km_prox;
        this.image = image;
    }

    public String getNome() {
        return nome;
    }

    public String getKm_oleo() {
        return km_oleo;
    }

    public int getImage() {
        return image;
    }

    public String getKm_prox() {
        return km_prox;
    }

    public void setKm_oleo(String km_oleo) {
        this.km_oleo = km_oleo;
    }
}
