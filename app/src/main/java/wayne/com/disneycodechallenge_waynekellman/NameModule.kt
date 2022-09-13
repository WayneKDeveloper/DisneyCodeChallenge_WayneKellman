package wayne.com.disneycodechallenge_waynekellman

import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class NameModule {
    //TODO: Can add extra Guests here

    @Provides
    @Named("haves")
    fun createAdapter1(): NameAdapter {
//        val map = mutableMapOf("Dale Gibson" to false, "Jeremy Gibson" to false)
        val map = mutableMapOf("Dale Gibson" to false, "Jeremy Gibson" to false, "Jeremfy Gibson" to false, "Jeremsy Gibson" to false, "Jeremy Gibson" to false, "Jertemy Gibson" to false, "Jerfemy Gibson" to false, "Jeremdy Gibson" to false, "Jeremy Gibsont" to false, "Jereqy Gibson" to false, "Jeremy Gibson" to false, "Jeremy Gitbson" to false)
        return NameAdapter(map)
    }
    @Provides
    @Named("needs")
    fun createAdapter2(): NameAdapter {
//        val map = mutableMapOf("Linda Gibson" to false, "Margaret Gibson" to false)
        val map = mutableMapOf("Linda Gibson" to false, "Margaret Gibson" to false, "Meargaret Gibson" to false, "Margaret Gibsont" to false, "Margaret Gibrson" to false, "Margaret Gidebson" to false, "Margafret Gibson" to false, "Margaret mGibson" to false, "Margaret Gfbson" to false, "Margargt Gibson" to false, "Margaret aGibson" to false, "Margaret sGibson" to false, "Margaresdct sGibson" to false, "Margaret sGibsrerwon" to false, "Margarxsaet sGibson" to false)
        return NameAdapter(map)
    }
}