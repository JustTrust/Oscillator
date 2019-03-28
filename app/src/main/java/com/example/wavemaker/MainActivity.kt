package com.example.wavemaker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private external fun touchEvent(action: Int, frequency: Int)

    private external fun startEngine()

    private external fun stopEngine()

    private val minFrequency: Int = 4

    private val maxFrequency: Int = 1800

    private val frequencyStep: Int = 10

    private var frequency: Int = minFrequency


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Example of a call to a native method
        startEngine()
        seekBar.max = maxFrequency
        seekBar.min = minFrequency
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                frequency = p1 * frequencyStep
                frequency_text.text = getString(R.string.frequency, frequency)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })
        frequency_text.text = getString(R.string.frequency, minFrequency * frequencyStep)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        touchEvent(event.action, frequency)
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
