package testing.steven.cathaytest

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

class CathayApplication  : Application(){
    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }
}