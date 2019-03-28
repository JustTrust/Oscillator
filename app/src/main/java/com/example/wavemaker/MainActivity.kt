package com.example.wavemaker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent



class MainActivity : AppCompatActivity() {

    private external fun touchEvent(action: Int)

    private external fun startEngine()

    private external fun stopEngine()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Example of a call to a native method
        startEngine()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        touchEvent(event.action)
        return super.onTouchEvent(event)
    }

    public override fun onDestroy() {
        stopEngine()
        super.onDestroy()
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-sound-lib")
        }
    }
}
