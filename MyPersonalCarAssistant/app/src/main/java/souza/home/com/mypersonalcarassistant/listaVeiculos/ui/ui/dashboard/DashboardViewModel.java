package souza.home.com.mypersonalcarassistant.listaVeiculos.ui.ui.dashboard;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DashboardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Bem-vindo ao dashboard!");
    }

    public LiveData<String> getText() {
        return mText;
    }
}