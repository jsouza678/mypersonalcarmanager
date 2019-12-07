package souza.home.com.mypersonalcarassistant.listaVeiculos.ui.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class NotificationsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public NotificationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Aqui estão as suas manutenções. Preste atenção para não ficar na rua!");
    }

    public LiveData<String> getText() {
        return mText;
    }
}