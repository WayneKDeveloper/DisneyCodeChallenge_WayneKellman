package wayne.com.disneycodechallenge_waynekellman

import androidx.lifecycle.MutableLiveData
import javax.inject.Inject
import javax.inject.Named

class MainViewModel @Inject constructor(@Named("haves") val nameAdapter1: NameAdapter, @Named("needs") val nameAdapter2: NameAdapter) {
    val popupLiveData : MutableLiveData<Event<Boolean>> = MutableLiveData()
    val buttonLiveData : MutableLiveData<Event<Boolean>> = MutableLiveData()
    init {
        val appComponent = DaggerApplicationComponent.builder().nameModule(NameModule()).build()
        appComponent.inject(this)
        nameAdapter1.makeList()
        nameAdapter2.makeList()

        nameAdapter1.iChecked = object : IsChecked {
            override fun isChecked(name: String) {
                nameAdapter1.map[name] = true
                popupLiveData.postValue(Event(false))
                buttonLiveData.postValue(Event(true))
            }

            override fun isUnchecked(name: String) {
                nameAdapter1.map[name] = false
                if (nameAdapter2.map.containsValue(true) && !nameAdapter1.map.containsValue(true)) {
                    popupLiveData.postValue(Event(true))
                }
                if (!nameAdapter1.map.containsValue(true)) {
                    buttonLiveData.postValue(Event(false))
                }
            }
        }
        nameAdapter2.iChecked = object : IsChecked {
            override fun isChecked(name: String) {
                nameAdapter2.map[name] = true
                if (!nameAdapter1.map.containsValue(true)) {
                    popupLiveData.postValue(Event(true))
                }
            }

            override fun isUnchecked(name: String) {
                nameAdapter2.map[name] = false
                if (!nameAdapter2.map.containsValue(true)) {
                    popupLiveData.postValue(Event(false))
                }
            }
        }
    }

}